fun leastCommonBits(input: List<List<Int>>) = input.first().indices.map {
    leastCommonBitAt(input, it)
}

fun mostCommonBits(input: List<List<Int>>) = input.first().indices.map {
    mostCommonBitAt(input, it)
}

fun mostCommonBitAt(input: List<List<Int>>, index: Int): Int {
    val count = input.count { it[index] == 1 }

    return when {
        count >= input.size / 2.0 -> 1
        else -> 0
    }
}

fun leastCommonBitAt(input: List<List<Int>>, index: Int): Int {
    val count = input.count { it[index] == 0 }

    return when {
        count > input.size / 2.0 -> 1
        else -> 0
    }
}

fun oxygenGeneratorRating(input: List<List<Int>>): List<Int> {
    var filtered = input

    input.indices.forEach { i ->
        filtered = filtered.filter { it[i] == mostCommonBitAt(filtered, i) }

        if (filtered.size <= 1) return filtered.first()
    }

    return filtered.first()
}

fun co2ScrubberRating(input: List<List<Int>>): List<Int> {
    var filtered2 = input

    input.indices.forEach { i ->
        filtered2 = filtered2.filter { it[i] == leastCommonBitAt(filtered2, i) }

        if (filtered2.size <= 1) return filtered2.first()
    }

    return filtered2.first()
}

fun main() {
    fun part1(input: List<String>): Int {
        val diagnosticReport = input.toInts()

        val gamma = mostCommonBits(diagnosticReport).joinToString("")
        val epsilon = leastCommonBits(diagnosticReport).joinToString("")

        return gamma.toInt(2) * epsilon.toInt(2)
    }

    fun part2(input: List<String>): Int {
        val diagnosticReport = input.toInts()

        val oxygen = oxygenGeneratorRating(diagnosticReport)
        val co2 = co2ScrubberRating(diagnosticReport)

        return oxygen.toInt(2) * co2.toInt(2)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)

    val input = readInput("Day03")
    println(part1(input)) // 3985686
    println(part2(input)) // 2555739
}

private fun List<Int>.toInt(radix: Int): Int {
    return this.joinToString("").toInt(radix)
}

private fun List<String>.toInts(): List<List<Int>> {
    return this.map { it.trim().map { x -> x.digitToInt() } }
}
