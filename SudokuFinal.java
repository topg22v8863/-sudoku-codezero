import java.util.*;


public class SudokuFinal
{
	private static int[][] Grid = {         //make this array visible to all the methods
	{7, 6, 5, 8, 4, 3, 2, 1, 9},
	{4, 1, 2, 6, 9, 7, 8, 5, 3},
	{9, 3, 8, 2, 5, 1, 7, 6, 4},
	{3, 2, 4, 1, 8, 6, 5, 9, 7},
	{1, 8, 9, 5, 7, 4, 6, 3, 2},
	{6, 5, 7, 9, 3, 2, 4, 8, 1},
	{8, 7, 1, 4, 6, 9, 3, 2, 5},
	{5, 9, 3, 7, 2, 8, 1, 4, 6},
	{2, 4, 6, 3, 1, 5, 9, 7, 8}
	};
	
	public static void Block(int n)
	{
		System.out.print(n+" ");
	}
	
	public static int[][] Difficulty()
	{
		Scanner sn = new Scanner(System.in);
		System.out.println("How hard do you want the game to be?");
		System.out.println("[1] Easy");
		System.out.println("[2] Medium");
		System.out.println("[3] Hard");
		System.out.println("[4] MAKE ME GO INSANE!!!!!");
		String ans = sn.nextLine();
		
		Random rn = new Random();
		int clues = 0;
		if (ans.equals("1"))
		{
			clues = 38;                                                             //the harder the level, the lower the initial clues at the start of the game
			
		}
		else if (ans.equals("2"))
		{
			 clues = 31;
			
		}
		else if (ans.equals("3"))
		{
			clues = 28;
		}
		else if (ans.equals("4"))
		{
			clues = 17;
		}
		int remove = 81 - clues;                                                    //number of numbers we should remove from the grid according to the difficulty level chosen by the player
		while(remove> 0)															//randomly remove numbers from the grid
			{
				int p = rn.nextInt(9);
				int t = rn.nextInt(9);
				if (Grid[p][t] == 0)												//if numbers is == to zero the skip
				{
				   continue;
				}
				else
				{
					Grid[p][t] = 0;
					remove -= 1;
				}
			}return Grid;
	}
	
	public static void DisplayGrid()											  //this method displays the grid
	{
		System.out.println("   _1_2_3_"+" "+"_4_5_6_"+" "+"_7_8_9_");
		System.out.println(" ");
		for (int i = 0; i < 9; i++) {
			
			System.out.print(i+1+" "+"|");
			System.out.print(" ");
			
			for ( int j = 0; j < 9; j++) {
				
					Block(Grid[i][j]);
					if ((j+1)%3 ==0){
						System.out.print("|");
						System.out.print(" ");
					}
				
			}System.out.println("");
			if ((i+1)%3 ==0){
				System.out.println("   _______"+" "+"_______"+" "+"_______");
				System.out.println(" ");
				}
		}
	}
	
	public static boolean RowCheck(int[][] Grid, int Row, int num)								//checks if a num appears once in a row
	{
		for (int i = 0; i < 9; i++)
		{
			if (Grid[Row][i] == num)
			{
				return false;                                            						//if the number you are trying to enter is in this row, return false
			}
		}
		return true;                                                     						// if number is not in this row then return true
	}
	
	public static boolean ColCheck(int[][] Grid, int Col, int num)								//checks if a num appears once in a column
	{
		for (int i = 0; i < 9; i++)
		{
			if (Grid[i][Col] == num)
			{
				return false;                                            						//if the number you are trying to enter is in this column, return false
			}
		}
		return true;                                                     						// if number is not in this column then return true
	}
	
	public static boolean BoxCheck(int[][] Grid, int Row, int Col, int num)
	{
		int FirstBoxRow = Row - Row%3;									 						//takes the x co-ordinate (row) of the first number of a square
		int FirstBoxCol = Col - Col%3;                                   						//takes the y co-ordinate (column) of the frrst number of a square
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				if (Grid[i][j] == num)
				{
					return false;																	//the number is already in this box so we cant add it here
				}
			}
		}
		return true;
	}
	
	public static boolean CheckValidity(int[][] Grid, int Row, int Col, int num)                            //checks if number passes all the tests
	{
		return RowCheck(Grid, Row, num) && ColCheck(Grid, Col, num) && BoxCheck(Grid, Row, Col, num);
	}
	
	public static boolean Slots()                                       									//checks whether there are still empty slots
    {
		for (int i = 0; i < 9; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				if (Grid[i][j] == 0)
				{
					return true;
				}
			}
		}
		return false;
	}	
	
	
	public static void play()
	{
		Scanner s = new Scanner(System.in);
		long StartTime = System.currentTimeMillis();
		while(Slots())
		{
			DisplayGrid();
			System.out.println("Enter the row number you want to solve (from 1 - 9) or <111> to Quit: ");
			int Row = s.nextInt()-1;
			if (Row == 110)
			{
				System.out.println("Well, at least you gave it a shot");
				System.exit(0);
			}else{
			System.out.println("Enter the column number you want to solve (from 1 - 9) or <111> to Quit: ");
			int Col = s.nextInt()-1;
			if (Col == 110)
			{
				System.out.println("Well, at least you gave it a shot");
				System.exit(0);
			}else{
			System.out.println("Enter a number from 1 - 9 ");
			int num = s.nextInt();
		
			if (CheckValidity(Grid, Row, Col, num))
			{
				Grid[Row][Col] = num;
				long EndTime = System.currentTimeMillis();
				Timer(StartTime, EndTime);
			}
			else
			{
				System.out.println("Oops! You can't put this number here");
				long EndTime = System.currentTimeMillis();
				Timer(StartTime, EndTime);
			}
			}
			}
		}
		System.out.println("Game Over");		
	}
	
	public static void Timer(long StartTime, long EndTime)											//keeps track of time as the you plat the game
	{
		long CurrentTime = (EndTime - StartTime)/1000;                                         		// current time in seconds
		long hour = CurrentTime/3600;
		long minutes = CurrentTime/60;
		long seconds = CurrentTime%60;
		System.out.println("Time: "+hour+" : "+minutes+" : "+seconds);
	}
	
		public static void main (String [] args) throws InterruptedException
	{
		
		System.out.print("Welcome to ");
		Thread.sleep(1000);
		System.out.print("S");
		Thread.sleep(500);
		System.out.print("U");
		Thread.sleep(500);
		System.out.print("D");
		Thread.sleep(500);
		System.out.print("O");
		Thread.sleep(500);
		System.out.print("K");
		Thread.sleep(500);
		System.out.println("U");
		Thread.sleep(3000);
		System.out.println("CRACKER'S EDITION");
		Thread.sleep(3000);
		System.out.println("The rules are simple");
		Thread.sleep(2000);
		System.out.println("1) 0 represents an empty box");
		Thread.sleep(3000);
		System.out.println("2) Fill out all the 0's withouT repeating a number in a row, single 3 x 3 box nor column");
		Thread.sleep(4000);
		System.out.println("E N J O Y");
		
		Difficulty();
		play();
		

	}
}
