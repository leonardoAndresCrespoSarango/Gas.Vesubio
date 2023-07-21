package com.gas.vesubio.models.services.values

import com.fasterxml.jackson.annotation.JsonFormat
import com.gas.vesubio.models.dao.IValueDAO
import com.gas.vesubio.models.entity.RegisterValue
import org.apache.poi.ss.formula.functions.Value
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.data.domain.Sort
import java.time.LocalDate
import java.util.*


@Service
class ValueServicesImpl:IValuesServices {
    @Autowired
    private val iValuesDao:IValueDAO?=null
    override fun saveValue(value: RegisterValue): RegisterValue {
        return iValuesDao!!.save(value)
    }

    override fun saveAllValues(registerValues: List<RegisterValue>) {
        iValuesDao!!.saveAll(registerValues)
    }


    override fun findValueById(valueId: Long): RegisterValue {
        return iValuesDao!!.findById(valueId).orElse(null)
    }
    override fun findAll(): Iterable<RegisterValue> {
        val pageSize = 360 // Tamaño de la página
        val page = 0 // Número de página (página 1)

        val pageable: PageRequest = PageRequest.of(page, pageSize, Sort.by("id")) // Ordenar por el atributo 'id'

        return iValuesDao!!.findAll(pageable).content

    }


    override fun getAllValues(): List<RegisterValue> {
        return iValuesDao!!.findAll().toList()
    }


    //override fun getValuesByDate(date: Date): List<Double> {
      //  return iValuesDao!!.findByDate(date)
    //}

    override fun getValuesByDate(date: Date): Map<String, List<Double>> {
        val values = iValuesDao!!.findByDate(date)
        val valueMap = mutableMapOf<String, MutableList<Double>>()
        for (value in values) {
            val valueType = value[1] as String
            val actualValue = value[0] as Double
            if (valueMap.containsKey(valueType)) {
                valueMap[valueType]?.add(actualValue)
            } else {
                valueMap[valueType] = mutableListOf(actualValue)
            }
        }
        return valueMap
    }

    override fun getValuesByDate30(date: Date): Map<String, List<Double>> {
        val values = iValuesDao!!.findByDateEvery30th(date)
        val valueMap = mutableMapOf<String, MutableList<Double>>()
        for (value in values) {
            val valueType = value[1] as String
            val actualValue = value[0] as Double
            if (valueMap.containsKey(valueType)) {
                valueMap[valueType]?.add(actualValue)
            } else {
                valueMap[valueType] = mutableListOf(actualValue)
            }
        }
        return valueMap
    }
}


