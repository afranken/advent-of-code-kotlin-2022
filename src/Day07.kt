import Node.Dir
import Node.File

fun main() {
    fun treeOf(input: List<String>): Dir {
        var current = Dir("/", null)
        input
            .drop(1)
            .filterNot(String::isBlank)
            .forEach {
                if (it.startsWith("$")) {
                    if (it.startsWith("$ cd")) {
                        val dirName = it.split(' ')[2]
                        current = if (dirName == "..") current.parent!! else current
                            .children
                            .filter { it.name == dirName }
                            .filterIsInstance<Dir>()
                            .firstOrNull() ?: Dir(dirName, current)
                    }
                } else if (it.startsWith("dir")) {
                    val dirName = it.split(' ')[1]
                    current.children.add(Dir(dirName, current))
                } else {
                    val (size, name) = it.split(' ')
                    current.children.add(File(name, size.toInt(), current))
                }
            }
        while (current.parent != null) {
            current = current.parent!!
        }
        return current
    }

    fun part1(input: List<String>): Int {
        val smallDirs = arrayListOf<Dir>()
        val queue = ArrayDeque<Dir>()
        val root = treeOf(input)
        queue.add(root)
        while (queue.isNotEmpty()) {
            val dir = queue.removeFirst()
            if (dir.size <= 100000) {
                smallDirs.add(dir)
            }
            queue.addAll(dir.children.filterIsInstance<Dir>())
        }

        return smallDirs.sumOf { it.size }
    }

    fun part2(input: List<String>): Int {
        val root = treeOf(input)
        val needToFree = root.size - 40000000
        val dirs = sortedSetOf<Dir>({ o1, o2 -> o1.size.compareTo(o2.size) })
        val queue = ArrayDeque<Dir>()
        queue.add(treeOf(input))
        while (queue.isNotEmpty()) {
            val dir = queue.removeFirst()
            if (dir.size >= needToFree) {
                dirs.add(dir)
            }
            queue.addAll(dir.children.filterIsInstance<Dir>())
        }

        return dirs.first().size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    check(part1(testInput) == 95437)
    println(part1(testInput))
    check(part2(testInput) == 24933642)
    println(part2(testInput))

    val input = readInput("Day07")
    check(part1(input) == 1086293)
    println(part2(input))
}

interface Node {
    val size: Int
    val name: String
    val parent: Dir?

    data class File(override val name: String, override val size: Int, override val parent: Dir?) : Node

    data class Dir(override val name: String, override val parent: Dir?) : Node {
        val children = hashSetOf<Node>()
        override val size: Int by lazy { children.sumOf { it.size } }
    }
}
