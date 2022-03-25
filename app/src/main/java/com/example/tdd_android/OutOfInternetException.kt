package com.example.tdd_android

import java.lang.Exception
import java.security.MessageDigest

data class OutOfInternetException(val msg:String = "") : Exception(msg)
data class NoDataException(val msg: String = "") : Exception(msg)


