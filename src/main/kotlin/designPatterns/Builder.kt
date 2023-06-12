package designPatterns

data class Computer private constructor(
    val cpu: String?,
    val ram: String?,
    val ssd: String?,
    val cooling: String?
) {

    data class Builder(
        private var cpu: String? = null,
        private var ram: String? = null,
        private var ssd: String? = null,
        private var cooling: String? = null
    ) {
        fun cpu(cpu: String) = this.apply { this.cpu = cpu }
        fun ram(ram: String) = this.apply { this.ram = ram }
        fun ssd(ssd: String) = this.apply { this.ssd = ssd }
        fun cooling(cooling: String) = this.apply { this.cooling = cooling }
        fun build() = Computer(cpu, ram, ssd, cooling)
        fun randomBuild() = cpu(cpu ?: "AMD")
            .ram(ram ?: "16GB")
            .ssd(ssd ?: "256GB")
            .cooling(cooling ?: "Cool")
            .build()
    }
}

fun main() {

    val computer = Computer.Builder()
        .cpu("Intel core i5")
        .cooling("Fast")
        .ssd("512GB")
        .build()

    println(computer)
}