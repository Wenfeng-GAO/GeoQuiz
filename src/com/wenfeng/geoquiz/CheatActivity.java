package com.wenfeng.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends Activity {
	private static final String TAG = CheatActivity.class.getSimpleName();
	private static final String KEY_SAVEDINSTANCESTATE_TEXTVIEW = "textViewAnswer";
	private static final String KEY_SAVEDINSTANCESTATE_BUTTON_BACK = "buttonBack";
	public static final String KEY_INTENT_EXTRA_CHEAT = "com.wenfeng.geoquiz.intent.extra.cheat";
	private boolean mAnswer;
	
	private Button buttonShowAnswer;
	private TextView textView, textViewApi;
	private String textViewAnswer;
	private String buttonText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(savedInstanceState != null) {
			textViewAnswer = savedInstanceState.getString(KEY_SAVEDINSTANCESTATE_TEXTVIEW);
			buttonText = savedInstanceState.getString(KEY_SAVEDINSTANCESTATE_BUTTON_BACK);
		} else {
			textViewAnswer = getResources().getString(R.string.textview_cheat_warning);
			buttonText = getResources().getString(R.string.button_show_answer);
		}
		setContentView(R.layout.activity_cheat);
		
		buttonShowAnswer = (Button) findViewById(R.id.button_show_answer);
		textView = (TextView) findViewById(R.id.textview_warning);
		textViewApi = (TextView) findViewById(R.id.textView_api);
		textViewApi.setText("API Level " + Build.VERSION.SDK_INT);
		 mAnswer = getIntent().getBooleanExtra(KEY_INTENT_EXTRA_CHEAT, false);
		
		buttonShowAnswer.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				showAnswer(mAnswer);
				if(buttonShowAnswer.getText().equals(getResources().getString(R.string.button_go_back))) {
					finish();
				} else {
					buttonShowAnswer.setText(R.string.button_go_back);
				}
			}
		});
		textView.setText(textViewAnswer);
		buttonShowAnswer.setText(buttonText);
		
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString(KEY_SAVEDINSTANCESTATE_TEXTVIEW, textView.getText().toString());
		outState.putString(KEY_SAVEDINSTANCESTATE_BUTTON_BACK, buttonShowAnswer.getText().toString());
	}

	private void showAnswer(boolean isTrue) {
		if(isTrue) {
			textView.setText(R.string.buttonYes);
		} else {
			textView.setText(R.string.buttonNo);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cheat, menu);
		return true;
	}

	@Override
	protected void onPause() {
		Log.d(TAG, "onPause");
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		Log.d(TAG, "onDestroy");
		super.onDestroy();
	}

	@Override
	protected void onStop() {
		Log.d(TAG, "onStop");
		super.onStop();
	}

	@Override
	public void finish() {
		if(!textView.getText().equals(getResources().getString(R.string.textview_cheat_warning))) {
			setResult(RESULT_OK);
			Log.d(TAG, "finish Result sent");
		}
		super.finish();
	}

}
