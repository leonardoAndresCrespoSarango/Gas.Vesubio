package com.gas.vesubio.models.services.valuestype

import com.gas.vesubio.models.entity.ValuesType
import org.springframework.transaction.annotation.Transactional

interface IValuesTypeService {

    @Transactional(readOnly = false)
    fun saveValueType(valuesType: ValuesType): ValuesType

    @Transactional(readOnly = true)
    fun findValueTypeById(valueTypeId: Long): ValuesType?

    //metodo para buscar todos los valores del registro
    @Transactional(readOnly = true)
    fun findAllValuesType(): List<ValuesType>
    //metodo para buscar valores por rango valores

}