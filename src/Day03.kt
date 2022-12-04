fun main() {
    /**
     * Lower case letters start at 97. a -> 1, so a -> ASCII-code - 96 since values start at 1
     * Upper case letter start at 65. A -> 65, so A -> ASCII-code - 38 since values start at 28
     * https://www.ascii-code.com/
     */
    fun codeFromChar(it: Char) = if (it.isUpperCase()) it.code - 38 else it.code - 96

    fun part1(input: List<String>): Int {
        return input
            .asSequence()
            .filterNot(String::isBlank)
            .map(String::toList)
            .map { it.subList(0, it.size / 2).toSet()
                .intersect(it.subList(it.size / 2, it.size).toSet())
            }
            .filter {
                it.size == 1 //some lines contain two duplicates. Not sure why
            }
            .map {
                it.first() //all sets have only one element.
            }
            .sumOf(::codeFromChar)
    }

    fun part2(input: List<String>): Int {
        return input
            .asSequence()
            .filterNot(String::isBlank)
            .chunked(3) {
                it.map(String::toSet)
                    .reduce(Set<Char>::intersect) //interset three lists at once
            }
            .filter {
                it.size == 1 //some lines contain two duplicates. Not sure why
            }
            .map {
                it.first() //all sets have only one element.
            }
            .sumOf(::codeFromChar)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    val part1Test = part1(testInput)
    println(part1Test)
    check(part1Test == 157)
    val part2Test = part2(testInput)
    println(part2Test)
    check(part2Test == 70)

    val input = readInput("Day03")
    val part1 = part1(input)
    println(part1)
    check(part1 == 7917)
    val part2 = part2(input)
    println(part2)
    check(part2 == 2585)
}
