package com.mush4brains.phoenix;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

/* MainActivity entry point to app
 * Team: Chuck, Derick, and Sara
 */
public class MainActivity extends Activity implements OnClickListener {

	private int mScreenWidth;
	private int mScreenHeight;
	private GlobalConstants mConstants = GlobalConstants.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.activity_main);

		// portrait mode only
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		// fullscreen
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		// set title
		ActionBar actionBar = getActionBar();
		actionBar.setTitle("Programmer's Office Survivor Guide");
		actionBar.setSubtitle("Ignore Everyone Else");
		actionBar.setBackgroundDrawable(new ColorDrawable(Color
				.rgb(163, 38, 56)));

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
		params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		setContentView(layout, params);

		int bambooId = getResources().getIdentifier("bamboo","drawable", this.getPackageName());
		
	//draws background
		if (Build.VERSION.SDK_INT >= 16)
		  layout.setBackground(getResources().getDrawable(bambooId));
		else
		  layout.setBackgroundDrawable(getResources().getDrawable(bambooId));
	
	
		// add text
		TextView textView = new TextView(this);
		textView.setText("Select your attacker...");
		textView.setX(5);
		textView.setY(5);
		params = new RelativeLayout.LayoutParams(mScreenWidth * 9 / 10, 100);
		textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
		layout.addView(textView, params);

		// Pirate
		ImageView imageView = new ImageView(this);
		imageView.setId(mConstants.ICON_MAIN_PIRATE);
		imageView.setX((mScreenWidth - 256) / 2);
		imageView.setY(100);
		imageView.setOnClickListener(this);
		StateListDrawable states = new StateListDrawable();
		int idResourcePressed = getResources().getIdentifier("ic_pressed",
				"drawable", this.getPackageName());
		int idResourcePirate = getResources().getIdentifier("ic_pirate",
				"drawable", this.getPackageName());
		states.addState(new int[] { android.R.attr.state_pressed },
				getResources().getDrawable(idResourcePressed));
		states.addState(new int[] {},
				getResources().getDrawable(idResourcePirate));
		imageView.setImageDrawable(states);
		params = new RelativeLayout.LayoutParams(256, 256);
		layout.addView(imageView, params);

		// ninja
		imageView = new ImageView(this);
		imageView.setId(mConstants.ICON_MAIN_NINJA);
		imageView.setX((mScreenWidth - 256) / 2);
		imageView.setY(400);
		imageView.setOnClickListener(this);
		states = new StateListDrawable();
		int idResourceNinja = getResources().getIdentifier("ic_ninja",
				"drawable", this.getPackageName());
		states.addState(new int[] { android.R.attr.state_pressed },
				getResources().getDrawable(idResourcePressed));
		states.addState(new int[] {},
				getResources().getDrawable(idResourceNinja));
		imageView.setImageDrawable(states);
		params = new RelativeLayout.LayoutParams(256, 256);
		layout.addView(imageView, params);

		// zombie
		imageView = new ImageView(this);
		imageView.setId(mConstants.ICON_MAIN_ZOMBIE);
		imageView.setX((mScreenWidth - 256) / 2);
		imageView.setY(700);
		imageView.setOnClickListener(this);
		states = new StateListDrawable();
		int idResourceZombie = getResources().getIdentifier("ic_zombie",
				"drawable", this.getPackageName());
		states.addState(new int[] { android.R.attr.state_pressed },
				getResources().getDrawable(idResourcePressed));
		states.addState(new int[] {},
				getResources().getDrawable(idResourceZombie));
		imageView.setImageDrawable(states);
		params = new RelativeLayout.LayoutParams(256, 256);
		layout.addView(imageView, params);
	}

	@Override
	public void onClick(View v) {

		if (v.getId() == mConstants.ICON_MAIN_NINJA) {

			Bundle bundle = new Bundle();
			bundle.putString("attacker", "ninja");
			Intent intent = new Intent(this, InterviewActivity.class);
			intent.putExtras(bundle);
			startActivity(intent);
		} else if (v.getId() == mConstants.ICON_MAIN_PIRATE) {

			Bundle bundle = new Bundle();
			bundle.putString("attacker", "pirate");
			Intent intent = new Intent(this, InterviewActivity.class);
			intent.putExtras(bundle);
			startActivity(intent);
		} else if (v.getId() == mConstants.ICON_MAIN_ZOMBIE) {

			Bundle bundle = new Bundle();
			bundle.putString("attacker", "zombie");
			Intent intent = new Intent(this, InterviewActivity.class);
			intent.putExtras(bundle);
			startActivity(intent);
		}

	}
}
