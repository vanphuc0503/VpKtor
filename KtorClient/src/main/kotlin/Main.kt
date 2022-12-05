import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*

suspend fun main(args: Array<String>) {
    val client = HttpClient(CIO)
    val response = client.get("https://ktor.io/")
    println(response.status)
    client.close()
}