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
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.routing.*
import io.ktor.server.http.content.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.http.*
import kotlinx.serialization.json.Json

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
    val krsService = KRSService()
    val nilaiService = NilaiService()

    // Controller initialization
    val authController = AuthController(userService)
    val mahasiswaController = MahasiswaController(mahasiswaService)
    val dosenController = DosenController(dosenService)
    val mataKuliahController = MataKuliahController(matkulService)
    val krsController = KRSController(krsService)
    val nilaiController = NilaiController(nilaiService)

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
        krsRoutes(krsController)
        nilaiRoutes(nilaiController)
    }
}
