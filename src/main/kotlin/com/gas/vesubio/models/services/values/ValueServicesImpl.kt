package com.gas.vesubio.models.services.values

import com.gas.vesubio.models.dao.IValueDAO
import com.gas.vesubio.models.entity.RegisterValue
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.awt.print.Pageable
import org.springframework.data.domain.Sort


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

    /*override fun findAll(): Iterable<RegisterValue> {
        return iValuesDao!!.findAll()
    }*/

}