package com.cc.doctormhealth.activity;

import android.os.Bundle;
import android.widget.ImageView;

import com.cc.doctormhealth.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by lzw on 14-9-24.
 */
public class ProfileNotifySettingActivity extends AVBaseActivity {

    @Bind(R.id.leftBtn)
    ImageView leftBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_setting_notify_layout);
        ButterKnife.bind(this);
        setTitle(R.string.profile_notifySetting);
    }

    @OnClick(R.id.leftBtn)
    public void onClick() {
        finish();
    }
}
