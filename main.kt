package chess

import kotlin.math.abs

fun gameSetUp():List<String>{
    println("Pawns-Only Chess")
    val playerNames: MutableList<String> = mutableListOf("","")
    println("First Player's name:")
    playerNames[0] = readln()
    println("Second Player's name:")
    playerNames[1] = readln()
    println("""
      +---+---+---+---+---+---+---+---+
    8 |   |   |   |   |   |   |   |   |
      +---+---+---+---+---+---+---+---+
    7 | B | B | B | B | B | B | B | B |
      +---+---+---+---+---+---+---+---+
    6 |   |   |   |   |   |   |   |   |
      +---+---+---+---+---+---+---+---+
    5 |   |   |   |   |   |   |   |   |
      +---+---+---+---+---+---+---+---+
    4 |   |   |   |   |   |   |   |   |
      +---+---+---+---+---+---+---+---+
    3 |   |   |   |   |   |   |   |   |
      +---+---+---+---+---+---+---+---+
    2 | W | W | W | W | W | W | W | W |
      +---+---+---+---+---+---+---+---+
    1 |   |   |   |   |   |   |   |   |
      +---+---+---+---+---+---+---+---+
        a   b   c   d   e   f   g   h
        """.trimIndent())

    return playerNames
}
fun printGameState(gameState: MutableList<MutableList<Char>>) {
    println("""
      +---+---+---+---+---+---+---+---+
    8 | ${gameState[7][0]} | ${gameState[7][1]} | ${gameState[7][2]} | ${gameState[7][3]} | ${gameState[7][4]} | ${gameState[7][5]} | ${gameState[7][6]} | ${gameState[7][7]} |
      +---+---+---+---+---+---+---+---+
    7 | ${gameState[6][0]} | ${gameState[6][1]} | ${gameState[6][2]} | ${gameState[6][3]} | ${gameState[6][4]} | ${gameState[6][5]} | ${gameState[6][6]} | ${gameState[6][7]} |
      +---+---+---+---+---+---+---+---+
    6 | ${gameState[5][0]} | ${gameState[5][1]} | ${gameState[5][2]} | ${gameState[5][3]} | ${gameState[5][4]} | ${gameState[5][5]} | ${gameState[5][6]} | ${gameState[5][7]} |
      +---+---+---+---+---+---+---+---+
    5 | ${gameState[4][0]} | ${gameState[4][1]} | ${gameState[4][2]} | ${gameState[4][3]} | ${gameState[4][4]} | ${gameState[4][5]} | ${gameState[4][6]} | ${gameState[4][7]} |
      +---+---+---+---+---+---+---+---+
    4 | ${gameState[3][0]} | ${gameState[3][1]} | ${gameState[3][2]} | ${gameState[3][3]} | ${gameState[3][4]} | ${gameState[3][5]} | ${gameState[3][6]} | ${gameState[3][7]} |
      +---+---+---+---+---+---+---+---+
    3 | ${gameState[2][0]} | ${gameState[2][1]} | ${gameState[2][2]} | ${gameState[2][3]} | ${gameState[2][4]} | ${gameState[2][5]} | ${gameState[2][6]} | ${gameState[2][7]} |
      +---+---+---+---+---+---+---+---+
    2 | ${gameState[1][0]} | ${gameState[1][1]} | ${gameState[1][2]} | ${gameState[1][3]} | ${gameState[1][4]} | ${gameState[1][5]} | ${gameState[1][6]} | ${gameState[1][7]} |
      +---+---+---+---+---+---+---+---+
    1 | ${gameState[0][0]} | ${gameState[0][1]} | ${gameState[0][2]} | ${gameState[0][3]} | ${gameState[0][4]} | ${gameState[0][5]} | ${gameState[0][6]} | ${gameState[0][7]} |
      +---+---+---+---+---+---+---+---+
        a   b   c   d   e   f   g   h
        """.trimIndent())
}
fun letterIntoNumber(userInput: String): MutableList<Int> {
    val convertedLetters: MutableList<Int> = mutableListOf(0,0)
    if (userInput[0].equals('a')) convertedLetters[0] = 0
    else if (userInput[0].equals('b')) convertedLetters[0] = 1
    else if (userInput[0].equals('c')) convertedLetters[0] = 2
    else if (userInput[0].equals('d')) convertedLetters[0] = 3
    else if (userInput[0].equals('e')) convertedLetters[0] = 4
    else if (userInput[0].equals('f')) convertedLetters[0] = 5
    else if (userInput[0].equals('g')) convertedLetters[0] = 6
    else if (userInput[0].equals('h')) convertedLetters[0] = 7

    if (userInput[2].equals('a')) convertedLetters[1] = 0
    else if (userInput[2].equals('b')) convertedLetters[1] = 1
    else if (userInput[2].equals('c')) convertedLetters[1] = 2
    else if (userInput[2].equals('d')) convertedLetters[1] = 3
    else if (userInput[2].equals('e')) convertedLetters[1] = 4
    else if (userInput[2].equals('f')) convertedLetters[1] = 5
    else if (userInput[2].equals('g')) convertedLetters[1] = 6
    else if (userInput[2].equals('h')) convertedLetters[1] = 7

    return convertedLetters
}
fun drawChecker (gameState: MutableList<MutableList<Char>>,blackPawns: Int, whitePawns: Int, firstPlayer: Boolean): Boolean {
    var draw: Boolean = false
    var invalidMovesW: Int = 0
    var invalidMovesB: Int = 0

    Loop@for (i in 0 until gameState.size){
        for (r in 0 until gameState[i].size){
            if (gameState[i][r] == 'W' && firstPlayer){
                if (i+1<8) {
                    if (gameState[i+1][r] != ' '){
                        if(r+1 != gameState[i].size && r-1 > 0){
                            if (gameState[i+1][r+1] != 'B' && gameState[i+1][r-1] != 'B'){
                                ++invalidMovesW
                                if (invalidMovesW == whitePawns) {
                                    draw = true
                                    break@Loop
                                }
                            }
                        } else if (r+1 != gameState[i].size){
                            if (gameState[i+1][r+1] != 'B'){
                                ++invalidMovesW
                                if (invalidMovesW == whitePawns) {
                                    draw = true
                                    break@Loop
                                }
                            }
                        } else if (r-1 > 0){
                            if (gameState[i+1][r-1] != 'B'){
                                ++invalidMovesW
                                if (invalidMovesW == whitePawns) {
                                    draw = true
                                    break@Loop
                                }
                            }
                            }
                        }
                    }
                }
             else if (gameState[i][r] == 'B' && !firstPlayer) {
                if (i - 1 > -1){
                    if (gameState[i-1][r] != ' '){
                        if(r+1 != gameState[i].size && r-1 > 0){
                            if (gameState[i-1][r+1] != 'W' && gameState[i-1][r-1] != 'W'){
                                ++invalidMovesB
                                if (invalidMovesB == blackPawns) {
                                    draw = true
                                    break@Loop
                                }
                            }
                        } else if (r-1 > 0) {
                            if (gameState[i-1][r-1] != 'W'){
                                ++invalidMovesB
                                if (invalidMovesB == blackPawns) {
                                    draw = true
                                    break@Loop
                                }
                            }
                        } else if (r+1 !=gameState[i].size){
                            if (gameState[i-1][r+1] != 'W'){
                                ++invalidMovesB
                                if (invalidMovesB == blackPawns) {
                                    draw = true
                                    break@Loop
                            }
                        }
                    }
                }
            }
        }
    }
    }
   // println("Draw: $draw")
    return draw

}
fun winByEndOfBoard(gameState: MutableList<MutableList<Char>>): String {
    var winner: String = ""
    for (i in 0 until gameState[0].size){
        if (gameState[0][i] == 'B'){
            winner = "Black"
        }
    }
    for(i in 0 until gameState[7].size){
        if (gameState[7][i] == 'W'){
            winner = "White"
        }
    }
    return winner
}
fun gameResult(blackPawns: Int,whitePawns: Int,draw: Boolean, winner:String ): Boolean {
    var gameOver: Boolean = false
    if (whitePawns == 0 || winner == "Black") {
        println("Black wins!")
        println("Bye!")
        gameOver = true
    } else if (blackPawns == 0 || winner == "White") {
        println("White wins!")
        println("Bye!")
        gameOver = true
    } else if (draw) {
        println("Stalemate!")
        println("Bye!")
        gameOver = true
    }
   // println("Gameover: $gameOver")
    return gameOver
}

