import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class pendulam extends PApplet {

PendulamClass[] pendulums;
boolean recording = true;
public void setup() {
    
    pendulums = new PendulamClass[5];
    reset();
}

public void draw() {
    background(0);

    for(PendulamClass p : pendulums){
        p.update();
        p.display();
    }

    if(recording){
        saveFrame("output/image-####.png");
        fill(255, 0, 0);
    }
    else{
        fill(0, 255, 0);
    }
    ellipse(width/2, height-10, 10, 10);
}

public void reset(){

    for(int i=0 ; i<pendulums.length ; i++){
        pendulums[i] = new PendulamClass(new PVector(width/2, 0), 250, (PI/4 + i));
    }
    
}

public void mousePressed() {
    reset();
}

public void keyPressed() {
    if(key == 'r' || key == 'R'){
        recording = !recording;
    }    
}

class PendulamClass{
    PVector origin;

    float length;

    float theta;
    float aVelocity = 0.0f;
    float aAcceleration = 0.00f;

    float x;
    float y;

    float gravity;


    PendulamClass(PVector o, float len, float angle){
        origin = o;
        length = len;
        theta = angle;

        x = origin.x;
        y = origin.y;

        gravity = -0.5f;
    }

    public void display(){

        x = origin.x + (length * sin(theta));
        y = origin.y + (length * cos(theta));

        stroke(255);
        strokeWeight(2);
        fill(255);

        line(origin.x, origin.y, x, y);

        ellipse(x, y, 40, 40);
    }

    public void update(){

        aAcceleration = (gravity * sin(theta)) / length;

        aVelocity = aVelocity + aAcceleration;
        theta = theta + aVelocity;

        aVelocity = aVelocity * (1-0.005f);
    }
}

  public void settings() {  size(700, 700); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "pendulam" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
