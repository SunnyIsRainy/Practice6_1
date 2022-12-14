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
        firstVoteBtn.setOnClickListener {
            ServerUtil.postRequestVote(mContext, mTopic.sides[0].id, object : ServerUtil.Companion.JsonResponseHandler{
                override fun onResponse(jsonObj: JSONObject) {
                    getTopicDetailFromServer()
                }
            })
        }

        secondVoteBtn.setOnClickListener {
            ServerUtil.postRequestVote(mContext, mTopic.sides[1].id, object : ServerUtil.Companion.JsonResponseHandler{
                override fun onResponse(jsonObj: JSONObject) {
                    getTopicDetailFromServer()
                }
            })
        }

    }

    override fun setValue() {
        mTopic = intent.getSerializableExtra("topic") as Topic

        topicTitleTxt.text = mTopic.title
        Glide.with(mContext).load(mTopic.imgUrl).into(topicImg)

        getTopicDetailFromServer()
    }

    override fun onResume() {
        super.onResume()
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
                    firstSideVoteCount.text = "${mTopic.sides[0].voteCount}???"


                    secondSideTxt.text = mTopic.sides[1].title
                    secondSideVoteCount.text = "${mTopic.sides[1].voteCount}???"
                }
            }

        })
    }



}