fun playersTurn (playerNames: List<String>, gameState: MutableList<MutableList<Char>>) {

    var turn: Int = 1
    var userInput: String
    val conformInput: Regex = "[a-hA-H][1-8][a-hA-H][1-8]".toRegex()
    var whitePawns: Int = 8
    var blackPawns: Int = 8
    var convertedLetters: MutableList<Int> = mutableListOf()
    var enPCoordsBlack: MutableList<Int> = mutableListOf(0,0)
    var enPCoordsWhite: MutableList<Int> = mutableListOf(0,0)
    var gameExit: Boolean = false
    Loop@ while (!gameExit) {
        if (turn % 2 == 0) {
            gameExit= gameResult(blackPawns,whitePawns, drawChecker(gameState,blackPawns,whitePawns,false),
                winByEndOfBoard(gameState))
            if (gameExit) break@Loop
            println("${playerNames[1]}'s turn:")
            userInput = readln()
            if (userInput == "exit") {
                gameExit = true
                println("Bye!")
                break@Loop
            }
            if (userInput.matches(conformInput)) {
                convertedLetters = letterIntoNumber(userInput)
                if (gameState[(userInput[1].toString().toInt()-1)][convertedLetters[0]] == 'B') {
                    if (gameState[(userInput[3].toString().toInt()-1)][convertedLetters[1]] == ' ') {
                        if (userInput[3].toString().toInt() - userInput[1].toString().toInt() == -1 && userInput[0] == userInput[2]){
                            gameState[(userInput[3].toString().toInt()-1)][convertedLetters[1]] = 'B'
                            gameState[(userInput[1].toString().toInt()-1)][convertedLetters[0]] = ' '
                            ++turn
                            enPCoordsWhite = mutableListOf(0,0)
                            printGameState(gameState)
                            gameExit = gameResult(blackPawns,whitePawns, drawChecker(gameState,blackPawns,whitePawns,false),
                                winByEndOfBoard(gameState))
                        } else if (userInput[3].toString().toInt() - userInput[1].toString().toInt() == -2 && userInput[0] == userInput[2] && userInput[1].toString().toInt() == 7) {
                            gameState[(userInput[3].toString().toInt()-1)][convertedLetters[1]] = 'B'
                            gameState[(userInput[1].toString().toInt()-1)][convertedLetters[0]] = ' '
                            enPCoordsBlack[0] = userInput[3].toString().toInt()+1
                            enPCoordsBlack[1] = convertedLetters[1]
                            ++turn
                            enPCoordsWhite = mutableListOf(0,0)
                            printGameState(gameState)
                            gameExit = gameResult(blackPawns,whitePawns, drawChecker(gameState,blackPawns,whitePawns,false),
                                winByEndOfBoard(gameState))
                        } else if ((userInput[3].toString().toInt()-1) == enPCoordsWhite[0] &&  convertedLetters[1] == enPCoordsWhite[1] && abs(convertedLetters[0]-convertedLetters[1]) == 1) {
                            --whitePawns
                            gameState[(userInput[1].toString().toInt()-1)][convertedLetters[0]] = ' '
                            gameState[(userInput[3].toString().toInt()-1)][convertedLetters[1]] = 'B'
                            gameState[(userInput[3].toString().toInt())][convertedLetters[1]] = ' '
                            ++turn
                            enPCoordsWhite = mutableListOf(0,0)
                            printGameState(gameState)
                            gameExit = gameResult(blackPawns,whitePawns, drawChecker(gameState,blackPawns,whitePawns,false),
                                winByEndOfBoard(gameState))
                        } else {
                           // println("${(userInput[3].toString().toInt()) == enPCoordsWhite[0] }, ${convertedLetters[1] == enPCoordsWhite[1]}, ${abs(convertedLetters[0]-convertedLetters[1]) == 1} $enPCoordsWhite, ${userInput[3].toString().toInt()}, $userInput")
                            println("Invalid Input")
                        }

                    } else if (gameState[(userInput[3].toString().toInt()-1)][convertedLetters[1]] == 'W' && abs(convertedLetters[0]-convertedLetters[1]) == 1) {
                        --whitePawns
                        gameState[(userInput[1].toString().toInt()-1)][convertedLetters[0]] = ' '
                        gameState[(userInput[3].toString().toInt()-1)][convertedLetters[1]] = 'B'
                        ++turn
                        enPCoordsWhite = mutableListOf(0,0)
                        printGameState(gameState)
                        gameExit = gameResult(blackPawns,whitePawns, drawChecker(gameState,blackPawns,whitePawns,false),
                            winByEndOfBoard(gameState))
                    } else{
                       // println("${(userInput[3].toString().toInt()-1) == enPCoordsWhite[0] }, ${convertedLetters[1] == enPCoordsWhite[1]}, ${abs(convertedLetters[0]-convertedLetters[1]) == 1} $enPCoordsWhite")
                        println("Invalid Input")
                    }


                } else println("No black pawn at ${userInput[0]}${userInput[1]}")

            } else {
               // println("${(userInput[3].toString().toInt()-1) == enPCoordsWhite[0] }, ${convertedLetters[1] == enPCoordsWhite[1]}, ${abs(convertedLetters[0]-convertedLetters[1]) == 1} $enPCoordsWhite")
                println("Invalid Input")
            }


        } else if (turn % 2 != 0) {
            gameExit= gameResult(blackPawns,whitePawns, drawChecker(gameState,blackPawns,whitePawns,true),
                winByEndOfBoard(gameState))
            if (gameExit) break@Loop
            println("${playerNames[0]}'s turn:")
            userInput = readln()
            if (userInput == "exit") {
                gameExit = true
                println("Bye!")
                break@Loop
            }
            if (userInput.matches(conformInput)) {
                convertedLetters = letterIntoNumber(userInput)
                if (gameState[(userInput[1].toString().toInt() - 1)][convertedLetters[0]] == 'W') {
                    if (gameState[(userInput[3].toString().toInt() - 1)][convertedLetters[1]] == ' ') {
                        if (userInput[3].toString().toInt() - userInput[1].toString().toInt() == 1 && userInput[0] == userInput[2]) {
                            gameState[(userInput[3].toString().toInt() - 1)][convertedLetters[1]] = 'W'
                            gameState[(userInput[1].toString().toInt() - 1)][convertedLetters[0]] = ' '
                            ++turn
                            enPCoordsBlack = mutableListOf(0,0)
                            printGameState(gameState)
                            gameExit = gameResult(blackPawns,whitePawns, drawChecker(gameState,blackPawns,whitePawns,true),
                                winByEndOfBoard(gameState))
                        } else if (userInput[3].toString().toInt() - userInput[1].toString().toInt() == 2 && userInput[0] == userInput[2] && userInput[1].toString().toInt() == 2) {
                            gameState[(userInput[3].toString().toInt() - 1)][convertedLetters[1]] = 'W'
                            gameState[(userInput[1].toString().toInt() - 1)][convertedLetters[0]] = ' '
                            ++turn
                            enPCoordsBlack = mutableListOf(0,0)
                            enPCoordsWhite[0] = userInput[3].toString().toInt() - 2
                            enPCoordsWhite[1] = convertedLetters[1]
                            printGameState(gameState)
                            gameExit = gameResult(blackPawns,whitePawns, drawChecker(gameState,blackPawns,whitePawns,true),
                                winByEndOfBoard(gameState))
                        } else if ((userInput[3].toString().toInt()) == enPCoordsBlack[0] &&  convertedLetters[1] == enPCoordsBlack[1] && abs(convertedLetters[0]-convertedLetters[1]) == 1) {
                        --blackPawns
                        gameState[(userInput[1].toString().toInt()-1)][convertedLetters[0]] = ' '
                        gameState[(userInput[3].toString().toInt()-1)][convertedLetters[1]] = 'W'
                        gameState[(userInput[3].toString().toInt()-2)][convertedLetters[1]] = ' '
                        ++turn
                        enPCoordsBlack = mutableListOf(0,0)
                        printGameState(gameState)
                            gameExit = gameResult(blackPawns,whitePawns, drawChecker(gameState,blackPawns,whitePawns,true),
                                winByEndOfBoard(gameState))
                        } else {
                            //println("${(userInput[3].toString().toInt()) == enPCoordsBlack[0]}, ${convertedLetters[1] == enPCoordsBlack[1]}, ${abs(convertedLetters[0]-convertedLetters[1]) == 1}, $enPCoordsBlack ")
                            println("Invalid Input")
                        }


                    } else if (gameState[(userInput[3].toString().toInt()-1)][convertedLetters[1]] == 'B' && abs(convertedLetters[0]-convertedLetters[1]) == 1) {
                        --blackPawns
                        gameState[(userInput[1].toString().toInt()-1)][convertedLetters[0]] = ' '
                        gameState[(userInput[3].toString().toInt()-1)][convertedLetters[1]] = 'W'
                        ++turn
                        enPCoordsBlack = mutableListOf(0,0)
                        printGameState(gameState)
                        gameExit = gameResult(blackPawns,whitePawns, drawChecker(gameState,blackPawns,whitePawns,true),
                            winByEndOfBoard(gameState))
                    } else{
                        //println("${(userInput[3].toString().toInt()-1) == enPCoordsBlack[0]}, ${convertedLetters[1] == enPCoordsBlack[1]}, ${abs(convertedLetters[0]-convertedLetters[1]) == 1}, $enPCoordsBlack ")
                        println("Invalid Input")
                    }
                } else {
                    println("No white pawn at ${userInput[0]}${userInput[1]}")
                }
            } else{
                //println("${(userInput[3].toString().toInt()-1) == enPCoordsBlack[0]}, ${convertedLetters[1] == enPCoordsBlack[1]}, ${abs(convertedLetters[0]-convertedLetters[1]) == 1}, $enPCoordsBlack ")
                println("Invalid Input")
            }

        }

    }
}


fun multiDimensionalListSetup(): MutableList<MutableList<Char>> {
    val gameState: MutableList<MutableList<Char>> = mutableListOf(
            mutableListOf(' ',' ',' ',' ',' ',' ',' ',' '),
            mutableListOf('W','W','W','W','W','W','W','W'),
            mutableListOf(' ',' ',' ',' ',' ',' ',' ',' '),
            mutableListOf(' ',' ',' ',' ',' ',' ',' ',' '),
            mutableListOf(' ',' ',' ',' ',' ',' ',' ',' '),
            mutableListOf(' ',' ',' ',' ',' ',' ',' ',' '),
            mutableListOf('B','B','B','B','B','B','B','B'),
            mutableListOf(' ',' ',' ',' ',' ',' ',' ',' '),
    )
    return gameState
}
fun main() {
    val gameState: MutableList<MutableList<Char>> = multiDimensionalListSetup()
    val playerNames: List<String> = gameSetUp()

    playersTurn(playerNames,gameState)

}
