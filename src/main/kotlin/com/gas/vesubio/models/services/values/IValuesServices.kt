package com.gas.vesubio.models.services.values

import com.gas.vesubio.models.entity.RegisterValue
import org.springframework.transaction.annotation.Transactional

interface IValuesServices {

    @Transactional(readOnly = false)
    fun saveValue(registerValue: RegisterValue):RegisterValue
    fun saveAllValues(registerValues: List<RegisterValue>)

    @Transactional(readOnly = true)
    fun findValueById(valueId:Long):RegisterValue
    @Transactional(readOnly = true)
    fun findAll():Iterable<RegisterValue>
    //@Transactional(readOnly = true)
    //fun allValueType()

}