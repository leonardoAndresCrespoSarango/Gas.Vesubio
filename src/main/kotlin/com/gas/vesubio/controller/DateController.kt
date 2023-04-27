package com.gas.vesubio.controller

import com.gas.vesubio.models.dao.IDateDAO
import com.gas.vesubio.models.entity.Dates
import com.gas.vesubio.models.services.dates.IDatesServices
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/dates")
class DateController {
//   Inyeccion
    @Autowired
    private val iDatesServices:IDatesServices?=null
    @GetMapping("/findDateById/{dateId}")
    fun findDateById(@PathVariable("dateId") dateId: Long): Any {
        val response=HashMap<String, Any>()
        return try {
            val resultFind: Dates? = iDatesServices!!.findDateById(dateId = dateId)
            if (resultFind == null){
                response["message"]="no se encontro fecha con el id: $dateId"
                ResponseEntity<Map<String, Any>>(response, HttpStatus.NOT_FOUND)
            } else {
                response["message"]="Datos encontrados"
                response["result"]=resultFind
                ResponseEntity<Map<String, Any>>(response, HttpStatus.OK)
            }
        } catch (e: Exception){
            response["message"]="Error en el servidor"
            response["result"]=" ${e.message} : ${e.cause}"
            ResponseEntity<Map<String, Any>>(response, HttpStatus.OK)
        }

    }



}