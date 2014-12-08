import java.util.Scanner;

/*variation of merge sort where input array is divided into k sub arrays
 at each step k arrays are sorted and merged
 when n<=k insertion sort is performed on sub array*/

public class kwayMergeInsertion {
	
	//arrays to store the arrays which contain a particular element
	//during the time of merging and dividing the arrays
	
	int[][] div;
	int[][] merg;
	//set initial size of both as zero 
	int dsize=0;
	int msize=0;
	
	public kwayMergeInsertion()
	{
		div = new int[20][];
		merg = new int[20][];
	}

	public static void main(String[] args) {
		
		Scanner scan=new Scanner(System.in);
		
		System.out.println("Enter the array: ");		
		int k=0;int x=0;
		String input = scan.nextLine();
		 String[] input1 = input.split(" ");
		while(!input.equals("end"))
		{ 
			input=scan.next();			
		    k = Integer.parseInt(input);
		    input=scan.next();	
		    x = Integer.parseInt(input);
		    input=scan.next();
		}   
		
		int[] arr = new int[input1.length];
		
		//converting the string array to integer array
		for(int i=0;i<input1.length;i++)
		{
			arr[i] = Integer.parseInt(input1[i]);
			
		}
		
		kwayMergeInsertion q = new kwayMergeInsertion();
		
		arr =q.kwayMergeInsertionsort(arr,k,x);	
		
		//printing the sorted array
		System.out.println();
		for(int i=0;i<arr.length-1;i++)
			System.out.print(arr[i]+" ");
			System.out.println(arr[arr.length-1]);
			
		//printing the division arrays
		q.print(q.div,q.dsize);	
		//printing the merging arrays
		q.print(q.merg,q.msize);
	

	}
	
	public int[] kwayMergeInsertionsort(int[] arr, int k, int x)
	{		
		//if arr length becomes less than k then go for insertion sort
		if(k>arr.length)
			return insertionsort(arr,x);
		
		//multidimensional array for storing the divided array
		int[][] arr1 = new int[k][];
		int m = 0;
		
		//dividing array
		for(int i = 0;i<arr.length;)
		{
			int n = 0;
			arr1[m] = new int[arr.length/k];
			while(n<arr.length/k)
			{
				
				arr1[m][n] = arr[i];
				i++;
				n++;				
			}
			
			m++;
			
			//to store the rest of elements in the last row 
			//because there can be less than or more than k elements left in arr
			if(m == k-1)
			{
				n = 0;
				arr1[m] = new int[arr.length-(k-1)*(arr.length/k)];
				
				while(n<arr1[m].length)
				{
					arr1[m][n] = arr[i];					
					i++;
					n++;
				}
			}			
			
		}
		//calling to fill the global array for storing division arrays
		checkx(arr1,x,k);
		
		int[] res = new int[arr1[0].length];
		for(int i=0;i<arr1[0].length;i++)
			res[i]=arr1[0][i];
		
		//recursive call to kwaymergeInsertionsort
		for(int i=0; i<k-1; i++)
		{
			res = merge(kwayMergeInsertionsort(res,k,x),kwayMergeInsertionsort(arr1[i+1],k,x),x);
		}
		
		return res;		
	}
	
	public int[] merge(int[]a, int[] b, int x)
	{
		int[] c=new int[a.length+b.length];
		
			
			int l=0,count1=0,count2=0; 
			//implementing the traditional merge sort algorithm
			while((count1<a.length)&&(count2<b.length))
			{
				if(a[count1]<b[count2])
				{
					c[l]=a[count1];
					count1++;					
				}
				else 
				{
					c[l]=b[count2];
					count2++;
				}
				l++;
		
			}
			
			if(count2<b.length)
			{				
				while(count2<b.length)
				{
					c[l]=b[count2];
					count2++;
					l++;
				}
			}
			
			if(count1<a.length)
			{				
				while(count1<a.length)
				{
					c[l]=a[count1];
					count1++;
					l++;					
				}
			}	
			
		//call to store merging time arrays in global merg array
			checkx1(c,x);
			
		return c;
}
	
	//traditional insertion sort implementation
	public int[] insertionsort(int[] arr,int x)
	{
		int temp=0;
		int i=0,k=1,j=0;
		while(j!=arr.length-1)
		{
			j++;
			k=j-1;
			i=j;
			while(k>=0)
			{
				
				if(arr[i]<arr[k])
				{
					temp=arr[k];
					arr[k]=arr[i];
					arr[i]=temp;
					k--; i--;
				}
				
				else
					break;
				
			}		
			
		}	
		
		return arr;
	}

	public void checkx(int[][] arr, int x, int k)
	{
		boolean flag = false;
		int i=0;
		
		while(i<k)
		{
			flag=false;
			for(int j =0; j<arr[i].length;j++)
			{
				//checking if the element x is present in the array
				if(arr[i][j]==x)
					flag = true;
			}
			
			//if x is present then fill the array as one of the rows in div array
			if(flag == true)
			{
				//System.out.println();
				div[dsize]= new int[arr[i].length];
				for(int j =0; j<arr[i].length;j++)
				div[dsize][j]=arr[i][j];
				dsize++;
			}
			
			
			i++;
		}	
		
	}
	
	public void checkx1(int[] arr, int x)
	{
		boolean flag = false;
		
		for(int i=0;i<arr.length;i++)
		{
			if(arr[i]==x)
				flag=true;
		}
		
		if(flag==true)
		{
			
			merg[msize]= new int[arr.length];
			for(int i=0;i<arr.length;i++)
				merg[msize][i]=arr[i];
			msize++;
		}
		
	}
	
	//printing the global multidimensional arrays
	public void print(int[][] arr,int size)
	{
		
		for(int i=0;i<size;i++)
		{				
			for(int j=0;j<arr[i].length;j++)
				System.out.print(arr[i][j]+" ");
			System.out.println();
		}	
		
				
	}

}
