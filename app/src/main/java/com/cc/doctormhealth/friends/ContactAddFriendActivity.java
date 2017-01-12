package com.cc.doctormhealth.friends;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.cc.doctormhealth.App;
import com.cc.doctormhealth.R;
import com.cc.doctormhealth.activity.AVBaseActivity;
import com.cc.doctormhealth.adapter.HeaderListAdapter;
import com.cc.doctormhealth.model.LeanchatUser;
import com.cc.doctormhealth.util.Constants;
import com.cc.doctormhealth.util.UserCacheUtils;
import com.cc.doctormhealth.view.RefreshableRecyclerView;
import com.cc.doctormhealth.viewholder.SearchUserItemHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.leancloud.chatkit.view.LCIMDividerItemDecoration;

/**
 * 查找好友页面
 */
public class ContactAddFriendActivity extends AVBaseActivity {

    @Bind(R.id.search_user_rv_layout)
    protected RefreshableRecyclerView recyclerView;

    @Bind(R.id.searchNameEdit)
    EditText searchNameEdit;
    @Bind(R.id.leftBtn)
    ImageView leftBtn;

    private HeaderListAdapter<LeanchatUser> adapter;
    private String searchName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_add_friend_activity);
        ButterKnife.bind(this);
        init();
        recyclerView.refreshData();
    }

    private void init() {
        setTitle(App.ctx.getString(R.string.contact_findFriends));
        adapter = new HeaderListAdapter<>(SearchUserItemHolder.class);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
       recyclerView.addItemDecoration(new LCIMDividerItemDecoration(this));
        recyclerView.setOnLoadDataListener(new RefreshableRecyclerView.OnLoadDataListener() {
            @Override
            public void onLoad(int skip, int limit, boolean isRefresh) {
                loadMoreFriend(skip, limit, isRefresh);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void loadMoreFriend(int skip, final int limit, final boolean isRefresh) {
        AVQuery<LeanchatUser> q = LeanchatUser.getQuery(LeanchatUser.class);
        q.whereContains(LeanchatUser.USERNAME, searchName);
        q.limit(Constants.PAGE_SIZE);
        q.skip(skip);
        LeanchatUser user = LeanchatUser.getCurrentUser();
        List<String> friendIds = new ArrayList<String>(FriendsManager.getFriendIds());
        friendIds.add(user.getObjectId());
        q.whereNotContainedIn(Constants.OBJECT_ID, friendIds);
        q.orderByDescending(Constants.UPDATED_AT);
        q.setCachePolicy(AVQuery.CachePolicy.NETWORK_ELSE_CACHE);
        q.findInBackground(new FindCallback<LeanchatUser>() {
            @Override
            public void done(List<LeanchatUser> list, AVException e) {
                UserCacheUtils.cacheUsers(list);
                recyclerView.setLoadComplete(list.toArray(), false);
            }
        });
    }

    @OnClick(R.id.searchBtn)
    public void search(View view) {
        searchName = searchNameEdit.getText().toString();
        recyclerView.refreshData();
    }

    @OnClick(R.id.leftBtn)
    public void onClick() {
        finish();
    }
}
