package com.yuyan.harp.user

import com.yuyan.harp.data.driver.ResultState
import com.yuyan.harp.data.model.ResultDataSource
import droid.app.Controller
import droid.content.WebIntent

class UserController: Controller() {
    fun login(name: String, mail: String) {
        val req = (intent as WebIntent).request
        val res = (intent as WebIntent).response
        println("UserController login() name = $name, mail = $mail")
        println("test prop file, get 99999 = ${ResultState["99999"]}")
        res.writer.println(ResultDataSource.create(10000))
    }
}