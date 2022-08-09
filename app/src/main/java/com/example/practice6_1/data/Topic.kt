package com.example.practice6_1.data

import org.json.JSONObject
import java.io.Serializable

class Topic: Serializable {

    var id = 0
    var title = ""
    var imgUrl = ""
    var sides = ArrayList<Side>()

    companion object {

        fun getTopicDataFromJson(jsonObj: JSONObject): Topic {
            val resultTopic = Topic()

            resultTopic.id = jsonObj.getInt("id")
            resultTopic.title = jsonObj.getString("title")
            resultTopic.imgUrl = jsonObj.getString("img_url")

            val sidesArr = jsonObj.getJSONArray("sides")

            for (i in 0 until sidesArr.length()) {
                val sideObj = sidesArr.getJSONObject(i)
                val side = Side.getSideFromJson(sideObj)
                resultTopic.sides.add(side)
            }

            return resultTopic
        }

    }

}