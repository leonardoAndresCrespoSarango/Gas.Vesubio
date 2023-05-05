package com.gas.vesubio.controller


import com.gas.vesubio.models.services.valuestype.IValuesTypeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

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

}