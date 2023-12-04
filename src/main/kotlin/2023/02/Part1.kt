package `2023`.`02`

import kotlin.math.max

fun main() {
    var result = 0
    for(game in Input().data) {
        val keyValue = game.removePrefix("Game ").split(":")
        var g = 0;  var r = 0; var b = 0;
        for (roll in keyValue[1].split(";")) {
            for (die in roll.split(",")) {
                val value = die.removePrefix(" ").split(" ")[0].toInt()
                when {
                    die.endsWith(" blue") -> b = max(b, value)
                    die.endsWith(" red") -> r = max(r, value)
                    die.endsWith(" green") -> g = max(g, value)
                }
            }
        }
        if (g > 13 || r > 12 || b > 14) {
            continue
        }
        result += keyValue[0].toInt()
    }
    println(result)
}
