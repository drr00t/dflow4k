package net.roadtoagility.dflow4k

import net.roadtoagility.dflow4k.validations.BaseValidation


/** base Entity definition. */
abstract class BaseEntity<TIdentity> protected constructor
    (override val identity: TIdentity, val versionId: VersionId)
    : BaseValidation(),EntityIdentity<TIdentity>
