package net.roadtoagility.dflow4k.validations

import io.konform.validation.ValidationErrors

/** Base validation . */
open class BaseValidation: Validable{
    private var _failures = mutableListOf<Failure>()

    fun failures(): List<Failure> = _failures

    override val isValid: Boolean
        get() = _failures.isEmpty()

    override fun appendValidationResult(failure: Failure) {
        _failures.add(failure)
    }

    override fun appendValidationResult(failures: List<Failure>) {
        _failures.addAll(failures)
    }

    fun appendValidationResult(failures: ValidationErrors) {
        failures.forEach{
            appendValidationResult(Failure.from(it))
        }
    }

//    companion object {
//        fun <T> Validation<T>.validate(value: T) {
//            val result = validate(value)
//            if (result is Invalid<T>) {
//                (value as BaseValidation).appendValidationResult(result.errors)
//            }
//        }
//    }
}

