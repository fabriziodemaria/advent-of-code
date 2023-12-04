import kotlin.math.min

fun main() {
    val input = Input().data
    val cardMatches = MutableList(input.size) { 1 }
    for(index in input.indices) {
        val splits = input[index].split(":", "|")
        val win = splits[1].split(" ").mapNotNull { it.toIntOrNull() }
        val have = splits[2].split(" ").mapNotNull { it.toIntOrNull() }
        val matches = have.toSet().filter { win.toSet().contains(it) }.size
        for(x in index + 1..min(index + matches, input.size - 1)) {
            cardMatches[x] += cardMatches[index]
        }
    }
    println(cardMatches.reduce { acc, next -> acc + next })
}