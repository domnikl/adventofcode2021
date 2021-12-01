fun main() {
    fun part1(input: List<String>): Int {
        return input.map { it.toInt() }.windowed(2).mapNotNull {
            if (it[0] < it[1]) it else null
        }.size
    }

    fun part2(input: List<String>): Int {
        val threeSlidingWindow = input.map { it.toInt() }.windowed(3)

        return threeSlidingWindow.map { it.sum() }.windowed(2).mapNotNull {
            if (it[0] < it[1]) it else null
        }.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
