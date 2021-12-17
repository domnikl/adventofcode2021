import kotlin.math.abs

fun main() {
    fun List<String>.parse() = flatMap { line ->
        line.split(",").map { it.toInt() }
    }

    fun solve(lines: List<Int>, costOfFuel: (distance: Int) -> Int): Int {
        val max = (0..(lines.maxOf { it }))
        return max
            .minOf { pos -> lines.sumOf { costOfFuel(abs(it - pos)) } }
    }

    fun part1(input: List<String>): Int {
        return solve(input.parse()) { it }
    }

    fun part2(input: List<String>): Int {
        return solve(input.parse()) { it * (it + 1) / 2 }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    check(part1(testInput) == 37)
    check(part2(testInput) == 168)

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}
