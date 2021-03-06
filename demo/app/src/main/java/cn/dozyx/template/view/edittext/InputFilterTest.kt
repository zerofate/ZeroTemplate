package cn.dozyx.template.view.edittext

import android.text.InputFilter
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatEditText
import cn.dozyx.core.base.BaseActivity
import cn.dozyx.core.utli.edittext.SpaceInputFilter

/**
 * Create by dozyx on 2019/5/31
 **/
class InputFilterTest : BaseActivity() {
    override fun getLayoutId(): Int {
        return 0
    }

    override fun getLayoutView(): View? {
        val contentView = LinearLayout(this)
        contentView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        val editText = AppCompatEditText(this)
        editText.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        contentView.addView(editText)

        editText.filters = arrayOf(InputFilter.AllCaps(), SpaceInputFilter())
        return contentView
    }
}