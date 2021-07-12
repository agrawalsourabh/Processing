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

public class force_mass extends PApplet {

Mover[] movers;
public void setup() {
    

    movers = new Mover[5];

    for(int i=0 ; i<movers.length ; i++){
        movers[i] = new Mover();
    }
}

public void draw() {
    background(127);

    for(Mover m : movers){
        PVector gravity = new PVector(0, 0.3f);
        gravity.mult(m.mass);
        m.applyForce(gravity);

        if(mousePressed){
            PVector wind = new PVector(0.2f, 0);
            m.applyForce(wind);
        }

        m.update();
        m.edges();
        m.drawBall();
    }
    
}


class Mover{
    PVector location;
    PVector velocity;
    PVector acceleration;

    float mass;

    Mover(){
        location = new PVector(random(width), height/2);
        velocity = new PVector(0, 0);
        acceleration = new PVector(0, 0);

        mass = random(0.5f, 5);
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

    public void drawBall(){
        stroke(0);
        strokeWeight(2);
        fill(127, 100);

        ellipse(location.x, location.y, mass*30, mass*30);
    }

    public void edges(){
        if(location.x < 0){
            location.x = 0;
            velocity.x = velocity.x * -1;
        }

        if(location.x > width){
            location.x = width;
            velocity.x = velocity.x * -1;
        }

        if(location.y > height){
            location.y = height;
            velocity.y = velocity.y * -1;
        }
    }
}
  public void settings() {  size(500, 500); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "force_mass" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
