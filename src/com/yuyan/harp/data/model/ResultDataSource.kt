package com.yuyan.harp.data.model

import java.util.HashMap
import com.google.gson.GsonBuilder

class ResultDataSource {
    data class BaseResult(var code: String, var msg: String)

    private val baseResult = BaseResult("", "")
    private val data: MutableMap<String, Any?> = HashMap()
    private val gson = GsonBuilder().disableHtmlEscaping().create()

    fun getCode(): String {
        return baseResult.code
    }

    fun setCode(code: String) {
        baseResult.code = code
        data.replace("code", baseResult.code)
    }

    fun getMsg(): String {
        return baseResult.msg
    }

    fun setMsg(msg: String) {
        baseResult.msg = msg
        data.replace("msg", baseResult.msg)
    }


    private constructor() {}
    constructor(code: String, msg: String) {
        baseResult.code = code
        baseResult.msg = msg
        data["code"] = code
        data["msg"] = msg
    }

    fun jsonString(): String {
        return gson.toJson(data)
    }

    fun <V> put(key: String, value: V) {
        data[key] = value
    }

    fun <V> with(key: String, value: V): ResultDataSource {
        put(key, value)
        return this
    }

    companion object {
        fun create(code: String): ResultDataSource {
            return ResultDataSource(code, "ResultState.get(code)")
        }
        fun create(code: Int): ResultDataSource {
            return ResultDataSource("$code", "ResultState.get(code)")
        }

        fun success(): ResultDataSource {
            return create("10000")
        }

        fun success(code: String, msg: String): ResultDataSource {
            return ResultDataSource(code, msg)
        }

        fun error(): ResultDataSource {
            return create("10001")
        }

        fun error(code: String, msg: String): ResultDataSource {
            return ResultDataSource(code, msg)
        }
    }
}