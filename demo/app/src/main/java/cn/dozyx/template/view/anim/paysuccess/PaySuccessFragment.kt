package cn.dozyx.template.view.anim.paysuccess

import cn.dozyx.template.view.anim.BaseSingleViewDemoFragment

/**
 * Create by dozyx on 2019/7/18
 */
class PaySuccessFragment : BaseSingleViewDemoFragment<PaySuccessView>() {
    override fun getAnimView(): PaySuccessView {
        return PaySuccessView(context)
    }
}
