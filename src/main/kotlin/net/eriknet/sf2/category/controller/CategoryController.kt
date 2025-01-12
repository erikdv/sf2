package net.eriknet.sf2.category.controller

import net.eriknet.sf2.category.service.CategoryService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CategoryController(
    private val service: CategoryService
) {

    @PostMapping("/api/category")
    @PreAuthorize("hasRole('ADMIN')")
    fun create(@RequestBody request: NewCategoryRequest) {
        service.save(request)
    }

    @GetMapping("/api/category")
    fun findAll(): List<CategoryResponse> {
        return service.findAll()
            .map {
                CategoryResponse(
                    it.slug,
                    it.title,
                    it.order
                )
            }
    }

}
