package net.roadtoagility.dflow4k

import org.testng.annotations.DataProvider
import kotlin.test.assertFalse
import kotlin.test.assertTrue


/** Tests data provider for Entity. */
open class AggregateBaseTest {
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
class AggregateTests: AggregateBaseTest(){

    @org.testng.annotations.Test
    fun `creating a new AgregateTest` () {
        val mfc = MyAggregateFactory()
        val ag = mfc.create(MyIdentity.from(EntityId.from(1)))
        assertTrue(ag.isValid)
    }

    @org.testng.annotations.Test
    fun `creating an invalid AgregateTest` () {
        val mfc = MyAggregateFactory()
        val ag = mfc.create(MyIdentity.from(EntityId.from(0)))
        assertFalse(ag.isValid)
    }

//    @org.testng.annotations.Test
//    fun `creating a valid MyAgregate` () {
//        val mfc = MyAggregateFactory()
//        val ent = mfc.create(MyIdentity.from(EntityId.from(0)))
//        val ag = mfc.create(ent)
//        assertFalse(ag.isValid)
//    }
}
