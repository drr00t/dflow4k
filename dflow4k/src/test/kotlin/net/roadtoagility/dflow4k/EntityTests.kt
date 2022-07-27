package net.roadtoagility.dflow4k

import org.testng.annotations.DataProvider
import kotlin.test.assertEquals

/** Tests data provider for Entity. */
open class EntityBaseTest {
    @DataProvider(name = "entityTestData")
    fun entityTestData(): MutableIterator<Array<Any>> {
        val data: ArrayList<Array<Any>> = arrayListOf()

        data.add(arrayOf("my name",MyIdentity(EntityId(1)), VersionId( 1L),
            EntityTest("my name",MyIdentity(EntityId(1)), VersionId( 1L))))

        return data.iterator()
    }

    @DataProvider(name = "entityValidData")
    fun entityValidData(): MutableIterator<Array<Any>> {
        val data: ArrayList<Array<Any>> = arrayListOf()

        data.add(arrayOf("my name",MyIdentity(EntityId(1)), VersionId( 1L),true))

        return data.iterator()
    }

    @DataProvider(name = "entityInvalidData")
    fun entityInvalidData(): MutableIterator<Array<Any>> {
        val data: ArrayList<Array<Any>> = arrayListOf()

        data.add(arrayOf("",MyIdentity(EntityId(1)), VersionId( 1L),1))

        return data.iterator()
    }
}

/** Entity Tests Implementation. */
class EntityTests: EntityBaseTest(){

    @org.testng.annotations.Test(dataProvider = "entityTestData", groups = ["entity"])
    fun `creating a new EntityTest` (name:String, idt:MyIdentity,version: VersionId, expected: EntityTest) {
        val etd = EntityTest.from(name, idt, version)
        assertEquals(expected, etd)
    }

    @org.testng.annotations.Test(dataProvider = "entityValidData", groups = ["entity"])
    fun `creating a valid EntityTest` (name:String, idt:MyIdentity,version: VersionId, expected: Boolean) {
        val etd = EntityTest.from(name, idt, version)
        assertEquals(expected, etd.isValid)
    }

    @org.testng.annotations.Test
    fun `Invalid EntityId Created` () {
        val etd = EntityId.from(0)
        assertEquals(1, etd.failures().count())
    }

    @org.testng.annotations.Test(dataProvider = "entityInvalidData", groups = ["entity"])
    fun `creating an invalid EntityTest` (name:String, idt:MyIdentity,version: VersionId, expected: Int) {
        val etd = EntityTest.from(name, idt, version)
        assertEquals(expected, etd.failures().count())
    }

    @org.testng.annotations.Test
    fun `Invalid MyIdentity Created` () {
        val etd = MyIdentity.from(EntityId.from(0))
        assertEquals(1, etd.failures().count())
    }
}
