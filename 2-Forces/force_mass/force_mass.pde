
Mover[] movers;

void setup() {
    size(800, 800);
    background(127);

    movers = new Mover[5];

    for(int i=0 ; i<movers.length ; i++){
        movers[i] = new Mover();
    }
    
}

void draw() {
    background(127);
    
    for(Mover m : movers){
        PVector gravity = new PVector(0, 0.1);
        PVector wind = new PVector(0.01, 0);

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
        acceleration = new PVector(0, 0.1);

        mass = random(0.5, 4);
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

        PVector f = PVector.div(force, mass);
        acceleration.add(f);
    }

    void drawBall(){
        stroke(0);
        strokeWeight(2);
        fill(122, 56, 20);

        ellipse(location.x, location.y, mass*50, mass*50);
    }

}

