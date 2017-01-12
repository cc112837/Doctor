package com.cc.doctormhealth.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;

import com.cc.doctormhealth.R;
import com.cc.doctormhealth.fragment.MessageAskFragment;
import com.cc.doctormhealth.fragment.MyFragment;
import com.cc.doctormhealth.fragment.NewsFragment;
import com.cc.doctormhealth.fragment.OrderFragment;
import com.cc.doctormhealth.friends.ContactFragment;
import com.cc.doctormhealth.model.LeanchatUser;
import com.cc.doctormhealth.service.UpdateService;
import com.cc.doctormhealth.util.UserCacheUtils;


/**
 * 创建人：吴聪聪
 * 邮箱:cc112837@163.com
 * 主界面相关内容
 * 创建时间：2017/1/11 10:05
 */
public class MainActivity extends AVBaseActivity {
    public static final int FRAGMENT_N = 5;
    public static final int[] tabsNormalBackIds = new int[]{R.mipmap.order_grey,
            R.mipmap.ask_gray, R.mipmap.news_grey, R.mipmap.friend_grey, R.mipmap.person_grey};
    public static final int[] tabsActiveBackIds = new int[]{R.mipmap.order_blue,
            R.mipmap.ask_blue, R.mipmap.news_blue, R.mipmap.friend_blue, R.mipmap.person_blue};
    private static final String FRAGMENT_TAG_ORDER = "order";
    private static final String FRAGMENT_TAG_ASK = "ask";
    private static final String FRAGMENT_TAG_NEWS = "news";
    private static final String FRAGMENT_TAG_FRIEND = "friend";
    private static final String FRAGMENT_TAG_PERSON = "person";
    private static final String[] fragmentTags = new String[]{FRAGMENT_TAG_ORDER, FRAGMENT_TAG_ASK,
            FRAGMENT_TAG_NEWS, FRAGMENT_TAG_FRIEND, FRAGMENT_TAG_PERSON};
    Button btn_yuyue,btn_message, btn_contact, btn_news, btn_my;
    View fragmentContainer;
    ContactFragment contactFragment;
    NewsFragment newsFragment;
    MessageAskFragment messageAskFragment;
    MyFragment myFragment;
    OrderFragment orderFragment;
    Button[] tabs;
    View recentTips, contactTips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        init();
        btn_message.performClick();
        UserCacheUtils.cacheUser(LeanchatUser.getCurrentUser());
    }

    @Override
    protected void onResume() {
        super.onResume();
        UpdateService updateService = UpdateService.getInstance(this);
        updateService.checkUpdate();
    }


    private void init() {
        tabs = new Button[]{btn_yuyue,btn_message,btn_news, btn_contact,btn_my};
    }

    private void findView() {
        //预约界面
        btn_yuyue = (Button) findViewById(R.id.btn_yuyue);
        //咨询消息
        btn_message = (Button) findViewById(R.id.btn_message);
        //友圈列表
        btn_contact = (Button) findViewById(R.id.btn_contact);
        //资讯列表
        btn_news = (Button) findViewById(R.id.btn_news);
        //我的个人界面
        btn_my = (Button) findViewById(R.id.btn_my);

        fragmentContainer = findViewById(R.id.fragment_container);
        recentTips = findViewById(R.id.iv_recent_tips);
        contactTips = findViewById(R.id.iv_contact_tips);
    }

    public void onTabSelect(View v) {
        int id = v.getId();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        hideFragments(manager, transaction);
        setNormalBackgrounds();
        if (id == R.id.btn_yuyue) {
            if (orderFragment == null) {
                orderFragment = new OrderFragment();
                transaction.add(R.id.fragment_container, orderFragment, FRAGMENT_TAG_ORDER);
            }
            transaction.show(orderFragment);
        } else if (id == R.id.btn_message) {
            if (messageAskFragment == null) {
                messageAskFragment = new MessageAskFragment();
                transaction.add(R.id.fragment_container, messageAskFragment, FRAGMENT_TAG_ASK);
            }
            transaction.show(messageAskFragment);
        } else if (id == R.id.btn_contact) {
            if (contactFragment == null) {
                contactFragment = new ContactFragment();
                transaction.add(R.id.fragment_container, contactFragment, FRAGMENT_TAG_FRIEND);
            }
            transaction.show(contactFragment);
        } else if (id == R.id.btn_news) {
            if (newsFragment == null) {
                newsFragment = new NewsFragment();
                transaction.add(R.id.fragment_container, newsFragment, FRAGMENT_TAG_NEWS);
            }
            transaction.show(newsFragment);
        } else if (id == R.id.btn_my) {
            if (myFragment == null) {
                myFragment = new MyFragment();
                transaction.add(R.id.fragment_container, myFragment, FRAGMENT_TAG_PERSON);
            }
            transaction.show(myFragment);
        }
        int pos;
        for (pos = 0; pos < FRAGMENT_N; pos++) {
            if (tabs[pos] == v) {
                break;
            }
        }
        transaction.commit();
        tabs[pos].setTextColor(getResources().getColor(R.color.color_bottom_text_press));
        setTopDrawable(tabs[pos], tabsActiveBackIds[pos]);
    }

    private void setNormalBackgrounds() {
        for (int i = 0; i < tabs.length; i++) {
            Button v = tabs[i];
            v.setTextColor(getResources().getColor(R.color.color_bottom_text_normal));
            setTopDrawable(v, tabsNormalBackIds[i]);
        }
    }

    private void setTopDrawable(Button v, int resId) {
        v.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(resId), null, null);
    }

    private void hideFragments(FragmentManager fragmentManager, FragmentTransaction transaction) {
        for (int i = 0; i < fragmentTags.length; i++) {
            Fragment fragment = fragmentManager.findFragmentByTag(fragmentTags[i]);
            if (fragment != null && fragment.isVisible()) {
                transaction.hide(fragment);
            }
        }
    }
}
