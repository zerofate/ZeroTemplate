package cn.dozyx.template.view.scroll

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.LinearLayout

/**
 * Create by dozyx on 2019/5/29
 **/
class ScrollableLinearLayout(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
            }
            MotionEvent.ACTION_MOVE -> {
            }
        }
        return super.onTouchEvent(event)
    }
}