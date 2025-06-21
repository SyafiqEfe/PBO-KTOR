package academic.security

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.auth.jwt.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import java.util.*

object JwtConfig {
    private const val secret = "jwt-secret-key"
    private const val issuer = "ktor-academic"
    private const val validityInMs = 36_000_00 * 24 // 1 day

    private val algorithm = Algorithm.HMAC256(secret)

    val verifier: JWTVerifier = JWT
        .require(algorithm)
        .withIssuer(issuer)
        .build()

    fun generateToken(userId: String, role: String): String =
        JWT.create()
            .withSubject("Authentication")
            .withIssuer(issuer)
            .withClaim("userId", userId)
            .withClaim("role", role)
            .withExpiresAt(Date(System.currentTimeMillis() + validityInMs))
            .sign(algorithm)

    fun configure(application: Application) {
        application.install(Authentication) {
            jwt("auth-jwt") {
                verifier(verifier)
                validate { credential ->
                    if (credential.payload.getClaim("userId").asString() != null) {
                        JWTPrincipal(credential.payload)
                    } else null
                }
            }
        }
    }
}
