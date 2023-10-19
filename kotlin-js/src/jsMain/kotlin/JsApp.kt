import kotlinx.browser.document
import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.WebSocket

fun main() {
    val chatMessages = document.getElementById("chatMessages") as HTMLDivElement
    val messageInput = document.getElementById("messageInput") as HTMLInputElement
    val sendButton = document.getElementById("sendButton") as HTMLButtonElement

    val ws = WebSocket("ws://localhost:8080/chat")

    ws.onmessage = { event ->
        val message = event.asDynamic().data as String
        val messageDiv = document.createElement("div")
        messageDiv.textContent = message
        chatMessages.appendChild(messageDiv)
    }

    sendButton.addEventListener("click", {
        val messageText = messageInput.textContent
        if (messageText != null) {
            ws.send(messageText)
        }
        //messageInput?.asDynamic().value = ""
    })
}