
fun main() {
    fun List<String>.parse() = flatMap { line ->
        line.split(",").map { it.toInt() }
    }

    fun solve(lines: List<Int>, days: Int): Long {
        var f = lines.groupingBy { it }.eachCount().mapValues { it.value.toLong() }.toMutableMap()

        repeat(days) {
            val nextGen = f.filterKeys { it != 0 }.mapKeys { it.key - 1 }.toMutableMap()
            nextGen[6] = nextGen.getOrDefault(6, 0) + f.getOrDefault(0, 0)
            nextGen[8] = f.getOrDefault(0, 0)
            f = nextGen
        }

        return f.values.sum()
    }

    fun part1(input: List<String>): Long {
        return solve(input.parse(), 80)
    }

    fun part2(input: List<String>): Long {
        return solve(input.parse(), 256)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")

    check(part1(testInput) == 5934L)
    check(part2(testInput) == 26984457539)

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}
