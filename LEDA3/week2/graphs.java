package week2;

import java.io.*;
import java.util.Scanner;
public class graphs {
	public static void main(String[] args)
	{
		Scanner in=new Scanner(System.in);
		String[] s1=in.nextLine().split(", ");
		String s2="";
		for(int i=0;i<s1.length;i++)
		{
			  if(s2.indexOf(s1[i].charAt(1))==-1)
			s2=s2+s1[i].charAt(1);
			  if(s2.indexOf(s1[i].charAt(3))==-1)
			s2=s2+s1[i].charAt(3);
			
		}
		//System.out.println(s2);
		int[][] a=new int[s2.length()][s2.length()];
		for(int i=0;i<s2.length();i++)
		{
			for(int j=0;j<s2.length();j++)
			     a[i][j]=0;
		}
		for(int i=0;i<s2.length();i++)
		{
			String n1=""+s2.charAt(i);
			//String n2=""+s1[i].charAt(3);
			for(int j=0;j<s1.length;j++)
			{
				if(n1.equals(""+s1[j].charAt(1)))
				{
					a[i][s2.indexOf(s1[j].charAt(3))]=1;
				}
			}	
		}
		  
		display(a,s2);
		System.out.println();
		adjacency_lists(a,s2);
	}
	public static void display(int[][] a, String s2)
	{
		System.out.print(" ");
		for(int i=0;i<s2.length();i++)
		System.out.print(" "+s2.charAt(i));
		System.out.println();
		for(int i=0;i<s2.length();i++)
		{
			System.out.print(s2.charAt(i)+" ");
			for(int j=0;j<s2.length();j++)
			     System.out.print(a[i][j]+" ");
			System.out.println();
		}
		
	}
	public static void adjacency_lists(int[][] a, String s2)
	{
		int j=0,k=0;
		for( j=0;j<s2.length();j++)
		{
			k=0;
			System.out.print(s2.charAt(j));
		for(int i=0;i<s2.length();i++)
		{
			if(a[j][k]==1)
				System.out.print("->"+s2.charAt(i));
			k++;
		}
		System.out.println("->"+"null");
		}
	}

}
