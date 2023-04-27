package com.gas.vesubio.models.services.dates

import com.gas.vesubio.models.dao.IDateDAO
import com.gas.vesubio.models.entity.Dates
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DatesServicesImpl: IDatesServices  {
    @Autowired
    private val iDatesDao:IDateDAO?=null
    override fun saveDate(date: Dates): Dates {
        return iDatesDao!!.save(date)
    }

    override fun findDateById(dateId: Long): Dates? {
        return iDatesDao!!.findById(dateId).orElse(null)
    }
}