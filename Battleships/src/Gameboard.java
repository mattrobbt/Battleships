import java.util.Random;
import java.io.*;
/**
 * 
 */
import java.util.Scanner;

/**
 * @author matthewrobb
 *
 */
public class Gameboard {
	/**
	 * @param args
	 * @return 
	 */
	public void Board(int[][]board, String[][]hiddenBoard, int getMode){
		int battleship = 4;
		int submarine = 1;
		int cruiser = 3;
		int destroyer = 2;
		placeShips(board, cruiser,getMode,"cruiser");
		placeShips(board, cruiser,getMode,"cruiser");
		placeShips(board, destroyer,getMode,"destroyer");
		placeShips(board, destroyer,getMode,"destroyer");
		placeShips(board, destroyer,getMode,"destroyer");
		placeShips(board, submarine,getMode,"submarine");
		placeShips(board, submarine,getMode,"submarine");
		placeShips(board, submarine,getMode,"submarine");
		placeShips(board, battleship,getMode,"battleship");
		showBoard(board, hiddenBoard);
		fireShips(board,hiddenBoard,getMode);
	}
	
	
	public void showBoard(int realboard[][], String board[][]) {
		System.out.println("     1   2   3   4   5   6   7   8   9   10  ");
		System.out.print("    ________________________________________");
		System.out.println("");
		for(int x=0;x<board.length;x++) {
			if(x+1==10) {
				System.out.print("10 ");
			}
			else {
				System.out.print(" " + (x+1) + " ");
			}
			
			for(int y=0;y<board[x].length;y++) {
				System.out.print("| " + board[x][y] + " " );
				
			}
			System.out.println("");
			System.out.print("    ________________________________________");
			System.out.println("");
		}
	}
	public void placeShips(int[][]board, int shipLength, int getMode, String shipType) {
		int range = 10;
		Random rand = new Random();
		int num = 0;
		int num2 = 0;
		int directionNum;
		if(getMode==1) {
			do {
				num = rand.nextInt(range);
				num2 = rand.nextInt(range);
			}while(board[num][num2] != 0);
		}
		else if(getMode==2) {
			do {
				System.out.println("Enter the coordinates to put down the " + shipType + ": " );
				Scanner getCoord = new Scanner(System.in);
				num = getCoord.nextInt();
				num--;
				num2 = getCoord.nextInt();
				num2--;
			}while(num > 10 || num < 0 || num2 > 10 || num2 < 0 );
		}
		
		boolean shipPlaced = false;
		int loopCount = 0;
		while(shipPlaced == false) {
			
			shipPlaced = true;
		    directionNum = rand.nextInt(4);
			if(directionNum ==0) {
				/*
				 * Direction number 0 plots the length of the ship up from the chosen coordinate
				 */
				for(int y=0; y<shipLength;y++) {
					if(num2-shipLength >0) {
						board[num][num2-y] = 1;	
					}
					else {
						shipPlaced = false;	
					}
				}
			}
		
			else if(directionNum ==1) {
				/*
				 * Direction number 1 plots the length of the ship right from the chosen coordinate
				 */
				for(int y=0; y<shipLength;y++) {
					if(num+shipLength<10) {
						board[num+y][num2] = 1;	
					}
					else {
						shipPlaced = false;
					}
				}
			}
			else if(directionNum ==2) {
				/*
				 * Direction number 2 plots the length of the ship down from the chosen coordinate
				 */
					for(int y=0; y<shipLength;y++) {
						if(num2+shipLength<10) {
							board[num][num2+y] = 1;
						}
						else {
							shipPlaced = false;
						}
					}
				}
			else if(directionNum ==3) {
				/*
				 * Direction number 3 plots the length of the ship left from the chosen coordinate
				 */
					for(int y=0; y<shipLength;y++) {
						if(num-shipLength >0) {
							board[num-y][num2] = 1;	
						}
						else {
							shipPlaced = false;
						}
					}
		}
	}
						
		
}
	public void loadGame(String hiddenBoard[][]){
		int sizeBoard = 10;
		String[] temp = new String [100];
		FileReader fileReader;
		String nextLine;
		BufferedReader bufferedReader;
		System.out.println("Enter the file name you want to load from: ");
		Scanner fileNameGetter = new Scanner(System.in);
		String fileName = fileNameGetter.nextLine();
		try {
			fileReader = new FileReader(fileName + ".txt");
			bufferedReader = new BufferedReader(fileReader);
			nextLine = bufferedReader.readLine();
			temp = nextLine.split("");
			
		}
		catch(IOException e){
			System.out.println("Error file not found!");
		}
		
		int[][] board = new int[10][10];
		int count=0;
		for(int x=0; x < sizeBoard; x++) {
			for(int y=0; y < sizeBoard;y++) {
				int num = Integer.parseInt(temp[count]);
				board[x][y] = num;
				count++;
			}
			
		}
		for(int x=0; x < sizeBoard; x++) {
			for(int y=0; y < sizeBoard;y++) {
				if(board[x][y]==3) {
					hiddenBoard[x][y] = "X";
				}
				else if(board[x][y]==4) {
					hiddenBoard[x][y] = "-";
				}
				
			}
		}
		System.out.println("Choose which mode you would like: ");
		System.out.println("1 Single Player");
		System.out.println("2 Against Bot");
		Scanner modeListener = new Scanner(System.in);
		int getMode = modeListener.nextInt();
		Board(board,hiddenBoard,getMode);
		
		
		
		
	}
	public void saveGame(int[][] board, String[][] hiddenBoard) {
		FileOutputStream outputStream;
		PrintWriter printWriter;
		System.out.print("Enter the file name you want to write to: ");
		Scanner fileName_input = new Scanner(System.in); 
		String fileName = fileName_input.nextLine();
		fileName = fileName + ".txt";
		try {
			outputStream = new FileOutputStream(fileName);
			printWriter = new PrintWriter(outputStream);
			for(int x=0; x < hiddenBoard.length; x++) {
				for(int y=0; y < hiddenBoard[x].length;y++) {
					printWriter.print(board[x][y]);
					
				}
			}
			printWriter.close();
			
			printWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("File not found!");
		}
		
	}
	
