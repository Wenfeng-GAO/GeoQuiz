package com.wenfeng.geoquiz;

import android.app.Activity;
import android.content.Intent;
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
	public static final String KEY_INTENT_EXTRA_CHEAT = "com.wenfeng.geoquiz.intent.extra.cheat";
	private boolean mAnswer;
	
	private Button buttonShowAnswer;
	private TextView textView;
	private String textViewAnswer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(savedInstanceState != null) {
			textViewAnswer = savedInstanceState.getString(KEY_SAVEDINSTANCESTATE_TEXTVIEW);
		} else {
			textViewAnswer = getResources().getString(R.string.textview_cheat_warning);
		}
		setContentView(R.layout.activity_cheat);
		
		buttonShowAnswer = (Button) findViewById(R.id.button_show_answer);
		textView = (TextView) findViewById(R.id.textview_warning);
		 mAnswer = getIntent().getBooleanExtra(KEY_INTENT_EXTRA_CHEAT, false);
		
		buttonShowAnswer.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				showAnswer(mAnswer);
//				setResult(RESULT_OK);
			}
		});
		textView.setText(textViewAnswer);
//		if(savedInstanceState != null) {
//			textView.setText(savedInstanceState.getString(KEY_SAVEDINSTANCESTATE_TEXTVIEW, this.getResources().getString(R.string.textview_cheat_warning).toString()));
//		}
		
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString(KEY_SAVEDINSTANCESTATE_TEXTVIEW, textView.getText().toString());
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
		
		Log.d(TAG, "getText:" + textView.getText());
		super.onPause();
		
		
	}

	@Override
	protected void onDestroy() {
		if(!textView.getText().equals(getResources().getString(R.string.textview_cheat_warning))) {
//			Intent intent = new Intent();
//			intent.putExtra("key", "Don't cheat!");
//			setResult(RESULT_OK, intent);
//			Log.d(TAG, "onPause");
			setResult(RESULT_OK);
		}
		Log.d(TAG, "onDestroy");
		super.onDestroy();
	}

	@Override
	protected void onStop() {
		Log.d(TAG, "onStop");
		super.onStop();
	}

}
