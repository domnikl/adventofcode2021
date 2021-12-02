private data class SimpleNavigation(val depth: Int = 0, val horizontal: Int = 0) {
    fun withDepth(depth: Int) = SimpleNavigation(horizontal = this.horizontal, depth = depth)
    fun withHorizontal(horizontal: Int) = SimpleNavigation(horizontal = horizontal, depth = this.depth)
}

private data class AdvancedNavigation(val depth: Int = 0, val horizontal: Int = 0, val aim: Int = 0) {
    fun forward(value: Int) = AdvancedNavigation(
        depth = this.depth + this.aim * value,
        horizontal = this.horizontal + value,
        aim = this.aim
    )

    fun down(value: Int) = AdvancedNavigation(
        depth = this.depth,
        horizontal = this.horizontal,
        aim = this.aim + value
    )

    fun up(value: Int) = AdvancedNavigation(
        depth = this.depth,
        horizontal = this.horizontal,
        aim = this.aim - value
    )
}

fun main() {
    fun part1(input: List<String>): Int {
        val nav = input.fold(SimpleNavigation()) { out, element ->
            val (command, value) = element.split(" ")

            when (command) {
                "forward" -> out.withHorizontal(out.horizontal + value.toInt())
                "down" -> out.withDepth(out.depth + value.toInt())
                else -> out.withDepth(out.depth - value.toInt())
            }
        }

        return nav.depth * nav.horizontal
    }

    fun part2(input: List<String>): Int {
        val nav = input.fold(AdvancedNavigation()) { out, element ->
            val (command, value) = element.split(" ")

            when (command) {
                "forward" -> out.forward(value.toInt())
                "down" -> out.down(value.toInt())
                else -> out.up(value.toInt())
            }
        }

        return nav.depth * nav.horizontal
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
