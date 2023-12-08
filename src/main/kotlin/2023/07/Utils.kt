package `2023`.`07`

internal fun scoreHand(handCards: List<Int>): Int {
    val occurrences: MutableMap<Int, Int> = mutableMapOf() // Card, Occurrs
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

data class Hand(
    var cards: List<Int>,
    var bid: Int,
    var noJokerHand: List<Int>
)
//
//data class JokerHand(
//    var hand: Hand,
//)
