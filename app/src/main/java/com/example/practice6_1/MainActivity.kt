package com.example.practice6_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.practice6_1.adapters.TopicAdapter
import com.example.practice6_1.data.Topic
import kotlinx.android.synthetic.main.activity_main.*

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
    }

}