package com.gas.vesubio.models.dao

import com.gas.vesubio.models.entity.RegisterValue
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository

/**
 *Es una interfaz que hereda CRUD. Cualquier clase que interactue con esta interfaz hereda los metodos del crud
 */
interface IValueDAO: PagingAndSortingRepository<RegisterValue, Long> {

}
