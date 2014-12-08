/*program to sort the string array according to the priority order given
 * the program takes two strings as input-one- the string to be sorted
 * (e.g,ba bb cdie a j c h ja he cf dffc acd fi fff) and
 * two-the priority order (e.g.,c h a b e j f i d g)
 * the output will be (c cf cdie h he a acd ba bb j ja fff fi dffc)*/

import java.util.Scanner;
public class sortString {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		String[] input1 = input.split(" ");
		
		
		input = scan.nextLine();
		
		
		
		
		String[] order1 = input.split(" ");
		
		String order ="";
		for(int i =0;i<order1.length;i++)
		{
			order=order+ order1[i];
		}
		
		sortString s = new sortString();
		String[] output = s.sort(input1,order);
		
		for(int i=0;i<output.length-1;i++)
		{
			System.out.print(output[i]+" ");
		}
		System.out.println(output[output.length-1]);

	}
	
	public String[] sort(String[] a, String order)
	{
		
		for(int i =0;;i++)
		{
			boolean flag = false;
			for(int j = 0;j<a.length-1;j++)
			{
				if(compare(a[j],a[j+1],order))
				{
					flag=true;
					swap(a,j,j+1);
				}
			}
			
			if(flag==false)
				break;
				
		}
		return a;
	}
	
	public boolean compare(String a, String b, String order)
	{
		int la = a.length();
		int lb= b.length();
		
		int count = 0;
		if(la>lb)
		{
			
			while(count!= lb)
			{
				
				if(order.indexOf(a.charAt(count))>order.indexOf(b.charAt(count)))
					return true;
				
				if(order.indexOf(a.charAt(count))<order.indexOf(b.charAt(count)))
					return false;
				count++;
			}
			
			return true;
		}
		count = 0;
		if(la<=lb)
		{
			
			while(count!=la)
			{
				
				if(order.indexOf(a.charAt(count))>order.indexOf(b.charAt(count)))					
					return true;
				
				
				if(order.indexOf(a.charAt(count))<order.indexOf(b.charAt(count)))					
					return false;
				
				count++;
			}
			
			return false;
		}
		
		return false;
	}
	
	public String[] swap(String[] a, int i, int j)
	{
		
		String temp="";
		temp=a[i];
		a[i] = a[j];
		a[j] = temp;		
		
		return a;
	}

}
