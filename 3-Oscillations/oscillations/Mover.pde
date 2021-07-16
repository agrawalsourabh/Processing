class Mover{

    float locX;
    float locY;

    float theta;
    float r;
    float delta;

    float aVelocity;
    float aAcceleration;

    Mover(){

        locX = width/2;
        locY = height/2;

        theta = 0.0;
        r = 200;
        delta = 50/r;

        aVelocity = 0;
        aAcceleration = 0.001;
    }

    void update(){

        aVelocity = aVelocity + aAcceleration;
        theta = theta + aVelocity;

        aVelocity = constrain(aVelocity, 0.001, 0.01);

        if(r <= 0){
            aAcceleration = 0.0;
            aVelocity = 0.0;
            r = 0.0;
        }
        else{
             r = r-delta;
        }
    }

    // with perlin noise
    void updateWithNoise(float t){

        float d = noise(t);
        d = map(d, 0, 1, 0, 0.2);

        aVelocity = aVelocity + aAcceleration;
        theta = theta + aVelocity;

        aVelocity = constrain(aVelocity, 0.001, 0.01);

        if(r <= 0){
            aAcceleration = 0.0;
            aVelocity = 0.0;
            r = 0.0;
        }
        else{
             r = r-d;
        }
    }

    void display(){

        float x = r*cos(theta);
        float y = r*sin(theta);

        pushMatrix();
        translate(locX, locY);
        rotate(theta);
        stroke(181, 76, 34, 75);
        strokeWeight(1);
        line(0, 0, x, y);
        strokeWeight(1);
        fill(255);
        ellipse(x, y, 1, 1);
        popMatrix();
    }
}