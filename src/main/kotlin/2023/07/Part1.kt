package `2023`.`07`

fun main() {
    val hands = parseGame(Input().input)
    val rankedHands = hands.sortedWith(handsComparator).reversed() // worse to better
    var total = 0
    for (i in rankedHands.indices) {
        total += (rankedHands[i].bid * (i + 1))
    }
    println(total)
}

private fun parseGame(input: Array<String>): List<Hand> {
    return input.map {
        val splits = it.split(" ")
        Hand(splits[0].toCharArray().map { card: Char ->
            when (card) {
                'T' -> 10
                'J' -> 11
                'Q' -> 12
                'K' -> 13
                'A' -> 14
                else -> card.toString().toInt()
            }
        }, splits[1].toInt(), listOf())
    }
}

private val handsComparator =  Comparator<Hand> { a, b ->
    val scoreA = scoreHand(a.cards)
    val scoreB = scoreHand(b.cards)
    if (scoreA == scoreB) {
        var compValue = 0
        for (i in 0..<a.cards.size) {
             compValue = b.cards[i] - a.cards[i]
            if (compValue != 0) break
        }
        compValue
    } else {
        scoreB - scoreA
    }
}

