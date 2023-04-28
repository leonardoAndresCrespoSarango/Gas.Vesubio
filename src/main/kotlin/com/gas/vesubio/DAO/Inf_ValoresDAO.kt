package com.gas.vesubio.DAO

import com.gas.vesubio.Model.Inf_Valores
import org.springframework.data.repository.CrudRepository

interface  Inf_ValoresDAO : CrudRepository<Inf_Valores, Long> {
}