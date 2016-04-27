package com.example.dragonne.myjourneytrackerfinal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Dragonne on 27/04/2016.
 */
public class Graph extends View{
    private int width,height;
    public Graph(Context context,AttributeSet attrs){
        super(context,attrs);
    }
    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        canvas.drawRect(0,0,width,height,paint);
        paint.setColor(Color.WHITE);
        canvas.drawLine(0,height/6,width,height/6,paint);//1st line
        canvas.drawLine(0,2*height/6,width,2*height/6,paint);//2nd line
        canvas.drawLine(0,3*height/6,width,3*height/6,paint); //3rd line
        canvas.drawLine(0,4*height/6,width,4*height/6,paint); //4th line
        canvas.drawLine(0,5*height/6,width,5*height/6,paint); //5th line
    }
    @Override
    protected void onSizeChanged(int xNew,int yNew,int xOld,int yOld){
        super.onSizeChanged(xNew,yNew,xOld,yOld);
        if(xNew<yNew){
            width = xNew;
            height = xNew;
        } else {
            width = yNew;
            height = yNew;
        }
    }

}
