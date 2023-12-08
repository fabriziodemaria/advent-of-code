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
        val hand: List<Int> = splits[0].toCharArray().map { card: Char ->
            when (card) {
                'T' -> 10
                'J' -> 1
                'Q' -> 12
                'K' -> 13
                'A' -> 14
                else -> card.toString().toInt()
            }
        }
        Hand(hand, splits[1].toInt(), transformJoker(hand))
    }
}

private val handsComparator =  Comparator<Hand> { a, b ->
    val scoreA = scoreHand(a.noJokerHand)
    val scoreB = scoreHand(b.noJokerHand)
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

private fun transformJoker(handCards: List<Int>): List<Int> {
    val occurrences: MutableMap<Int, Int> = mutableMapOf() // Card, Occurrences
    for (card in handCards) {
        occurrences.putIfAbsent(card, 0)
        occurrences[card] = occurrences[card]!! + 1
    }
    val mostPresentCard = occurrences.entries
        .filter { it.key != 1 }
        .sortedByDescending { it.key }
        .maxByOrNull { it.value }?.key
    if (mostPresentCard == null) {
        return listOf(14, 14, 14, 14, 14)
    }
    return handCards.toMutableList().map { x ->
        when (x) {
            1 -> mostPresentCard
            else -> x
        }
    }
}
