package net.eriknet.sf2.category.service

import net.eriknet.sf2.category.controller.NewCategoryRequest
import net.eriknet.sf2.category.model.Category
import net.eriknet.sf2.category.repository.CategoryRepository
import org.springframework.stereotype.Service

@Service
class CategoryService(
    val repository: CategoryRepository
) {
    fun findAll(): List<Category> =
        repository.findAll()

    fun save(request: NewCategoryRequest) {
        repository.save(Category(request.slug, request.title, request.order))
    }

}
