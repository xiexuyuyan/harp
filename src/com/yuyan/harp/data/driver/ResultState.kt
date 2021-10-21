package com.yuyan.harp.data.driver

import java.io.IOException
import java.io.InputStream
import java.util.*

enum class ResultState {
    INSTANCE;

    private val properties: Properties = Properties()

    companion object {
        fun init(propInputStream: InputStream) {
            try {
                INSTANCE.properties.load(propInputStream)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        @kotlin.jvm.JvmStatic
        operator fun get(key: String): String {
            return INSTANCE.properties.getProperty(key)
        }
    }

}