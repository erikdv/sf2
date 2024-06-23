package net.eriknet.sf2.account.repository

import net.eriknet.sf2.account.model.Account
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*
@Repository
interface AccountRepository : CrudRepository<Account, Long> {

    fun findByUsername(username: String): Account?

    override fun findAll(): List<Account>

    fun findById(id: UUID): Account?


}
