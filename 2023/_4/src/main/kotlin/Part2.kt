import kotlin.math.max

fun main() {
    var total = 0.0
    var cardMatches = mutableListOf<Int>()
    val input = Input().sample
    for(index in 0..<input.size) {
        val splits = input[index].split(":", "|")
        val win = splits[1].split(" ").mapNotNull { it.toIntOrNull() }
        val have = splits[2].split(" ").mapNotNull { it.toIntOrNull() }
        val matches = have.toSet().filter { win.toSet().contains(it) }.size
        total += (max(matches, (index-input.size-1)))
    }
    println(total)
}