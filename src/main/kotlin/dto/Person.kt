package dto

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.UUID
private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

fun List<Person>.findPersonById (id: String): Person? =
    this.find { it.id == id }

fun List<Person>.collectByGender (gender: Gender): List<Person> =
    this.filter { it.gender == gender }

fun List<Person>.sortByNameOrBirthDate(expr: String?): List<Person> = when(expr) {
    "name" -> this.sortedWith(compareBy(Person::name))
    "birthDate" -> this.sortedWith(compareBy(Person::birthDate))
    else -> this
}

data class Person(
    var id: String = UUID.randomUUID().toString(),
    val name: String? = null,
    val gender: Gender,
    val birthDate: LocalDate? = null
) {
    constructor(id: String = UUID.randomUUID().toString(), name: String, gender: Gender, birthDate: String) : this(id, name, gender, LocalDate.parse(birthDate, formatter))
}


