import java.io.*;
//implementing the great school multiplication
public class multiplication {

	public static void main(String[] args) throws IOException 
	{
		String num1, num2,num3,num4;
		
		num4="";
		int res, carry=0,n1,n2=1;
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		//System.out.println("Enter the first number\n");		
		num1=br.readLine();				//storing the big numbers as strings
		
		//System.out.println("Enter the second number\n");		
		num2=br.readLine();				//storing the big number as string
		
		String sign=checkSign(num1,num2);
		
		if(num1.charAt(0)=='-')
			num1=num1.substring(1,num1.length());
		if(num2.charAt(0)=='-')
			num2=num2.substring(1,num2.length());
		
		//the program will implement num1*num2
		//picking each digit of num2, one at a time 
		//loop will continue till all of the digits of num2 have been used
		while(n2<num2.length()+1)
		{
			n1=1;
			num3="";
			//traversing all the digits of num1 to multiply with each digit of num2
			while(n1<(num1.length()+1))
			{
				
				//reading one character at a time
				char c1=num1.charAt(num1.length()-n1);
				char c2=num2.charAt(num2.length()-n2);
				
				n1++;
				//converting character into integer so as to multiply
				//implementing multiplication and adding the carry
				res=(Integer.parseInt(""+c1)*Integer.parseInt(""+c2))+carry;
				
				//separating carry part and the units digit
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
			
			}
			if(carry!=0)
			{
				num3=carry+num3;
				carry=0;
			}
			
			//appending zeroes at the end of the string obtained after multiplication of each digit of num2 with num1
			int a=n2;
			while(a>1)
			{
				num3+='0';
				a--;
			}
			
			num4=add(num3,num4);			
			n2++;
		}	
		num4=sign+num4;
		
		System.out.println(num4);
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

	//method to return the sign that should appear after multiplication
	public static String checkSign(String num1,String num2)
	{		
	   if(((num1.charAt(0)!='-')&&(num2.charAt(0)=='-'))||((num1.charAt(0)=='-')&&(num2.charAt(0)!='-')))
			return ""+'-';			
		
		else 
			return "";		
	}
}
