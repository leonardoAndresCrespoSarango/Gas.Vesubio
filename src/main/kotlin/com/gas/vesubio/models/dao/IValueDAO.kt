package com.gas.vesubio.models.dao

import com.fasterxml.jackson.annotation.JsonFormat
import com.gas.vesubio.models.entity.RegisterValue
import org.apache.poi.ss.formula.functions.Value
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import java.time.LocalDate
import java.util.Date

/**
 *Es una interfaz que hereda CRUD. Cualquier clase que interactue con esta interfaz hereda los metodos del crud
 */
interface IValueDAO: PagingAndSortingRepository<RegisterValue, Long> {

   // @Query("SELECT rv.value FROM RegisterValue rv WHERE rv.registerDate.date = :date")
    //fun findByDate(@Param("date") date: Date): List<Double>

    @Query("SELECT rv.value, rv.valuesType.description FROM RegisterValue rv WHERE rv.registerDate.date = :date")
    fun findByDate(@Param("date") date: Date): List<Array<Any>>
    //@Query("SELECT rv.value, rv.valuesType.description FROM (SELECT rv.value, rv.valuesType.description, ROW_NUMBER() OVER (ORDER BY rv.registerDate) AS rowNumber FROM RegisterValue rv WHERE rv.registerDate.date = :date) rv WHERE rv.rowNumber % 30 = 0")
    //fun findByDate30Min(@Param("date") date: Date): List<Array<Any>>

    //@Query("SELECT rv.value, rv.valuesType.description FROM (SELECT rv.value, rv.valuesType.description, ROW_NUMBER() OVER (ORDER BY rv.id) AS rowNumber FROM RegisterValue rv WHERE rv.registerDate.date = :date) rv WHERE rv.rowNumber % 30 = 0")
    //fun findByDateEvery30th(@Param("date") date: Date): List<Array<Any>>

        @Query("SELECT rv.value, rv.valuesType.description FROM RegisterValue rv WHERE rv.registerDate.date = :date AND (SELECT COUNT(*) FROM RegisterValue WHERE rv.registerDate.date = :date AND id <= rv.id) % 30 = 0")
        fun findByDateEvery30th(@Param("date") date: Date): List<Array<Any>>

}
