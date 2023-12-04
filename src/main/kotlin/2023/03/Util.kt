package `2023`.`03`

class Util {
    fun printMatrix(matrix: MutableList<MutableList<Char>>) {
        for (x in 0..<matrix.size) {
            for (y in 0..<matrix[0].size) {
                print(matrix[x][y])
            }
            println()
        }
    }
}