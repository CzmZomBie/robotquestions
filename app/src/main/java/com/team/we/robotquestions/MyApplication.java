package com.team.we.robotquestions;

import android.app.Application;
import android.content.Context;

import com.tencent.bugly.Bugly;
import com.tencent.bugly.crashreport.CrashReport;

import org.xutils.x;

/**
 * Created by czm on 2017/6/2 0002.
 */

public class MyApplication extends Application {

    public static Context getContext() {
        return context;
    }

    static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        Bugly.init(getApplicationContext(), "8900b5897b", false);
        x.Ext.init(this);
        context = this;
        CrashReport.setUserId("9527");
    }

}
