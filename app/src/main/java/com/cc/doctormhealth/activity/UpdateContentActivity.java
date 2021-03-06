package com.cc.doctormhealth.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.cc.doctormhealth.R;
import com.cc.doctormhealth.util.Constants;


/**
 * Created by lzw on 14-9-17.
 */
public class UpdateContentActivity extends AVBaseActivity {
  private TextView fieldNameView;
  private EditText valueEdit;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.update_content_layout);
    findView();
    init();
  }



  private void init() {
    Intent intent = getIntent();
    String fieldName = intent.getStringExtra(Constants.INTENT_KEY);
    String editHint = getString(R.string.chat_common_please_input_hint);
    String changeTitle = getString(R.string.chat_common_change_title);
    editHint = editHint.replace("{0}", fieldName);
    changeTitle = changeTitle.replace("{0}", fieldName);
    fieldNameView.setText(fieldName);
    valueEdit.setHint(editHint);
    setTitle(changeTitle);
  }

  public void updateContent() {
    Intent i = new Intent();
    i.putExtra(Constants.INTENT_VALUE, valueEdit.getText().toString());
    setResult(RESULT_OK, i);
    finish();
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();

    return super.onOptionsItemSelected(item);
  }

  private void findView() {
    fieldNameView = (TextView) findViewById(R.id.fieldName);
    valueEdit = (EditText) findViewById(R.id.valueEdit);
  }
}
