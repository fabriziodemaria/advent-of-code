import java.lang.Exception
import kotlin.math.max
import kotlin.math.min

fun main() {
    // Generate matrix
    val matrix: MutableList<MutableList<Char>> = mutableListOf()
    for(line in Input().data) {
        val currentRow = line.toCharArray().toMutableList()
        matrix.add(currentRow)
    }
    var total = 0;
    // Scan for gears
    for(x in 0..< matrix.size) {
        for(y in 0..< matrix[0].size) {
            if(matrix[x][y] == '*') {
                total += calculateGear(matrix, x, y)
            }
        }
    }
    println(total)
}

private fun calculateGear(matrix: MutableList<MutableList<Char>>, x: Int, y: Int): Int {
    val surroundingNumbers: MutableList<Int> = mutableListOf()

    // Scan surrounding numbers, row by row, for rows in and around "*"
    for(a in max(x-1, 0)..min(x+1, matrix.size - 1)) {
        val currentX = matrix[a]
        var l = y
        var r = y
        while (l > 0 && currentX[l - 1] in '0'..'9') { l-- }
        while (r < (currentX.size - 1) && currentX[r + 1] in '0'..'9') { r++ }

        // 'l' and 'r' define the range where surrounding numbers have been found in current row
        String(currentX.toCharArray())
            .subSequence(l, r + 1)
            .split(".", "*")
            .mapNotNull { it.toIntOrNull() }
            .forEach { surroundingNumbers.add(it) }
    }

    // Only add numbers to "total" if exactly two have been found around "*"
    return when (surroundingNumbers.size) {
        2 -> surroundingNumbers[0] * surroundingNumbers[1]
        else -> 0
    }
}


