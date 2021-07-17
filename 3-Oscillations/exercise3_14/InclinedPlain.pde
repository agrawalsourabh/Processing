class InclinedPlain{
    PVector location;
    float angle;

    float length;

    float x;
    float y;

    InclinedPlain(float len, float a){
        length = len;
        angle = a;
    }

    void display(){
        stroke(255);
        strokeWeight(1);

        x = length * cos(angle);
        y = length * sin(angle);

        pushMatrix();
        translate(width/4, height/2);
        line(0, 0, x, y);
        popMatrix();
        
    }
}