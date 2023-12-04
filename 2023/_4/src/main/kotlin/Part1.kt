import kotlin.math.pow

fun main() {
    var total = 0.0
    for(card in Input().data) {
        val splits = card.split(":", "|")
        val win = splits[1].split(" ").mapNotNull { it.toIntOrNull() }
        val have = splits[2].split(" ").mapNotNull { it.toIntOrNull() }
        val matches = have.toSet().filter { win.toSet().contains(it) }.size
        when (matches) {
            0 -> continue
            1 -> total++
            else -> total += 2.0.pow((matches - 1).toDouble())
        }
    }
    println(total)
}