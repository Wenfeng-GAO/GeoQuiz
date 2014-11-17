package com.wenfeng.geoquiz;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	public static final String TAG = MainActivity.class.getSimpleName();
	private static int questionIndex = 0;
	private QuestionBank[] questions = {
			new QuestionBank(R.string.question_ocean, true),
			new QuestionBank(R.string.question_africa, false),
			new QuestionBank(R.string.question_american, false),
			new QuestionBank(R.string.question_asia, true),
			new QuestionBank(R.string.question_mideast, false)
	};
	
	TextView mQuestionTextView;
	Button mYesButton, mNoButton, mNextButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mQuestionTextView = (TextView) findViewById(R.id.textView_question);
		mYesButton = (Button) findViewById(R.id.button_yes);
		mNoButton = (Button) findViewById(R.id.button_no);
		mNextButton = (Button) findViewById(R.id.button_next);
		
		mYesButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				Toast.makeText(MainActivity.this, R.string.toastIncorrect, Toast.LENGTH_SHORT).show();
				answerQuestion(true);
			}
		});
		
		mNoButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				Toast.makeText(MainActivity.this, R.string.toastCorrect, Toast.LENGTH_SHORT).show();
				answerQuestion(false);
			}
		});
		
		mNextButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				updateQuestion();
			}
		});
		
		updateQuestion();
	}
	
	private void answerQuestion(boolean myAnswer) {
		if(questions[questionIndex].isCorrect() == myAnswer)
			Toast.makeText(MainActivity.this, R.string.toastCorrect, Toast.LENGTH_SHORT).show();
		else
			Toast.makeText(MainActivity.this, R.string.toastIncorrect, Toast.LENGTH_SHORT).show();
	}
	
	private void updateQuestion() {
		questionIndex = (questionIndex+1) % questions.length;
		mQuestionTextView.setText(questions[questionIndex].getQuestion());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
