package com.gas.vesubio.controller


import com.gas.vesubio.models.entity.RegisterDate
import com.gas.vesubio.models.entity.RegisterValue
import com.gas.vesubio.models.entity.ValuesType
import com.gas.vesubio.models.services.valuestype.IValuesTypeService
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

/**
 *
 */
@RestController
@RequestMapping("/valuesTypeController")

class ValuesTypeController {
    //interface ValuesTypeService

    @Autowired
    private val iValuesTypeService:IValuesTypeService?=null

    /**
     * service that consist of find values with ID.
     * if it finds de correct id  with de ID that you put show: no se encontro ningun dato con el id
     * but if it doesn't find the ID the service show: Datos encontrados
     */

    @GetMapping("/findValuesTypeById/{valueTypeId}")
    fun findValuesTypeById(@PathVariable("valueTypeId") valueTypeId: Long): Any{
        val response=HashMap<String, Any>()
        return try {
            val resultFind = iValuesTypeService!!.findValueTypeById(valueTypeId = valueTypeId)
            if (resultFind == null){
                response["message"]="no se encontro ningun dato con el id: $valueTypeId"
                ResponseEntity<Map<String, Any>>(response, HttpStatus.NOT_FOUND)
            } else {
                response["message"]="Datos encontrados"
                response["result"]=resultFind
                ResponseEntity<Map<String, Any>>(response, HttpStatus.OK)
            }
        } catch (e: Exception){
            response["message"]="Error en el servidor"
            response["result"]="${e.message} : ${e.cause}"
            ResponseEntity<Map<String, Any>>(response, HttpStatus.OK)
        }
    }

    /**
     *
     */

    @GetMapping("/findAllValues")
    fun findAllValues(): Any{
        val response=HashMap<String, Any>()
        return try {
            val resultFind = iValuesTypeService!!.findAllValuesType()
            if (resultFind.isEmpty()){
                response["message"]="no se encontraron valores"
                ResponseEntity<Map<String, Any>>(response, HttpStatus.NOT_FOUND)
            } else {
                response["message"]="Datos encontrados"
                response["result"]=resultFind
                ResponseEntity<Map<String, Any>>(response, HttpStatus.OK)
            }
        } catch (e: Exception){
            response["message"]="Error en el servidor"
            response["result"]="${e.message} : ${e.cause}"
            ResponseEntity<Map<String, Any>>(response, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }


    @PostMapping("/procesar")
    fun IngresarValueType(@RequestParam("file") file: MultipartFile): ResponseEntity<Map<String, Any>> {
        val response = HashMap<String, Any>()
        var workbook: XSSFWorkbook? = null // Declarar el Workbook fuera del try-catch

        val valueTypeAll = iValuesTypeService!!.findAllValuesType()

        try {
            workbook = XSSFWorkbook(file.inputStream)
            val sheet = workbook.getSheetAt(0) // Consideramos la primera hoja del libro
            val firstRow = sheet.getRow(0) // Obtenemos la primera fila

            val dataArray = ArrayList<String>()

            for (i in 0 until firstRow.lastCellNum step 2) {
                val cell = firstRow.getCell(i)
                if (cell != null) {
                    val cellValue = cell.getStringCellValue()
                    val words = cellValue.split(" ") // Separamos el contenido por espacios
                    val modifiedValue = words.dropLast(1).joinToString(" ") // Eliminamos la última palabra

                    // Verificar si modifiedValue ya existe en valueTypeAll
                    val valueExists = valueTypeAll.any { it.description == modifiedValue }

                    if (!valueExists) {
                        dataArray.add(modifiedValue)

                        // Crear un objeto ValuesType a partir del valor modifiedValue y guardarlo en la base de datos
                        val newValueType = ValuesType(modifiedValue)
                        iValuesTypeService.saveValueType(newValueType)
                    }
                }
            }

            response["status"] = "success"
            response["data"] = dataArray

            return ResponseEntity(response, HttpStatus.OK)

        } catch (e: Exception) {
            response["status"] = "error"
            response["message"] = "Error al procesar el archivo: ${e.message}"
            return ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR)
        } finally {
            // En el bloque finally, asegúrate de cerrar el workbook incluso si ocurre una excepción
            try {
                workbook?.close()
            } catch (e: Exception) {
                // Manejar cualquier excepción al cerrar el workbook (opcional)
            }
        }
    }

}