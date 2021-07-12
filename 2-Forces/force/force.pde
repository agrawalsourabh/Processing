
Mover m;

void setup() {
    size(800, 800);
    background(127);

    m = new Mover();
}

void draw() {
    background(127);
    

    PVector gravity = new PVector(0, 0.1);
    PVector wind = new PVector(0.01, 0);

    m.applyForce(gravity);
    m.applyForce(wind);

    m.edges();
    m.update();
    m.drawBall();
}


class Mover{
    PVector location;
    PVector velocity;
    PVector acceleration;

    Mover(){
        location = new PVector(0, 0);
        velocity = new PVector(0, 0);
        acceleration = new PVector(0, 0.1);
    }

    void update(){
        velocity.add(acceleration);

        location.add(velocity);

        velocity.limit(5);

        acceleration.setMag(0);
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

    void applyForce(PVector force){
        acceleration.add(force);
    }

    void drawBall(){
        stroke(0);
        strokeWeight(2);
        fill(127);

        ellipse(location.x, location.y, 50, 50);
    }

}
