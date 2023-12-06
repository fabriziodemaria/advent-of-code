package `2023`.`05`

import java.lang.Exception
import kotlin.math.max
import kotlin.math.min

fun main() {
    val input = Input().example
    val seeds = input[0].removePrefix("seeds: ")
        .split(" ").toList()
        .map { it.toLong() }
        .chunked(2)
    val destinationRanges = traverseAlmanac(
        generateAlmanac(input),
        seeds.map { LongRange(it[0], it[0] + it[1]) },
        "seed",
        "location")
    println(destinationRanges.minOfOrNull { range -> range.first })
}

private fun traverseAlmanac(
    almanac: MutableMap<Pair<String, String>, MutableList<Long>>,
    sourceRanges: List<LongRange>,
    sourceName: String,
    destination: String): List<LongRange> {
    almanac.filterKeys { it.first == sourceName }
        .firstNotNullOfOrNull {
            if ( it.key.second == destination ) {
                return mapRanges(sourceRanges, it.value.chunked(3))
            } else {
                return traverseAlmanac(
                    almanac,
                    mapRanges(sourceRanges, it.value.chunked(3)),
                    it.key.second,
                    destination)
            }
        }
    throw Exception("Data not found for $sourceName");
}

/*
 Recomputes all input ranges according to mapEntries, and removes duplicated information in result.
 Practically speaking, this is returning the ranges needed for the next level of the almanac.
 */
private fun mapRanges(ranges: List<LongRange>, mapEntries: List<List<Long>>): List<LongRange> {
    val nextStepValues: MutableList<LongRange> = mutableListOf()
    val sortedMapEntries = mapEntries.sortedBy { it[1] }
    val mapEntriesMin = sortedMapEntries.first()[1]
    val mapEntriesMax = sortedMapEntries.last()[1] + sortedMapEntries.last()[2]

    for (range: LongRange in ranges) {
        for (chunk in mapEntries) {
            nextStepValues.addAll(mapRange(range, chunk))
        }

        // Deal with all the source entries that were not matching any mapEntry
        if (range.first < mapEntriesMin) {
            nextStepValues.add(LongRange(range.first, min(mapEntriesMin, range.first)))
        }
        if (range.last > mapEntriesMax) {
            nextStepValues.add(LongRange(max(mapEntriesMax, range.first), range.last))
        }
    }
    return mergeRanges(nextStepValues)
}

/*
 Range:    {}
 mapEntry: []
 Recomputes a single input range, according to a single mapEntry
 */
private fun mapRange(range: LongRange, mapEntry: List<Long>): List<LongRange> {
    val rangeMutator = mapEntry[0] - mapEntry[1]
    val mapEntryRangeLength = mapEntry[1] + mapEntry[2]
    if (range.first < mapEntry[1]) {
        if (range.last < mapEntry[1]) {
            // {_} []
            return listOf()
        } else {
            if (range.last < mapEntryRangeLength) {
                // {_[_} ]
                return listOf(
                    LongRange(mapEntry[1] + rangeMutator, range.last + rangeMutator))
            } else {
                // {_[_]_}
                return listOf(
                    LongRange(mapEntry[1] + rangeMutator, mapEntryRangeLength + rangeMutator),
                )
            }
        }
    } else {
        if (range.first < mapEntryRangeLength) {
            if (range.last < mapEntry[1]) {
                // Impossible
                throw Exception()
            } else {
                if (range.last < mapEntryRangeLength) {
                    // [ {_} ]
                    return listOf(LongRange(range.first + rangeMutator, range.last + rangeMutator))
                } else {
                    // [ {_]_}
                    return listOf(
                        LongRange(range.first + rangeMutator, mapEntryRangeLength + rangeMutator),
                    )
                }
            }
        } else {
            if (range.last < mapEntry[1]) {
                // Impossible
                throw Exception()
            } else {
                if (range.last < mapEntryRangeLength) {
                    // impossible
                    throw Exception()
                } else {
                    // [] {_}
                    return listOf()
                }
            }
        }

    }
}

/*
 Given an input list of different ranges, merges the list in the smallest possible list of ranges.
 This removes redundant data and keeps the memory footprint small
 */
private fun mergeRanges(ranges: List<LongRange>): MutableList<LongRange> {
    val sortedRanges = ranges.sortedBy { it.first }
    val mergedRanges = mutableListOf<LongRange>()
    for (currentRange in sortedRanges) {
        if (mergedRanges.isEmpty() || currentRange.first > mergedRanges.last().last) {
            mergedRanges.add(currentRange)
        } else {
            val previousRange = mergedRanges.last()
            mergedRanges.removeAt(mergedRanges.lastIndex)
            mergedRanges.add(
                LongRange(
                    min(previousRange.first, currentRange.first),
                    max(previousRange.last, currentRange.last)
                )
            )
        }
    }
    return mergedRanges
}

/*
 Generate almanac data struct from given input string.
 Format: <Source name, Destination name> -> List of mapEntries
 */
private fun generateAlmanac(input: Array<String>): MutableMap<Pair<String, String>, MutableList<Long>> {
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
