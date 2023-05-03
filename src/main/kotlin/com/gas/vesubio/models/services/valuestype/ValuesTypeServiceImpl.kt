package com.gas.vesubio.models.services.valuestype

import com.gas.vesubio.models.dao.IValuesTypeDAO
import com.gas.vesubio.models.entity.ValuesType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ValuesTypeServiceImpl : IValuesTypeService{

    @Autowired
    private val iValuesTypeDAO: IValuesTypeDAO?=null

    override fun saveValueType(valuesType: ValuesType): ValuesType {
        return iValuesTypeDAO!!.save(valuesType)
    }

    override fun findValueTypeById(valueTypeId: Long): ValuesType? {
        return iValuesTypeDAO!!.findById(valueTypeId).orElse(null)
    }

    override fun findAllValuesType(): List<ValuesType> {
        return iValuesTypeDAO!!.findAll().toList()
    }


}