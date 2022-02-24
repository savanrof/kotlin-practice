package dto

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.util.UUID

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PersonTest {
    private lateinit var fixedId: String
    private lateinit var personList: MutableList<Person>
    private val person1 = Person(name="ABC", gender = Gender.MALE, birthDate = "2000-02-03")
    private val person2 = Person(name="XYZ", gender = Gender.FEMALE, birthDate = "1800-07-08")

    @BeforeAll
    private fun beforeAll() {
        fixedId = UUID.randomUUID().toString()
        personList = mutableListOf(
            Person(name="Test1", gender = Gender.MALE, birthDate = "1990-01-02"),
            Person(name="Test2", gender = Gender.MALE, birthDate = "1991-02-03"),
            person1,
            Person(name="Test3", gender = Gender.FEMALE, birthDate = "1992-03-04"),
            Person(name="Test4", gender = Gender.FEMALE, birthDate = "1994-05-06"),
            Person(name="Test5", gender = Gender.FEMALE, birthDate = "1995-06-07"),
            person2,
            Person(name="Test6", gender = Gender.MALE, birthDate = "1996-07-08"),
            Person(name="Test7", gender = Gender.FEMALE, birthDate = "1998-09-10")
        )
    }

    @AfterAll
    private fun afterAll() {
        fixedId = ""
        personList.clear()
    }

    @Test
    fun `Test find person by ID`() {
        val personExpect = Person(id = fixedId, name="Test8", gender = Gender.MALE, birthDate = "1993-04-05")
        personList.add(personExpect)
        val personActual = personList.findPersonById(id = fixedId)
        personExpect shouldBe personActual
    }

    @Test
    fun `Test find person by ID should return null`() {
        val id = "abc-xyz"
        val personActual = personList.findPersonById(id = id)
        personActual shouldBe null
    }

    @Test
    fun `Test collect person by gender`() {
        val personListExpect = arrayOf<Person>(
            Person(name="Test9", gender = Gender.OTHER, birthDate = "1997-08-09"),
            Person(name="Test10", gender = Gender.OTHER, birthDate = "1999-11-12"),
        )
        personList.addAll(personListExpect)
        val personListActual = personList.collectByGender(Gender.OTHER)
        personListExpect shouldBe personListActual
    }

    @Test
    fun `Test sort by name`() {
        val personListActual = personList.sortByNameOrBirthDate("name")
        personListActual[0] shouldBe person1
        personListActual.last() shouldBe person2
    }

    @Test
    fun `Test sort by birthdate`() {
        val personListActual = personList.sortByNameOrBirthDate("birthDate")
        personListActual[0] shouldBe person2
        personListActual.last() shouldBe person1
    }

    @Test
    fun `Test default sort`() {
        val personListExpect = personList.toMutableList()
        val personListActual = personList.sortByNameOrBirthDate(null)
        personListExpect shouldBe personListActual
    }
}