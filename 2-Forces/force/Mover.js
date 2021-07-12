class Mover{
    PVector location;
    PVector velocity:
    PVector acceleration;

    void Mover(){
        location = new PVector(0, 0);
        velocity = new PVector(0, 0);
        acceleration = new PVector(0, 0);
    }

    void update(){
        PVector mouse = new PVector(mouseX, mouseY);

        mouse.sub(location);
        mouse.setMag(0.1);

        acceleration = mouse;

        velocity.add(acceleration);
        location.add(velocity);

        velocity.limit(5);

    }

    void drawBall(){
        stroke(0);
        strokeWeight(2);
        fill(127);

        ellipse(location.x, location.y, 50, 50);
    }

}