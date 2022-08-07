package com.example.practice6_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.practice6_1.data.Topic
import kotlinx.android.synthetic.main.activity_view_topic_detail.*

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
    }

}