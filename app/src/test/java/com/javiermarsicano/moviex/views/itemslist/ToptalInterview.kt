package main

import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Test

/**

 */

class SolutionTest {
    @Test
    fun `test simple case`() {
        val array = listOf(1, 1, 1, 1, 1, 1)

        val result = LoadBalancer.solution(array.toIntArray())

        assertFalse(result)
    }

    @Test
    fun `test complex case`() {
        val array = listOf(5,0,9,2,2,1,9,1,1,2,1)

        val result = LoadBalancer.solution(array.toIntArray())

        assertTrue(result)
    }

   }

object LoadBalancer {
    fun solution(A: IntArray): Boolean {
        return false
    }
}

/**
Expected time:
My time:
 Computational complexity performance:

 **/


