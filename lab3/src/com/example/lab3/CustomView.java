package com.example.lab3;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class CustomView extends View {

 private String name;
 private Paint paint;
 public CustomView(Context context, String name) {
  super(context);
  this.name =name;

  
  // TODO Auto-generated constructor stub
 }
 
 @Override
 protected void onDraw(Canvas canvas) {
  paint.setLinearText(true);
  //paint.setLinearText(name);
  canvas.drawText(name, 10, 10, 10, 10, paint);
  // TODO Auto-generated method stub
  super.onDraw(canvas);
  
  
 }
  

}