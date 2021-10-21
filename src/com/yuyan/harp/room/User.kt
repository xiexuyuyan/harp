package com.yuyan.harp.room

import org.yuyan.room.entity.PrimaryKey
import org.yuyan.room.entity.ColumnInfo
import org.yuyan.room.entity.Entity

@Entity(tableName = "users")
data class User(
    @PrimaryKey
    @ColumnInfo(name = "uid")
    var uid: Int,

    @ColumnInfo(name = "user_name")
    var name: String,

    @ColumnInfo(name = "user_mail")
    var mail: String
    ) {

    override fun toString(): String {
        return ("uid: " + uid
                + ", name: " + name
                + ", mail: " + mail)
    }
    constructor():this(0, "", "")
}