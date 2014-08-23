package com.mush4brains.phoenix;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.RelativeLayout.LayoutParams;

public class InterviewActivity extends Activity {
  
  private int mScreenWidth;
  private int mScreenHeight;
  private GlobalConstants mConstants = GlobalConstants.getInstance();
  private String mAttacker;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_interview);
    
    
    //portrait mode only
    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        
    //fullscreen
//    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//      WindowManager.LayoutParams.FLAG_FULLSCREEN);
    
    //get dimensions
    Context context = getApplicationContext();
    Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
    Point size = new Point();
    display.getSize(size);
    mScreenWidth = size.x;
    mScreenHeight = size.y;
    
    //create layout
    RelativeLayout layout = new RelativeLayout(this);
    LayoutParams params = null;
    params = new RelativeLayout.LayoutParams(size.x * 94/100, size.y * 94/100);
    layout.setBackgroundColor(Color.RED);
    setContentView(layout, params);    
    
    Bundle extras = getIntent().getExtras();
    if (extras != null) 
    {
      //String value = extras.getString("key");
      mAttacker = extras.getString("attacker");
      Toast.makeText(getApplicationContext(), mAttacker,Toast.LENGTH_SHORT).show();
    }
    

    
    
  }
}
