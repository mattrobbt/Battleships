import java.util.Scanner;

/**
 * 
 */

/**
 * @author matthewrobb
 *
 */
public class Menu {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		processUserChoice();
		
	}

	public static void displayMenu() {
	/*
	 * Outputs the choices for the user to choose from.
	 * Method is static as it is not required to change and its set as void because no value is returned
	 */
		System.out.println(" ");
		System.out.println("Please select one of the options below");
		System.out.println("1 New Game");
		System.out.println("2 Load Game");
		System.out.println("3 Instructions");
		System.out.println("4 Exit");
		System.out.print("When in game type -1 to exit!");
		
	}
	public static void processUserChoice() {
		int getChoice;
		/*
		 * A while do statement is used as the condition can only be checked after the while has run at least once.
		 */
		do {
/*
 * A while do is used as the whole program needs to run and get inputs before it can check if the user chose to exit.
 */
			/**
			 * Outputs options by calling displayMenu()
			 * Then takes an input from the user on which choice they want.
			 */
			displayMenu();
			System.out.println(" ");
			Scanner choiceListener = new Scanner(System.in); 
			getChoice = choiceListener.nextInt();
			/**
			 * If statements are used to compare the number input with the numbers of the corresponding choices
			 * 
			 */
			if (getChoice == 1) {
				int board[][] = new int[10][10];
				String hiddenBoard[][] = new String[10][10];
	
				for(int x=0; x < hiddenBoard.length; x++) {
					for(int y=0; y < hiddenBoard[x].length;y++) {
						hiddenBoard[x][y] = "0";
					}
				}
				Gameboard board1 = new Gameboard();
				System.out.println("Choose which mode you would like: ");
				System.out.println("1 Single Player");
				System.out.println("2 Against Bot");
				Scanner modeListener = new Scanner(System.in);
				int getMode = modeListener.nextInt();
				
				board1.Board(board,hiddenBoard,getMode);
			}
			
			else if (getChoice == 2) {
				String hiddenBoard[][] = new String[10][10];
				
				for(int x=0; x < hiddenBoard.length; x++) {
					for(int y=0; y < hiddenBoard[x].length;y++) {
						hiddenBoard[x][y] = "0";
					}
				}
				Gameboard board1 = new Gameboard();
				board1.loadGame(hiddenBoard);
				
				
			}
			else if (getChoice == 3) {
				
			}
			else {
				if(getChoice != 4) {
				processUserChoice();
				}
			}
			
					
				
		}while (getChoice != 4);		
		System.out.println("Exiting Battleships....");
		
		}
}