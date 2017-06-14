package com.team.we.robotquestions.activity.basehome;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;


import com.team.we.robotquestions.R;
import com.tencent.bugly.beta.Beta;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by czm on 2017/4/27.
 */

public  class BaseActivity extends Activity {


    protected final String TAG = this.getClass().getSimpleName();

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        addActivity(this);
        x.view().inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        removeActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    public Context getContext() {
        return context;
    }


    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    private static List<Activity> activitys = new ArrayList<>();

    private void addActivity(Activity activity) {
        activitys.add(activity);
    }

    private static void removeActivity(Activity activity) {
        activitys.remove(activity);
        activity.finish();
    }

    public void clearAllActivity() {
        for (Activity activity : activitys) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    public void startActivity(Class<?> clz) {
        startActivity(new Intent().setClass(this, clz));
    }

    private static Toast toast;

    public void toastShow(String message) {
        if (toast == null) {
            toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

    public void toastShow(int res) {
        if (toast == null) {
            toast = Toast.makeText(context, getString(res), Toast.LENGTH_SHORT);
        } else {
            toast.setText(getString(res));
        }
        toast.show();
    }

    public void toastLongShow(int res) {
        Toast.makeText(context, getString(res), Toast.LENGTH_SHORT).show();
    }

    public void toastLongShow(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public boolean doStartApplicationWithPackageName(String packagename) {
        if (context == null) return false;
        // 通过包名获取此APP详细信息，包括Activities、services、versioncode、name等等
        PackageInfo packageinfo = null;
        try {
            packageinfo = context.getPackageManager().getPackageInfo(packagename, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        if (packageinfo == null) {
            return false;
        }

        // 创建一个类别为CATEGORY_LAUNCHER的该包名的Intent
        Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
        resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        resolveIntent.setPackage(packageinfo.packageName);

        // 通过getPackageManager()的queryIntentActivities方法遍历
        List<ResolveInfo> resolveinfoList = context.getPackageManager()
                .queryIntentActivities(resolveIntent, 0);

        ResolveInfo resolveinfo = resolveinfoList.iterator().next();
        if (resolveinfo != null) {
            // packagename = 参数packname
            String packageName = resolveinfo.activityInfo.packageName;
            // 这个就是我们要找的该APP的LAUNCHER的Activity[组织形式：packagename.mainActivityname]
            String className = resolveinfo.activityInfo.name;
            // LAUNCHER Intent
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);

            // 设置ComponentName参数1:packagename参数2:MainActivity路径
            ComponentName cn = new ComponentName(packageName, className);

            intent.setComponent(cn);
            context.startActivity(intent);
        }
        return true;
    }

    ImageOptions imageOptions = null;

    public void XImage(ImageView imagem, String src) {
        if (null == imageOptions) {
            imageOptions = new ImageOptions.Builder()
                    .setFailureDrawableId(R.drawable.icon_error)
                    .build();
        }
        x.image().bind(imagem, src, imageOptions);
    }

    public void XImage(ImageView imagem, String src, int load, int failur) {
        if (null == imageOptions) {
            imageOptions = new ImageOptions.Builder()
                    .setLoadingDrawableId(load)
                    .setFailureDrawableId(failur)
                    .build();
        }
        x.image().bind(imagem, src, imageOptions);
    }


}
