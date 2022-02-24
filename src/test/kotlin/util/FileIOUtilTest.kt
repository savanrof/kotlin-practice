package util

import dto.Gender
import dto.Person
import io.kotest.assertions.throwables.shouldThrow
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.*
import kotlin.test.assertFailsWith

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FileIOUtilTest {

 private lateinit var personList: MutableList<Person>
 private val errList = mutableListOf<Person>()
 private val mock = mockk<FileIOUtil>()

 @BeforeAll
 private fun beforeAll() {
  personList = mutableListOf(
   Person(name="Test1", gender = Gender.MALE, birthDate = "1990-01-02"),
   Person(name="Test2", gender = Gender.MALE, birthDate = "1991-02-03")
  )
 }

 @AfterAll
 private fun afterAll() {
  personList.clear()
 }

 @Test
 fun `Test write json file`() {
  every { mock.writeToJsonFile(personList,"write") } returns Unit
  mock.writeToJsonFile(personList,"write")
  verify { mock.writeToJsonFile(personList,"write") }

  shouldThrow<java.lang.Exception> {
   every { mock.writeToJsonFile(errList,"Exception") }.throws(java.lang.Exception())
   mock.writeToJsonFile(errList,"Exception")
  }
 }

 @Test
 fun `Test read json file`() {
  every { mock.readFromJsonFile("read") } returns Unit
  mock.readFromJsonFile("read")
  verify { mock.readFromJsonFile("read") }

  shouldThrow<java.lang.Exception> {
   every { mock.readFromJsonFile("readfileException") }.throws(java.lang.Exception())
   mock.readFromJsonFile("readfileException")
  }
 }

}