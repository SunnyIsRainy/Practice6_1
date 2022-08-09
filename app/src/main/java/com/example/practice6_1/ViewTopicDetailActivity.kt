package com.example.practice6_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.practice6_1.data.Topic
import com.example.practice6_1.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_view_topic_detail.*
import org.json.JSONObject

class ViewTopicDetailActivity : BaseActivity() {

    lateinit var mTopic: Topic

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_topic_detail)
        setupEvent()
        setValue()
    }

    override fun setupEvent() {
    }

    override fun setValue() {
        mTopic = intent.getSerializableExtra("topic") as Topic

        topicTitleTxt.text = mTopic.title
        Glide.with(mContext).load(mTopic.imgUrl).into(topicImg)

        getTopicDetailFromServer()
    }

    fun getTopicDetailFromServer() {
        ServerUtil.getRequestTopicDetail(mContext, mTopic.id ,object : ServerUtil.Companion.JsonResponseHandler{
            override fun onResponse(jsonObj: JSONObject) {
                val dataObj = jsonObj.getJSONObject("data")
                val topicObj = dataObj.getJSONObject("topic")
                val topic = Topic.getTopicDataFromJson(topicObj)

                mTopic = topic

                runOnUiThread{
                    firstSideTxt.text = mTopic.sides[0].title
                    firstSideVoteCount.text = "${mTopic.sides[0].voteCount}표"


                    secondSideTxt.text = mTopic.sides[1].title
                    secondSideVoteCount.text = "${mTopic.sides[1].voteCount}표"
                }
            }

        })
    }



}