/**
 * @author: Leonar Crespo
 * @since: 28/04/2023
 */
package com.gas.vesubio.models.entity

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

/**
 * La siguiente clase nos permite ingresar los tipos de valores. se ingresara 15 tipos de valores según el csv de Gas Vesubio
 * los cuales tienen como atributos Id y el tipo.
 *
 */
@Entity
@Table(name = "values_type")
class ValuesType: Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "values_tpe_id")
    val id: Long = 0

    @Column(name = "description", nullable = false)
    val description:String? = null

}