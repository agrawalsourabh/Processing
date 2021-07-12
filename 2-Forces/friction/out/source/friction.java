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

public class friction extends PApplet {


Mover m;
Surface surface;

public void setup() {
    
    background(127);

    surface = new Surface();
    m = new Mover();
    
}

public void draw() {
    background(127);

    surface.drawSurface();
    surface.createPatch("sand");
    surface.createPatch("ice");

    if(m.location.x >= width/8 && m.location.x <= (0.75f*width)){
        PVector ice_friction = new PVector(0, 0);
        ice_friction.add(m.velocity.get());
        ice_friction.normalize();
        ice_friction.mult(-1);

        float ice_c = 0.001f;

        ice_friction.mult(ice_c);

        m.applyForce(ice_friction);
    }

    else if(m.location.x >= width/2 && m.location.x <= (0.75f*width)){
        PVector sand_friction = new PVector(0, 0);
        sand_friction.add(m.velocity.get());
        sand_friction.normalize();
        sand_friction.mult(-1);

        float sand_c = 0.05f;

        sand_friction.mult(sand_c);

        m.applyForce(sand_friction);
    }

    else{
        PVector friction = new PVector(0, 0);
        friction.add(m.velocity.get());
        friction.normalize();
        friction.mult(-1);

        float c = 0.01f;

        friction.mult(c);

        m.applyForce(friction);
    }
    

    m.update();
    m.drawBall();
}

class Surface{
    int surfaceHeight = 10;
    public void drawSurface(){
        fill(130, 98, 81);
        rect(0, height/2, width, surfaceHeight);
    }

    public void createPatch(String surfaceType){

        if(surfaceType == "sand"){
            fill(184, 174, 83);
            rect(width/2, height/2, width/4, surfaceHeight);
        }

        if(surfaceType == "ice"){
            fill(144, 192, 222);
            rect(width/8, height/2, width/4, surfaceHeight);
        }
        
    }
}


class Mover{
    PVector location;
    PVector velocity;
    PVector acceleration;

    Mover(){
        location = new PVector(0, height/2);
        velocity = new PVector(2, 0);
        acceleration = new PVector(0, 0);

    }

    public void checkEdge(){
        if(location.x >= width){
            velocity.x *= velocity.x;
            location.x = width;
        }
    }

    public void update(){
        velocity.add(acceleration);
        location.add(velocity);
        velocity.limit(5);
        acceleration.setMag(0);
    }

    public void applyForce(PVector force){
        acceleration.add(force);
    }

    public void drawBall(){
        stroke(0);
        strokeWeight(2);
        fill(122, 56, 20);

        ellipse(location.x + 25, location.y-15, 50, 50);
    }

}


  public void settings() {  size(800, 800); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "friction" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
