package `2023`.`05`

import java.lang.Exception
import kotlin.math.min

fun main() {
    val input = Input().input
    val seeds = input[0].removePrefix("seeds: ")
        .split(" ").toList()
        .map { it.toLong() }
    val almanac = generateAlmanac(input)
    var minLocation = Long.MAX_VALUE

    for (seed in seeds) {
        minLocation = min(
            traverseAlmanac(almanac, seed, "seed", "location"),
            minLocation)
    }
    println(minLocation)
}

fun traverseAlmanac(
    almanac: MutableMap<Pair<String, String>, MutableList<Long>>,
    sourceValue: Long, sourceName: String,
    destination: String): Long {
    almanac.filterKeys { it.first == sourceName }.firstNotNullOfOrNull {
        val matchingChunk: List<Long> =
            it.value.chunked(3)
                .firstOrNull { chunk -> chunk[1] <= sourceValue && sourceValue < chunk[1] + chunk[2] }
                .orEmpty()

        if ( it.key.second == destination ) {
            when {
                matchingChunk.isEmpty() -> return sourceValue
                else -> return sourceValue - matchingChunk[1] + matchingChunk[0]
            }

        } else {
            val nextDestinationValue =  when {
                matchingChunk.isEmpty() -> sourceValue
                else -> sourceValue - matchingChunk[1] + matchingChunk[0]
            }
            return traverseAlmanac(almanac, nextDestinationValue, it.key.second, destination)
        }
    }
    throw Exception() // Destination not found, error in input map
}

/*
(<Source name, Destination name> -> flat ranges data)
 */
fun generateAlmanac(input: Array<String>): MutableMap<Pair<String, String>, MutableList<Long>> {
    val almanac: MutableMap<Pair<String, String>, MutableList<Long>> = mutableMapOf()
    var currentPair: Pair<String, String> = Pair("","")
    for (i in input.indices) {
        if (input[i].endsWith("map:")) {
            currentPair = Pair(
                input[i].split("-", " ")[0],
                input[i].split("-", " ")[2]
            )
            almanac[currentPair] = mutableListOf()
        } else if (input[i] != "") {
            almanac[currentPair]?.addAll(input[i].split(" ").toList().map { it.toLong() })
        }
    }
    return almanac
}
