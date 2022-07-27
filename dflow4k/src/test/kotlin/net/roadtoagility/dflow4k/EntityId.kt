package net.roadtoagility.dflow4k

import io.konform.validation.Validation
import io.konform.validation.jsonschema.maximum
import io.konform.validation.jsonschema.minimum
import net.roadtoagility.dflow4k.validations.BaseValidation

/** Entity identity ValueObject. */
data class EntityId(
    val id: Int
): BaseValidation(){

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun equals(other: Any?): Boolean  =
        other is EntityId &&
                other.id == id

    override fun toString(): String {
        return id.toString()
    }

    companion object {
        fun from(id: Int): EntityId {
            val entityId = EntityId(id)
            val result = Validation<EntityId> {
                EntityId::id required {
                    minimum(1)
                    maximum(Int.MAX_VALUE)
                }
            }.validate(entityId)
            entityId.appendValidationResult(result.errors)
            return entityId
        }
    }
}
