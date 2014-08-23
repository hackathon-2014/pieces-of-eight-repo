package com.mush4brains.phoenix;

import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

public class InterviewActivity extends Activity implements OnClickListener {

	private int mScreenWidth;
	private int mScreenHeight;
	private GlobalConstants mConstants = GlobalConstants.getInstance();
	private String mAttacker;
	private SurvivalStepFactory mSurvival;
	private SurvivalStep mSurvivalStep;

	private String TAG = "InterviewActivity";

	private TextView mQuestionTextView;

	private Button mFirstResponseButton;
	private Button mSecondResponseButton;

	private int mFirstResponseQuestionId;
	private int mSecondResponseQuestionId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_interview);

		// portrait mode only
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		// get dimensions
		Context context = getApplicationContext();
		Display display = ((WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		mScreenWidth = size.x;
		mScreenHeight = size.y;

		// create layout
		RelativeLayout layout = new RelativeLayout(this);
		LayoutParams params = null;
		params = new RelativeLayout.LayoutParams(size.x * 94 / 100,
				size.y * 94 / 100);
		layout.setBackgroundColor(Color.rgb(163, 38, 56));
		setContentView(layout, params);
		mSurvival = new SurvivalStepFactory(context.getAssets());

		// get attacker type
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			mAttacker = extras.getString("attacker");
			// Toast.makeText(getApplicationContext(), mAttacker,
			// Toast.LENGTH_SHORT).show();
			if (mAttacker.equals("pirate")) {
				mSurvival.LoadData("text/pirates.txt");
				mSurvivalStep = mSurvival.getSurvivalStep(randInt(0, 2));
			} else if (mAttacker.equals("ninja")) {
				mSurvival.LoadData("text/ninjas.txt");
				mSurvivalStep = mSurvival.getSurvivalStep(randInt(0, 2));
			} else if (mAttacker.equals("zombie")) {
				mSurvival.LoadData("text/zombies.txt");
				mSurvivalStep = mSurvival.getSurvivalStep(randInt(0, 2));
			}
		}

		// Display attacker type
		TextView textView = new TextView(this);
		textView.setText("You're facing a " + mAttacker);
		textView.setX(5);
		textView.setY(30);
		textView.setBackgroundColor(Color.BLACK);
		textView.setTextColor(Color.YELLOW);
		params = new RelativeLayout.LayoutParams(mScreenWidth * 9 / 10, 70);
		textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
		layout.addView(textView, params);

		// Display initial question
		mQuestionTextView = new TextView(this);
		mQuestionTextView.setText(mSurvivalStep.getQuestionText());
		mQuestionTextView.setX(5);
		mQuestionTextView.setY(110);
		mQuestionTextView.setBackgroundColor(Color.BLACK);
		mQuestionTextView.setTextColor(Color.YELLOW);
		params = new RelativeLayout.LayoutParams(mScreenWidth * 9 / 10, 400);
		mQuestionTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
		layout.addView(mQuestionTextView, params);

		// Display first response button
		mFirstResponseButton = new Button(this);
		mFirstResponseButton.setText(mSurvivalStep.getResponseText(0));
		mFirstResponseButton.setX((mScreenWidth - mScreenWidth * 9 / 10) / 2);
		mFirstResponseButton.setY(mScreenHeight * 4 / 10);
		params = new RelativeLayout.LayoutParams(mScreenWidth * 84 / 100, 200);
		mFirstResponseButton.setId(mConstants.RESPONSE_ONE);
		mFirstResponseButton.setOnClickListener(this);
		layout.addView(mFirstResponseButton, params);
		mFirstResponseQuestionId = mSurvivalStep.getResponseNextId(0);

		// Display second response button
		mSecondResponseButton = new Button(this);
		mSecondResponseButton.setText(mSurvivalStep.getResponseText(1));
		mSecondResponseButton.setX((mScreenWidth - mScreenWidth * 9 / 10) / 2);
		mSecondResponseButton.setY(mScreenHeight * 7 / 10);
		params = new RelativeLayout.LayoutParams(mScreenWidth * 84 / 100, 200);
		mSecondResponseButton.setId(mConstants.RESPONSE_TWO);
		mSecondResponseButton.setOnClickListener(this);
		layout.addView(mSecondResponseButton, params);
		mSecondResponseQuestionId = mSurvivalStep.getResponseNextId(1);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == mConstants.RESPONSE_ONE) {
			mSurvivalStep = mSurvival.getSurvivalStep(mFirstResponseQuestionId);
			NextQuestion();
		} else if (v.getId() == mConstants.RESPONSE_TWO) {
			mSurvivalStep = mSurvival
					.getSurvivalStep(mSecondResponseQuestionId);
			NextQuestion();
		}
	}

	// Updates the UI for the next question.
	private void NextQuestion() {

		// Update the question text.
		mQuestionTextView.setText(mSurvivalStep.getQuestionText());

		// Update first response button.
		mFirstResponseQuestionId = mSurvivalStep.getResponseNextId(0);

		// A value of -1 indicates the terminating survival step.
		if (mFirstResponseQuestionId == -1) {
			Toast.makeText(this, mSurvivalStep.getQuestionText(),
					Toast.LENGTH_LONG).show();
			// try {
			// this.showDialog("Game Over");
			// } catch (Exception e) {
			// Log.d(TAG, e.getMessage());
			// }
			finish();
		}

		mFirstResponseButton.setText(mSurvivalStep.getResponseText(0));

		// Update second response button.
		mSecondResponseButton.setText(mSurvivalStep.getResponseText(1));
		mSecondResponseQuestionId = mSurvivalStep.getResponseNextId(1);
	}

	private void showDialog(final String alertMessage) throws Exception {
		AlertDialog.Builder builder = new AlertDialog.Builder(
				InterviewActivity.this);
		builder.setMessage(alertMessage);
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// do something positive
				Log.d(TAG, "Positive");
				dialog.dismiss();
			}
		});

		builder.setNegativeButton("Bummer",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Log.d(TAG, "Negative");
						dialog.dismiss();
					}
				});

		builder.show();
	}

	public static int randInt(int min, int max) {
		Random random = new Random();
		int num = random.nextInt((max - min) + 1) + min;
		return num;
	}
}
