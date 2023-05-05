/**
 * @author: Paulina Azuero
 * @since: 05/05/2023
 */
package com.gas.vesubio.models.entity

import java.io.Serializable
import java.util.Date
import javax.persistence.*

/**
 * La siguiente clase se le ingresa en una base de datos por medio de @Entity
 * se le mapea con el nombre "register_values"
 *
 * Se implementa la interfaz Serializable para indicar que la clase
 * pueda serializar y deserializar para transferir a través de una red.
 *
 *
 */

@Entity
@Table(name = "register_values")
class RegisterValue: Serializable {
    /**
     * Variable id es la clave primaria. Se utiliza "GenerateValue" para que
     * el id se genere automaticamente de forma secuencia
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "value_id")
    val id:Long?=0

    /**
     * Variable value guarda datos de la empresa Gas Vesubio
     */
    @Column(name = "value")
    val value:Double?=0.0
    /**
     *
     */
    @Column(name = "date_value", nullable = false)
    val date: Date?=null
    /**
     * Existe una relacion 1-muchos por lo cual  esta tabla guarda los datos de la
     * tabla RegisterDate.
     * Para mencionar esta relacion se utiliza la sintaxis @ManyToOne
     */
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "date_id")
    val registerDate:RegisterDate?=null
    /**
     * Existe una relacion 1-muchos por lo cual  esta tabla guarda los datos de la
     * tabla Inf_Value.
     * Para mencionar esta relacion se utiliza la sintaxis @ManyToOne
     */
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "value_type_id")
    val valuesType:ValuesType?=null

}