package net.roadtoagility.dflow4k

import org.testng.annotations.DataProvider
import kotlin.test.assertEquals

open class VersionIdBaseTest {
    @DataProvider(name = "versionIdValueData")
    fun versionIdValueData(): MutableIterator<Array<Long>> {
        val data: ArrayList<Array<Long>> = arrayListOf()

        data.add(arrayOf(1L,1L))
        data.add(arrayOf(100L,100L))
        data.add(arrayOf(10000000L,10000000L))

        return data.iterator()
    }

    @DataProvider(name = "versionIdToStringData")
    fun versionIdToStringData(): MutableIterator<Array<Any>> {
        val data: ArrayList<Array<Any>> = arrayListOf()

        val case1 = arrayOf<Any>(1L, "1")
        val case2 = arrayOf<Any>(3L, "3")

        data.add(case1)
        data.add(case2)

        return data.iterator()
    }

    @DataProvider(name = "versionIdValidData")
    fun versionIdValidData(): MutableIterator<Array<VersionId>> {
        val data: ArrayList<Array<VersionId>> = arrayListOf()

        val case1 = arrayOf(VersionId(1L), VersionId(1L))
        val case2 = arrayOf(VersionId(3L), VersionId(3L))

        data.add(case1)
        data.add(case2)

        return data.iterator()
    }

    @DataProvider(name = "versionIdNextData")
    fun versionIdNextData(): MutableIterator<Array<VersionId>> {
        val data: ArrayList<Array<VersionId>> = arrayListOf()

        val case1 = arrayOf(VersionId(1L), VersionId(2L))
        val case2 = arrayOf(VersionId(3L), VersionId(4L))

        data.add(case1)
        data.add(case2)

        return data.iterator()
    }
}

internal class VersionIdTest: VersionIdBaseTest() {

    @org.testng.annotations.Test(dataProvider = "versionIdValueData", groups = ["version-id"])
    fun testGetValue(version: Long, expected: Long) {
        val id = VersionId(version)
        assertEquals(expected, id.value)
    }

    @org.testng.annotations.Test(dataProvider = "versionIdValidData", groups = ["version-id"])
    fun testCopy(input: VersionId, expected: VersionId) {
        val copy = input.copy()
        assertEquals(expected, copy)
    }

    @org.testng.annotations.Test(dataProvider = "versionIdToStringData", groups = ["version-id"])
    fun testToString(input:Long, expected:String) {
        val version = VersionId(input)
        assertEquals(expected, version.toString())
    }

    @org.testng.annotations.Test(dataProvider = "versionIdValidData", groups = ["version-id"])
    fun testHashCode(input:VersionId, expected: VersionId) {
        assertEquals(expected.hashCode(), input.hashCode())
    }

    @org.testng.annotations.Test(dataProvider = "versionIdValidData", groups = ["version-id"])
    fun testEquals(input:VersionId, expected: VersionId) {
        assertEquals(expected, input)
    }

    @org.testng.annotations.Test(dataProvider = "versionIdNextData", groups = ["version-id"])
    fun testNext(input:VersionId, expected: VersionId) {
        val next = input.next()
        assertEquals(expected, next)
    }
}