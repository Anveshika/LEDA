import java.util.Scanner;
import java.io.*;
public class quicksort {

	public static void main(String[] args) {

		Scanner scan=new Scanner(System.in);
		
		//System.out.println("Enter the array: ");
		String input=scan.next();
		
		String[] input1 = input.split(",");
		
		int[] arr = new int[input1.length];
		
		for(int i=0;i<input1.length;i++)
		{
			arr[i] = Integer.parseInt(input1[i]);			
		}
		
		//printing state of array before making a call to sorting method
        System.out.print("(");
		for(int i=0;i<arr.length-1;i++)			
			System.out.print(arr[i]+",");
		System.out.println(arr[arr.length-1]+")");
		
		quicksort q = new quicksort();
		
		arr =q.sort(arr);			
		
		System.out.print("(");
		for(int i=0;i<arr.length-1;i++)			
			System.out.print(arr[i]+",");
		System.out.println(arr[arr.length-1]+")");

	}
	
	public int[] sort(int[] arr)
	{
		
		
		//base case
		if(arr.length<=1)
	
			return arr ;	
		
		
		String s1="";
		String s2="";
		String s3="";
		int count1=0,count2=0,count3=0;
		
		//segregating the elements of the array by comparing with the pivot element
		//choosing the first element of input array as the pivot 
		for(int i=0;i<arr.length;i++)
		{
			if(arr[i]<arr[0])
			{
				s1=s1+ arr[i]+" ";
				count1++;
			}
			
			if(arr[i]==arr[0])
			{
				s2 = s2+ arr[i]+ " ";
				count2++;
			}
			
			if(arr[i]>arr[0])
			{
				s3 = s3+ arr[i]+ " ";	
				count3++;
			}
		}
		
		if(count1==0)
			s1=s1+" ";
		if(count2==0)
			s2=s2+" ";
		if(count3==0)
			s3=s3+" ";
		
		//converting string into integer array format
		String[] a = s1.split(" ");
		String[] b = s2.split(" ");
		String[] c = s3.split(" ");
		
		int[] a1 = new int[a.length];
		int[] b1 = new int[b.length];
		int[] c1 = new int[c.length];		
		
		for(int i=0;i<a1.length;i++)				
			a1[i] = Integer.parseInt(a[i]);	
		
		for(int i=0;i<b1.length;i++)					
			b1[i] = Integer.parseInt(b[i]);		
		
		for(int i=0;i<c1.length;i++)
			c1[i] = Integer.parseInt(c[i]);
		display(a1, b1, c1);
		return concat(sort(a1),b1,sort(c1));		
		
	}
	
	//concatenating the three arrays in order given
	public int[] concat(int[] a, int[] b, int[] c)
	{		
		
		
		
		int[] d = new int[a.length+b.length+c.length];
		int count =0;
		for(int i=0;i< a.length;i++)
		{
			d[count] = a[i];
			count++;
		}
		
		for(int i=0;i< b.length;i++)
		{
			d[count] = b[i];
			count++;
		}
		
		for(int i=0;i< c.length;i++)
		{
			d[count] = c[i];
			count++;
		}
		
		return d;
	}
	
	public void display(int[] a, int[] b, int[] c)
	{	
		if(a.length!=0)
		{
			System.out.print("(");
			for(int i=0;i<a.length-1;i++)			
				System.out.print(a[i]+",");	
			System.out.print(a[a.length-1]+") : ");		
		}
		
		else
			System.out.print("() : ");
		
		if(b.length!=0)
		{
			System.out.print("(");
			for(int i=0;i<b.length-1;i++)		
				System.out.print(b[i]+",");
			System.out.print(b[b.length-1]+") : ");
		}
		
		else
			System.out.print("() : ");
		
		if(c.length!=0)
		{
			System.out.print("(");
			for(int i=0;i<c.length-1;i++)		
				System.out.print(c[i]+",");
			System.out.println(c[c.length-1]+") ");
		}
		
		else
			System.out.println("() ");
		
	}

}
