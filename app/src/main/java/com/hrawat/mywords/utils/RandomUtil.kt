package com.hrawat.mywords.utils

import com.hrawat.mywords.model.Puzzle
import java.util.*

/**
 * Created by hrawat on 12/25/2017.
 */
class RandomUtil {
    val randomWords = arrayListOf('Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P', 'A', 'S', 'D', 'F', 'G', 'H'
            , 'J', 'K', 'L', 'Z', 'X', 'C', 'V', 'B', 'N', 'M')
    val random = Random()

    val words = arrayListOf("FORD", "MARUTI", "JEEP", "INTEL", "APPLE", "STRING", "SIMPLE", "GAME", "CARD", "REALITY")

    val style = arrayListOf("VERTICAL", "HORIZONTAL", "IN-VERTICAL")


    private var puzzleList = ArrayList<Puzzle>()
    private lateinit var wordPosList: MutableList<Int>

    fun getRandomDigit(): ArrayList<Puzzle> {
        val randomList = ArrayList<Puzzle>()
        var i = 0
        while (i < 49) {
            randomList.add(Puzzle(randomWords[random.nextInt(25)].toString(), i, false))
//            val randomNum = ThreadLocalRandom.current().nextInt(65, 90 + 1)
//            randomList.add(Character.toString(randomNum.toChar()))
            i++
        }
        return randomList

    }


    fun getRandomArray(randomWord: String): ArrayList<Puzzle> {
        val randomList = ArrayList<Puzzle>()
        var i = 0
        while (i < 49) {
            randomList.add(Puzzle(randomWords[random.nextInt(25)].toString(), i, false))
            i++
        }
        return hideWordinArray(randomList, randomWord)

    }


    private fun hideWordinArray(randomList: ArrayList<Puzzle>, word: String): ArrayList<Puzzle> {

        val length = word.length

        val randomPos = getRandomPosition()

        val randomStyle= style[random.nextInt(style.size)]

        when(randomStyle)
        {
             "VERTICAL"->{
                 var colomn = 0
                 if (randomPos < 6)
                     colomn = 1
                 else
                     colomn = randomPos / 7
                 if (7 - colomn >= length) {
                   return addWordsVertical(randomPos, word,randomList)
                 } else
                     hideWordinArray(randomList,word)

             }
            "HORIZONTAL"->{
                val row = randomPos % 7
                //check horizontal
                val size = 7 - row
                if (size >= length) {
                 return  addWordsHorizontal(randomPos,word,randomList)
                } else
                  hideWordinArray(randomList,word)
            }
            "IN-VERTICAL"->
            {
                var colomn = 0
                colomn = if (randomPos < 6)
                    1
                else
                    randomPos / 7
                if (7 - colomn >= length) {
                    return addWordsVerticalInverse(randomPos, word,randomList)
                } else
                    hideWordinArray(randomList,word)

            }
        }

//        var row = randomPos % 7
//        //check horizontal
//        var size = 7 - row
//        if (size >= length) {
//            Toast.makeText(this@PuzzleActivity, "horizontal success at " + randomPos, Toast.LENGTH_SHORT).show()
//        } else
//            Toast.makeText(this@PuzzleActivity, "horizontal fail at " + randomPos, Toast.LENGTH_SHORT).show()
//        //check vertical
//        var colomn = 0
//        if (randomPos < 6)
//            colomn = 1
//        else
//            colomn = randomPos / 7
//        if (7 - colomn >= length) {
//            puzzleAdapter.addWordsVertically(randomPos, word)
//            Toast.makeText(this@PuzzleActivity, "vertical success at " + randomPos, Toast.LENGTH_SHORT).show()
//        } else
//            Toast.makeText(this@PuzzleActivity, "vertical fail at " + randomPos, Toast.LENGTH_SHORT).show()
//
//        return randomList;
       return hideWordinArray(randomList,word)

    }

    fun getRandomPosition(): Int {
        return random.nextInt(49)
    }


    fun getRandomWord(): String {

        return words[random.nextInt(words.size)]
    }


    private fun addWordsHorizontal(positon: Int, wordList: String, randomList:ArrayList<Puzzle>): ArrayList<Puzzle> {

        var firstPosition = positon
        wordPosList = arrayListOf()
        for (char in wordList.toCharArray().asList()) {
            randomList[firstPosition]= Puzzle(char.toString(), firstPosition, false)
            wordPosList.add(firstPosition)
            firstPosition += 1
        }
        return randomList
    }


    private fun addWordsVertical(positon: Int, wordList: String, randomList: ArrayList<Puzzle>): ArrayList<Puzzle> {

        var firstPosition = positon
        wordPosList = arrayListOf()
        for (char in wordList.toCharArray().asList()) {
            randomList[firstPosition] = Puzzle(char.toString(), firstPosition, false)
            wordPosList.add(firstPosition)
            firstPosition += 7
        }
        return randomList
    }

    private fun addWordsVerticalInverse(positon: Int, wordList: String, randomList: ArrayList<Puzzle>): ArrayList<Puzzle> {

        var lastPosition = positon + (7 * (wordList.length - 1))
        wordPosList = arrayListOf()
        for (char in wordList.toCharArray().asList()) {
            randomList[lastPosition] = Puzzle(char.toString(), lastPosition, false)
            wordPosList.add(lastPosition)
            lastPosition -= 7
        }
        return randomList
    }


     fun getWordPositionList():MutableList<Int>
    {
        return wordPosList
    }

}