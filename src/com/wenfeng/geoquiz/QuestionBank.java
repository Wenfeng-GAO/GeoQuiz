package com.wenfeng.geoquiz;

public class QuestionBank {
	private int mQuestion;
	private boolean mCorrect;
	private boolean mCheated;
	
	public QuestionBank(int question, boolean correct) {
		mQuestion = question;
		mCorrect = correct;
		mCheated = false;
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

	public boolean isCheated() {
		return mCheated;
	}

	public void setCheated(boolean cheated) {
		mCheated = cheated;
	}
	
}
