from Ball import Ball

global displayWidth, displayHeight, ball

def setup():
    global displayWidth, displayHeight, ball
    
    size(displayWidth / 2, displayHeight / 2)
    background(127)
    ball =  Ball()
    

def draw():
    global ball
    background(127)
    ball.drawBall()
    ball.update()
    
    
