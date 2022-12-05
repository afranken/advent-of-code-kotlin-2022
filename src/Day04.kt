fun main() {
    fun part1(input: List<String>): Int {
        val overlaps = input
            .asSequence()
            .filterNot(String::isBlank)
            .map { //6-6,4-6
                it.split(",") // 6-6 4-6
                    .map {// 6-6
                        it.split("-") // 6 6
                    }.map {
                        (it[0].toInt()..it[1].toInt()).toSet()
                    }
            }.map {
                val section1 = it[0]
                val section2 = it[1]
                if (section1.containsAll(section2) || section2.containsAll(section1)) {
                    1
                } else {
                    0
                }
            }
        val sum = overlaps.sum()
        println(sum)
        return sum
    }

    fun part2(input: List<String>): Int {
        val overlaps = input
            .asSequence()
            .filterNot(String::isBlank)
            .map { //6-6,4-6
                it.split(",") // 6-6 4-6
                    .map {// 6-6
                        it.split("-") // 6 6
                    }.map {
                        (it[0].toInt()..it[1].toInt()).toSet()
                    }
            }.map {
                val section1 = it[0]
                val section2 = it[1]
                if (section1.intersect(section2).isNotEmpty()) {
                    1
                } else {
                    0
                }
            }
        val sum = overlaps.sum()
        println(sum)
        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day04")
    check(part1(input) == 595)
    check(part2(input) == 952)
}
