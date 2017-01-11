package com.cc.doctormhealth.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.feedback.FeedbackAgent;
import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback;
import com.cc.doctormhealth.R;
import com.cc.doctormhealth.model.LeanchatUser;
import com.cc.doctormhealth.service.PushManager;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.leancloud.chatkit.LCChatKit;

import static com.cc.doctormhealth.App.ctx;


public class SettingActivity extends Activity implements View.OnClickListener {

    @Bind(R.id.leftBtn)
    ImageView leftBtn;
    @Bind(R.id.account)
    LinearLayout account;
    @Bind(R.id.noti_news)
    LinearLayout notiNews;
    @Bind(R.id.fankui)
    LinearLayout fankui;
    @Bind(R.id.tv_volume)
    TextView tvVolume;
    @Bind(R.id.cache)
    LinearLayout cache;
    @Bind(R.id.logout)
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.leftBtn, R.id.account, R.id.noti_news, R.id.fankui, R.id.cache, R.id.logout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.leftBtn:
                finish();
                break;
            case R.id.account:
                Intent intent = new Intent();
                intent.setClass(SettingActivity.this, ChangePwdActivity.class);
                startActivity(intent);
                break;
            case R.id.noti_news:
                Intent intent5 = new Intent(SettingActivity.this, ProfileNotifySettingActivity.class);
                startActivity(intent5);
                break;
            case R.id.fankui:
                FeedbackAgent agent = new FeedbackAgent(SettingActivity.this);
                agent.startDefaultThreadActivity();
                break;
            case R.id.cache:
                Toast.makeText(SettingActivity.this, "清理完成", Toast.LENGTH_LONG).show();
                SystemClock.sleep(3000);
                tvVolume.setText("0M");
                break;
            case R.id.logout:
                LCChatKit.getInstance().close(new AVIMClientCallback() {
                    @Override
                    public void done(AVIMClient avimClient, AVIMException e) {
                    }
                });
                PushManager.getInstance().unsubscribeCurrentUserChannel();
                LeanchatUser.logOut();
                finish();
                Intent intent6 = new Intent(ctx, EntryLoginActivity.class);
                ctx.startActivity(intent6);
                break;
        }
    }
}

