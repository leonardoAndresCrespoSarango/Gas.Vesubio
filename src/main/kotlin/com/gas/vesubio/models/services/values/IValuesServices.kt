package com.gas.vesubio.models.services.values

import com.fasterxml.jackson.annotation.JsonFormat
import com.gas.vesubio.models.entity.RegisterValue
import org.apache.poi.ss.formula.functions.Value
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

interface IValuesServices {

    @Transactional(readOnly = false)
    fun saveValue(registerValue: RegisterValue):RegisterValue
    fun saveAllValues(registerValues: List<RegisterValue>)

    @Transactional(readOnly = true)
    fun findValueById(valueId:Long):RegisterValue
    @Transactional(readOnly = true)
    fun findAll():Iterable<RegisterValue>
    fun getAllValues(): List<RegisterValue>

    fun getValuesByDate(date: Date): Map<String, List<Double>>

    fun getValuesByDate30(date: Date): Map<String, List<Double>>

    //fun getValuesByDate(date: Date): List<Double>








}