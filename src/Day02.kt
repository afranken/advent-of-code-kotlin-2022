fun main() {


    fun part1(input: List<String>): Int {

        val scores: List<Int> = input.map {
            val shapeInput = it.split(" ")
            val theirShape = Shape.of(shapeInput[0])
            val myShape = Shape.of(shapeInput[1])

            myShape.score + myShape.outcomeAgainst(theirShape)
        }
        val sum = scores.sum()
        println("Sum of input is $sum")
        return sum
    }

    fun part2(input: List<String>): Int {
        val scores: List<Int> = input.map {
            val shapeInput = it.split(" ")
            val theirShape = Shape.of(shapeInput[0])
            val myShape = Shape.of2(shapeInput[1], theirShape)

            myShape.score + myShape.outcomeAgainst(theirShape)
        }
        val sum = scores.sum()
        println("Sum of input is $sum")
        return sum
    }

    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("Day02")
    check(part1(input) == 12794)
    check(part2(input) == 14979)
}

abstract class Shape {

    abstract val score: Int
    abstract fun outcomeAgainst(other: Shape): Int

    companion object {
        const val WIN: Int = 6
        const val DRAW: Int = 3
        const val LOSS: Int = 0
        fun of(s: String) : Shape {
            return when (s) {
                "A" -> Rock()
                "Y" -> Paper()
                "B" -> Paper()
                "X" -> Rock()
                "C" -> Scissors()
                else -> Scissors() // "Z"
            }
        }

        fun of2(s: String, theirs: Shape) : Shape {
            when (theirs) {
                is Rock -> {
                    return when(s) {
                        "X" -> Scissors()
                        "Y" -> Rock()
                        else -> Paper()
                    }
                }
                is Paper -> {
                    return when(s) {
                        "X" -> Rock()
                        "Y" -> Paper()
                        else -> Scissors()
                    }
                }
                else -> return when(s) { // Scissors()
                    "X" -> Paper()
                    "Y" -> Scissors()
                    else -> Rock()
                }
            }
        }
    }
}

class Rock : Shape() {
    override val score: Int = 1
    override fun outcomeAgainst(other: Shape): Int {
        return when (other) {
            is Rock -> DRAW
            is Scissors -> WIN
            else -> LOSS
        }
    }
}

class Paper : Shape() {
    override val score: Int = 2

    override fun outcomeAgainst(other: Shape): Int {
        return when (other) {
            is Paper -> DRAW
            is Rock -> WIN
            else -> LOSS
        }
    }
}

class Scissors : Shape() {
    override val score: Int = 3
    override fun outcomeAgainst(other: Shape): Int {
        return when (other) {
            is Scissors -> DRAW
            is Paper -> WIN
            else -> LOSS
        }
    }
}
