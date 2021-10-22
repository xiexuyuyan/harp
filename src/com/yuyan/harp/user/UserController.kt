package com.yuyan.harp.user

import com.yuyan.harp.data.model.ResultDataSource
import com.yuyan.harp.room.*
import droid.app.Controller
import droid.content.WebIntent
import java.util.*
import javax.servlet.http.HttpServletResponse

class UserController: Controller() {
    lateinit var userDatabase: UserDatabase
    lateinit var userDao: UserDao
    lateinit var webSessionDao: WebSessionDao

    override fun onCreate() {
        super.onCreate()
        println("UserController onCreate in child")
        userDatabase = UserDatabaseHelper.get()
        userDao = userDatabase.userDao()
        webSessionDao = userDatabase.webSessionDao()
    }

    override fun onStart() {
        super.onStart()
        println("UserController onStart in child")
    }

    private fun login(user: User) {
        println("UserController login() user = $user")

        val req = (intent as WebIntent).request
        val res = (intent as WebIntent).response
        val response: HttpServletResponse = (res as HttpServletResponse)

        val sessionKey = formatSessionKey()
        val webSession = WebSession(user.uid, sessionKey)

        if (user.uid == 0) {
            val resultState = ResultDataSource.create(10003)
            val retState: String = resultState.jsonString()
            response.writer.println(retState)

            println("UserController login() header session key = $sessionKey, retState = $retState")
            return
        }

        webSessionDao.getSessionByUid(webSession.uid).let {
            if (it.uid == 0)  {
                webSessionDao.insert(webSession)
            } else {
                webSessionDao.update(webSession)
            }
        }

        val login = Login.createCurrent(user.uid)
        userDao.login(login)

        response.addHeader("session_key", sessionKey)
        val resultState = ResultDataSource.create(10000)
        resultState.put("session_key", sessionKey)
        val retState: String = resultState.jsonString()
        response.writer.println(retState)

        println("UserController login() header session key = $sessionKey, retState = $retState")
    }
    
    fun login(uid: Int) {
        // TODO: 2021/10/22 检查uid的规范性 
        println("UserController login() uid = $uid")
        val user = userDao.getUserByUid(uid = uid)
        login(user)
    }

    fun login(name: String, mail: String) {
        // TODO: 2021/10/22 检查姓名和邮箱的规范性
        println("UserController login() name = $name, mail = $mail")
        val user: User = userDao.getUserByNameAndMail(name, mail)
        login(user)
    }

    private fun formatSessionKey(): String {
        var uuid: String
        while (true) {
            uuid = UUID.randomUUID().toString()
            val webSession = webSessionDao.getSessionBySessionKey(uuid)
            if (webSession.uid == 0) {
                break
            }
        }
        return uuid
    }

}