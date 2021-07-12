Mover[] movers;
void setup() {
    size(500, 500);

    movers = new Mover[5];

    for(int i=0 ; i<movers.length ; i++){
        movers[i] = new Mover();
    }
}

void draw() {
    background(127);

    for(Mover m : movers){
        PVector gravity = new PVector(0, 0.3);
        gravity.mult(m.mass);
        m.applyForce(gravity);

        if(mousePressed){
            PVector wind = new PVector(0.2, 0);
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

        mass = random(0.5, 5);
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

    void drawBall(){
        stroke(0);
        strokeWeight(2);
        fill(127, 100);

        ellipse(location.x, location.y, mass*30, mass*30);
    }

    void edges(){
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
