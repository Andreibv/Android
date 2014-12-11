package com.example.gestiodonto;
 

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

public class Splash extends ActionBarActivity {
	 protected static final int TIMER_RUNTIME = 4000; // in ms --> 10s

	 protected boolean mbActive;
	     protected ProgressBar mProgressBar;
	     @Override
	     public void onCreate(final Bundle savedInstanceState) {
	       super.onCreate(savedInstanceState);
	       setContentView(R.layout.activity_splash);
	       mProgressBar = (ProgressBar) findViewById(R.id.progressSplash);

	       Vibrator vibrator =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
	       vibrator.vibrate(2000);
	 final Thread timerThread = new Thread() {
	           @Override
	           public void run() {
	               mbActive = true;
	               try {
	                   int waited = 0;
	                   while(mbActive && (waited < TIMER_RUNTIME)) {
	                       sleep(200);
	                       if(mbActive) {
	                           waited += 200;
	                           updateProgress(waited);
	                       }
	                   }
	           } catch(InterruptedException e) {
	               // do nothing
	           } finally {
	               onContinue();
	           }
	         }
	      };
	      timerThread.start();
	    }
	    @Override
	    public void onDestroy() {
	        super.onDestroy();
	    }
	    public void updateProgress(final int timePassed) {
	        if(null != mProgressBar) {
	            // Ignore rounding error here
	            final int progress = mProgressBar.getMax() * timePassed / TIMER_RUNTIME;
	            mProgressBar.setProgress(progress);
	        }
	    }

	 public void onContinue() {
	 	Intent i = new Intent(getApplicationContext(),MainActivity.class);
	 	startActivity(i);
	 	 	 
	    }
 
}
