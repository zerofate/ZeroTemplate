package com.zerofate.template.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.zerofate.template.base.BaseTestActivity

class FragmentLifeCycleActivity : BaseTestActivity() {

    private var topFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addButton("add", Runnable {
            addFragment(newFragmentInstant())
        })

        addButton("replace", Runnable {
            replaceFragment(newFragmentInstant())
        })

        addButton("stack add", Runnable {
            addFragment(newFragmentInstant(), true)
        })

        addButton("stack replace", Runnable {
            replaceFragment(newFragmentInstant(), true)
        })

        addButton("hide", Runnable {
            hideFragment(topFragment!!)
        })

        addButton("show", Runnable {
            showFragment(topFragment!!)
        })

        addButton("remove", Runnable {
            removeFragment(topFragment!!)
        })
    }

    private fun newFragmentInstant(): Fragment {
        topFragment = LifeCycleFragment()
        return topFragment!!
    }


}
