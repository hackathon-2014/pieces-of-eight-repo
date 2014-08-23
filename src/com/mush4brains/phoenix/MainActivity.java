package com.mush4brains.phoenix;

import android.app.ActionBar;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

/* MainActivity entry point to app
 * Team: Chuck, Derick, and Sara
 */
public class MainActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    
    //portrait mode only
    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        
    //fullscreen
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
      WindowManager.LayoutParams.FLAG_FULLSCREEN);
    
    //set title
    ActionBar actionBar = getActionBar();
    actionBar.setTitle("Office Survivor Guide");
    actionBar.setSubtitle("Ignore Everyone Else");
    actionBar.setBackgroundDrawable(new ColorDrawable(Color.RED));
    
    //create layout
    RelativeLayout layout = new RelativeLayout(this);
    LayoutParams params = null;
    params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    setContentView(layout, params);
    
    
  }
}
