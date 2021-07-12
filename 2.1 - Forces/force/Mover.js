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
        acceleration = force;
    }

    void update(){
        velocity.add(acceleration);
        location.add(acceleration);
    }

    void display(){
        stroke(0);
        strokeWeight(2);
        fill(127);

        ellipse(location.x, location.y, 48, 48);
    }
}