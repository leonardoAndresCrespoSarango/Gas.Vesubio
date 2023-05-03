package com.gas.vesubio.models.services.valuestype

import com.gas.vesubio.models.entity.ValuesType
import org.springframework.transaction.annotation.Transactional

interface IValuesTypeService {
    /**
     * @author Leonardo Crespo
     *consist in creation of class (Interface) to do the service that can use this methods
     */

    // method that consist about cant only read but can read / write
    @Transactional(readOnly = false)
    fun saveValueType(valuesType: ValuesType): ValuesType
    //method about cant only read values
    @Transactional(readOnly = true)
    fun findValueTypeById(valueTypeId: Long): ValuesType?

    // method to search all values of the register that this proyect have
    @Transactional(readOnly = true)
    fun findAllValuesType(): List<ValuesType>


}