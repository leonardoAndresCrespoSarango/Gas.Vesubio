package com.gas.vesubio.Model

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "Inf_Inf_Valores")
class Inf_Valores: Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "var_id")
    val id: Long? = 0
    @Column(name = "var_descrip", nullable = false)
    val descripcion:String? = null
}