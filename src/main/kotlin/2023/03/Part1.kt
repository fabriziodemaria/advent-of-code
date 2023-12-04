package `2023`.`03`

fun main() {
    // Generate matrix
    val matrix: MutableList<MutableList<Char>> = mutableListOf()
    for(line in Input().data) {
        val currentRow = line.toCharArray().toMutableList()
        matrix.add(currentRow)
    }

    // Total without caring for symbols
    var sum = 0
    for (x in 0..<matrix.size) {
        String(matrix[x].toCharArray()).split(Regex("[^0-9]"))
            .mapNotNull { it.toIntOrNull() }
            .forEach { sum += it }
    }

    // Mark numbers close to symbols
    for(x in 0..< matrix.size) {
        for(y in 0..< matrix[0].size) {
            val currentChar = matrix[x][y]
            if((currentChar < '0' || currentChar > '9') && currentChar != '.' && currentChar != '!' ) {
                markSurroundingNumbers(matrix, x, y)
            }
        }
    }

    // Total for numbers without surrounding symbols
    var sumNoSymbol = 0
    for (x in 0..<matrix.size) {
        String(matrix[x].toCharArray()).split(".")
            .mapNotNull { it.toIntOrNull() }
            .forEach { sumNoSymbol += it }
    }

    // Total for numbers with surrounding symbols
    println(sum - sumNoSymbol)
}

private fun markSurroundingNumbers(matrix: MutableList<MutableList<Char>>, x: Int, y: Int) {
    for(a in x-1..x+1) {
        for(b in y-1..y+1) {
            if (a < 0 || a > matrix.size - 1 || b < 0 || b > matrix[0].size - 1) {
                // outside the boundaries
                continue
            }
            if (matrix[a][b] in '0'..'9') {
                matrix[a][b] = '!' // MARK
            }
        }
    }
}
