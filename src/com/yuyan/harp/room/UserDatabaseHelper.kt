package com.yuyan.harp.room

import org.yuyan.room.base.DatabaseConfigure
import org.yuyan.room.base.Room.Companion.databaseBuilder

enum class UserDatabaseHelper {
    INSTANCE;

    private lateinit var database: UserDatabase

    companion object {
        private const val JDBC_DRIVER = "com.mysql.cj.jdbc.Driver"
        fun init() {
            try {
                Class.forName(JDBC_DRIVER)
            } catch (e: ClassNotFoundException) {
                e.printStackTrace()
            }
            INSTANCE.database = databaseBuilder(
                UserDatabase::class.java, object : DatabaseConfigure(
                    "yuyan", "123456"
                ) {
                    override fun getUrl(): String {
                        return "jdbc:mysql://localhost:3306/" +
                                "test" +
                                "?useSSL=false" +
                                "&allowPublicKeyRetrieval=true" +
                                "&serverTimezone=UTC" +
                                "&rewriteBatchedStatements=true"
                    }
                }).build()
        }

        fun get(): UserDatabase {
            return INSTANCE.database
        }
    }
}