package com.yuyan.harp.room

import org.yuyan.room.entity.PrimaryKey
import org.yuyan.room.entity.ColumnInfo
import org.yuyan.room.entity.Entity

@Entity(tableName = "web_session")
data class WebSession(
    @PrimaryKey
    @ColumnInfo(name = "uid")
    var uid: Int,

    @ColumnInfo(name = "session_key")
    var sessionKey: String
    ) {
    constructor(): this(0, "12345678-abcd-abcd-abcd-12345678abcd")

    override fun toString(): String {
        return ("uid = " + uid
                + ", sessionKey = " + sessionKey)
    }
}