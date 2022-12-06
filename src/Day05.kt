fun main() {
    fun part1(input: List<String>, stackSize: Int, moveDrop: Int): String {
        val stacks = mutableMapOf<Int, ArrayDeque<Char>>()
        for (i in 0 until stackSize) {
            stacks[i] = ArrayDeque()
        }

        input
            .take(stackSize)
            .filterNot(String::isBlank)
            .map {
                it
                    .chunkedSequence(4)
                    .forEachIndexed { index: Int, s: String ->
                        if (s.contains("[")) {
                            stacks[index]!!.addFirst(s.elementAt(s.indexOf("[")+1))
                        }
                    }
            }

        input.drop(moveDrop)
            .map {
                Move(it)
            }.forEach {
                for(i in 0 until it.count) {
                    stacks[it.to]!!.add(stacks[it.from]!!.removeLast())
                }
            }

        val chars = stacks.values.map {
            it.removeLast()
        }.toCharArray()
        val message = String(chars)
        println(
            message
        )
        return message
    }

    fun part2(input: List<String>, stackSize: Int, moveDrop: Int): String {
        val stacks = mutableMapOf<Int, ArrayDeque<Char>>()
        for (i in 0 until stackSize) {
            stacks[i] = ArrayDeque()
        }

        input
            .take(stackSize)
            .filterNot(String::isBlank)
            .map {
                it
                    .chunkedSequence(4)
                    .forEachIndexed { index: Int, s: String ->
                        if (s.contains("[")) {
                            stacks[index]!!.addFirst(s.elementAt(s.indexOf("[")+1))
                        }
                    }
            }

        input.drop(moveDrop)
            .map {
                Move(it)
            }.forEach {
                val deque = ArrayDeque<Char>()
                for(i in 0 until it.count) {
                    deque.addFirst(stacks[it.from]!!.removeLast())
                }
                stacks[it.to]!!.addAll(deque)
            }

        val chars = stacks.values.map {
            it.removeLast()
        }.toCharArray()
        val message = String(chars)
        println(
            message
        )
        return message
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput, 3, 5) == "CMZ")
    check(part2(testInput, 3, 5) == "MCD")

    val input = readInput("Day05")
    check(part1(input, 9, 10) == "VQZNJMWTR")
    check(part2(input, 9, 10) == "NLCDCLVMQ")
}

//move 7 from 3 to 9
class Move(s: String) {
    val count: Int
    val from: Int
    val to: Int

    init {
        //0    1 2    3 4  5
        //move 7 from 3 to 9
        val split = s.split(" ")
        count = split[1].toInt()
        //access starts at 0, not 1
        from = split[3].toInt() - 1
        to = split[5].toInt() - 1
    }
}
