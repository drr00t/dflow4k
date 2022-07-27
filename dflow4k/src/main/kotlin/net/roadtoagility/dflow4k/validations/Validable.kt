package net.roadtoagility.dflow4k.validations

/** interface for Entity Identity implementation. */
interface Validable{

    val isValid: Boolean

    fun appendValidationResult(failure: Failure)

    fun appendValidationResult(failures:List<Failure>)
}

