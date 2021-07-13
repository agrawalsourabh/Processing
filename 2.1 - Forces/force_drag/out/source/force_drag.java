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

public class force_drag extends PApplet {

Mover[] movers;
Liquid l;

public void setup(){
  
  movers = new Mover[9];

  reset();

  l = new Liquid(0, height/2, width, height/2, 0.1f);
}

public void draw(){
  
  background(235, 225, 206);

  l.display();

  for(Mover m : movers){

    if(l.contains(m)){
      PVector drag_force = l.drag(m);
      m.applyForce(drag_force);
    }

    PVector gravity = new PVector(0, 0.1f*m.mass);
    m.applyForce(gravity);

    m.display();
    m.update();
    m.checkEdges();
  }
}

public void mousePressed() {
  reset();  
}

public void reset(){
  for(int i=0 ; i<movers.length ; i++){
    movers[i] = new Mover(random(0.5f, 3), 40+i*70, 0);
  }
}
class Liquid{
    float x;
    float y;
    float rect_width;
    float rect_height;

    float c;

    Liquid(float x_, float y_, float w, float h, float c_){
        x = x_;
        y = y_;
        rect_width = w;
        rect_height = h;
        c = c_;
    }

    public void display(){
        fill(105, 103, 99);
        rect(x, y, rect_width, rect_height);
    }

    public boolean contains(Mover m){
        PVector l = m.location;

        return l.x>x && l.x<rect_width+x && l.y>y && l.y<rect_height+y;
    }

    public PVector drag(Mover m){
        float speed = m.velocity.mag();
        float drag_mag = c*speed*speed;

        PVector drag_force = m.velocity.get();
        drag_force.mult(-1);

        drag_force.normalize();
        drag_force.mult(drag_mag);

        return drag_force;
    }
}
class Mover{
  PVector location;
  PVector velocity;
  PVector acceleration;

  float mass;
  float radius;
  
  Mover(float m, float x, float y){
    mass = m;
    radius = mass * 8;

    location = new PVector(x, y);
    velocity = new PVector(0, 0);
    acceleration = new PVector(0, 0);
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
    fill(255, 100);
    stroke(0);
    strokeWeight(2);
    
    ellipse(location.x, location.y, radius*2, radius*2);
  }

  public void checkEdges(){
    if(location.y > height){
      velocity.y = velocity.y * -0.9f;
      location.y = height;
    }
  }

}
  public void settings() {  size(700, 700); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "force_drag" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
