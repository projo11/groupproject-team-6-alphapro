Overview
The problem we would like to solve is not being able to remove pieces from the board once they have been placed. 
We need to make 3 changes to fix this:
1. Reprint the chess board whenever a new piece is added to it
2. Allow the pieces to be dragged after they are placed
3. Add a trash can panel that you can drag pieces to, refunding them

Pseudocode
Step 1. reprinting of the board
in PieceShopPane.java, line 494
//Remove the addP function
//add the piece to the board
//clear the existing board from the screen
//reprint the board

Step 2. allow pieces to be dragged
in PieceShopPane.java
//remove the entire if statement from line 359 (and 407), and the else if on 363 (and 410)
//from 364 to 447: Add checks into each add piece function to see if enough cost is available to add the given piece
//at line 404:
//if we click on a GImage
	//check the map to see if it's one of our pieces
		//if it is, allow it to be dragged

Step 3. allow pieces to be removed and refunded
//create a new GImage with a trash can icon at line 43
//add the created image at line 307
//in line 120, we need to add the GIamges to a map where they can be matched with their corresponding piece 
//at line 485:
//if we release the mouse on the trash can icon while holding a piece
	//check the map to see which piece we are holding
	//remove that piece from the board
	//gain cost equal to that piece's value
	//reprint the board