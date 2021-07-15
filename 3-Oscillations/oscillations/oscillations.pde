Mover[] movers;
void setup() {
    size(700, 512);
    movers = new Mover[1];
    reset();
}

void draw() {

    for(Mover m : movers){
        m.update();
        m.display();
    }
    
}

void mousePressed() {
    saveFrame("output.png");
    reset();
}

void reset(){
    background(0);

    for(int i=0 ; i<movers.length ; i++){
        movers[i] = new Mover();
    }
}
