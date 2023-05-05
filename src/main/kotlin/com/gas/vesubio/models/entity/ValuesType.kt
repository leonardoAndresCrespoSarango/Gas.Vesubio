package com.gas.vesubio.models.entity

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

/**
 * Represents Value Type of these entity.
 *
 * @property id Unique identifier of Value Type
 * @property description Description of Value Type
 */
@Entity
@Table(name = "values_type")
class ValuesType: Serializable {
    /**
     * Unique identifier of Value Type
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "values_tpe_id")
    val id: Long = 0

    /**
     * Description of Value Type
     */
    @Column(name = "description", nullable = false)
    val description:String? = null

}