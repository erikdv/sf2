package net.eriknet.sf2

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.servers.Server
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication

@OpenAPIDefinition(servers = [ Server( url = "/") ])
@SpringBootApplication
@EntityScan
class Sf2Application

fun main(args: Array<String>) {
    runApplication<Sf2Application>(*args)
}
