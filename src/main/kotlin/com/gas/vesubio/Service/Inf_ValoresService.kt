package com.gas.vesubio.Service

import com.gas.vesubio.Model.Inf_Valores
import org.springframework.transaction.annotation.Transactional

interface  Inf_ValoresService {
    //    false lectura y escritura
    @Transactional(readOnly = false)
    fun saveValues(valores: Inf_Valores):Inf_Valores
    //   True para solo lecura
    @Transactional(readOnly = true)
    fun findValueById(var_id:Long):Inf_Valores?

    //metodo para buscar todos los valores del registro
    @Transactional(readOnly = true)
    fun findAllValues(): List<Inf_Valores>
    //metodo para buscar valores por rango valores
    @Transactional(readOnly = true)
    fun findValuesByRange(valores: Inf_Valores): List<Inf_Valores>


}