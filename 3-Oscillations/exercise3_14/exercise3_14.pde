InclinedPlain plain;
Box box;

void setup() {
    size(512, 512);
    plain = new InclinedPlain(300, -PI/6);
    box = new Box(300, -PI/6);
}

void draw() {
    background(0);
    plain.display();
    box.display();
}

