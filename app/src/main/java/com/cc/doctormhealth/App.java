package com.cc.doctormhealth;

import android.app.Application;
import android.os.StrictMode;

import com.avos.avoscloud.AVAnalytics;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.cc.doctormhealth.friends.AddRequest;
import com.cc.doctormhealth.model.LeanchatUser;
import com.cc.doctormhealth.model.UpdateInfo;
import com.cc.doctormhealth.service.PushManager;
import com.cc.doctormhealth.util.LeanchatUserProvider;
import com.cc.doctormhealth.util.Utils;

import cn.leancloud.chatkit.LCChatKit;

/**
 * Created by lzw on 14-5-29.
 */
public class App extends Application {
    public static boolean debug = true;
    public static App ctx;

    @Override
    public void onCreate() {
        super.onCreate();
        ctx = this;
        Utils.fixAsyncTaskBug();
        String appId = "cirdf9pJrnd6XpNW1Xn3OVf5-gzGzoHsz";
        String appKey = "eFwqv2nwhEDg9qdqzPUr3fga";
        LeanchatUser.alwaysUseSubUserClass(LeanchatUser.class);
        AVObject.registerSubclass(AddRequest.class);
        AVObject.registerSubclass(UpdateInfo.class);

        // 节省流量
        AVOSCloud.setLastModifyEnabled(true);
        LCChatKit.getInstance().setProfileProvider(new LeanchatUserProvider());
        LCChatKit.getInstance().init(this, appId, appKey);
        PushManager.getInstance().init(ctx);
        AVOSCloud.setDebugLogEnabled(debug);
        AVAnalytics.enableCrashReport(this, !debug);

        if (App.debug) {
            openStrictMode();
        }
    }

    public void openStrictMode() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()   // or .detectAll() for all detectable problems
                .penaltyLog()
                .build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .detectLeakedClosableObjects()
                .penaltyLog()
                //.penaltyDeath()
                .build());
    }


}
