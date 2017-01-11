package com.cc.doctormhealth.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMConversationQueryCallback;
import com.cc.doctormhealth.App;
import com.cc.doctormhealth.R;
import com.cc.doctormhealth.event.GroupItemClickEvent;
import com.cc.doctormhealth.util.ConversationUtils;
import com.cc.doctormhealth.viewholder.GroupItemHolder;

import java.util.List;

import butterknife.Bind;
import cn.leancloud.chatkit.adapter.LCIMCommonListAdapter;
import cn.leancloud.chatkit.utils.LCIMConstants;

/**
 * Created by lzw on 14-10-7.
 */
public class ConversationGroupListActivity extends AVBaseActivity {

  @Bind(R.id.activity_group_list_srl_view)
  protected RecyclerView recyclerView;

  LinearLayoutManager layoutManager;
  private LCIMCommonListAdapter<AVIMConversation> itemAdapter;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.group_list_activity);
    initView();

    setTitle(App.ctx.getString(R.string.conversation_group));
    refreshGroupList();
  }

  private void initView() {
    layoutManager = new LinearLayoutManager(this);
    recyclerView.setLayoutManager(layoutManager);
    itemAdapter = new LCIMCommonListAdapter<>(GroupItemHolder.class);
    recyclerView.setAdapter(itemAdapter);
  }

  private void refreshGroupList() {
    ConversationUtils.findGroupConversationsIncludeMe(new AVIMConversationQueryCallback() {
      @Override
      public void done(List<AVIMConversation> conversations, AVIMException e) {
        if (filterException(e)) {
          itemAdapter.setDataList(conversations);
          itemAdapter.notifyDataSetChanged();
        }
      }
    });
  }

  public void onEvent(GroupItemClickEvent event) {
    Intent intent = new Intent(ConversationGroupListActivity.this, ChatRoomActivity.class);
    intent.putExtra(LCIMConstants.CONVERSATION_ID, event.conversationId);
    startActivity(intent);
  }
}
