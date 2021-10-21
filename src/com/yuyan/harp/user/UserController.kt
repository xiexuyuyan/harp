package com.yuyan.harp.user

import com.yuyan.harp.data.driver.ResultState
import com.yuyan.harp.data.model.ResultDataSource
import com.yuyan.harp.room.Login
import com.yuyan.harp.room.User
import com.yuyan.harp.room.UserDatabase
import com.yuyan.harp.room.UserDatabaseHelper
import droid.app.Controller
import droid.content.WebIntent
import javax.servlet.http.HttpServletResponse

class UserController: Controller() {
    lateinit var userDatabase: UserDatabase

    override fun onCreate() {
        super.onCreate()
        userDatabase = UserDatabaseHelper.get()
        println("UserController onCreate in child")
    }

    override fun onStart() {
        super.onStart()
        println("UserController onStart in child")
    }

    fun login(uid: Int) {
        println("uid = $uid")
    }

    fun login(name: String, mail: String) {
        val req = (intent as WebIntent).request
        val res = (intent as WebIntent).response
        println("UserController login() name = $name, mail = $mail")
        println("test prop file, get 99999 = ${ResultState["99999"]}")
        val user: User = userDatabase.userDao().getUserByNameAndMail(name, mail)
        println(user)
        val response: HttpServletResponse = (res as HttpServletResponse)
        response.addHeader("session_key", "1234")
        val retState: String = ResultDataSource.create(10001).jsonString()
        println(retState)
        response.writer.println(retState)
    }
}