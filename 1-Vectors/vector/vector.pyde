global displayWidth, displayHeight
global x, y, xSpeed, ySpeed

def setup():
    global displayHeight, displayWidth, x, y, xSpeed, ySpeed
    size(displayWidth / 2, displayHeight / 2)
    background(255)
    stroke(0)
    strokeWeight(2)
    fill(127)
    x = 0
    y = 0
    xSpeed = 2
    ySpeed = 2
    
def draw():
    
    background(255)
    global x, y, xSpeed, ySpeed
    
    x = x + xSpeed
    y = y + ySpeed
    
    if x >= width or x <= 0 :
        xSpeed = xSpeed * -1
        
    if y >= height or y <= 0 :
        ySpeed = ySpeed * -1
        
    
    ellipse(x, y, 50, 50)
    
