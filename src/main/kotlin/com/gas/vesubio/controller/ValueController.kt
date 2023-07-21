package com.gas.vesubio.controller

import com.gas.vesubio.models.entity.RegisterDate
import com.gas.vesubio.models.entity.RegisterValue
import com.gas.vesubio.models.entity.ValuesType
import com.gas.vesubio.models.services.dates.DatesServicesImpl
import com.gas.vesubio.models.services.values.IValuesServices
import com.gas.vesubio.models.services.values.ValueServicesImpl
import com.monitorjbl.xlsx.StreamingReader
import org.apache.poi.ss.formula.functions.Value
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.*
import java.util.stream.Collectors
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.validation.Valid


@RestController
@RequestMapping("/values")
class ValueController {
    @Autowired
    @PersistenceContext
    private lateinit var entityManager: EntityManager


    @Autowired
    private val iValueServices:IValuesServices?=null
    private val valueService:ValueServicesImpl?=null
    private val v:ValueServicesImpl?=null

    //@Autowired
    //private lateinit var valueController: ValueController
    @Autowired
    private lateinit var controllerDate: DateController
    @Autowired
    private lateinit var valueTypeController: ValuesTypeController

    @GetMapping("/findValueById/{valueId}")
    fun findValueById(@PathVariable("valueId")valueId:Long):Any{
        val response=HashMap<String, Any>()
        return try{
            val resultFind:RegisterValue?=iValueServices!!.findValueById(valueId= valueId)
            if(resultFind==null){
                response["message"]="No se encontro ningun valor"
                ResponseEntity<Map<String, Any>>(response, HttpStatus.NOT_FOUND)
            }else{
                response["message"]="Datos encontrados"
                response["result"]=resultFind
                ResponseEntity<Map<String, Any>>(response, HttpStatus.ACCEPTED)
            }

        }catch (e:Exception){
            response["message"]="Error en el servidor"
            response["result"]=" ${e.message} : ${e.cause}"
            ResponseEntity<Map<String, Any>>(response, HttpStatus.INTERNAL_SERVER_ERROR)

        }
    }
    @PostMapping("/saveValue")
    fun saveValue(@Valid @RequestBody value: RegisterValue, result: BindingResult): Any{
        val response=HashMap<String, Any>()

        if(result.hasErrors()){
            val errors=result.fieldErrors.stream().map {
                error -> "'Field ${error.field}'${error.defaultMessage}"}
                    .collect(Collectors.toList())
            response["errors"]= errors
            return ResponseEntity<Map<String, Any>> (response, HttpStatus.BAD_REQUEST)
        }
        return try{
            val registerValue=iValueServices!!.saveValue(value)
            response["message"]="Datos creados correctamente"
            response["result"]=registerValue
            ResponseEntity<Map<String, Any>>(response, HttpStatus.OK)
        }catch (e:Exception){
            response["message"]="Error en el servidor"
            response["result"]=" ${e.message} : ${e.cause}"
            ResponseEntity<Map<String, Any>>(response, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
    @GetMapping("/findAllValues")
    fun findAllValues():Any{
        val response=HashMap<String, Any>()
        return try{
            val registerValueAll=iValueServices!!.findAll().toList()
            if (registerValueAll.isEmpty()){
                response["message"]="No se encontro ningun registro en la base de datos"
                ResponseEntity<Map<String, Any>>(response, HttpStatus.NOT_FOUND)
            }else{
                response["message"]="Datos encontrados"
                response["result"]=registerValueAll
                ResponseEntity<Map<String, Any>>(response, HttpStatus.ACCEPTED)
            }
        }catch (e:Exception){

        }

    }

    @GetMapping("/all")
    fun getAllValues(): ResponseEntity<List<RegisterValue>> {
        val values = iValueServices!!.getAllValues()
        return ResponseEntity.ok(values)
    }



    @GetMapping("/by-date")
    fun getValuesByDate(@RequestParam("date") dateString: String): ResponseEntity<Map<String, List<Double>>> {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val date = dateFormat.parse(dateString)
        val valueMap = iValueServices!!.getValuesByDate(date)
        return ResponseEntity.ok(valueMap)
    }


    /*@GetMapping("/by-date30")
    fun getValuesByDate30(@RequestParam("date") dateString: String): ResponseEntity<Map<String, List<Double>>> {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val date = dateFormat.parse(dateString)
        val valueMap = iValueServices!!.getValuesByDate30(date)
        return ResponseEntity.ok(valueMap)
    }*/
    @PostMapping("/Import")
    fun ImportData(@RequestParam("file") file: MultipartFile) {
        val response = HashMap<String, Any>()
        val workbook = XSSFWorkbook(file.inputStream)
        val sheet = workbook.getSheetAt(0)
        val inputFormat = SimpleDateFormat("MM/dd/yyyy hh:mm:ss")
        val lastRowNum = sheet.lastRowNum
        val firstRow = sheet.getRow(0) // Obtén la primera fila
        val lastColumnNum = firstRow.lastCellNum
        try {
            val registerDateAllResponse = controllerDate!!.findAll()
            val registerValueAllResponse = valueTypeController!!.findAllValues()
            if ((registerDateAllResponse is ResponseEntity<*> && registerDateAllResponse.statusCode == HttpStatus.ACCEPTED) && registerValueAllResponse is ResponseEntity<*>) {
                val responseBody = registerDateAllResponse.body as HashMap<*, *>
                val registerDateAll = responseBody["result"] as List<*>
                val responseBodyValue = registerValueAllResponse.body as HashMap<*, *>
                val registerValueAll = responseBodyValue["result"] as List<*>
                val registerValuesList = mutableListOf<RegisterValue>() // Lista para almacenar los objetos RegisterValue
                for (columnIndex in 0 until lastColumnNum step 2) {
                    val cellValue = firstRow.getCell(columnIndex)?.stringCellValue?.trim()
                    if (cellValue.isNullOrEmpty()) {
                        break
                    }
                    var primeraFila = true
                    val words = cellValue.split(" ")
                    val modifiedText = words.dropLast(1).joinToString(" ")
                    for (rowIndex in 0..lastRowNum) {
                        val row = sheet.getRow(rowIndex)
                        if (primeraFila) {
                            primeraFila = false
                            continue
                        }
                        val fecha = row.getCell(columnIndex)
                        val fechaa = fecha.stringCellValue
                        val valor = row.getCell(columnIndex + 1)
                        val date = inputFormat.parse(fechaa)
                        val dateR = SimpleDateFormat("MM/dd/yyyy").format(date)
                        val value = valor.stringCellValue.replace(",", ".").toDouble()

                        val registerValue = RegisterValue()

                        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S")
                        for (registerDate in registerDateAll) {
                            val id_Register = (registerDate as RegisterDate).id
                            val date_Register = registerDate.date
                            val date = dateFormat.parse(date_Register.toString())
                            val dateFormatted = SimpleDateFormat("MM/dd/yyyy").format(date)

                            if (dateFormatted == dateR) {
                                registerValue.registerDate = RegisterDate(id_Register, date)
                            }
                        }
                        for (registerValueType in registerValueAll) {
                            val id_ValueType = (registerValueType as ValuesType).id
                            val type_ValueType = registerValueType.description

                            if (type_ValueType == modifiedText) {
                                registerValue.valuesType = ValuesType(id_ValueType, type_ValueType)
                            }

                        }

                        registerValue.date = date
                        registerValue.value = value

                        registerValuesList.add(registerValue) // Agregar el objeto RegisterValue a la lista

                        if (registerValuesList.size >= 1000) {
                        // Si la lista alcanza un tamaño de 1000, guardar los registros en lotes
                            iValueServices!!.saveAllValues(registerValuesList)
                           registerValuesList.clear() // Limpiar la lista después de guardar los registros
                            println("subido")
                         }
                    }
                }
                //iValueServices!!.saveAllValues(registerValuesList)
                //println(registerValuesList.size)

                if (registerValuesList.isNotEmpty()) {
                    // Guardar los registros restantes en la lista
                    //println("guardar")
                    iValueServices!!.saveAllValues(registerValuesList)
                }
            } else {
                println("Error al obtener los datos de RegisterDate")
                response["message"] = "Error al obtener los datos de RegisterDate"
            }
        } catch (e: Exception) {
            println("Error al obtener los datos de RegisterDate: ${e.message}")
            response["message"] = "Error al obtener los datos de RegisterDate: ${e.message}"
        } finally {
            workbook.close()
        }
        println("Cargado satisfactoriamente")
    }



}






