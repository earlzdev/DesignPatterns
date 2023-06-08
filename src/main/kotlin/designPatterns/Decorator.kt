package designPatterns/*Декоратор представляет собой структурный шаблон проектирования, который позволяет динамически подключать к объекту дополнительное поведение.
 По сути, это более гибкая альтернатива практике по созданию подклассов с целью расширения функциональности.*/

interface Caller {
    fun notify(message: String)
}

class ConsoleNotifier : Caller {
    override fun notify(message: String) {
        println(message)
    }
}

class LoggingNotifier(private val notifier: Caller) : Caller {
    override fun notify(message: String) {
        notifier.notify(message)
        println("LOG - $message") // Like a logger
    }
}

class FancyNotifier(private val notifier: Caller) : Caller {
    override fun notify(message: String) {
        val border = "-".repeat(message.length)
        notifier.notify("""
            $border
            $message
            $border
        """.trimIndent())
    }
}

interface Maker {
    fun make(color: String)
}

class SimpleMaker : Maker {
    override fun make(color: String) {
        println(color)
    }
}

class DarkMaker(private val maker: SimpleMaker) : Maker {
    override fun make(color: String) {
        maker.make("BLACK")
        maker.make(color)
    }
}

class WhiteMaker(private val maker: DarkMaker) : Maker {
    override fun make(color: String) {
        maker.make(color)
        maker.make("WHITE")
    }
}