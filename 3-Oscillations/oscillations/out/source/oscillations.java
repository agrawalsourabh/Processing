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

public class oscillations extends PApplet {

Mover[] movers;
float t;
public void setup() {
    
    movers = new Mover[1];
    t = 0;
    reset();
}

public void draw() {

    for(Mover m : movers){
        // m.update();
        m.updateWithNoise(t);
        m.display();
    }
    
    t += 0.1f;
}

public void mousePressed() {
    saveFrame("output.png");
    reset();
}

public void reset(){
    background(0);

    for(int i=0 ; i<movers.length ; i++){
        movers[i] = new Mover();
    }
}
class Mover{

    float locX;
    float locY;

    float theta;
    float r;
    float delta;

    float aVelocity;
    float aAcceleration;

    Mover(){

        locX = width/2;
        locY = height/2;

        theta = 0.0f;
        r = 200;
        delta = 50/r;

        aVelocity = 0;
        aAcceleration = 0.001f;
    }

    public void update(){

        aVelocity = aVelocity + aAcceleration;
        theta = theta + aVelocity;

        aVelocity = constrain(aVelocity, 0.001f, 0.01f);

        if(r <= 0){
            aAcceleration = 0.0f;
            aVelocity = 0.0f;
            r = 0.0f;
        }
        else{
             r = r-delta;
        }
    }

    // with perlin noise
    public void updateWithNoise(float t){

        float d = noise(t);
        d = map(d, 0, 1, 0, 0.2f);

        aVelocity = aVelocity + aAcceleration;
        theta = theta + aVelocity;

        aVelocity = constrain(aVelocity, 0.001f, 0.01f);

        if(r <= 0){
            aAcceleration = 0.0f;
            aVelocity = 0.0f;
            r = 0.0f;
        }
        else{
             r = r-d;
        }
    }

    public void display(){

        float x = r*cos(theta);
        float y = r*sin(theta);

        pushMatrix();
        translate(locX, locY);
        rotate(theta);
        stroke(181, 76, 34, 75);
        strokeWeight(1);
        line(0, 0, x, y);
        strokeWeight(1);
        fill(255);
        ellipse(x, y, 1, 1);
        popMatrix();
    }
}
  public void settings() {  size(700, 512); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "oscillations" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
