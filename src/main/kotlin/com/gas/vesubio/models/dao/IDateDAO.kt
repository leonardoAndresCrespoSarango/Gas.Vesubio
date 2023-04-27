package com.gas.vesubio.models.dao

import com.gas.vesubio.models.entity.Dates
import org.springframework.data.repository.CrudRepository

interface IDateDAO: CrudRepository<Dates, Long> {


}