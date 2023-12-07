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

data class Hand(
    var cards: List<Int>,
    var bid: Int
)

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
        }, splits[1].toInt())
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

private fun scoreHand(handCards: List<Int>): Int {
    val occurrences: MutableMap<Int, Int> = mutableMapOf()
    for (card in handCards) {
        occurrences.putIfAbsent(card, 0)
        occurrences[card] = occurrences[card]!! + 1
    }
    return when {
        occurrences.values.sorted() == listOf(5) -> 7
        occurrences.values.sorted() == listOf(1, 4) -> 6
        occurrences.values.sorted() == listOf(2, 3) -> 5
        occurrences.values.sorted() == listOf(1, 1, 3) -> 4
        occurrences.values.sorted() == listOf(1, 2, 2) -> 3
        occurrences.values.sorted() == listOf(1, 1, 1, 2) -> 2
        else -> 1
    }
}

