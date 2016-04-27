package com.example.dragonne.myjourneytrackerfinal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Dragonne on 27/04/2016.
 */
public class Graph extends View{
    public Graph(Context context,AttributeSet attrs){
        super(context,attrs);
    }
    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        Paint paint = new Paint();
    }
}
