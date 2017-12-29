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

    val words = arrayListOf("FORD", "MARUTI", "JEEP", "INTEL", "APPLE", "STRING", "SIMPLE", "GAME","CARD","REALITY")

    fun getRandomDigit(): ArrayList<Puzzle> {
        val randomList = ArrayList<Puzzle>()
        var i = 0
        while (i < 49) {
            randomList.add(Puzzle(randomWords[random.nextInt(25)].toString(),i,false))
//            val randomNum = ThreadLocalRandom.current().nextInt(65, 90 + 1)
//            randomList.add(Character.toString(randomNum.toChar()))
            i++
        }
        return randomList

    }


    fun getRandomPosition(): Int{
        return random.nextInt(49)
    }


    fun getRandomWord(): String {

        return words[random.nextInt(words.size)]
    }

}