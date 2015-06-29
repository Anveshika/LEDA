import java.util.Scanner;


public class NQueensProblem {

	int N;
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		NQueensProblem obj = new NQueensProblem();
		
		System.out.println("Enter the value of N");
		obj.N = scan.nextInt();
		
		//creating an N*N board
		int [][] board = new int[obj.N][obj.N];
			
		if(!obj.Board(board,0, obj))		
		{
			System.out.println("No solution");
			System.exit(0);
		}
			
		obj.printBoard(board, obj);
		
		scan.close();

	}
	
	public boolean Board(int board[][], int col, NQueensProblem obj)
	{
		if(col>=N)
			return true;
		
		for(int i = 0; i<N;i++)
		{
			if(checkPlace(board,i,col, obj))
			{
				board[i][col] = 1;
				if(Board(board, col+1, obj))
					return true;
				board[i][col] = 0;
			}
		}
		
		return false;
	}
	
	public static boolean checkPlace(int board[][], int row, int col,NQueensProblem obj)
	{
		int i, j;
		
		//checking for row 
		for(i=0; i<col; i++)
		{
			if(board[row][i] == 1)
				return false;
		}
		
		//checking for diagonal
		for(i=row,j=col; i>=0 && j>=0; i--,j--)
		{
			if(board[i][j] == 1)
				return false;
		}
		
		//checking for column
		for(i=row,j=col; j>=0 && i<obj.N; i++,j--)
		{
			if(board[i][j] == 1)
				return false;
		}
		return true;
	}

	//method to print the board on console
	public void printBoard(int board[][], NQueensProblem obj)
	{
		int i;
		for(i =0; i<obj.N; i++)
		{
			for(int j = 0; j<obj.N; j++)
				if(board[i][j] == 1)
					System.out.print("Q\t");
				else
					System.out.print("_\t");
			
			System.out.println("\n");
		}
	}
}
