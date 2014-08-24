package com.mush4brains.phoenix;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

public class MessageActivity extends Activity implements OnClickListener {

  private int mScreenWidth;
  private int mScreenHeight;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_message);
    
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
        size.y * 40 / 100);
    layout.setBackgroundColor(Color.rgb(163, 38, 56));
    layout.setX(10);
    layout.setY(200);    
    setContentView(layout, params);

    Bundle extras = getIntent().getExtras();
    if (extras != null) {
      TextView textView = new TextView(this);
      textView.setText(extras.getString("message"));
      textView.setGravity(Gravity.CENTER_HORIZONTAL);
      textView.setX((mScreenWidth - mScreenWidth * 9 / 10) / 4);
      textView.setY(30);
      textView.setBackgroundColor(Color.BLACK);
      textView.setTextColor(Color.YELLOW);
      params = new RelativeLayout.LayoutParams(mScreenWidth * 9 / 10, 240);
      textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
      layout.addView(textView, params);    
    }
    
    // Display first response button
    Button button = new Button(this);
    button.setText("OK");
    button.setX(mScreenWidth/2 - 60);
    button.setY(330);//mScreenHeight - 80);//550);//* 4 / 10);
    params = new RelativeLayout.LayoutParams(120, 70);
    button.setId(100);
    button.setOnClickListener(this);
    layout.addView(button, params);

    
  }
  
  public void onClick(View v){
    finish();
  }
  
}
