package net.roadtoagility.dflow4k

import io.konform.validation.Validation
import net.roadtoagility.dflow4k.validations.BaseValidation

/** Entity identity implementation. */
data class MyIdentity constructor(private val id: EntityId) : BaseValidation(), EntityIdentity<EntityId> {

    init {
        appendValidationResult(identity.failures())
    }

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

    companion object{
        fun from(entityId: EntityId): MyIdentity {
            val id = MyIdentity(entityId)
            val result = Validation<MyIdentity> {
                MyIdentity::id required {
                }
            }.validate(id)
            id.appendValidationResult(result.errors)
            return id
        }
    }
}

