fun main() {
    var sum = 0
    val data: Array<String> = arrayOf()
    for (line in data) {
        var pointer = 0
        var first = 0
        var second = 0
        val crunched = crunch(line)
        val size = crunched.size
        while(pointer < size) {
            if (crunched[pointer] in 0..9) {
                first = crunched[pointer]
                break
            }
            pointer++
        }
        pointer = size - 1
        while(pointer >= 0) {
            if (crunched[pointer] in 0..9) {
                if (first != 0) {
                    first *= 10
                }
                second = crunched[pointer]
                break
            }
            pointer--
        }
        sum += first + second
    }
    println(sum)
}

private fun crunch(i: String): Array<Int> {
    val nums = IntArray(i.length) { -1 }
    var cursor = 0
    for (p in i.toCharArray()) {
        val foundIndex = i.indexOf("zero" , cursor, true)
        if (foundIndex != -1) {
            nums[foundIndex] = 0
        }
        cursor++
    }
    cursor = 0
    for (p in i.toCharArray()) {
        val foundIndex = i.indexOf("one" , cursor, true)
        if (foundIndex != -1) {
            nums[foundIndex] = 1
        }
        cursor++
    }
    cursor = 0
    for (p in i.toCharArray()) {
        val foundIndex = i.indexOf("two" , cursor, true)
        if (foundIndex != -1) {
            nums[foundIndex] = 2
        }
        cursor++
    }
    cursor = 0
    for (p in i.toCharArray()) {
        val foundIndex = i.indexOf("three" , cursor, true)
        if (foundIndex != -1) {
            nums[foundIndex] = 3
        }
        cursor++
    }
    cursor = 0
    for (p in i.toCharArray()) {
        val foundIndex = i.indexOf("four" , cursor, true)
        if (foundIndex != -1) {
            nums[foundIndex] = 4
        }
        cursor++
    }
    cursor = 0
    for (p in i.toCharArray()) {
        val foundIndex = i.indexOf("five" , cursor, true)
        if (foundIndex != -1) {
            nums[foundIndex] = 5
        }
        cursor++
    }
    cursor = 0
    for (p in i.toCharArray()) {
        val foundIndex = i.indexOf("six" , cursor, true)
        if (foundIndex != -1) {
            nums[foundIndex] = 6
        }
        cursor++
    }
    cursor = 0
    for (p in i.toCharArray()) {
        val foundIndex = i.indexOf("seven" , cursor, true)
        if (foundIndex != -1) {
            nums[foundIndex] = 7
        }
        cursor++
    }
    cursor = 0
    for (p in i.toCharArray()) {
        val foundIndex = i.indexOf("eight" , cursor, true)
        if (foundIndex != -1) {
            nums[foundIndex] = 8
        }
        cursor++
    }
    cursor = 0
    for (p in i.toCharArray()) {
        val foundIndex = i.indexOf("nine" , cursor, true)
        if (foundIndex != -1) {
            nums[foundIndex] = 9
        }
        cursor++
    }
    cursor = 0
    for (p in i.toCharArray()) {
        val foundIndex = i.indexOf("0" , cursor, true)
        if (foundIndex != -1) {
            nums[foundIndex] = 0
        }
        cursor++
    }
    cursor = 0
    for (p in i.toCharArray()) {
        val foundIndex = i.indexOf("1" , cursor, true)
        if (foundIndex != -1) {
            nums[foundIndex] = 1
        }
        cursor++
    }
    cursor = 0
    for (p in i.toCharArray()) {
        val foundIndex = i.indexOf("2" , cursor, true)
        if (foundIndex != -1) {
            nums[foundIndex] = 2
        }
        cursor++
    }
    cursor = 0
    for (p in i.toCharArray()) {
        val foundIndex = i.indexOf("3" , cursor, true)
        if (foundIndex != -1) {
            nums[foundIndex] = 3
        }
        cursor++
    }
    cursor = 0
    for (p in i.toCharArray()) {
        val foundIndex = i.indexOf("4" , cursor, true)
        if (foundIndex != -1) {
            nums[foundIndex] = 4
        }
        cursor++
    }
    cursor = 0
    for (p in i.toCharArray()) {
        val foundIndex = i.indexOf("5" , cursor, true)
        if (foundIndex != -1) {
            nums[foundIndex] = 5
        }
        cursor++
    }
    cursor = 0
    for (p in i.toCharArray()) {
        val foundIndex = i.indexOf("6" , cursor, true)
        if (foundIndex != -1) {
            nums[foundIndex] = 6
        }
        cursor++
    }
    cursor = 0
    for (p in i.toCharArray()) {
        val foundIndex = i.indexOf("7" , cursor, true)
        if (foundIndex != -1) {
            nums[foundIndex] = 7
        }
        cursor++
    }
    cursor = 0
    for (p in i.toCharArray()) {
        val foundIndex = i.indexOf("8" , cursor, true)
        if (foundIndex != -1) {
            nums[foundIndex] = 8
        }
        cursor++
    }
    cursor = 0
    for (p in i.toCharArray()) {
        val foundIndex = i.indexOf("9" , cursor, true)
        if (foundIndex != -1) {
            nums[foundIndex] = 9
        }
        cursor++
    }
    return nums.toTypedArray()
}