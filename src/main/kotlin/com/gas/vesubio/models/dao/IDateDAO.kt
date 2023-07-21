package com.gas.vesubio.models.dao

import com.gas.vesubio.models.entity.RegisterDate
import com.gas.vesubio.models.entity.RegisterValue
import org.springframework.data.repository.CrudRepository
import java.util.*

/**
 * este codigo es una interfaz de Kotlin llamada "IDateDAO" que amplía la interfaz de Spring Data "CrudRepository" con los tipos genéricos
 * "RegisterDate" y "Long".
 *
 * Esta interfaz define el conjunto básico de operaciones CRUD (Crear, Leer, Actualizar y Eliminar) que se pueden realizar en la entidad
 * "RegisterDate". Estas operaciones incluyen guardar una nueva entidad, recuperar una entidad por ID y eliminar una entidad.
 *
 * Al extender la interfaz "CrudRepository", la interfaz hereda todos los métodos definidos en la interfaz, como "save()", "findById()", "findAll()", "deleteById()", etc.
 *
 * La interfaz no proporciona ninguna implementación de estos métodos
 */
interface IDateDAO: CrudRepository<RegisterDate, Long> {
    /**
     * Para buscar si existe una fecha en la base de datos
     */


    fun findByDate(date: Date): List<RegisterDate>

}