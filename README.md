<img width="271" alt="BlockBreaker" src="https://user-images.githubusercontent.com/46427281/133891398-3f35597c-1fcc-4bdf-bc34-ff158dc9047e.png">


# OverView

This project features nice block breaker game written in Java. The user needs to breaks all the block by bouncing ball on a paddle

# Features
* The User have 1 stage gameplay, viewing 24 blocks the user need to break.
* The User can replay the stage as many times as he want
* Feature improvement if the game consist of adding more stages , with more type of blocks. In the BlockType enum class, you can see there is 2 more colors for blocks.
  This is intended for blocks that need to be hit more then one time to shutter.

# Class Hierarchy
* GameObject interface consist of 2 functions, setPos - set the x and y position of gameobject on board, and draw - draw the gameobject on the board.
* GameObject class c'tor take x and y position of object, and path to the png related to the object, so we can draw it on board.
* Ball class inherits from gameobject, feature improvement for ball object is that every time the ball hits a block, he will change color
* Paddle class inherits from gameobject, no current spaciel features for this class.
* Block class inherits from gameobject, special features as described may be block type. Draw function is overridden because we need to check if the block had been hit or not

# System interfaces
* Game GUI made by Jframe
* Written in Java PL
