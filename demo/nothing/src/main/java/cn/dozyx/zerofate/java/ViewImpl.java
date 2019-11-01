package cn.dozyx.zerofate.java;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Create by timon on 2019/11/1
 **/
public class ViewImpl implements IView {
    private BaseQuickAdapter<String, BaseViewHolder> adapter;
    public ViewImpl() {
    }

    @Override
    public <T> void showDatas(List<T> datas) {
        adapter.setNewData(datas);
    }
}
