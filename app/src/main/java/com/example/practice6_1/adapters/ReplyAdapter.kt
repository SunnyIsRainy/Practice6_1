package com.example.practice6_1.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.practice6_1.R
import com.example.practice6_1.data.Reply
import com.example.practice6_1.data.Topic

class ReplyAdapter(
    val mContext: Context,
    resId: Int,
    val mList: List<Reply>): ArrayAdapter<Reply>(mContext, resId, mList) {

    val mInflater = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var tempRow = convertView
        if (tempRow == null) {
            tempRow = mInflater.inflate(R.layout.reply_detail_item, null)
        }

        val row = tempRow!!

        val data = mList[position]
        val replyNickname = row.findViewById<TextView>(R.id.replyNicknameTxt)
        val replyContent = row.findViewById<TextView>(R.id.replyContentTxt)

        replyNickname.text = data.writerNickname
        replyContent.text = data.content

        return row
    }

}