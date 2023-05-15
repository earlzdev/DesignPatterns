/*
Цепочка обязанностей - поведенческий шаблон проектирования. Избегает связывания отправителя запроса с получателем, даваяя обработать запрос более чем одному объекту.
Связывает объекты-получатели и передает запрос по цепочке пока объект не обработает его.

Характерные черты паттерна:

объект-клиент, который инициирует запрос, не владеет информацией о том, какой из объектов в цепи будет этот запрос обрабатывать. В запросе есть анонимный получатель (implicit receiver);
нет прямой зависимости между отправителем и приемником. Запрос от отправителя пересматривается объектами-приемниками по цепочке до тех пор, пока не будет обработан;
объект-обработчик обрабатывает запрос в зависимости от выполнения некоторого условия. Если обработчик не может обработать запрос, он передает его следующему объекту-обработчику;
если в течение просмотра всей цепи обработчиков, запрос не обработан, то последний объект-обработчик в цепи может по особому обработать этот запрос.
Паттерн имеет следующие особенности реализации:

создается иерархия классов, содержащих ссылки (указатели) на своего преемника (successor);
на основе иерархии строится цепочка обработчиков. Здесь создаются объекты-обработчики, ссылающиеся на своего преемника (successor). ;
создается объект-клиент, который будет отправлять запрос. Этот объект-клиент ссылается на «ближайший» объект-обработчик. Термин «ближайший» означает, что объекты-обработчики могут ссылаться друг на друга с учетом некоторой иерархии классов. Эта иерархия строится по принципу «от конкретного к общему»;
объект-клиент посылает запрос путем вызова соответствующего метода (операции).
Взаимодействие между клиентом и объектами-обработчиками можно сформулировать следующими шагами:

создать объекты, которые обрабатывают запрос и сформировать их в цепочку;
создать клиента, который указывает на первый объект в цепи;
инициировать запрос клиентом для его просмотра объектами-обработчиками на предмет обработки. Запрос продвигается по цепочке пока некоторый объект не обработает его.
Паттерн Chain of Responsibility используется в следующих случаях:

когда существует более чем один объект, способный обработать запрос. Этот объект заранее неизвестен и должен быть найден автоматически;
когда нужно отправить запрос одному объекту из набора объектов, при этом не зная, какой именно объект обработает запрос;
когда набор объектов, которые должны обработать запрос, формируется динамически.
*/

class Client {
    fun main() {
        val firstHandler = FirstConcreteHandler()
        val secondHandler = SecondConcreteHandler()
        firstHandler.successor = secondHandler
       firstHandler.handleRequest(Priority.SECOND)
    }
}

abstract class Handler {

    var successor: Handler? = null

    abstract fun handleRequest(priority: Priority)
}

class FirstConcreteHandler : Handler() {

    override fun handleRequest(priority: Priority) {
        println("обработка запроса handler 1")
        // обработка запроса
        if (priority == Priority.FIRST) {
            println("завершение запроса handler 1")
           // завершение запроса
        } else if (successor != null) {
            // дальше по цепи
            println("передача дальше по цепи handler 1")
            successor?.handleRequest(priority)
        }
    }
}

class SecondConcreteHandler : Handler() {

    override fun handleRequest(priority: Priority) {
        println("обработка запроса handler 2")
        // некоторая обработка запроса
        if (priority == Priority.SECOND) {
            println("завершение запроса handler 2")
            // завершение обработки
        } else if (successor != null) {
            successor?.handleRequest(priority)
            println("дальше по цепи handler 2")
            // дальше по цепи
        }
    }
}

enum class Priority {
    FIRST, SECOND
}

/**
 *
 * Example
 *
 */

class Factory {

    fun createDetail() {
        val designer = Designer()
        val engineer = Engineer()
        val caster = Caster()
        val painter = Painter()
        designer.successor = engineer
        engineer.successor = caster
        caster.successor = painter
        designer.doSomeWork(Detail("Detail", DetailState.TODO), WorkStates.PAINT_DETAIL)
    }
}

abstract class Worker {

    var successor: Worker? = null
    abstract fun doSomeWork(detail: Detail, desiredState: WorkStates)
}

class Designer : Worker() {

    override fun doSomeWork(detail: Detail, desiredState: WorkStates) {
        detail.state = DetailState.DESIGNED
        println("designer: detail -> $detail")
        if (desiredState == WorkStates.CREATE_DESIGN) {
            println("designer: work ends")
        } else successor?.doSomeWork(detail, desiredState)
    }
}

class Engineer : Worker() {

    override fun doSomeWork(detail: Detail, desiredState: WorkStates) {
        detail.state = DetailState.SCHEME_CREATED
        println("engineer: detail -> $detail")
        if (desiredState == WorkStates.CREATE_SCHEME) {
            println("engineer: work ends")
        } else successor?.doSomeWork(detail, desiredState)
    }
}

class Caster : Worker() {

    override fun doSomeWork(detail: Detail, desiredState: WorkStates) {
        detail.state = DetailState.CASTED
        println("caster: detail -> $detail")
        if (desiredState == WorkStates.CAST_DETAIL) {
            println("caster: work ends")
        } else successor?.doSomeWork(detail, desiredState)
    }
}

class Painter : Worker() {

    override fun doSomeWork(detail: Detail, desiredState: WorkStates) {
        detail.state = DetailState.PAINTED
        println("painter: detail -> $detail")
        if (desiredState == WorkStates.PAINT_DETAIL) {
            println("painter: work ends")
        } else successor?.doSomeWork(detail, desiredState)
    }
}

enum class WorkStates {
    CREATE_DESIGN, CREATE_SCHEME, CAST_DETAIL, PAINT_DETAIL
}

data class Detail(
    val name: String,
    var state: DetailState
)

enum class DetailState {
    TODO, DESIGNED, SCHEME_CREATED, CASTED, PAINTED
}



/**
 *
 * pattern: Chain of responsibility
 *
 * description: a design pattern consisting of “a source of command objects and a series of processing objects”.
 * Each processing object in the chain is responsible for a certain type of command, and the processing is done,
 * it forwards the command to the next processor in the chain.
 *
 */

enum class BlockFactor {
    ONE, TWO, THREE
}

/**
 *
 * I decided to give an analogy from the Minecraft game.
 * In this game there are blocks that can be broken with a stone pickaxe, iron and diamond.
 * For example: diamond may mine by iron and diamond pickaxes unlike cobblestone, which is mined by any
 *
 */
abstract class Block(private val factor: BlockFactor) {
    fun mayMine(factor: BlockFactor) = this.factor.ordinal <= factor.ordinal
}

/**
 *
 * blocks from the game
 *
 */
class StoneBlock: Block(BlockFactor.ONE)
class DiamondBlock: Block(BlockFactor.TWO)
class ObsidianBlock: Block(BlockFactor.THREE)

abstract class Pickaxe(private val factor: BlockFactor) {

    private var nextPickaxe: Pickaxe? = null
    fun changeNextPickaxe(pickaxe: Pickaxe) {
        this.nextPickaxe = pickaxe
    }

    /**
     *
     * we mine the block, if it doesn't work, we take another pickaxe, if there is one
     *
     * @return return true if a pickaxe can mine
     */
    fun mine(block: Block): Boolean =
        if (block.mayMine(factor)) {
            true
        } else {
            nextPickaxe?.mine(block) ?: false
        }

}

/**
 *
 * pickaxes from the game
 *
 */
class StonePickaxe: Pickaxe(BlockFactor.ONE)

class IronPickaxe: Pickaxe(BlockFactor.TWO)

class DiamondPickaxe: Pickaxe(BlockFactor.THREE)

