import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class subtraction {

	public static void main(String[] args) throws IOException
	{
		
		String num1, num2,num3;
		num3="";
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		//System.out.println("Enter the first number\n");		
		num1=br.readLine();	
		
		//System.out.println("Enter the second number\n");		
		num2=br.readLine();
		if(num1.equals(num2))
		{
			System.out.println("0");
			System.exit(0);;
		}
		if(num1.length()<num2.length())
		{
			int n=num2.length()-num1.length();
			while(n!=0)
			{
				num1="0"+num1;
				n--;
			}
		}
		
		//appending zeros to num2 if length of num2 is less
		else if(num2.length()<num1.length())
		{
			int n=num1.length()-num2.length();
			while(n!=0)
			{
				num2="0"+num2;
				n--;
			}
		}
		
		num3=checkSmall(num1,num2);
		
		if(num3.equals(num1))		
			//then implement num2-num1
			System.out.println("-"+subtract(num2,num1));	
			
		else
			System.out.println(subtract(num1,num2));
		

	}
	
	public static String checkSmall(String num1,String num2)
	{
		int n=0;
		
		while(n<num1.length())
		{
			char c1=num1.charAt(n);		
			char c2=num2.charAt(n);
			
			int n1=Integer.parseInt(""+c1);
			int n2=Integer.parseInt(""+c2);
			n++;
			
			if(n1<n2)
				return num1;
			else if(n1>n2)
				return num2;
			else
				continue;
			
		}
		if(n==num1.length())
			
		n++;
		return "0";
	}
	
	public static String subtract(String s1,String s2)
	{
		int b=0,res=0;
		String result="";
		while(s1.length()>0)
		{
		
			char ch1=s1.charAt(s1.length()-1);
			char ch2=s2.charAt(s2.length()-1);
		
			int n1=Integer.parseInt(""+ch1);
			int n2=Integer.parseInt(""+ch2);
		
			n1=n1-b;
			if(n1<n2)
			{
				n1=n1+10;
				b=1;
			}
			
			else
				b=0;
			
			res=n1-n2;
			result=res+result;
			
			s1=s1.substring(0,s1.length()-1);
			s2=s2.substring(0,s2.length()-1);
		}
		return result; 
	}

}
