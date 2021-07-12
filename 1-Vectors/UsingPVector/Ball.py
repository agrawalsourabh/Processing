class Ball:
    def __init__(self):
        self.location =  PVector(0, 0)
        self.velocity =  PVector(2, 2)
        self.acceleration = PVector(0, 0)
   
    
    def update(self):
        mouse = PVector(mouseX, mouseY)
        
        mouse.sub(self.location)
        mouse.setMag(0.1)
        self.acceleration = mouse
      
        self.velocity.add(self.acceleration)
        self.location.add(self.velocity)
        
        self.velocity.limit(5)
            
    def drawBall(self):
        stroke(0)
        strokeWeight(2)
        fill(127)
        
        ellipse(self.location.x, self.location.y, 50, 50)
        
