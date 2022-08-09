package com.example.practice6_1.data

import org.json.JSONObject
import java.io.Serializable

class Reply: Serializable {

    var id = 0
    var content = ""
    var writerNickname = ""

    companion object {

        fun getReplyDataFromJson(jsonObj: JSONObject): Reply {
            val resultTopic = Reply()

            resultTopic.id = jsonObj.getInt("id")
            resultTopic.content = jsonObj.getString("content")
            resultTopic.writerNickname = jsonObj.getJSONObject("user").getString("nick_name")

            return resultTopic
        }

    }

}