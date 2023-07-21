package com.gas.vesubio.controller
import com.gas.vesubio.models.entity.RegisterDate
import com.gas.vesubio.models.services.dates.DatesServicesImpl
import com.gas.vesubio.models.services.dates.IDatesServices
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.text.SimpleDateFormat
import java.util.stream.Collectors
import javax.validation.Valid
import org.apache.poi.xssf.usermodel.XSSFWorkbook
@RestController
@RequestMapping("/dates")

class DateController {

    @Autowired
    private val iDatesServices:IDatesServices?=null
    private val s:DatesServicesImpl?=null
    @GetMapping("/findDateById/{dateId}")
    fun findDateById(@PathVariable("dateId") dateId: Long): Any {
        val response=HashMap<String, Any>()
        return try {
            val resultFind: RegisterDate? = iDatesServices!!.findDateById(dateId = dateId)
            if (resultFind == null){
                response["message"]="no se encontro fecha con el id: $dateId"
                ResponseEntity<Map<String, Any>>(response, HttpStatus.NOT_FOUND)
            } else {
                response["message"]="Datos encontrados"
                response["result"]=resultFind
                ResponseEntity<Map<String, Any>>(response, HttpStatus.ACCEPTED)
            }
        } catch (e: Exception){
            response["message"]="Error en el servidor"
            response["result"]=" ${e.message} : ${e.cause}"
            ResponseEntity<Map<String, Any>>(response, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
    @PostMapping("/saveDate")
    fun saveDate (@Valid @RequestBody date: RegisterDate, result: BindingResult): Any{
        val response=HashMap<String, Any>()
        if (result.hasErrors()){
            val errors = result.fieldErrors
                .stream().map { error -> "'Field ${error.field}' ${error.defaultMessage}" }
                .collect(Collectors.toList())
            response["errors"] = errors
            return ResponseEntity<Map<String, Any>>(response, HttpStatus.BAD_REQUEST)
        }
        return try {
            val registerDate = iDatesServices!!.saveDate(date)
            response["message"]="Datos creados correctamente !!"
            response["result"] = registerDate
            ResponseEntity<Map<String, Any>>(response, HttpStatus.OK)
        }catch (e: Exception){
            response["message"]="Error en el servidor"
            response["result"]=" ${e.message} : ${e.cause}"
            ResponseEntity<Map<String, Any>>(response, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }



    @GetMapping("/findAll")
    fun findAll (): Any {
        val response=HashMap<String, Any>()

        return try{
            val registerDateAll=iDatesServices!!.findAll().toList()
            if (registerDateAll.isEmpty()){
                response["message"]="No se encontro ningun registro en la base de datos"
                ResponseEntity<Map<String, Any>>(response, HttpStatus.NOT_FOUND)
            } else {
                response["message"]="Datos encontrados"
                response["result"]=registerDateAll
                ResponseEntity<Map<String, Any>>(response, HttpStatus.ACCEPTED)
            }
        }catch (e:Exception){

        }
    }
   @PostMapping("/importDates")
    fun importDates(@RequestParam("file") file: MultipartFile) {
        val registerDates = ArrayList<RegisterDate>()
        val response = HashMap<String, Any>()
        try {
            val workbook = XSSFWorkbook(file.inputStream)
            val sheet = workbook.getSheetAt(0)
            println (sheet)
            val dateFormat = SimpleDateFormat("MM/dd/yyyy")
            val a= sheet.physicalNumberOfRows
            println(a)
            for (rowNum in 1 until 40001) {
                val row = sheet.getRow(rowNum)
                val dateStr = row.getCell(0).stringCellValue
                val date = dateFormat.parse(dateStr)
                // Verificar si la fecha ya está registrada en la base de datos
                if (!iDatesServices!!.isDateAlreadyRegistered(date)) {
                    val registerDate = RegisterDate()
                    registerDate.date = date
                    //registerDates.add(registerDate)
                    iDatesServices!!.saveDate(registerDate)
                }
            }
            //iDatesServices!!.saveDates(registerDates)
            workbook.close()
            response["message"] = "Datos importados correctamente !!"
            ResponseEntity<Map<String, Any>>(response, HttpStatus.OK)
        }
        catch (e: Exception) {
            e.printStackTrace()
            response["message"] = "Error en el servidor"
            response["result"] = "${e.message} : ${e.cause}"
            ResponseEntity<Map<String, Any>>(response, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}