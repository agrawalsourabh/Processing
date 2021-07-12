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

public class force extends PApplet {


Mover m;

public void setup() {
    
    m = new Mover();
}

public void draw() {
    background(127);
    PVector gravity = new PVector(0, 0.3f);
    m.applyForce(gravity);

    if(mousePressed){
        PVector wind = new PVector(0.3f, 0);
        m.applyForce(wind);
    }
   
    m.update();
    m.edges();
    m.display();
    
}

class Mover{
    PVector location;
    PVector velocity;
    PVector acceleration;

    Mover(){
        location = new PVector(width/2, height/2);
        velocity = new PVector(0, 0);
        acceleration = new PVector(0, 0);
    }

    public void applyForce(PVector force){
        acceleration.add(force);
    }

    public void update(){
        velocity.add(acceleration);
        location.add(velocity);

        acceleration.mult(0);
    }

    public void display(){
        stroke(0);
        strokeWeight(2);
        fill(127);

        ellipse(location.x, location.y, 48, 48);
    }

    public void edges(){

       if(location.x > width){
            location.x = width;
            velocity.x = velocity.x * -1;
        }
        else if(location.x < 0){
            velocity.x = velocity.x * -1;
            location.x = 0;
        }
        if(location.y >= height ){
            velocity.y = velocity.y * -1;
            location.y = height;
        }
    }
}
  public void settings() {  size(640, 360); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "force" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
