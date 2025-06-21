package academic

import academic.controllers.*
import academic.db.DatabaseFactory
import academic.plugins.configureErrorHandling
import academic.routes.*
import academic.services.*
import academic.security.JwtConfig
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.callloging.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.http.content.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import org.slf4j.event.Level

fun main() {
    embeddedServer(Netty, port = 8080, module = Application::module).start(wait = true)
}

fun Application.module() {
    // Database init
    DatabaseFactory.init()

    // Service initialization
    val userService = UserService()
    val mahasiswaService = MahasiswaService()
    val dosenService = DosenService()
    val matkulService = MataKuliahService()
    val bimbinganService = BimbinganService()

    // Controller initialization
    val authController = AuthController(userService)
    val mahasiswaController = MahasiswaController(mahasiswaService)
    val dosenController = DosenController(dosenService)
    val mataKuliahController = MataKuliahController(matkulService)
    val bimbinganController = BimbinganController(bimbinganService)

    // CORS configuration
    install(CORS) {
        allowMethod(HttpMethod.Options)
        allowMethod(HttpMethod.Put)
        allowMethod(HttpMethod.Delete)
        allowMethod(HttpMethod.Patch)
        allowHeader(HttpHeaders.Authorization)
        allowHeader(HttpHeaders.ContentType)
        anyHost() // For development only
    }

    // Error handling
    configureErrorHandling()

    install(CallLogging) {
        level = Level.INFO
        filter { call -> call.request.path().startsWith("/api") }
        format { call ->
            val status = call.response.status()
            val httpMethod = call.request.httpMethod.value
            val userAgent = call.request.headers["User-Agent"]
            "Status: $status, HTTP method: $httpMethod, User agent: $userAgent"
        }
    }

    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            ignoreUnknownKeys = true
            isLenient = true
        })
    }

    // JWT Configuration
    JwtConfig.configure(this)

    routing {
        // Static content
        staticResources("/", "static")
        
        // API Routes
        authRoutes(authController)
        mahasiswaRoutes(mahasiswaController)
        dosenRoutes(dosenController)
        mataKuliahRoutes(mataKuliahController)
        bimbinganRoutes(bimbinganController)
    }
}
