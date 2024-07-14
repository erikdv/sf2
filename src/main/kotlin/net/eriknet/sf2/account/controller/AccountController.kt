package net.eriknet.sf2.account.controller

import net.eriknet.sf2.account.model.Account
import net.eriknet.sf2.account.service.AccountService
import net.eriknet.sf2.security.controller.AuthenticationRequest
import net.eriknet.sf2.security.controller.AuthenticationResponse
import net.eriknet.sf2.security.service.AuthenticationService
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/account")
class AccountController(
    private val accountService: AccountService,
    private val authenticationService: AuthenticationService
) {

    @PostMapping
    fun create(@RequestBody accountRequest: AccountRequest): AuthenticationResponse {

        accountService.createAccount(
            account = accountRequest.toModel()
        ) ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot create the user")

        return authenticationService.authenticate(
            AuthenticationRequest(
                username = accountRequest.username,
                password = accountRequest.password
            )
        )
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    fun listAll() : List<AccountResponse> =
        accountService.findAll().map { it.toResponse() }.toList()


    private fun AccountRequest.toModel(): Account =
        Account(
            username = this.username,
            password = this.password,
        )

    private fun Account.toResponse(): AccountResponse =
        AccountResponse(
            uuid = this.id,
            username = this.username,
            roles = this.roles.map { it.name }
        )

}
