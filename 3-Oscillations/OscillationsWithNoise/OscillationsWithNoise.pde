Mover m;
boolean  recording = false;
void setup() {
    size(512, 512);
    reset();
}

void draw() {
    m.update();
    m.display();

    if(recording){
        saveFrame("output/image_####.png");
        fill(0, 255, 0);
    }
    else{
        fill(255, 0, 0);
    }

    ellipse(500, 500, 10, 10);
}

void reset(){
    background(0);
    m = new Mover();
}

void mousePressed() {
    reset();
}

void keyPressed() {
    if(key == 'r' || key == 'R'){
        recording = !recording;
    }
}

