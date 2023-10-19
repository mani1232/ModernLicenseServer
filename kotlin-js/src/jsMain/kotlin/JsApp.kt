import kotlinx.browser.document
import org.w3c.dom.WebSocket

fun main() {
    val chatMessages = document.getElementById("chatMessages")
    val messageInput = document.getElementById("messageInput")
    val sendButton = document.getElementById("sendButton")

    val ws = WebSocket("ws://localhost:8080/chat")

    ws.onmessage = { event ->
        val message = event.asDynamic().data as String
        val messageDiv = document.createElement("div")
        messageDiv.textContent = message
        chatMessages?.appendChild(messageDiv)
    }

    sendButton?.addEventListener("click", {
        val messageText = messageInput?.asDynamic().value as String
        ws.send(messageText)
        messageInput?.asDynamic().value = ""
    })
}