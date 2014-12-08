import java.util.Scanner;
//qsort is refinement of quicksort which is in-place, fast and space efficient

public class qsort {

	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		
		System.out.println("Enter the array: ");
		String input=scan.next();
		
		String[] input1 = input.split(",");
		
		int[] arr = new int[input1.length];
		
		for(int i=0;i<input1.length;i++)
			arr[i] = Integer.parseInt(input1[i]);
		
		qsort q = new qsort();
		
		arr =q.sort(arr);			
		
		for(int i=0;i<arr.length-1;i++)
			System.out.print(arr[i]+",");
			System.out.println(arr[arr.length-1]);

	}
	
	public int[] sort(int[] arr)
	{
		return null;
	}

}
