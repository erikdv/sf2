package net.eriknet.sf2.account.service

import net.eriknet.sf2.account.model.Account
import net.eriknet.sf2.account.repository.AccountRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class AccountService(
    private val accountRepository: AccountRepository,
    private val encoder: PasswordEncoder

) {

    fun createAccount(account: Account) : Account? {
        val found = accountRepository.findByUsername(account.username)
        return if (found == null) {
            accountRepository.save(
                Account(
                    username = account.username,
                    password = encoder.encode(account.password),
                ))
        } else null
    }

    fun findAll(): List<Account> =
        accountRepository.findAll()

    fun findByUserId(uuid: UUID) : Account? =
        accountRepository.findById(uuid)

}
