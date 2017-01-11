package com.cc.doctormhealth.event;

import com.cc.doctormhealth.friends.AddRequest;

/**
 * Created by wli on 15/12/3.
 */
public class NewFriendItemClickEvent {
  public AddRequest addRequest;
  public boolean isLongClick;
  public NewFriendItemClickEvent(AddRequest request, boolean isLongClick) {
    addRequest = request;
    this.isLongClick = isLongClick;
  }
}
