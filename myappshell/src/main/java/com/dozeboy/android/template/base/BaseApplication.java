package com.dozeboy.android.template.base;

import android.app.Application;
import android.os.StrictMode;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;
import com.squareup.leakcanary.BuildConfig;
import com.squareup.leakcanary.LeakCanary;


/**
 * @author dozeboy
 * @date 2018/11/6
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        if (BuildConfig.DEBUG) {
            configStrictMode();
        }
        super.onCreate();
        initLeakCanary();
        Utils.init(this);
    }

    private void initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }

    private void configStrictMode() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build());

        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .penaltyLog()
                .penaltyDeath()
                .build());
    }
}