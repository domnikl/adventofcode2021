fun leastCommonBits(input: List<List<Int>>): List<Int> {
    return countOnes(input).map {
        when {
            it > input.size / 2 -> 0
            else -> 1
        }
    }
}

fun mostCommonBits(input: List<List<Int>>): List<Int> {
    val half = input.size / 2.0

    return countOnes(input).map {
        when {
            it >= half -> 1
            else -> 0
        }
    }
}

fun mostCommonBitAt(input: List<List<Int>>, index: Int): Int {
    val half = input.size / 2.0
    val count = input.count { it[index] == 1 }

    return when {
        count >= half -> 1
        else -> 0
    }
}

fun leastCommonBitAt(input: List<List<Int>>, index: Int): Int {
    val half = input.size / 2.0
    val count = input.count { it[index] == 0 }

    return when {
        count > half -> 1
        else -> 0
    }
}

fun countOnes(input: List<List<Int>>): List<Int> {
    val counts = Array(input.first().size) { 0 }

    input.forEach { line ->
        line.map { it == 1 }.forEachIndexed { index, b ->
            if (b) counts[index]++
        }
    }

    return counts.toList()
}

fun oxygenGeneratorRating(input: List<List<Int>>): List<Int> {
    var filtered = input

    (input.indices).forEach { i ->
        val mostCommon = mostCommonBitAt(filtered, i)

        filtered = filtered.filter { it[i] == mostCommon }

        if (filtered.size <= 1) return filtered.first()
    }

    return filtered.first()
}

fun co2ScrubberRating(input: List<List<Int>>): List<Int> {
    var filtered2 = input

    (input.indices).forEach lit@{ i ->
        val leastCommon = leastCommonBitAt(filtered2, i)

        println(filtered2)
        println("least common at $i: $leastCommon")

        filtered2 = filtered2.filter { it[i] == leastCommon }

        if (filtered2.size <= 1) {
            return filtered2.first()
        }
    }

    return filtered2.first()
}

fun main() {
    fun part1(input: List<String>): Int {
        val mostCommon = mostCommonBits(input.toInts()).joinToString("")
        val epsilon = leastCommonBits(input.toInts()).joinToString("")

        return mostCommon.toInt(2) * epsilon.toInt(2)
    }

    fun part2(input: List<String>): Int {
        val oxygenGeneratorRating = oxygenGeneratorRating(input.toInts()).toInt(2)
        val co2 = co2ScrubberRating(input.toInts()).toInt(2)

        return oxygenGeneratorRating * co2
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
