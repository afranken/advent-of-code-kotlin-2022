fun main() {
    fun part1(input: List<String>): Int {
        val count = 4
        return input[0]
            .windowed(count)
            .mapIndexed {
                    index, chunk ->
                if (chunk.toSet().size == count) {
                    index + count
                } else {
                    //chunk contains duplicate chars
                    0
                }
            }.first { it != 0 }
    }

    fun part2(input: List<String>): Int {
        val count = 14
        return input[0]
            .windowed(count)
            .mapIndexed {
                    index, chunk ->
                if (chunk.toSet().size == count) {
                    index + count
                } else {
                    //chunk contains duplicate chars
                    0
                }
            }.first { it != 0 }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 5)

    val input = readInput("Day06")
    check(part1(input) == 1480)
    check(part2(input) == 2746)
}

