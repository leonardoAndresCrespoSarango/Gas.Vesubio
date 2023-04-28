package com.gas.vesubio.Service

import com.gas.vesubio.DAO.Inf_ValoresDAO
import com.gas.vesubio.Model.Inf_Valores
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
abstract class Inf_ValoresServiceImpl: Inf_ValoresService {
    @Autowired
    private val valoresDAO: Inf_ValoresDAO?=null
    override fun saveValues(valores: Inf_Valores): Inf_Valores {
        return valoresDAO!!.save(valores)
    }

    override fun findValueById(var_id: Long): Inf_Valores? {
        return valoresDAO!!.findById(var_id).orElse(null)
    }
    //override fun findValuesByRange(valores: Inf_Valores): List<Inf_Valores> {
        // Asumiendo que la entidad Inf_Valores tiene propiedades "valorMin" y "valorMax"
        //return valoresDAO.findByValorMinGreaterThanEqualAndValorMaxLessThanEqual(valores.valorMin, valores.valorMax)
       // return
   // }

}
