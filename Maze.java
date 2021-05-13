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
import javafx.animation.*; 
import javafx.geometry.*; 

import java.util.*; 
import java.net.*;
import java.lang.*; 
import java.io.*; 

public class Maze extends Application {
   
   FlowPane rootFlowPane = new FlowPane(); 
   MazeCanvas canvas = new MazeCanvas(); 
   
   //declare the array 
   //create a 2D array to store the data from the file 
   int [][] mazeArray = new int[21][21]; 
   
   //variables for the position of the player's square in the array 
   int rowIndex = 0; 
   int colIndex; 
   
   VBox message = new VBox(); 
   
   Label label = new Label(); 

   public void start(Stage stage) {
   
      try {
      
         rootFlowPane.getChildren().add(canvas); 
         
         label.setBackground(new Background(new BackgroundFill(Color.CYAN, CornerRadii.EMPTY, Insets.EMPTY)));
         label.setText("hey"); 
         message.getChildren().add(label); 
         rootFlowPane.getChildren().add(message); 
         
         //create a file and read that file into a scanner object 
         File file = new File("MazeFile.txt"); 
         Scanner scan = new Scanner(file); 
         
         //initialize the array 
         //while there is data to be left in the file 
         while(scan.hasNextInt()) {
            //loop through the 2D array and add the data from the file into the array 
            for(int i = 0; i < 21; i++) {
               for(int j = 0; j < 21; j++) {
                  //get the current element in the maze array and assign it to the next int read from the file 
                  mazeArray[i][j] = scan.nextInt(); 
               }
            }
         }
         
         //loop through the array 
         for(int i = 0; i < 21; i++) {
            for(int j = 0; j < 21; j++) {
               //if the number at the current index is a 0
               if(mazeArray[i][j] == 0) {
                  //draw a black square 
                  //the arguments for the drawSquare method are: color, row num, col num
                  //i is associated with the rows which is associated with the y values
                  //j is associated with the columns which is associated with the x values
                  canvas.drawBackground(0, i, j); 
                  
               }
               //if the number at the current index is a 1
               else if(mazeArray[i][j] == 1) {
                  //draw a white square
                  //the arguments for the drawSquare method are: color, row num, col num
                  canvas.drawBackground(1, i, j); 
                  
               }
            }
         }
         
         //assign the column number 
         //find the first 0 in the array 
         for(int i = 0; i < 21; i++) {
            if(mazeArray[rowIndex][i] == 0) {
               colIndex = i; 
            }
         }


         //after the background has been drawn, draw the player's square 
         canvas.drawSquare(rowIndex, colIndex);
         
         
         //add the key listener 
         rootFlowPane.setOnKeyPressed(new KeyHandler()); 
                  
         
         //create the scene object and set the root and size
         Scene myScene = new Scene(rootFlowPane, 525, 525); 
         //set the scene to the stage 
         stage.setScene(myScene); 
         stage.setTitle("Maze"); 
         stage.show();   
         
         //request the focus
         canvas.requestFocus(); 
   
      }
      catch(FileNotFoundException fnfe) {
         System.out.println("The file you are looking for does not exist"); 
      }
   
   }  //end of the start method 
   
   //key listener inner class
   public class KeyHandler implements EventHandler<KeyEvent> {
      
      public void handle(KeyEvent ke) {

         //if the down arrow is pressed 
         if(ke.getCode() == KeyCode.DOWN) {
            //check if the space below is valid 
            //the space will be valid if the square below is white and it is not in the bottom row 
            if(rowIndex != 20 && mazeArray[rowIndex+1][colIndex] != 1) {
               //clear the old square 
               canvas.clearOldSquare(rowIndex, colIndex); 
               
               //change the position of the square to the square below it 
               //increase the rowIndex variable and leave the colIndex the same 
               rowIndex++; 
               
               //draw the square in the new position 
               canvas.drawSquare(rowIndex, colIndex); 
            
            } 
            else if(rowIndex == 20) {
               System.out.println("You Win!"); 
            
            }  

         }
         
         //if the up arrow is pressed 
         else if(ke.getCode() == KeyCode.UP) {
            //check if the space above is valid 
            //the space will be valid if the square above is white and if it is not in the top row
            if(rowIndex != 0 && mazeArray[rowIndex-1][colIndex] != 1) {
               //clear the old square 
               canvas.clearOldSquare(rowIndex, colIndex); 

               //change the position of the square to the square above it 
               //decrease the rowIndex variable and leave the colIndex the same 
               rowIndex--; 
               
               //draw the square in the new position 
               canvas.drawSquare(rowIndex, colIndex); 
            
            }
         }
         
         //if the left arrow is pressed 
         else if(ke.getCode() == KeyCode.LEFT) {
            //check if the space to the left is valid 
            //the space will be valid if the square to the left is white and if it is not in the most left column 
            if(colIndex != 0 && mazeArray[rowIndex][colIndex-1] != 1) {
               //clear the old square 
               canvas.clearOldSquare(rowIndex, colIndex); 
                              
               //change the position of the square to the square left of it 
               //decrease the colIndex variable and leave the rowIndex the same 
               colIndex--; 
               
               //draw the square in the new position 
               canvas.drawSquare(rowIndex, colIndex); 
            }
         }
         //if the right arrow is pressed 
         else if(ke.getCode() == KeyCode.RIGHT) {
            //check if the space to the right is valid 
            //the space will be valid if the square to the right is white and if it is not in the most right column 
            if(colIndex != 20 && mazeArray[rowIndex][colIndex+1] != 1) {
               //clear the old square 
               canvas.clearOldSquare(rowIndex, colIndex); 
               
               //change the position of the square to the square right of it 
               //increase the colIndex variable and leave the rowIndex the same 
               colIndex++; 
               
               //draw the square in the new position 
               canvas.drawSquare(rowIndex, colIndex);
            }
         }
      }
   }
         
   public static void main(String[] args) {
      launch(args); 
   }

}