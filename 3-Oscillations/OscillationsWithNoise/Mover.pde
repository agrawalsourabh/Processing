class Mover{
    float theta;
    float aVelocity;
    float aAcceleration;

    float r;
    float c;

    float x;
    float y;

    Mover(){
        theta = 0;
        aVelocity = 0;
        aAcceleration = 0.001;

        c = 250;
    }

    void update(){
        
        aVelocity = aVelocity + aAcceleration;
        theta = aVelocity + theta;

        aVelocity = constrain(aVelocity, 0, 0.05);

        if(theta >= TWO_PI){
            aVelocity = 0.0;
            aAcceleration = 0.0;
        }
        else{
            r = c * noise(frameCount);
        }
    }

    void display(){

        stroke(255, 100);
        strokeWeight(1);
        

        x = r*cos(theta);
        y = r*sin(theta);

        pushMatrix();
        translate(width/2, height/2);

        line(0, 0, x, y);

        fill(153, 59, 52, 100);
        ellipse(x, y, 10, 10);
        popMatrix();
    }
}