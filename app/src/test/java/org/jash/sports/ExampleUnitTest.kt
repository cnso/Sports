package org.jash.sports

import kotlinx.coroutines.runBlocking
import org.jash.common.utils.retrofit
import org.jash.sports.net.Service
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
    @Test
    fun testNetwork() {
        val service = retrofit.create(Service::class.java)
        runBlocking {
            var res = service.getNewsByPage(4, 1, 2)
            println(res)
        }
    }
}