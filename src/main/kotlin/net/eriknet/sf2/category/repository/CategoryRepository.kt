package net.eriknet.sf2.category.repository

import net.eriknet.sf2.category.model.Category
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CategoryRepository: CrudRepository<Category, UUID> {
    override fun findAll(): List<Category>

    fun findBySlug(slug: String): Category
}
