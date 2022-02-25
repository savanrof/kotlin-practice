package util

import dto.Person

class AlgorithmUtil {
    fun quickSort(unSortedlist: MutableList<Person>, left: Int, right: Int, expr: String): Unit {
        if (unSortedlist.size < 2 || unSortedlist.size == 0) { unSortedlist }
        if (left >= right) { return }
        val middle = left + (right - left) /2
        val pivot = unSortedlist[middle]
        var i = left
        var j = right
        while (i <= j) {
            if (expr == "name") {
                while (unSortedlist[i].name!! < pivot.name!!) {
                    i++
                }
                while (unSortedlist[j].name!! > pivot.name!!) {
                    j--
                }
            }

            if (expr == "birthDate") {
                while (unSortedlist[i].birthDate!! < pivot.birthDate!!) {
                    i++
                }
                while (unSortedlist[j].birthDate!! > pivot.birthDate!!) {
                    j--
                }
            }

            if (i <= j) {
                unSortedlist[i] = unSortedlist[j].also { unSortedlist[j] = unSortedlist[i]}
                i++
                j--
            }
        }
        if ( left < j) { quickSort(unSortedlist, left, i, expr)}
        if ( right > i) { quickSort(unSortedlist, i, right, expr)}
    }

}