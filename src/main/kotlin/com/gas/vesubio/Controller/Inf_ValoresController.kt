package com.gas.vesubio.Controller
import com.gas.vesubio.Model.Inf_Valores
import com.gas.vesubio.Service.Inf_ValoresService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/info_valores")
class Inf_ValoresController {
    @Autowired
    private val valoresService:Inf_ValoresService?=null
    @GetMapping("/{id}")
    fun findInfoValoresById(@PathVariable("var_id") var_id: Long): ResponseEntity<Inf_Valores?> {
        val infoValores = valoresService!!.findValueById(var_id)
        return if (infoValores != null) {
                ResponseEntity.ok(infoValores)
            } else {
                ResponseEntity.notFound().build()
            }
    }
    /*@PostMapping("/guardar")
    fun saveInfoValores(@RequestBody infoValores: Inf_Valores): ResponseEntity<Inf_Valores> {
        val savedInfoValores = valoresService!!.saveValues(infoValores)
        return ResponseEntity.ok(savedInfoValores)
    }*/
    @GetMapping("/listarAll")
    fun findAllInfoValores(): ResponseEntity<List<Inf_Valores>> {
        val infoValoresList = valoresService!!.findAllValues()
        return ResponseEntity.ok(infoValoresList)
    }









}