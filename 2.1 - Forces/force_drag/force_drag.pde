Mover[] movers;
Liquid l;

void setup(){
  size(700, 700);
  movers = new Mover[9];

  reset();

  l = new Liquid(0, height/2, width, height/2, 0.1);
}

void draw(){
  
  background(235, 225, 206);

  l.display();

  for(Mover m : movers){

    if(l.contains(m)){
      PVector drag_force = l.drag(m);
      m.applyForce(drag_force);
    }

    PVector gravity = new PVector(0, 0.1*m.mass);
    m.applyForce(gravity);

    m.display();
    m.update();
    m.checkEdges();
  }
}

void mousePressed() {
  reset();  
}

void reset(){
  for(int i=0 ; i<movers.length ; i++){
    movers[i] = new Mover(random(0.5, 3), 40+i*70, 0);
  }
}
