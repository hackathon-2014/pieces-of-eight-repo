package com.mush4brains.phoenix;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.os.AsyncTask;

public class SurvivalStepFactory {

	private List<SurvivalStep> mSteps;

	private String mFilepath;

	public SurvivalStepFactory(String filepath) {
		this.mSteps = new ArrayList<SurvivalStep>();
		this.mFilepath = filepath;
		readDataFile();
	}

	public static SurvivalStep getTestSurvivalStep() {
		SurvivalStep step = new SurvivalStep(1, "Is the Zombie slow or fast?");
		step.Answers.put(2, "Slow");
		step.Answers.put(3, "Fast");
		return step;
	}

	public SurvivalStep getSurvivalStep(int stepId) {
		SurvivalStep step = mSteps.get(stepId);
		return step;
	}

	public List<SurvivalStep> getSurvivalSteps() {
		return this.mSteps;
	}

	private void readDataFile() {
		try {
			InputStream inputStream = new FileInputStream(mFilepath);

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
			}
		} catch (FileNotFoundException e) {

		} catch (IOException e) {

		}
	}

	public void LoadData() {
		new AsyncDataFileReader().execute(mFilepath);
	}

	private class AsyncDataFileReader extends AsyncTask<String, Void, String> {
		@Override
		protected String doInBackground(String... params) {
			try {
				InputStream inputStream = new FileInputStream(mFilepath);

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
								step.Answers.put(
										Integer.parseInt(dataArray[i]),
										dataArray[i + 1]);
							}
						}

						mSteps.add(step);
					}

					inputStream.close();
				}
			} catch (FileNotFoundException e) {

			} catch (IOException e) {

			}

			return "Done";
		}

		@Override
		protected void onPostExecute(String result) {
			//
		}
	}
}
