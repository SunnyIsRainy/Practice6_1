package com.example.practice6_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.practice6_1.adapters.TopicAdapter
import com.example.practice6_1.data.Topic
import com.example.practice6_1.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : BaseActivity() {

    lateinit var mTopicAdapter: TopicAdapter
    val mTopicList = ArrayList<Topic>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvent()
        setValue()
    }

    override fun setupEvent() {
    }

    override fun setValue() {
        mTopicAdapter = TopicAdapter(mContext, R.layout.topic_list_item, mTopicList)
        topicListView.adapter = mTopicAdapter

        fun getTopicListFromServer() {

            ServerUtil.getRequestTopicInfo(mContext, object : ServerUtil.Companion.JsonResponseHandler{
                override fun onResponse(jsonObj: JSONObject) {

                    val dataObj = jsonObj.getJSONObject("data")
                    val topicsArr = dataObj.getJSONArray("topics")

                    for (index in 0 until topicsArr.length()) {
                        val topicsObj = topicsArr.getJSONObject(index)
                        val topicsData = Topic.getTopicDataFromJson(topicsObj)

                        mTopicList.add(topicsData)
                    }

                    runOnUiThread {
                        mTopicAdapter.notifyDataSetChanged()
                    }

                }

            })

        }

        getTopicListFromServer()

    }

}