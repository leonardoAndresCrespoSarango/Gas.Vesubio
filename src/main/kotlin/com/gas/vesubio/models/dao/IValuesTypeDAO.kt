package com.gas.vesubio.models.dao

import com.gas.vesubio.models.entity.ValuesType
import org.springframework.data.repository.CrudRepository

interface  IValuesTypeDAO : CrudRepository<ValuesType, Long> {
}