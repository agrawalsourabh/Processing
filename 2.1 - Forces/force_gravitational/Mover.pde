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

    void applyForce(PVector force){
        PVector f = PVector.div(force, mass);
        acceleration.add(f);
    }

    void update(){
        velocity.add(acceleration);
        location.add(velocity);

        acceleration.mult(0);
    }

    void display(Attractor a){

        float d = PVector.sub(location, a.location).mag();
        stroke(153, 61, 43, d*0.1);
        strokeWeight(1);
        fill(153, 61, 43, 1);

        ellipse(location.x, location.y, mass*16, mass*16);
    }

    void checkEdges(){
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