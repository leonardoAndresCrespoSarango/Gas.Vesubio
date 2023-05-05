package com.gas.vesubio.controller

import com.gas.vesubio.models.entity.RegisterValue
import com.gas.vesubio.models.services.values.IValuesServices
import org.hibernate.engine.internal.Collections
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.stream.Collectors
import javax.validation.Valid

@RestController
@RequestMapping("/values")
class ValueController {
    @Autowired
    private val iValueServices:IValuesServices?=null
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

}

