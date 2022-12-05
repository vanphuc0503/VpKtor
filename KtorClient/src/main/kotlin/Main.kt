import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.websocket.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.websocket.*
import kotlinx.coroutines.runBlocking

suspend fun main(args: Array<String>) {
    //part 1
    /*val client = HttpClient(CIO)
    val response = client.get("https://ktor.io/")
    println(response.status)
    client.close()*/

    val client = HttpClient {
        install(WebSockets)
    }

    runBlocking {
        try {
            client.webSocket(method = HttpMethod.Get, host = "127.0.0.1", port = 8080, path = "/chat") {
                while (true) {
                    val othersMessage = incoming.receive() as? Frame.Text ?: continue
                    println(othersMessage.readText())
                    val myMessage = readLine()
                    if (myMessage != null) {
                        send(myMessage)
                    }
                }
            }
        } catch (ex: Exception) {
            println(ex)
        }
    }

    client.close()
    println("Connection closed. Goodbye!")
}