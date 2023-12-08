package `2023`.`08`

import java.util.SortedMap

fun main() {
    val raw = Input().data
    val instructions = raw.toList()[0]
    val directions = parseDirections(raw.toList())
    println(instructions)
    println(directions)
    var totalSteps = 0
    var currentLocation = directions.firstKey()
    while (true) {
        val (steps, newLocation) = go(instructions, directions, currentLocation)
        totalSteps += steps
        currentLocation = newLocation
        if (newLocation == "ZZZ") break
    }
    println(totalSteps)
}

fun go(instructions: String, map: Map<String, Pair<String, String>>, currentLocation: String): Pair<Int, String> {
    var location: String = currentLocation
    for(i in instructions.toCharArray().indices) {
        when (instructions.toCharArray()[i]) {
            'L' -> location = map[location]!!.first
            'R' -> location = map[location]!!.second
        }
        println(location)
        if (location == "ZZZ") {
            return (Pair(i+1, location))
        }
    }
    return (Pair(instructions.length, location))
}

private fun parseDirections(input: List<String>): SortedMap<String, Pair<String, String>> {
    val map: MutableMap<String, Pair<String, String>> = mutableMapOf()
    for(line in input.subList(2, input.size))  {
        val data = line.split(" ", "=", "(", ")", ",").filter { it.isNotEmpty() }
        map[data[0]] = Pair(data[1], data[2])
    }
    return map.toSortedMap()
}
