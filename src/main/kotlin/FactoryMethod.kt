
/*
Фабричный метод (Factory Method) - это паттерн, который определяет интерфейс для создания объектов некоторого класса,но непосредственное решение о том, объект какого класса создавать происходит в подклассах.
То есть паттерн предполагает, что базовый класс делегирует создание объектов классам-наследникам.

Абстрактный класс Product определяет интерфейс класса, объекты которого надо создавать.
Конкретные классы ConcreteProductA и ConcreteProductB представляют реализацию класса Product. Таких классов может быть множество
Абстрактный класс Creator определяет абстрактный фабричный метод FactoryMethod(), который возвращает объект Product.
Конкретные классы ConcreteCreatorA и ConcreteCreatorB - наследники класса Creator, определяющие свою реализацию метода FactoryMethod(). Причем метод FactoryMethod() каждого отдельного класса-создателя возвращает определенный конкретный тип продукта. Для каждого конкретного класса продукта определяется свой конкретный класс создателя.
Таким образом, класс Creator делегирует создание объекта Product своим наследникам. А классы ConcreteCreatorA и ConcreteCreatorB могут самостоятельно выбирать какой конкретный тип продукта им создавать.
*/

abstract class Product

class ConcreteProductA : Product()

class ConcreteProductB : Product()

abstract class Creator {
    abstract fun factoryMethod() : Product
}

class ConcreteCreatorA : Creator() {
    override fun factoryMethod(): Product = ConcreteProductA()
}

class ConcreteCreatorB : Creator() {
    override fun factoryMethod(): Product = ConcreteProductB()
}