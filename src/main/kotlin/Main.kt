import algorithms.BubbleSort
import java.util.*

fun main(args: Array<String>) {

//    LoggingNotifier(
//        FancyNotifier(
//            ConsoleNotifier()
//        )
//    ).notify
//    y("Hello, World!")

//    WhiteMaker(
//        DarkMaker(
//            SimpleMaker()
//        )
//    ).make("RED")

//    val client = Client()
//    client.main()

//    val factory = Factory()
//    factory.createDetail()

    val bubbleSort = BubbleSort()
    println(Arrays.toString(bubbleSort.sort(arrayOf(1, 2, 7, 4, 109, 7, 45, 34, 6, 99, 56, 32, 45))))

}

