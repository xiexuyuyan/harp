package com.yuyan.harp.room

import org.yuyan.room.entity.ColumnInfo
import org.yuyan.room.entity.Entity
import java.text.SimpleDateFormat
import java.util.*

@Entity(tableName = "login")
data class Login(
    @ColumnInfo(name = "uid")
    var uid: Int,

    @ColumnInfo(name = "login_date")
    var date: String,

    @ColumnInfo(name = "login_time")
    var time: String
    ) {
    constructor(): this(0, "2021-10-21", "22:19:19")

    override fun toString(): String {
        return ("uid = " + uid
                + ", date = " + date
                + ", time = " + time)
    }

    companion object {
        fun createCurrent(uid: Int): Login {
            val dateOrigin = Date()
            val format = SimpleDateFormat()
            format.applyPattern("yyyy-MM-dd")
            val date = format.format(dateOrigin)
            format.applyPattern("HH:mm:ss")
            val time = format.format(dateOrigin)
            return Login(uid, date, time)
        }
    }
}
