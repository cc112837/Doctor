package com.cc.doctormhealth.activity;

import android.os.Bundle;
import android.view.View;

import com.avos.avoscloud.im.v2.AVIMConversation;

import cn.leancloud.chatkit.activity.LCIMConversationFragment;

/**
 * Created by wli on 16/7/11.
 */
public class ConversationFragment extends LCIMConversationFragment {

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void setConversation(AVIMConversation conversation) {
        super.setConversation(conversation);
    }
}
