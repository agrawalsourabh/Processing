class Liquid{
    float x;
    float y;
    float rect_width;
    float rect_height;

    float c;

    Liquid(float x_, float y_, float w, float h, float c_){
        x = x_;
        y = y_;
        rect_width = w;
        rect_height = h;
        c = c_;
    }

    void display(){
        fill(105, 103, 99);
        rect(x, y, rect_width, rect_height);
    }

    boolean contains(Mover m){
        PVector l = m.location;

        return l.x>x && l.x<rect_width+x && l.y>y && l.y<rect_height+y;
    }

    PVector drag(Mover m){
        float speed = m.velocity.mag();
        float drag_mag = c*speed*speed;

        PVector drag_force = m.velocity.get();
        drag_force.mult(-1);

        drag_force.normalize();
        drag_force.mult(drag_mag);

        return drag_force;
    }
}