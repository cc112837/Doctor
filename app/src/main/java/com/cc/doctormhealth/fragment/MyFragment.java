package com.cc.doctormhealth.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.GetCallback;
import com.cc.doctormhealth.R;
import com.cc.doctormhealth.model.LeanchatUser;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lzw on 14-9-17.
 */
public class MyFragment extends BaseFragment {
    @Bind(R.id.username)
    TextView username;
    @Bind(R.id.headView)
    ImageView headView;
    @Bind(R.id.money)
    RelativeLayout money;
    @Bind(R.id.pride)
    RelativeLayout pride;
    @Bind(R.id.setting)
    RelativeLayout setting;
    @Bind(R.id.about)
    RelativeLayout about;
    @Bind(R.id.share)
    RelativeLayout share;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        headerLayout.showTitle(R.string.profile_title);
    }


    @Override
    public void onResume() {
        super.onResume();
        refresh();
    }

    private void refresh() {
        LeanchatUser curUser = LeanchatUser.getCurrentUser();
        username.setText(curUser.getUsername());
        LeanchatUser.getCurrentUser().fetchInBackground(new GetCallback<AVObject>() {
            @Override
            public void done(AVObject avObject, AVException e) {
                LeanchatUser user = (LeanchatUser) avObject;
                String avatarUrl = user.getAvatarUrl();
                Picasso.with(getContext()).load(avatarUrl).placeholder(R.mipmap.doctor_man).error(R.mipmap.doctor_man).into(headView);
            }
        });
    }

//
//    @OnClick(R.id.profile_notifysetting_view)
//    public void onNotifySettingClick() {
//        Intent intent = new Intent(ctx, ProfileNotifySettingActivity.class);
//        ctx.startActivity(intent);
//    }
//
//
//    @OnClick(R.id.profile_logout_btn)
//    public void onLogoutClick() {
//        LCChatKit.getInstance().close(new AVIMClientCallback() {
//            @Override
//            public void done(AVIMClient avimClient, AVIMException e) {
//            }
//        });
//        PushManager.getInstance().unsubscribeCurrentUserChannel();
//        LeanchatUser.logOut();
//        getActivity().finish();
//        Intent intent = new Intent(ctx, EntryLoginActivity.class);
//        ctx.startActivity(intent);
//    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.money, R.id.pride, R.id.setting, R.id.about, R.id.share})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.money:
                break;
            case R.id.pride:
                break;
            case R.id.setting:
                break;
            case R.id.about:
                break;
            case R.id.share:
                break;
        }
    }
}
