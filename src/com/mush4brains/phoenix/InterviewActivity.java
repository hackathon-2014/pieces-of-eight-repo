package com.mush4brains.phoenix;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RelativeLayout.LayoutParams;

public class InterviewActivity extends Activity implements OnClickListener{
  
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
    layout.setBackgroundColor(Color.rgb(163,  38,  56));
    setContentView(layout, params);    

    //get attacker type
    Bundle extras = getIntent().getExtras();
    if (extras != null) 
    {
      mAttacker = extras.getString("attacker");
      Toast.makeText(getApplicationContext(), mAttacker,Toast.LENGTH_SHORT).show();
    }
    
    //Display attacker type
    TextView textView = new TextView(this);
    textView.setText("You're facing a " + mAttacker);
    textView.setX(5);
    textView.setY(30);
    textView.setBackgroundColor(Color.BLACK);
    textView.setTextColor(Color.YELLOW);
    
    params = new RelativeLayout.LayoutParams(mScreenWidth * 9/10,70);
    textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
    layout.addView(textView, params);

    //Display initial question 
    textView = new TextView(this);
    textView.setText("In terrible situations such as this you must take personal inventory. Are you a man or a woman or both?");
    textView.setX(5);
    textView.setY(110);
    textView.setBackgroundColor(Color.BLACK);
    textView.setTextColor(Color.YELLOW);
    params = new RelativeLayout.LayoutParams(mScreenWidth * 9/10, 400);
    textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
    layout.addView(textView, params);
    
    Button button = new Button(this);
    button.setText("Man");
    button.setX((mScreenWidth - mScreenWidth * 8/10)/3);//   5);
    button.setY(mScreenHeight - 460);
    params = new RelativeLayout.LayoutParams(mScreenWidth * 8/10, 100);
    button.setId(mConstants.RESPONSE_ONE);
    button.setOnClickListener(this);
    layout.addView(button, params);
    
    button = new Button(this);
    button.setText("Woman");
    button.setX((mScreenWidth - mScreenWidth * 8/10)/3);//   5);
    button.setY(mScreenHeight - 340);
    params = new RelativeLayout.LayoutParams(mScreenWidth * 8/10, 100);
    button.setId(mConstants.RESPONSE_TWO);
    button.setOnClickListener(this);
    layout.addView(button, params);
    
    button = new Button(this);
    button.setText("I'm undecided");
    button.setX((mScreenWidth - mScreenWidth * 8/10)/3);//   5);
    button.setY(mScreenHeight - 220);
    params = new RelativeLayout.LayoutParams(mScreenWidth * 8/10, 100);
    button.setId(mConstants.RESPONSE_THREE);
    button.setOnClickListener(this);
    layout.addView(button, params);

  }

  @Override
  public void onClick(View v) {
    // TODO Auto-generated method stub
    
    if (v.getId() == mConstants.RESPONSE_ONE){
      
    }
    else if (v.getId() == mConstants.RESPONSE_TWO){
      
    }
    else if (v.getId() == mConstants.RESPONSE_THREE){
      
    }
    
  }
  
  
  
}
