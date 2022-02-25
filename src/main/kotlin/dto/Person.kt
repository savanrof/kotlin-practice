package dto

import util.AlgorithmUtil
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.UUID
private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

fun MutableList<Person>.findPersonById (id: String): Person? =
    this.find { it.id == id }

fun MutableList<Person>.collectByGender (gender: Gender): MutableList<Person> =
    this.filter { it.gender == gender }.toMutableList()

fun MutableList<Person>.sortByNameOrBirthDate(expr: String?): MutableList<Person> {
    if (expr == null) { this }
    else {
        var left = 0
        var right = this.size - 1
        AlgorithmUtil().quickSort(this, left, right, expr!!)
    }
    return this
}

data class Person(
    var id: String = UUID.randomUUID().toString(),
    val name: String? = null,
    val gender: Gender,
    val birthDate: LocalDate? = null
) {
    constructor(id: String = UUID.randomUUID().toString(), name: String, gender: Gender, birthDate: String) : this(id, name, gender, LocalDate.parse(birthDate, formatter))
}


