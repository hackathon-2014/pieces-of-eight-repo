package com.mush4brains.phoenix;

import java.util.HashMap;
import java.util.Map;

public class SurvivalStep {

	private int stepId;

	private String questionText;

	public Map<Integer, String> Answers;

	public SurvivalStep() {
		this.Answers = new HashMap<Integer, String>();
	}

	public SurvivalStep(int stepId, String questionText) {
		this.stepId = stepId;
		this.questionText = questionText;
		this.Answers = new HashMap<Integer, String>();
	}

	public int getResponseCount() {
		return Answers.size();
	}

	public int getResponseNextId(int responseIndex) {

		int count = 0;
		for (Map.Entry<Integer, String> entry : this.Answers.entrySet()) {
			if (count == responseIndex)
				return entry.getKey();
			count++;
		}

		return -1;
	}

	public String getResponseText(int responseIndex) {
		int count = 0;
		for (Map.Entry<Integer, String> entry : this.Answers.entrySet()) {
			if (count == responseIndex)
				return entry.getValue();
			count++;
		}

		return "";
	}

	public int getStepId() {
		return this.stepId;
	}

	public void setStepId(int id) {
		this.stepId = id;
	}

	public String getQuestionText() {
		return this.questionText;
	}

	public void setQuestionText(String question) {
		this.questionText = question;
	}
}
