package `2023`.`06`

fun main() {
    val input = Input().input
    val times = input[0].removePrefix("Time: ")
        .split(" ")
        .filter { it != "" }
        .map { it.toInt() }
    val distances = input[1].removePrefix("Distance: ")
        .split(" ")
        .filter { it != "" }
        .map { it.toInt() }
    val races: MutableList<Pair<Int, Int>> = mutableListOf()
    for(i in times.indices) {
        races.add(Pair(times[i], distances[i]))
    }
    var total = 1
    for(race in races) {
        var waysToWin = 0
        for(buttonTime in 0..race.first) {
            if((race.first - buttonTime)*buttonTime > race.second) waysToWin++
        }
        total *= waysToWin
    }
    println(total)
}

