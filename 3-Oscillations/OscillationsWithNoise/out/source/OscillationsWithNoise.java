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

public class OscillationsWithNoise extends PApplet {

Mover m;
boolean  recording = false;
public void setup() {
    
    reset();
}

public void draw() {
    m.update();
    m.display();

    if(recording){
        saveFrame("output/image_####.png");
        fill(0, 255, 0);
    }
    else{
        fill(255, 0, 0);
    }

    ellipse(500, 500, 10, 10);
}

public void reset(){
    background(0);
    m = new Mover();
}

public void mousePressed() {
    reset();
}

public void keyPressed() {
    if(key == 'r' || key == 'R'){
        recording = !recording;
    }
}

class Mover{
    float theta;
    float aVelocity;
    float aAcceleration;

    float r;
    float c;

    float x;
    float y;

    Mover(){
        theta = 0;
        aVelocity = 0;
        aAcceleration = 0.001f;

        c = 250;
    }

    public void update(){
        
        aVelocity = aVelocity + aAcceleration;
        theta = aVelocity + theta;

        aVelocity = constrain(aVelocity, 0, 0.05f);

        if(theta >= TWO_PI){
            aVelocity = 0.0f;
            aAcceleration = 0.0f;
        }
        else{
            r = c * noise(frameCount);
        }
    }

    public void display(){

        stroke(255, 100);
        strokeWeight(1);
        

        x = r*cos(theta);
        y = r*sin(theta);

        pushMatrix();
        translate(width/2, height/2);

        line(0, 0, x, y);

        fill(153, 59, 52, 100);
        ellipse(x, y, 10, 10);
        popMatrix();
    }
}
  public void settings() {  size(512, 512); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "OscillationsWithNoise" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
