package `2023`.`06`

fun main() {
    val input = Input().input
    val time = input[0].removePrefix("Time: ")
        .replace(" ","").toLong()
    val distance = input[1].removePrefix("Distance: ")
        .replace(" ","").toLong()
    var waysToWin = 0
    for(buttonTime in 0..time) {
        if((time - buttonTime)*buttonTime > distance) waysToWin++
    }
    println(waysToWin)
}

