package com.example.myapplication;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.view.GestureDetectorCompat;

public class SwipeLeftRight extends AppCompatActivity {

    private GestureDetectorCompat mGestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipeleftright);

        mGestureDetector = new GestureDetectorCompat(this, new GestureListener());

    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener{

        @Override
        public boolean onFling(MotionEvent downEvent, MotionEvent moveEvent, float velocityX, float velocityY) {
            boolean result = false;
            float diffy = moveEvent.getY() - downEvent.getY();
            float diffx = moveEvent.getX() - downEvent.getX();

            if(Math.abs(diffx)> Math.abs(diffy)){
                //left or right swipe
                if (Math.abs(diffx) > 100 && Math.abs(velocityX) > 100 ){
                    if(diffx > 0){
                        onSwipeRight();
                    }
                    else
                        {
                        onSwipeLeft();
                    }
                    result = true;
                }
            }
            return result;
        }
    }

    private void onSwipeRight() {
        Toast.makeText(SwipeLeftRight.this, "Swipe Right",Toast.LENGTH_SHORT).show();
    }

    private void onSwipeLeft() {
        Toast.makeText(SwipeLeftRight.this, "Swipe Left",Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}