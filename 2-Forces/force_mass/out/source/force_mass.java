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
    
    background(127);

    movers = new Mover[5];

    for(int i=0 ; i<movers.length ; i++){
        movers[i] = new Mover();
    }
    
}

public void draw() {
    background(127);
    
    for(Mover m : movers){
        PVector gravity = new PVector(0, 0.1f);
        PVector wind = new PVector(0.01f, 0);

        gravity.mult(m.mass);
        m.applyForce(gravity);
        m.applyForce(wind);

        m.edges();
        m.update();
        m.drawBall();
    }
    
}


class Mover{
    PVector location;
    PVector velocity;
    PVector acceleration;

    float mass;

    Mover(){
        location = new PVector(random(width), 0);
        velocity = new PVector(0, 0);
        acceleration = new PVector(0, 0.1f);

        mass = random(0.5f, 4);
    }

    public void update(){
        velocity.add(acceleration);

        location.add(velocity);

        velocity.limit(5);

        acceleration.setMag(0);
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

    public void applyForce(PVector force){

        PVector f = PVector.div(force, mass);
        acceleration.add(f);
    }

    public void drawBall(){
        stroke(0);
        strokeWeight(2);
        fill(122, 56, 20);

        ellipse(location.x, location.y, mass*50, mass*50);
    }

}

  public void settings() {  size(800, 800); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "force_mass" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
