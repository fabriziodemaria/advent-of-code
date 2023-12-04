package `2023`.`01`

fun main() {
    var sum = 0
    for (line in Input().data) {
        val digits = indexMatching(line)
        sum += digits.first() * 10 + digits.last()
    }
    println(sum)
}

private fun indexMatching(input: String): List<Int> {
    val nums = mutableListOf<Int>()
    for (i in 0..input.length) {
        val p = input.subSequence(i, input.length)
        when {
            p.startsWith("zero") || p.startsWith("0") -> nums.add(0)
            p.startsWith("one") || p.startsWith("1") -> nums.add(1)
            p.startsWith("two") || p.startsWith("2") -> nums.add(2)
            p.startsWith("three") || p.startsWith("3") -> nums.add(3)
            p.startsWith("four") || p.startsWith("4") -> nums.add(4)
            p.startsWith("five") || p.startsWith("5") -> nums.add(5)
            p.startsWith("six") || p.startsWith("6") -> nums.add(6)
            p.startsWith("seven") || p.startsWith("7") -> nums.add(7)
            p.startsWith("eight") || p.startsWith("8") -> nums.add(8)
            p.startsWith("nine") || p.startsWith("9") -> nums.add(9)
        }
    }
    return nums.toList()
}
