package cn.dozyx.shell

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import com.dozeboy.android.template.R
import cn.dozyx.shell.base.BaseShellActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import timber.log.Timber

class MainActivity : BaseShellActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("MainActivity.onCreate")
        setupDrawer()
    }

    override fun getLayoutId(): Int = R.layout.activity_main
    private fun setupDrawer() {
        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.drawer_open, R.string.drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
    }

    override fun onDestroy() {
        Timber.d("MainActivity.onDestroy")
        super.onDestroy()
    }
}
