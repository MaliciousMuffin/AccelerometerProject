package processing.test.attempt2;

import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import android.content.Context; 
import android.hardware.Sensor; 
import android.hardware.SensorManager; 
import android.hardware.SensorEvent; 
import android.hardware.SensorEventListener; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class attempt2 extends PApplet {







Context context;
SensorManager manager;
Sensor sensor;
AccelerometerListener listener;
float ax, ay, az;
//float boxX = width/2;
//float boxY = height+250;
float boxWidth = 200;
float boxHeight = 50;
float boxY = height + 1500;
float speed = 2;
float ballX = random(400);
float ballY = 50;
float yDir = 3;
float xPos = 600;
int score = 0;




public void setup(){
 //size(400, 400); 
 rectMode(CORNER);
 
 noLoop();
   context = getActivity();
   manager = (SensorManager)context.getSystemService(Context.SENSOR_SERVICE);
   sensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
   listener = new AccelerometerListener();
   manager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_GAME);
   
   String s = "Tilt the phone to dodge the balls! Click the screen to start!";
   textSize(32);
   text(s, width/2, height/2);
   
   
 
}






public void Ball(){
  ellipse(ballX, ballY, 80, 80);
  
    ballY = ballY + yDir;
  
  if(ballY > height && intersectsBox(ballX, ballX) == false){
    yDir++;
    ballY = 50;
    ballX = random(400);
    ellipse(ballX, ballY, 40, 40); 
    score = score + 1;
  }
}






public void draw(){

  //background(0);
//if (mousePressed){
  background(0);
  String t = "Tilt the phone to dodge the ball!";
  
  textSize(50);
  text(t, width/5, height/4);
  
  text(score, width/2, height/2);
  
  
  if(intersectsBox(ballX, ballY)){
    //color with red
   fill(255, 0, 0);
   score = score - 1;
  }
  else{
    //color with green
    fill(0, 255, 0);
  }
  
  
  if (xPos > width-boxWidth){
     String r = "COME BACK!! TILT LEFT!!";
     text(r, width/5, height/3);
  }
  if (xPos < boxWidth){
     String r = "COME BACK!! TILT RIGHT!!";
     text(r, width/5, height/3);
  }
  
  xPos -= ax;
  rect(xPos, boxY, boxWidth, boxHeight);

  System.out.println(xPos);
  
  Ball();
//}
}






class AccelerometerListener implements SensorEventListener {
  public void onSensorChanged(SensorEvent event) {
    ax = event.values[0];
    ay = event.values[1];
    az = event.values[2];    
  }
  public void onAccuracyChanged(Sensor sensor, int accuracy) {
  }
}



public boolean intersectsBox(float x, float y){
  if(x > xPos && x < xPos + boxWidth){
   if(y > boxY && y < boxY + boxHeight){
    return true;
   } 
  }

  return false;
}

public void mousePressed(){
   loop(); 
}

/*
boolean intersectsBox(float x, float y){
  if(x > boxX && x < boxX + boxWidth){
   if(y > boxY && y < boxY + boxHeight){
    return true;
   } 
  }

  return false;
}

*/



/*
 void keyPressed() {
  if (key == CODED) {
    if (keyCode == LEFT) {
      boxX = boxX - speed;
    } else if (keyCode == RIGHT) {
      boxX = boxX + speed;
    }
  }
 
}
*/
  public void settings() {  fullScreen(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "attempt2" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
