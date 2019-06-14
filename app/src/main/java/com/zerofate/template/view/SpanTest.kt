package com.zerofate.template.view

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.ImageSpan

import com.dozeboy.android.core.base.BaseActivity
import com.zerofate.template.R
import kotlinx.android.synthetic.main.activity_span_test.*

/**
 * Create by timon on 2019/6/4
 */
class SpanTest : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val spannableString = SpannableString("123456789")
//        spannableString.setSpan(ImageSpan(this,R.drawable.ic_menu_camera),0,1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(ForegroundColorSpan(Color.BLUE), 2, 4, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        text1.text = spannableString
        edit1.setText(spannableString)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_span_test
    }
}