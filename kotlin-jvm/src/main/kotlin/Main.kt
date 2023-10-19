import io.jooby.kt.runApp

fun main(args: Array<String>) {
    println("Starting with arguments: ${args.joinToString(", ")}")
    runApp(args, ::HttpApp)
}