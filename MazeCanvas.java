//Import Statements 
import javafx.application.*; //to extend application
import javafx.stage.*; //to create the stage
import javafx.scene.*; //to create the stage
import javafx.scene.canvas.*; //to acess the canvas methods 
import javafx.scene.paint.*; 
import javafx.scene.layout.*; //to create the flow pane
import javafx.scene.control.*; //for the buttons and labels
import javafx.event.*; //for the combobox listener and event handler
import javafx.scene.input.*; //for the text field object 
import javafx.scene.image.*; 
import javafx.geometry.*; 

import java.util.*; 
import java.net.*;
import java.lang.*; 

public class MazeCanvas extends Canvas {

   //INSTANCE VARIABLES 
   //the square starts in the top row at the 0 in the file 
   int xPos; 
   int yPos; 
   

   //CONSTRUCTOR 
   public MazeCanvas() {
      setWidth(525); 
      setHeight(525); 
      
   }
   
   //draw method to draw the borders of the maze 
   //it takes in 3 ints that represent the color to draw, and the x and y coordinates
   //for where to draw the borders 
   public void drawBackground(int color, int row, int col) {
      //create a graphics context object 
      GraphicsContext gc = getGraphicsContext2D(); 
      
      //if color = 0 --> draw a white square 
      if(color == 0) {
         gc.setFill(Color.PINK); 
         //the x position for the square will be the col index number * 25
         //the y position for the square will be the row index number * 25 
         gc.fillRect(col*25, row*25, 25, 25); 
         
      }
      //if color = 1 --> draw a black square
      else if(color == 1) {
         gc.setFill(Color.BLACK); 
         //the x position for the square will be the col index number * 25
         //the y position for the square will be the row index number * 25 
         gc.fillRect(col*25, row*25, 25, 25); 
      }
   
   }
   //method to clear the previous square 
   public void clearOldSquare(int row, int col) {
      //create a graphics context object 
      GraphicsContext gc = getGraphicsContext2D(); 
               
      //clear the drawing of the previous square 
      gc.setFill(Color.PINK); 
      gc.fillRect(col*25, row*25, 25, 25);
   
   }
   
   
   //draw method to draw the player's square 
   public void drawSquare(int row, int col) {
      //create a graphics context object 
      GraphicsContext gc = getGraphicsContext2D(); 

      //set the fill of the square 
      gc.setFill(Color.YELLOW); 
      
      //draw the square 
      gc.fillRect(col*25, row*25, 25, 25); 
       

   }
   
    



}