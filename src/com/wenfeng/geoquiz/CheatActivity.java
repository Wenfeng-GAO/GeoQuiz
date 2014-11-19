package com.wenfeng.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends Activity {
	private static final String TAG = CheatActivity.class.getSimpleName();
	
	public static final String KEY_INTENT_EXTRA_CHEAT = "com.wenfeng.geoquiz.intent.extra.cheat";
	private boolean mAnswer;
	
	private Button buttonShowAnswer;
	private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cheat);
		
		buttonShowAnswer = (Button) findViewById(R.id.button_show_answer);
		textView = (TextView) findViewById(R.id.textview_warning);
		 mAnswer = getIntent().getBooleanExtra(KEY_INTENT_EXTRA_CHEAT, false);
		
		buttonShowAnswer.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				showAnswer(mAnswer);
				setResult(RESULT_OK);
			}
		});
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

}
