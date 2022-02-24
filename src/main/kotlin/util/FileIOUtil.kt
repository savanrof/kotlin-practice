package util

import dto.Person
import org.json.JSONArray
import java.io.FileWriter
import java.io.PrintWriter
import java.nio.charset.Charset
import org.json.JSONException
import org.json.JSONObject
import java.io.File

open class FileIOUtil {
    private val currentDirectory: String = System.getProperty("user.dir")
    fun writeToJsonFile(personList: List<Person>, fileName: String) {
        val path = "$currentDirectory/$fileName.json"
        val json = JSONArray()
        try {
            personList.forEach{
                val inner = JSONObject()
                inner.put("id", it.id )
                inner.put("name", it.name)
                inner.put("gender", it.gender)
                inner.put("birthDate", it.birthDate)
                json.put(inner)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        try {
            PrintWriter(FileWriter(path, Charset.defaultCharset()))
                .use { it.write(json.toString()) }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        finally {
            println("______JSON file write completed______")
        }
    }

    fun readFromJsonFile(fileName: String) {
        try {
            val fileName = "$currentDirectory/$fileName.json"
            var lines: List<String> = File(fileName).readLines()
            lines.forEach {line -> println(line) }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        } finally {
            println("______JSON file read completed______")
        }
    }
}