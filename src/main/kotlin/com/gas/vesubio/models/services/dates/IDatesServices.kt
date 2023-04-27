package com.gas.vesubio.models.services.dates

import com.gas.vesubio.models.entity.Dates
import org.springframework.transaction.annotation.Transactional

interface IDatesServices  {
//    false lectura y escritura
    @Transactional(readOnly = false)
    fun saveDate(date:Dates):Dates
//   True para solo lecura
    @Transactional(readOnly = true)
    fun findDateById(dateId:Long):Dates?



}