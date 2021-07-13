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

public class forces_friction extends PApplet {

Mover m;

public void setup() {
    
    m = new Mover();
}

public void draw() {
    background(127);

    if(mousePressed){
        println("mouse pressed");
        PVector friction = m.velocity.get();
        friction.normalize();

        float ice_friction = -0.01f;
        friction.mult(ice_friction);

        m.applyForce(friction);
    }
    

    m.update();
    m.display();
    m.edge();
}


class Mover{
    PVector location;
    PVector velocity;
    PVector acceleration;

    float mass;
    float radius;

    Mover(){
        location = new PVector(0, height/2);
        velocity = new PVector(0.7f, 0);
        acceleration = new PVector(0, 0);

        mass = 1;
        radius = (mass * 50) / 2;
    }

    public void applyForce(PVector force){
        PVector f = PVector.div(force, mass);
        acceleration.add(f);
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

        ellipse((location.x + radius), location.y, radius*2, radius*2);

        fill(255);
        line(0, (height/2 + radius), width, (height/2 + radius));

    }

    public void edge(){
        if((location.x + radius*2) > width){
            location.x = width - radius*2;
            velocity.mult(0);
        }
    }
}

  public void settings() {  size(700, 700); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "forces_friction" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
