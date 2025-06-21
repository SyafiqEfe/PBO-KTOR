package academic

import academic.controllers.*
import academic.repositories.*
import academic.db.DatabaseFactory.initDatabase // ✅ Impor initDatabase
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.routing.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.plugins.callloging.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.http.content.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import org.slf4j.event.Level

fun main() {
    // Ubah port ke 8081 jika 8080 sedang digunakan
    embeddedServer(Netty, port = 8081, module = Application::module).start(wait = true)
}

fun Application.module() {
    // ✅ Inisialisasi koneksi database
    initDatabase()

    // Initialize repositories
    val userRepo = UserRepository()
    val mahasiswaRepo = MahasiswaRepository()
    val dosenRepo = DosenRepository()
    val matkulRepo = MataKuliahRepository()

    // Initialize controllers
    val authCtrl = AuthController(userRepo)
    val mhsCtrl = MahasiswaController(mahasiswaRepo, matkulRepo)
    val dosenCtrl = DosenController(dosenRepo, mahasiswaRepo)
    val matkulCtrl = MataKuliahController(matkulRepo)

    // Install plugins
    install(CallLogging) {
        level = Level.INFO
        filter { call -> call.request.path().startsWith("/api") }
    }

    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            ignoreUnknownKeys = true
            isLenient = true
        })
    }

    install(Authentication) {
        jwt("auth-jwt") {
            realm = "Academic System"
            verifier(AuthController.JWT_VERIFIER)
            validate { credential ->
                val userId = credential.payload.getClaim("id").asString()
                val userRole = credential.payload.getClaim("role").asString()
                if (!userId.isNullOrEmpty() && !userRole.isNullOrEmpty()) {
                    JWTPrincipal(credential.payload)
                } else {
                    null
                }
            }
        }
    }

    // Configure routing
    routing {
        // Health check endpoint
        get("/health") {
            call.respond(mapOf("status" to "OK", "port" to "8081"))
        }

        // Auth routes
        post("/api/login") { authCtrl.login(call) }
        post("/api/register") { authCtrl.register(call) }

        // Protected routes
        authenticate("auth-jwt") {
            // User profile
            get("/api/me") { authCtrl.getMe(call) }

            // Mahasiswa routes
            route("/api/mahasiswa") {
                get { mhsCtrl.getAll(call) }
                post { mhsCtrl.create(call) }

                route("/{id}") {
                    get { mhsCtrl.getById(call) }
                    put { mhsCtrl.update(call) }
                    delete { mhsCtrl.delete(call) }

                    // Mata kuliah operations
                    post("/matkul") { mhsCtrl.ambilMatkul(call) }
                    delete("/matkul/{kode}") { mhsCtrl.dropMatkul(call) }
                }
            }

            // Dosen routes
            route("/api/dosen") {
                get { dosenCtrl.getAll(call) }
                post { dosenCtrl.create(call) }

                route("/{id}") {
                    get { dosenCtrl.getById(call) }
                    put { dosenCtrl.update(call) }
                    delete { dosenCtrl.delete(call) }

                    // Bimbingan operations
                    post("/bimbingan/{mhsId}") { dosenCtrl.addBimbingan(call) }
                    delete("/bimbingan/{mhsId}") { dosenCtrl.removeBimbingan(call) }
                }
            }

            // MataKuliah routes
            route("/api/matakuliah") {
                get { matkulCtrl.getAll(call) }
                post { matkulCtrl.create(call) }

                route("/{kode}") {
                    get { matkulCtrl.getByKode(call) }
                    put { matkulCtrl.update(call) }
                    delete { matkulCtrl.delete(call) }
                }
            }
        }

        // Static files - serve HTML files from resources/static
        static("/") {
            resources("static")
            defaultResource("index.html", "static")
        }
    }
}
