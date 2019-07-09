package cn.dozyx.template.view.anim

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import cn.dozyx.core.base.BaseActivity
import cn.dozyx.core.fragment.FragmentWrapper
import cn.dozyx.template.R
import kotlinx.android.synthetic.main.activity_animator_pager.*
import timber.log.Timber

/**
 * Create by timon on 2019/7/9
 */
class AnimatorPagerActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupPager()
    }

    private fun setupPager() {
        val fragments = arrayOf(FragmentWrapper("响铃", BellAnimatorFragment()), FragmentWrapper("菜单", ExpandMenuFragment()))
        pager_fragment.adapter = object : FragmentPagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            override fun getItem(position: Int): Fragment {
                Timber.d("AnimatorPagerActivity.getItem: $position")
                return fragments[position].fragment
            }

            override fun getCount(): Int {
                return fragments.size
            }

            override fun getPageTitle(position: Int): CharSequence? {
                return fragments[position].title
            }

        }
        tab_layout.setupWithViewPager(pager_fragment)
    }

    override fun getLayoutId(): Int = R.layout.activity_animator_pager
}
