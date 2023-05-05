package com.gas.vesubio.models.services.dates

import com.gas.vesubio.models.dao.IDateDAO
import com.gas.vesubio.models.entity.RegisterDate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * La clase se anota con "@Service", que se usa para indicar que es un componente de servicio y Spring Framework debe detectarlo
 * automáticamente.
 */
@Service
class DatesServicesImpl: IDatesServices  {
    @Autowired
    private val iDatesDao:IDateDAO?=null
    override fun saveDate(date: RegisterDate): RegisterDate {
        return iDatesDao!!.save(date)
    }

    override fun findDateById(dateId: Long): RegisterDate? {
        return iDatesDao!!.findById(dateId).orElse(null)
    }

    override fun findAll(): Iterable<RegisterDate> {
        return iDatesDao!!.findAll()
    }
}