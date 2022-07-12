package net.roadtoagility.dflow4k

import kotlin.Int as Int

data class VersionId(
    val value: Long

    ): Comparable<VersionId> {

    constructor (versionId: VersionId) : this(versionId.value) {

    }

    fun empty(): VersionId {
        return VersionId(VersionEmpty)
    }

    fun new(): VersionId {
        return VersionId(VersionInitial)
    }

    fun next(): VersionId {
        return VersionId(this + increment())
    }

    private fun increment(): VersionId {
        return VersionId(VersionIncrement)
    }

    override operator fun compareTo(other: VersionId): Int =
        this.value.compareTo(other.value)

    override fun equals(other: Any?): Boolean {
        if (other == null) {
            return false
        }
        return this.compareTo(other as VersionId) == 0
    }

    override fun toString(): String {
        return value.toString()
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    operator fun unaryPlus(): VersionId = VersionId(this.value.unaryPlus())

    operator fun plus(b: VersionId) = VersionId(this.value + b.value)

    companion object {
        const val VersionEmpty = 0L
        const val VersionInitial = 1L
        const val VersionIncrement = 1L
    }
}