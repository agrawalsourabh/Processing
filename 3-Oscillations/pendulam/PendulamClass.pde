class PendulamClass{
    PVector origin;

    float length;

    float theta;
    float aVelocity = 0.0;
    float aAcceleration = 0.00;

    float x;
    float y;

    float gravity;


    PendulamClass(PVector o, float len, float angle){
        origin = o;
        length = len;
        theta = angle;

        x = origin.x;
        y = origin.y;

        gravity = -0.5;
    }

    void display(){

        x = origin.x + (length * sin(theta));
        y = origin.y + (length * cos(theta));

        stroke(255);
        strokeWeight(2);
        fill(255);

        line(origin.x, origin.y, x, y);

        ellipse(x, y, 40, 40);
    }

    void update(){

        aAcceleration = (gravity * sin(theta)) / length;

        aVelocity = aVelocity + aAcceleration;
        theta = theta + aVelocity;

        aVelocity = aVelocity * (1-0.005);
    }
}

