package tictactoe

val field = mutableListOf(
    MutableList(3) {'_'},
    MutableList(3) {'_'},
    MutableList(3) {'_'}
)

fun main() {

    var player = 'O'

    printField()

    while (true) {
        player = if (player == 'X') 'O' else 'X'
        makeMove(player)
        printField()
        if (checkField()) break
    }
}

fun checkForWin(char: Char): Boolean {
    for (row in field) {
        if (row[0] == row[1] && row[0] == row[2] && row[0] == char) return true
    }
    for (i in 0..2) {
        if (field[0][i] == field[1][i] && field[0][i] == field[2][i] && field[0][i] == char) return true
    }
    if (field[0][0] == field[1][1] && field[0][0] == field[2][2] && field[0][0] == char) return true
    if (field[0][2] == field[1][1] && field[0][2] == field[2][0] && field[0][2] == char) return true

    return false
}

fun checkField(): Boolean {
    val emptySpace = field.joinToString().contains('_')
    val xWins = checkForWin('X')
    val oWins = checkForWin('O')
    when {
        !emptySpace && !xWins && !oWins -> {
            println("Draw")
            return true
        }
        xWins -> {
            println("X wins")
            return true
        }
        oWins -> {
            println("O wins")
            return true
        }
    }
    return false
}

fun printField() {
    println("---------")
    for (row in field) {
        print("| ")
        print(row.joinToString(" "))
        print(" |\n")
    }
    println("---------")
}

fun makeMove(player: Char) {
    var coordinates: List<Int>

    while (true) {
        print("Enter the coordinates: ")
        try {
            coordinates = readLine()!!.split(" ").map { it.toInt() }.toList()
        } catch (e: NumberFormatException) {
            println("You should enter numbers!")
            continue
        }

        if (coordinates[0] !in 1..3 || coordinates[1] !in 1..3) {
            println("Coordinates should be from 1 to 3!")
            continue
        }

        if (field[coordinates[0] - 1][coordinates[1] - 1] != '_') {
            println("This cell is occupied!")
            continue
        }

        field[coordinates[0] - 1][coordinates[1] - 1] = player
        break
    }
}
