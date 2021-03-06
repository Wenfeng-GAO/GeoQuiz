package com.wenfeng.geoquiz;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private static final String TAG = MainActivity.class.getSimpleName();
	private static final String KEY_QUESTION_INDEX = "index";
	
	private int questionIndex = 0;
	
	private static QuestionBank[] questions = {
			new QuestionBank(R.string.question_ocean, true),
			new QuestionBank(R.string.question_africa, false),
			new QuestionBank(R.string.question_american, false),
			new QuestionBank(R.string.question_asia, true),
			new QuestionBank(R.string.question_mideast, false)
	};
	
	private TextView mQuestionTextView;
	private Button mYesButton, mNoButton;
	private ImageButton buttonForward, buttonBackward;
	private Button buttonShowAnswer;

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if(savedInstanceState != null) {
			questionIndex = savedInstanceState.getInt(KEY_QUESTION_INDEX, 0);
		}
		  	 
		setContentView(R.layout.activity_main);
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			ActionBar actionBar = getActionBar();
			actionBar.setSubtitle("Bodies of Water");
		}
		mQuestionTextView = (TextView) findViewById(R.id.textView_question);
		mYesButton = (Button) findViewById(R.id.button_yes);
		mNoButton = (Button) findViewById(R.id.button_no);
		buttonShowAnswer = (Button) findViewById(R.id.button_show_answer);
		buttonForward = (ImageButton) findViewById(R.id.image_button_forward);
		buttonBackward = (ImageButton) findViewById(R.id.image_button_backward);
		
		mYesButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				answerQuestion(true);
			}
		});
		
		mNoButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				answerQuestion(false);
			}
		});
		
		buttonForward.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				updateQuestion(v.getId());
			}
		});
		
		buttonBackward.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				updateQuestion(v.getId());
			}
		});
		
		buttonShowAnswer.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), CheatActivity.class);
				intent.putExtra(CheatActivity.KEY_INTENT_EXTRA_CHEAT, questions[questionIndex].isCorrect());
				startActivityForResult(intent, 0);
				Log.d(TAG, "after startActivity");
			}
		});
		
		mQuestionTextView.setText(questions[questionIndex].getQuestion());
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == RESULT_OK) {
			questions[questionIndex].setCheated(true);
		}
	}


	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "onDestroy");
	}



	@Override
	protected void onPause() {
		super.onPause();
		Log.d(TAG, "onPause");
	}



	@Override
	protected void onResume() {
		super.onResume();
		Log.d(TAG, "onResume");
	}



	@Override
	protected void onStart() {
		super.onStart();
		Log.d(TAG, "onStart");
	}



	@Override
	protected void onStop() {
		super.onStop();
		Log.d(TAG, "onStop");
	}



	private void answerQuestion(boolean myAnswer) {
		if(questions[questionIndex].isCheated()) {
			Toast.makeText(getApplicationContext(), R.string.toast_cheat, Toast.LENGTH_SHORT).show();
		} else {
			if(questions[questionIndex].isCorrect() == myAnswer)
				Toast.makeText(MainActivity.this, R.string.toastCorrect, Toast.LENGTH_SHORT).show();
			else
				Toast.makeText(MainActivity.this, R.string.toastIncorrect, Toast.LENGTH_SHORT).show();
		}
	}
	
	private void updateQuestion(int buttonId) {
		if(buttonId == R.id.image_button_forward) {
			questionIndex = (questionIndex+1) % questions.length;
		} else {
			questionIndex = (questionIndex-1+questions.length) % questions.length;
		}
		mQuestionTextView.setText(questions[questionIndex].getQuestion());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		Log.d(TAG, "onSaveInstanceState");
		outState.putInt(KEY_QUESTION_INDEX, questionIndex);
	}

}
