package com.wenfeng.geoquiz;

public class QuestionBank {
	private int mQuestion;
	private boolean mCorrect;
	
	public QuestionBank(int question, boolean correct) {
		mQuestion = question;
		mCorrect = correct;
	}

	public int getQuestion() {
		return mQuestion;
	}

	public void setQuestion(int question) {
		mQuestion = question;
	}

	public boolean isCorrect() {
		return mCorrect;
	}

	public void setCorrect(boolean correct) {
		mCorrect = correct;
	}
	
}
