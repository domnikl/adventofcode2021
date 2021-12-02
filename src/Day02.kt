private data class SimpleNavigation(val depth: Int = 0, val horizontal: Int = 0)
private data class AdvancedNavigation(val depth: Int = 0, val horizontal: Int = 0, val aim: Int = 0)

fun main() {
    fun part1(input: List<String>): Int {
        val nav = input.fold(SimpleNavigation()) { out, element ->
            val (command, value) = element.split(" ")

            when (command) {
                "forward" -> SimpleNavigation(out.depth, out.horizontal + value.toInt())
                "down" -> SimpleNavigation(out.depth + value.toInt(), out.horizontal)
                else -> SimpleNavigation(out.depth - value.toInt(), out.horizontal)
            }
        }

        return nav.depth * nav.horizontal
    }

    fun part2(input: List<String>): Int {
        val nav = input.fold(AdvancedNavigation()) { out, element ->
            val (command, value) = element.split(" ")

            when (command) {
                "forward" -> AdvancedNavigation(out.depth + out.aim * value.toInt(), out.horizontal + value.toInt(), out.aim)
                "down" -> AdvancedNavigation(out.depth, out.horizontal, out.aim + value.toInt())
                else -> AdvancedNavigation(out.depth, out.horizontal, out.aim -  value.toInt())
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
