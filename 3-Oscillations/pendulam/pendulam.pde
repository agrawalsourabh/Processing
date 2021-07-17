PendulamClass[] pendulums;
boolean recording = true;
void setup() {
    size(700, 700);
    pendulums = new PendulamClass[5];
    reset();
}

void draw() {
    background(0);

    for(PendulamClass p : pendulums){
        p.update();
        p.display();
    }

    if(recording){
        saveFrame("output/image-####.png");
        fill(255, 0, 0);
    }
    else{
        fill(0, 255, 0);
    }
    ellipse(width/2, height-10, 10, 10);
}

void reset(){

    for(int i=0 ; i<pendulums.length ; i++){
        pendulums[i] = new PendulamClass(new PVector(width/2, 0), 250, (PI/4 + i));
    }
    
}

void mousePressed() {
    reset();
}

void keyPressed() {
    if(key == 'r' || key == 'R'){
        recording = !recording;
    }    
}

