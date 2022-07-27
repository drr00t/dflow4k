package net.roadtoagility.dflow4k

import io.konform.validation.Validation
import io.konform.validation.jsonschema.maxLength
import io.konform.validation.jsonschema.minLength

/** Basic Entity implementation. */
class EntityTest(val name: String, idt: MyIdentity, version: VersionId) : BaseEntity<MyIdentity>(idt, version) {


    init {
        appendValidationResult(identity.failures())
    }

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
        return "($name)($identity)($versionId)"
    }

    companion object{
        fun from(name: String, idt: MyIdentity, version: VersionId): EntityTest {
            val entity = EntityTest(name, idt, version)
            val result = Validation<EntityTest> {
                EntityTest::name required {
                    minLength(5)
                    maxLength(50)
                }
            }.validate(entity)

            entity.appendValidationResult(result.errors)

            return entity
        }
    }
}

