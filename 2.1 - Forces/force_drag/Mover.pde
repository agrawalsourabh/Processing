class Mover{
  PVector location;
  PVector velocity;
  PVector acceleration;

  float mass;
  float radius;
  
  Mover(float m, float x, float y){
    mass = m;
    radius = mass * 8;

    location = new PVector(x, y);
    velocity = new PVector(0, 0);
    acceleration = new PVector(0, 0);
  }

  void applyForce(PVector force){
    PVector f = PVector.div(force, mass);
    acceleration.add(f);
  }

  void update(){
    velocity.add(acceleration);
    location.add(velocity);

    acceleration.mult(0);
  }
  
  void display(){
    fill(255, 100);
    stroke(0);
    strokeWeight(2);
    
    ellipse(location.x, location.y, radius*2, radius*2);
  }

  void checkEdges(){
    if(location.y > height){
      velocity.y = velocity.y * -0.9;
      location.y = height;
    }
  }

}
