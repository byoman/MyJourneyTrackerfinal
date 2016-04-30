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
        canvas.drawRect(0, 0, width, height, paint);
        paint.setColor(Color.WHITE);
        canvas.drawLine(0, height / 6, width, height / 6, paint);//1st line
        canvas.drawLine(0, 2 * height / 6, width, 2 * height / 6, paint);//2nd line
        canvas.drawLine(0, 3 * height / 6, width, 3 * height / 6, paint); //3rd line
        canvas.drawLine(0, 4 * height / 6, width, 4 * height / 6, paint); //4th line
        canvas.drawLine(0,5*height/6,width,5*height/6,paint); //5th line
        if(MainActivity.useGPS){
            paint.setColor(Color.RED);
            canvas.drawLine(0,height-(MainActivity.ave*(height/60)), width,height-(MainActivity.ave*(height/60)), paint); // draw the line of the average speed
            paint.setColor(Color.GREEN);
            for(int i = 1; i<MainActivity.locs.size();i++){
                float old = MainActivity.locs.get(i-1).getSpeed()*3.6f;
                float cur = MainActivity.locs.get(i).getSpeed() * 3.6f;
                float w1 = (width/MainActivity.locs.size()+2)*(i-1);
                float w2=(width/MainActivity.locs.size()+2)*(i);
                float h1 = (height-(old*(height/60)));
                float h2 = (height-(cur*(height/60)));
                canvas.drawLine(w1,h1,w2,h2,paint);
            }
        }
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
