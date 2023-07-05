/**
 * @author: Paulina Azuero
 * @since: 28/04/2023
 */
package com.gas.vesubio.models.entity

import java.io.Serializable
import java.time.LocalDateTime
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


/**
 * The presented code is a Kotlin class called "RegisterDate" that is used to map a table called
 *  * "register_dates" in a relational database. This is achieved through the "@Entity" annotation,
 *  * which indicates that the class represents a persistent entity. Additionally, the "@Table"
 *  * annotation is used to specify the name of the table in the database.
 *  *
 *  * The class also implements the "Serializable" interface, which indicates that the class
 *  * can be serialized and deserialized for transfer over a network or for caching in memory.
 *  *
 *  * The class has two properties, "id" and "date", which correspond to the columns "date_id" and
 *  * "date" in the "register_dates" table. The "id" property is annotated with "@Id", indicating
 *  * that it is the primary key of the table. The "@GeneratedValue" annotation is used to specify that the value of the primary key will be automatically generated using a sequence. The "date" property is annotated with "@Column" to specify the name of the column in the table and that it cannot be null.
 *  *
 *  * In summary, this class is used to represent a persistent entity in a relational database
 *  * that contains a date and its automatically generated unique identifier.
 *
 *  Represents the date entity of register
 *  *
 *  * @property id  unique identifier of Register Date
 *  * @property date Register Date.
 */
@Entity
@Table(name = "register_dates")
class RegisterDate: Serializable {
    /**
     * unique identifier of Register Date
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "date_id")
    var id: Long? = 0

    /**
     * Register Date.
     */
    @Column(name = "date", nullable = false)
    var date: Date?=null
    //val date: Date? = null



    constructor()
    constructor(date: Date) {
        this.date = date

    }
    constructor(id: Long?, date: Date) {
        this.id = id
        this.date = date
    }

    override fun toString(): String {
        return "RegisterDate(id=$id, date=$date)"
    }
        constructor (date: LocalDateTime )





}


