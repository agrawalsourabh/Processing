
Mover m;

void setup() {
    size(640, 360);
    m = new Mover();
}

void draw() {
    background(127);
    PVector gravity = new PVector(0, 0.3);
    m.applyForce(gravity);

    if(mousePressed){
        PVector wind = new PVector(0.3, 0);
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

    void applyForce(PVector force){
        acceleration.add(force);
    }

    void update(){
        velocity.add(acceleration);
        location.add(velocity);

        acceleration.mult(0);
    }

    void display(){
        stroke(0);
        strokeWeight(2);
        fill(127);

        ellipse(location.x, location.y, 48, 48);
    }

    void edges(){

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
