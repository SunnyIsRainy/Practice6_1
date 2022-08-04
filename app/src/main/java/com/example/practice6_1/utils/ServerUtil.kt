package com.example.practice6_1.utils

import android.content.Context
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import org.json.JSONObject
import java.io.IOException

class ServerUtil {

    companion object {
        interface JsonResponseHandler{
            fun onResponse(jsonObj: JSONObject)
        }

        val BASE_URL = "http://54.180.52.26"

        fun postRequestLogin(email: String, password: String, handler: JsonResponseHandler?) {
            val urlString = "${BASE_URL}/user"
            val formData = FormBody.Builder()
                .add("email", email)
                .add("password", password)
                .build()
            val request = Request.Builder()
                .url(urlString)
                .post(formData)
                .build()
            val client = OkHttpClient()
            client.newCall(request).enqueue(object : Callback{
                override fun onFailure(call: Call, e: IOException) {
                }

                override fun onResponse(call: Call, response: Response) {
                    val bodyString = response.body!!.string()
                    val jsonObj = JSONObject(bodyString)
                    handler?.onResponse(jsonObj)
                }

            })
        }

        fun putRequestSignUp(email: String, password: String, nickname: String, handler: JsonResponseHandler?) {
            val urlString = "${BASE_URL}/user"
            val formData = FormBody.Builder()
                .add("email", email)
                .add("password", password)
                .add("nick_name", nickname)
                .build()
            val request = Request.Builder()
                .url(urlString)
                .put(formData)
                .build()
            val client = OkHttpClient()
            client.newCall(request).enqueue(object : Callback{
                override fun onFailure(call: Call, e: IOException) {
                }

                override fun onResponse(call: Call, response: Response) {
                    val bodyString = response.body!!.string()
                    val jsonObj = JSONObject(bodyString)
                    handler?.onResponse(jsonObj)
                }

            })
        }

        fun getRequestDuflCheck(type: String, value: String, handler: JsonResponseHandler?) {
            val urlBuilder = "${BASE_URL}/user_check".toHttpUrlOrNull()!!.newBuilder()
            urlBuilder.addEncodedQueryParameter("type", type)
            urlBuilder.addEncodedQueryParameter("value", value)
            val urlString = urlBuilder.toString()

            val request = Request.Builder()
                .url(urlString)
                .get()
                .build()
            val client = OkHttpClient()
            client.newCall(request).enqueue(object : Callback{
                override fun onFailure(call: Call, e: IOException) {
                }

                override fun onResponse(call: Call, response: Response) {
                    val bodyString = response.body!!.string()
                    val jsonObj = JSONObject(bodyString)
                    handler?.onResponse(jsonObj)
                }

            })
        }

        fun getRequestTopicInfo(context: Context, handler: JsonResponseHandler?) {
            val urlBuilder = "${BASE_URL}/v2_main_info".toHttpUrlOrNull()!!.newBuilder()
            val urlString = urlBuilder.toString()

            val request = Request.Builder()
                .url(urlString).header("X-Http_Token", ContextUtil.getToken(context))
                .get()
                .build()
            val client = OkHttpClient()
            client.newCall(request).enqueue(object : Callback{
                override fun onFailure(call: Call, e: IOException) {
                }

                override fun onResponse(call: Call, response: Response) {
                    val bodyString = response.body!!.string()
                    val jsonObj = JSONObject(bodyString)
                    handler?.onResponse(jsonObj)
                }
            })
        }

    }

}