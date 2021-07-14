Mover[] movers;
Attractor a;
int count;
void setup() {
    size(600, 700);
    movers = new Mover[10];
    count = 0;
    reset();
}

void draw() {
    a.display();

    for(Mover m : movers){
        PVector force = a.attract(m);
        m.applyForce(force);

        PVector wind = new PVector(0.01, 0);
        m.applyForce(wind);

        m.update();
        m.display(a);
        m.checkEdges();
    }
}

void mousePressed() {

    saveFrame("image-"+count+".png");
    count = count + 1;
    reset();
}

void reset(){
    background(127);
    a = new Attractor();

    for(int i=0 ; i<movers.length ; i++){
        movers[i] = new Mover();
    }
    
    
}

