package net.roadtoagility.dflow4k

/** Value Object for Entity versioning. */
data class VersionId(
    override val value: Long

    ): Comparable<VersionId>, ValueObject<Long> {

    constructor (versionId: VersionId) : this(versionId.value)

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
        private const val VersionEmpty: Long = 0L
        private const val VersionInitial: Long = 1L
        private const val VersionIncrement: Long = 1L

        fun empty(): VersionId {
            return VersionId(VersionEmpty)
        }
        fun new(): VersionId {
            return VersionId(VersionInitial)
        }

    }
}
