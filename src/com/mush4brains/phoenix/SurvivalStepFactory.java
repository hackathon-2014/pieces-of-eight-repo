package com.mush4brains.phoenix;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.content.res.AssetManager;
import android.util.Log;

public class SurvivalStepFactory {

	private static final String TAG = "SurvivalStepFactory";

	private List<SurvivalStep> mSteps;

	private AssetManager mAssetManager;

	public SurvivalStepFactory(AssetManager assetManager) {
		this.mSteps = new ArrayList<SurvivalStep>();
		this.mAssetManager = assetManager;
	}

	public static SurvivalStep getTestSurvivalStep() {
		SurvivalStep step = new SurvivalStep(1, "Is the Zombie slow or fast?");
		step.Answers.put(2, "Slow");
		step.Answers.put(3, "Fast");
		return step;
	}

	public SurvivalStep getSurvivalStep(int stepId) {
		for (int i = 0; i < mSteps.size(); i++) {
			SurvivalStep step = mSteps.get(i);
			if (step.getStepId() == stepId) {
				return step;
			}
		}

		// if not found, return a dummy step.
		return getTestSurvivalStep();
	}

	public List<SurvivalStep> getSurvivalSteps() {
		return this.mSteps;
	}

	public void LoadData(String filename) {
		readDataFile(filename);
	}

	private void readDataFile(String filename) {
		try {
			InputStream inputStream = mAssetManager.open(filename);

			if (inputStream != null) {
				InputStreamReader isr = new InputStreamReader(inputStream);
				BufferedReader br = new BufferedReader(isr);
				String receiveString = "";

				// first line in the file is the header row.
				receiveString = br.readLine();

				// start reading at the 2nd line of the file.
				while ((receiveString = br.readLine()) != null) {
					String[] dataArray;
					dataArray = receiveString.split("\t");

					SurvivalStep step = new SurvivalStep();
					step.setStepId(Integer.parseInt(dataArray[0]));
					step.setQuestionText(dataArray[1]);
					step.Answers.put(Integer.parseInt(dataArray[2]),
							dataArray[3]);

					if (dataArray.length > 4) {
						for (int i = 4; i < dataArray.length; i += 2) {
							step.Answers.put(Integer.parseInt(dataArray[i]),
									dataArray[i + 1]);
						}
					}

					mSteps.add(step);
				}

				inputStream.close();
			} else {
				Log.d("MainActivity", "Null input stream");
			}
		} catch (FileNotFoundException e) {
			Log.d(TAG, "FileNotFoundException: " + e.getMessage());
		} catch (IOException e) {
			Log.d(TAG, "IOException: " + e.getMessage());
		}
	}
}