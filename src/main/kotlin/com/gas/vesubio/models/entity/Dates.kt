package com.gas.vesubio.models.entity

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import javax.xml.crypto.Data

@Entity
@Table(name = "Dates")
class Dates: Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "date_id")
    val id: Long? = 0
    @Column(name = "date", nullable = false)
    val date: Data? = null
}