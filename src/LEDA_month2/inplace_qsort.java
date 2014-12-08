import java.util.Scanner;

public class inplace_qsort {

	int comp_x_pivot=0;
	int x;
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		int n0= scan.nextInt();		
		
		String[] input1=input.split(" ");
		
		int[] input2 = new int[input1.length];
		
		for(int i=0;i<input1.length;i++)
			input2[i]=Integer.parseInt(input1[i]);
		
		inplace_qsort iq=new inplace_qsort();
		int a = scan.nextInt();
		
		for(int i=0;i<input2.length;i++)
			if(input2[i]==a)
			{
				iq.x=i ;
				break;
			}
		
		iq.qsort(input2,0,input2.length-1,n0);
		
		System.out.println();
		for(int i =0;i<input2.length;i++)
			System.out.print(input2[i]+" ");
		
		System.out.println("\n"+iq.comp_x_pivot);
		

	}
	
	public void qsort(int[] arr, int l, int r, int n0)
	{
		int j;
		while(r-l+1>n0)
		{
			
			int p = arr[l];	
			
			int i = l;
			 j = r;

			
			while(i<=j)
			{
				if(i==x)
					comp_x_pivot++; 

				if(j==x)
					comp_x_pivot++;
				
				while(arr[i]<p)
				{					
					i++;
					if(i==x)
						comp_x_pivot++;
				}			
				
				while(arr[j]>p)
				{
					j--;

					if(j==x)
						comp_x_pivot++;
				}
				
				if(i<=j)
				{
					swap(arr,i,j);
					i++;
					j--;					
				
				}
			}
			
			if(i<(l+r)/2)
			{
				qsort(arr,l,j,n0);
				l=i;
			}
			
			else
			{
				qsort(arr,i,r,n0);
				r=j;
			}
		}
		
		insertionsort(arr);
	}
	
	
	public int[] swap(int[]a, int i, int j)
	{
		int temp;
		temp = a[i];
		a[i]=a[j];
		a[j]=temp;
		
		return a;
	}
	
	public int[] insertionsort(int[] arr)
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

}
