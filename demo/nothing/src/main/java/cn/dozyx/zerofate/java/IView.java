package cn.dozyx.zerofate.java;

import java.util.List;

/**
 * Create by timon on 2019/11/1
 **/
public interface IView {
    <T> void showDatas(List<T> datas);
}
