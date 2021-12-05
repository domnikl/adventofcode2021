class Board(private val data: List<List<Int>>, called: List<Int>) {
    private val transformed = data.indices.map { column ->
        data.indices.map { row ->
            data[row][column]
        }
    }

    private val unmarked: MutableList<Int> = data.flatten().toMutableList()
    private val marked: MutableList<Int> = mutableListOf()

    val winsAt: Int?
    val score
        get() = winsAt?.let { unmarked.sum() * it }

    val takes
        get() = marked.size

    init {
        winsAt = play(called)
    }

    private fun play(called: List<Int>): Int? {
        for (call in called) {
            unmarked.remove(call)
            marked.add(call)

            if (hasItWonYet()) {
                return call
            }
        }

        return null
    }

    private fun hasCompleteRow() = data.any { marked.containsAll(it) }
    private fun hasCompleteColumn() = transformed.any { marked.containsAll(it) }

    private fun hasItWonYet(): Boolean {
        return hasCompleteRow() || hasCompleteColumn()
    }
}

fun buildBoards(input: List<String>): List<Board> {
    val called = input[0].trim().split(",").map { it.toInt() }

    val boardsInput = input.subList(1, input.size).filter { it.isNotEmpty() }

    return boardsInput.chunked(5).map { board ->
        board.map { line ->
            line.split(" ").mapNotNull {
                if (it.isBlank()) null else it.trim().toInt()
            }
        }
    }.map { Board(it, called) }
}


fun main() {
    fun part1(input: List<String>): Int {
        val sorted = buildBoards(input).filter { it.winsAt != null }.sortedBy { it.takes }
        val winner = sorted.first()

        return winner.score!!
    }

    fun part2(input: List<String>): Int {
        val sorted = buildBoards(input).sortedBy { it.takes }.reversed()
        val lastWinner = sorted.first()

        return lastWinner.score!!
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 4512)
    check(part2(testInput) == 1924)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
