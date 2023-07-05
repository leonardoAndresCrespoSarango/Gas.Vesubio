package com.gas.vesubio.models.dao

import com.gas.vesubio.models.entity.RegisterValue
import org.springframework.data.repository.CrudRepository

/**
 *Es una interfaz que hereda CRUD. Cualquier clase que interactue con esta interfaz hereda los metodos del crud
 */
interface IValueDAO: CrudRepository<RegisterValue, Long> {

}
