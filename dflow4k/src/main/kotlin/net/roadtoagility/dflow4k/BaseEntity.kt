package net.roadtoagility.dflow4k

abstract class BaseEntity<TIdentity> protected constructor
    (override val identity: TIdentity, val versionId: VersionId)
    :EntityIdentity<TIdentity>