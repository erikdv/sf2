package net.eriknet.sf2.security.repository

import net.eriknet.sf2.security.model.RefreshToken
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RefreshTokenRepository : CrudRepository<RefreshToken, Long> {

    fun findByRefreshToken(token: String): RefreshToken
    fun save(refreshToken: RefreshToken)
}
