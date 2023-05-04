package com.gas.vesubio.models.entity

import java.io.Serializable
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.Email
import javax.validation.constraints.Size
import javax.xml.crypto.Data

@Entity
@Table(name = "register_dates")
class RegisterDate: Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "date_id")
    val id: Long? = 0

    @Column(name = "date", nullable = false)
    val date: Date? = null

}