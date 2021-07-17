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

public class exercise3_14 extends PApplet {

InclinedPlain plain;
Box box;

public void setup() {
    
    plain = new InclinedPlain(300, -PI/6);
    box = new Box(300, -PI/6);
}

public void draw() {
    background(0);
    plain.display();
    box.display();
}

class Box{
    float x;
    float y;

    float mass;
    float a;
    float length;

    Box(float len, float angle){

        a = angle;
        length = len;
        mass = 20;

        x = (mass*3-length) + len*cos(angle);
        y = mass*3 + len*sin(angle);

        
    }

    public void display(){
        stroke(255);
        fill(255);

        pushMatrix();
        translate(width/4, height/2);
        rotate(a);
        rect(x, y, mass*3, mass*3);
        
        popMatrix();
    }
}
class InclinedPlain{
    PVector location;
    float angle;

    float length;

    float x;
    float y;

    InclinedPlain(float len, float a){
        length = len;
        angle = a;
    }

    public void display(){
        stroke(255);
        strokeWeight(1);

        x = length * cos(angle);
        y = length * sin(angle);

        pushMatrix();
        translate(width/4, height/2);
        line(0, 0, x, y);
        popMatrix();
        
    }
}
  public void settings() {  size(512, 512); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "exercise3_14" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
