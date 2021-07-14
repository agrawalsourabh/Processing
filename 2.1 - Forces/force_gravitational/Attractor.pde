class Attractor{
    PVector location;

    float mass;
    float G;

    Attractor(){
        location = new PVector(width/2, height/2);
        mass = 5;
        G = 1;
    }

    PVector attract(Mover m){
        // F = ((G*m1*m2)/d*d) * unit_r

        PVector force = PVector.sub(location, m.location);
        float d = force.mag();

        d = constrain(d, 5, 25);
        force.normalize();
        
        float strength = (G * mass * m.mass) / (d*d);

        force.mult(strength);

        return force;
    }

    void display(){
        stroke(153, 61, 43);
        strokeWeight(3);
        fill(153, 61, 43, 1);

        ellipse(location.x, location.y, mass*16, mass*16);
    }
}