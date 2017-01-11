package com.cc.doctormhealth.activity;

import android.os.Bundle;

import com.cc.doctormhealth.R;


/**
 * Created by lzw on 14-9-24.
 */
public class ProfileNotifySettingActivity extends AVBaseActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.profile_setting_notify_layout);
    setTitle(R.string.profile_notifySetting);
  }
}
