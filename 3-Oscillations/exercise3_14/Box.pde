class Box{
    float x;
    float y;

    float mass;
    float a;
    float length;

    Box(float len, float angle){

        a = angle;
        length = len;
        mass = 20;

        x = (mass*3-length) + len*cos(angle);
        y = mass*3 + len*sin(angle);

        
    }

    void display(){
        stroke(255);
        fill(255);

        pushMatrix();
        translate(width/4, height/2);
        rotate(a);
        rect(x, y, mass*3, mass*3);
        
        popMatrix();
    }
}