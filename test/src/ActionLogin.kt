import com.google.gson.GsonBuilder
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.junit.jupiter.api.Test
import java.io.IOException

class ActionLogin {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            request()
        }
        private fun request() {
            val url = "http://localhost:6688/login/actionLogin.php"
            val client = OkHttpClient()
            // @Headers({"Content-Type:application/json","Accept: application/json"})//需要添加头
            val jsonHeader: MediaType = "application/json;charset=utf-8".toMediaType()
            val gson = GsonBuilder().disableHtmlEscaping().create()
            val map: MutableMap<String, String> = HashMap()
            map["name"] = "tom"
            map["mail"] = "tom@ktc.com"
            val jsonPost = gson.toJson(map)
            val requestBody = jsonPost.toString().toRequestBody(jsonHeader)
            val request = Request.Builder()
                .url(url)
                .post(requestBody)
                .build()
            val call = client.newCall(request)
            call.enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    println("onFailure: ")
                }

                override fun onResponse(call: Call, response: Response) {
                    val body = response.body ?: return
                    println("body.string() = " + body.string())
                }
            })
        }
    }


}