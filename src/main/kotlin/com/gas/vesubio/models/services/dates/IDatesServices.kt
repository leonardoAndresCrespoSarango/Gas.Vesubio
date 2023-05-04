package com.gas.vesubio.models.services.dates

import com.gas.vesubio.models.entity.RegisterDate
import org.springframework.transaction.annotation.Transactional

interface IDatesServices  {
//    false lectura y escritura
    @Transactional(readOnly = false)
    fun saveDate(date:RegisterDate):RegisterDate
//   True para solo lecura
    @Transactional(readOnly = true)
    fun findDateById(dateId:Long):RegisterDate?

    @Transactional(readOnly = true)
    fun findAll():Iterable<RegisterDate>
}