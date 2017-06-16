package com.mygt.vrapp;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;

public class SmoothHorizontalScrollView extends HorizontalScrollView{
    final String TAG = "SmoothHorizontalScrollView";
    public SmoothHorizontalScrollView(Context context) {
        this(context, null, 0);
    }
    public SmoothHorizontalScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public SmoothHorizontalScrollView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}


    @Override
    public void scrollBy(int dx, int dy) {
    	super.scrollBy(dx, dy);
    }

    @Override
    public void computeScroll(){
        super.computeScroll();
        
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        return super.dispatchKeyEvent(event);
    }

    
    @Override
    protected int computeScrollDeltaToGetChildRectOnScreen(Rect rect) {
        if (getChildCount() == 0) {
        	return 0;
        }
        int width = getWidth();
        int screenLeft = getScrollX();
        int screenRight = screenLeft + width;

        Log.i(TAG,"width "+width);
        Log.i(TAG,"screenLeft "+screenLeft);
        Log.i(TAG,"screenRight "+screenRight);
        Log.i(TAG,"rect.left "+rect.left);
        Log.i(TAG,"rect.right "+rect.right);
        //leave room for left fading edge for the first time//////
        int fadingEdge = 60 ;

        // leave room for left fading edge as long as rect isn't at very left
        if (rect.left > 0) {
            screenLeft += fadingEdge;
        }

        // leave room for right fading edge as long as rect isn't at very right
        if (rect.right < getChildAt(0).getWidth()) {
            screenRight -= fadingEdge;
        }
        Log.i(TAG,"screenLeft "+screenLeft);
        Log.i(TAG,"screenRight "+screenRight);
        int scrollXDelta = 0;

        if (rect.right > screenRight && rect.left > screenLeft) {
            // need to move right to get it in view: move right just enough so
            // that the entire rectangle is in view (or at least the first
            // screen size chunk).

            if (rect.width() > width) {
                // just enough to get screen size chunk on
                scrollXDelta += (rect.left - screenLeft);
            } else {
                // get entire rect at right of screen
                scrollXDelta += (rect.right - screenRight);
            }
            Log.i(TAG,"scrollXDelta "+scrollXDelta);
            // make sure we aren't scrolling beyond the end of our content
            int right = getChildAt(0).getRight();
            int distanceToRight = right - screenRight;
            scrollXDelta = Math.min(scrollXDelta, distanceToRight);

        } else if (rect.left < screenLeft && rect.right < screenRight) {
            // need to move right to get it in view: move right just enough so that
            // entire rectangle is in view (or at least the first screen
            // size chunk of it).

            if (rect.width() > width) {
                // screen size chunk
                scrollXDelta -= (screenRight - rect.right);
            } else {
                // entire rect at left
                scrollXDelta -= (screenLeft - rect.left);
            }
            Log.i(TAG,"scrollXDelta "+scrollXDelta);
            // make sure we aren't scrolling any further than the left our content
            scrollXDelta = Math.max(scrollXDelta, -getScrollX());
        }
        return scrollXDelta;
    }
    
    @Override
    protected boolean onRequestFocusInDescendants(int direction,
            Rect previouslyFocusedRect) {
        if(previouslyFocusedRect != null){
            if (direction == View.FOCUS_FORWARD) {
                direction = View.FOCUS_RIGHT;
            } else if (direction == View.FOCUS_BACKWARD) {
                direction = View.FOCUS_LEFT;
            }
        	View nextFocus = FocusFinder.getInstance().findNextFocusFromRect(this,
                    previouslyFocusedRect, direction);
            if (nextFocus == null) {
                return false;
            }
            return nextFocus.requestFocus(direction, previouslyFocusedRect);
        }else{
            ViewGroup vGroup = (ViewGroup) this.getChildAt(0);
            int count = vGroup.getChildCount();
            View child = null;
            if ((direction & FOCUS_FORWARD) != 0) {
            	//向前时，选择第一个，即id最小的一个
            	for (int i = 0; i < count; i++) {
					if(child != null && child.getVisibility() == View.VISIBLE){
						View temp = vGroup.getChildAt(i);
						if(temp.getVisibility()==View.VISIBLE && temp.getId() < child.getId()){
							child = temp;
						}
					}else{
						child = vGroup.getChildAt(i);
					}
				}
            } else {
            	//向后选择最后一个,即id最大的一个
            	for (int i = 0; i < count; i++) {
					if(child != null && child.getVisibility()==View.VISIBLE){
						View temp = vGroup.getChildAt(i);
						if(temp.getVisibility()==View.VISIBLE && temp.getId() > child.getId()){
							child = temp;
						}
					}else{
						child = vGroup.getChildAt(i);
					}
				}
            }
            if (child != null && child.getVisibility()==View.VISIBLE) {
                if (child.requestFocus(direction, previouslyFocusedRect)) {
                    return true;
                }
            }
            return false;
        }
    }
}
