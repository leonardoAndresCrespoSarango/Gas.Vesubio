package com.gas.vesubio.models.services.dates

import com.gas.vesubio.models.entity.RegisterDate
import org.springframework.transaction.annotation.Transactional
import java.util.Date

interface IDatesServices  {
//    false lectura y escritura
    @Transactional(readOnly = false)
    fun saveDate(date:RegisterDate):RegisterDate

    @Transactional(readOnly = false)
    fun saveDates(date: List<RegisterDate>):List<RegisterDate>

    //   True para solo lecura
    @Transactional(readOnly = true)
    fun findDateById(dateId:Long):RegisterDate?

    @Transactional(readOnly = true)
    fun findAll():Iterable<RegisterDate>

    //Metodo para guardar los datos del csv
    @Transactional(readOnly = false)
    fun saveAll(dates: Iterable<RegisterDate>): Iterable<RegisterDate>

    fun isDateAlreadyRegistered(date: Date): Boolean




}