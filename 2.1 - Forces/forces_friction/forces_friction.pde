// friction = -1 * c * net_force * unit_velocity
// In this example net_force = 1

Mover m;

void setup() {
    size(700, 700);
    m = new Mover();
}

void draw() {
    background(127);

    if(mousePressed){
        println("mouse pressed");
        PVector friction = m.velocity.get();
        friction.normalize();

        float ice_friction = -0.01;
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
        velocity = new PVector(0.7, 0);
        acceleration = new PVector(0, 0);

        mass = 1;
        radius = (mass * 50) / 2;
    }

    void applyForce(PVector force){
        PVector f = PVector.div(force, mass);
        acceleration.add(f);
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

        ellipse((location.x + radius), location.y, radius*2, radius*2);

        fill(255);
        line(0, (height/2 + radius), width, (height/2 + radius));

    }

    void edge(){
        if((location.x + radius*2) > width){
            location.x = width - radius*2;
            velocity.mult(0);
        }
    }
}

