package net.roadtoagility.dflow4k

import org.testng.annotations.DataProvider
import kotlin.test.assertEquals

data class EntityId(
    val id: Int
){
    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun equals(other: Any?): Boolean  =
        other is EntityId &&
                other.id == id

    override fun toString(): String {
        return id.toString()
    }
}

class MyIdentity(
    private val id:EntityId
):EntityIdentity<EntityId>{
    override val identity: EntityId
        get() = id

    override fun equals(other: Any?): Boolean =
        other is MyIdentity &&
                other.id == id

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun toString(): String {
        return id.toString()
    }
}

class EntityTest(
    val name:String,
    idt:MyIdentity,
    version:VersionId
):BaseEntity<MyIdentity>(idt,version){
    override fun equals(other: Any?): Boolean =
        other is EntityTest &&
                other.name == name &&
                other.identity == identity &&
                other.versionId == versionId

    override fun hashCode(): Int {
        return name.hashCode()
            .plus(31 + identity.hashCode())
            .plus(31 + versionId.hashCode())
    }

    override fun toString(): String {
        return "($name)(${identity.toString()})($versionId)"
    }
}

open class EntityBaseTest {
    @DataProvider(name = "entityTestData")
    fun entityTestData(): MutableIterator<Array<Any>> {
        val data: ArrayList<Array<Any>> = arrayListOf()

        data.add(arrayOf("my name",MyIdentity(EntityId(1)), VersionId( 1L),
            EntityTest("my name",MyIdentity(EntityId(1)), VersionId( 1L))))

        return data.iterator()
    }
}

internal class EntityTests: EntityBaseTest(){

    @org.testng.annotations.Test(dataProvider = "entityTestData", groups = ["entity"])
    fun testCreateEntity(name:String, idt:MyIdentity,version: VersionId, expected: EntityTest) {
        val etd = EntityTest(name, idt, version)
        assertEquals(expected, etd)
    }
}