package com.gas.vesubio.models.dao

import com.gas.vesubio.models.entity.RegisterDate
import org.springframework.data.repository.CrudRepository

interface IDateDAO: CrudRepository<RegisterDate, Long> {


}