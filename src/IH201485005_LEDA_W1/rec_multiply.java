import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class rec_multiply {

	public static void main(String[] args) throws IOException 
	{
		String num1, num2,num3;
		
		num3="";		
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Enter the first number\n");		
		num1=br.readLine();				//storing the big numbers as strings
		
		System.out.println("Enter the second number\n");		
		num2=br.readLine();				//storing the big number as string

		if(num2.length()>num1.length())
			{
				int n=num2.length()-num1.length();
				while(n>0)
				{
					num1='0'+num1;
					n--;
				}
				System.out.println("num1= "+num1);
			}
		
		else if(num1.length()>num2.length())
		{
			int n=num1.length()-num2.length();
			while(n>0)
			{
				num2='0'+num2;
				n--;				
			}
			System.out.println("num2= "+num2);
		}
				
		num3=multiply(num1,num2);
		
		System.out.println(num3);
		
	}
	
	public static String multiply(String a, String b)
	{
		
		int na=a.length();
		int nb=b.length();
		String res="";
		String b0="",b1="",a0="",a1="";
		
		//base condition for recursion
		if((na==1)&&(nb==1))
		{
			int ai=Integer.parseInt(a);
			int bi=Integer.parseInt(b);
			return res+(ai*bi);
		}
		
		else
		{
			//padding with zero if there are odd number of digits in the number
			if(na%2==1)
			{
				a='0'+a;
				na+=1;
			}			
			
			//dividing the digit into a1 and a0
		    a1=a.substring(0,na/2);
			a0=a.substring(na/2);
			
			//padding with zero if there are odd number of digits in the number
			if(nb%2==1)
			{
				b='0'+b;
				nb+=1;
			}
		
			//dividing the digit into b1 and b0
			b1=b.substring(0,nb/2);
			b0=b.substring(nb/2);				

			//a · b = a1 · b1 ·B^2k +(a1 · b0+a0 · b1) ·B^k +a0 · b0 
			
			//implementing first part of expression 
			res=multiply(a1,b1);
			int n1=na;
			//appending just zeroes at the end since we are working with 10's complement
			while (n1>0)
				{
					res=res+'0';
					n1--;					
				}

			//implementing 2nd part of expression 
			String res1=add(multiply(a1,b0),multiply(a0,b1));			
			n1=na/2;
			//appending zeroes at end
			while (n1>0)
			{
				res1=res1+'0';
				n1--;
			}
			
			res=add(res,res1);
			
			//implementing 3rd part of expression 
			res=add(res,multiply(a0,b0));
			
			return res;
			
		}	
		
	}

	//method to add two strings
		public static String add(String num1,String num2)
		{
			
			String num3="";
			int res, carry=0;
			
			
			//appending zeros to num1 if length of num1 is less
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
			
			//running the loop till strings are not null
			while(num1.length()!=0)
			{
				
				char c1=num1.charAt(num1.length()-1);
				char c2=num2.charAt(num2.length()-1);
				
				//convert c1 and c2 into integer and then add
				res=Integer.parseInt(""+c1)+ Integer.parseInt(""+c2)+carry;		
				
				//if we get a 2 digit number after addition then breaking the carry and storing the units part
				if(res/10!=0)
				{
					num3=(res%10)+num3;
					carry=res/10;
				}
				
				else	
				{
					num3=res+num3;	
					carry=0;
				}
			
				//truncating the last digit of num1 and num2
				num1=num1.substring(0,num1.length()-1);
				num2=num2.substring(0,num2.length()-1);
			}
			
			if(carry!=0)
			num3=carry+num3;
			//System.out.println("num3= "+num3);
			return num3;
		}
}