	public void fireShips(int[][] board, String [][] hiddenBoard,int getMode) {
		int shotsTaken = 0;
		if(getMode==1) {
		while(shotsTaken != 100) {
			shotsTaken +=1;
			int num = 0;
			int num2 = 0;
			do {
				
				System.out.println("Enter coordinate to shoot at: ");
				Scanner coordinate_getter = new Scanner(System.in); 
				num = coordinate_getter.nextInt();
				if(num==-1) {
					System.out.println("Exiting Game....");
					System.out.println("Would you like to save the game:");
					System.out.println("1 Yes");
					System.out.println("2 No");
					Scanner saverGetter = new Scanner(System.in);
					int saveOrNot = saverGetter.nextInt();
					if(saveOrNot == 1) {
						saveGame(board,hiddenBoard);
					}
					Menu.processUserChoice();
				}
				
				num2 = coordinate_getter.nextInt();
				num--;
				num2--;
				if(board[num][num2]==3){
					System.out.println("This location has already been hit!" + "\n");
					shotsTaken +=1;
				}
				if(getMode==2) {
					fireShips(board,hiddenBoard, 2);
				}
			}while(num > 10 || num2 >10 || num < 0 || num2 < 0);
			
			if(board[num][num2] == 1) {
				System.out.println("Hit ship at (" + (num + 1) + "," + (num2 + 1) + ")");
				board[num][num2] = 3;
				hiddenBoard[num][num2] = "X";
			}
			else {
				System.out.println("Miss shot!");
				hiddenBoard[num][num2] = "-";
				board[num][num2] = 4;
			}
			showBoard(board,hiddenBoard);
		
			
			}
		}
		else if(getMode==2) {
			fireShips(board, hiddenBoard, 1);
			int range = 10;
			int num=0;
			int num2=0;
			Random rand = new Random();
			do{
				num = rand.nextInt(range);
				num2 = rand.nextInt(range);
				num--;
				num--;
			}while(board[num][num2]==3) ;
			
			if(board[num][num2] == 1) {
				System.out.println("Bot has hit your ship at (" + (num + 1) + "," + (num2 + 1) + ")");
				board[num][num2] = 3;
				hiddenBoard[num][num2] = "X";
			}
			else {
				System.out.println("Bot has missed a shot!");
				hiddenBoard[num][num2] = "-";
				board[num][num2] = 4;
			}
		}
	}
	

}