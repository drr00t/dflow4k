package net.roadtoagility.dflow4k.validations

import io.konform.validation.ValidationError

/** Value Object for Failure. */
data class Failure(
    val propertyName:String,
    val message:String,
    val value:String
    ){

    companion object{
        fun from(propertyName:String, message:String, value:String): Failure {
            return Failure(propertyName,message,value)
        }

        fun from(propertyName:String, message:String): Failure {
            return from(propertyName,message, "")
        }

        fun from(error: ValidationError):Failure{
            return from(error.dataPath,error.message)
        }
    }
}
