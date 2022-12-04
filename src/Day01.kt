fun main() {
    fun countCalories(input: List<String>): List<Int> {
        val caloryList: MutableList<Int> = mutableListOf()
        var caloryCounter = 0
        for (it in input) {
            if (it.isBlank()) {
                caloryList.add(caloryCounter)
                caloryCounter = 0
            } else {
                caloryCounter += it.toInt()
            }
        }
        caloryList.add(caloryCounter)
        return caloryList
    }

    fun part1(input: List<String>): Int {
        val maxCalories = countCalories(input).max()
        println("Max calories found: $maxCalories")
        return maxCalories
    }

    fun part2(input: List<String>): Int {
        val maxCalories = countCalories(input).sortedDescending().take(3).sum()
        println("Max calories found: $maxCalories")
        return maxCalories
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    val input = readInput("Day01")
    check(part1(input) == 72602)
    check(part2(input) == 207410)
}
