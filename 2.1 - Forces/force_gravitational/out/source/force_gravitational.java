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

public class force_gravitational extends PApplet {

Mover[] movers;
Attractor a;
int count;
public void setup() {
    
    movers = new Mover[10];
    count = 0;
    reset();
}

public void draw() {
    a.display();

    for(Mover m : movers){
        PVector force = a.attract(m);
        m.applyForce(force);

        PVector wind = new PVector(0.01f, 0);
        m.applyForce(wind);

        m.update();
        m.display(a);
        m.checkEdges();
    }
}

public void mousePressed() {

    saveFrame("image-"+count+".png");
    count = count + 1;
    reset();
}

public void reset(){
    background(127);
    a = new Attractor();

    for(int i=0 ; i<movers.length ; i++){
        movers[i] = new Mover();
    }
    
    
}

class Attractor{
    PVector location;

    float mass;
    float G;

    Attractor(){
        location = new PVector(width/2, height/2);
        mass = 5;
        G = 1;
    }

    public PVector attract(Mover m){
        // F = ((G*m1*m2)/d*d) * unit_r

        PVector force = PVector.sub(location, m.location);
        float d = force.mag();

        d = constrain(d, 5, 25);
        force.normalize();
        
        float strength = (G * mass * m.mass) / (d*d);

        force.mult(strength);

        return force;
    }

    public void display(){
        stroke(153, 61, 43);
        strokeWeight(3);
        fill(153, 61, 43, 1);

        ellipse(location.x, location.y, mass*16, mass*16);
    }
}
class Mover{
    PVector location;
    PVector velocity;
    PVector acceleration;

    float mass;

    Mover(){
        location = new PVector(random(width), random(height));
        velocity = new PVector(0, 0);
        acceleration = new PVector(0, 0);

        // mass = random(0.5, 3);
        mass = 1;
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

    public void display(Attractor a){

        float d = PVector.sub(location, a.location).mag();
        stroke(153, 61, 43, d*0.1f);
        strokeWeight(1);
        fill(153, 61, 43, 1);

        ellipse(location.x, location.y, mass*16, mass*16);
    }

    public void checkEdges(){
        if(location.x > width){
            location.x = width;
            velocity.x = velocity.x * -1;
        }

        if(location.x < 0){
            location.x = 0;
            velocity.x = velocity.x * -1;
        }

        if(location.y > height){
            location.y = height;
            velocity.y = velocity.y * -1;
        }

        if(location.y < 0){
            location.y = 0;
            velocity.y = velocity.y * -1;
        }
    }


}
  public void settings() {  size(600, 700); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "force_gravitational" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
