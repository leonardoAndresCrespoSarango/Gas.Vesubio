package com.gas.vesubio.controller

import com.gas.vesubio.models.entity.RegisterDate
import com.gas.vesubio.models.services.dates.IDatesServices
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
@RequestMapping("/dates")
class DateController {

    @Autowired
    private val iDatesServices:IDatesServices?=null



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

}