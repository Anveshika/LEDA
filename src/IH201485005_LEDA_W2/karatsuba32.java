import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class karatsuba32 {

	public static void main(String[] args) throws IOException 
	{
		String num1, num2,num3;
		
		num3="";		
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Enter the first number\n");		
		num1=br.readLine();				//storing the big numbers as strings
		
		System.out.println("Enter the second number\n");		
		num2=br.readLine();				//storing the big number as string
		
				
		num3=kar_multiply(num1,num2);
		num3=num3.replaceFirst("^0*","");
		System.out.println(num3);
		
	}
	
	public static String kar_multiply(String a, String b)
	{
		if(b.length()>a.length())
		{
			int n=b.length()-a.length();
			while(n>0)
			{
				a='0'+a;
				n--;
			}
			
		}
	
	  else if(a.length()>b.length())
	  {
		int n=a.length()-b.length();
		while(n>0)
		{
			b='0'+b;
			n--;				
		}
		
	  }
		int na=a.length();
		int nb=b.length();
		String res="";
		String b0="",b1="",a0="",a1="";
		
		//base condition for recursion
		if((na<=32)&&(nb<=32))		
			return scl_multiply(a,b);
		
		
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
			String p2=kar_multiply(a1,b1);
			
			//implementing third part of expression
			String p0=kar_multiply(a0,b0);
			

			//implementing 2nd part of expression 
			String p1=kar_multiply(add(a0,a1),add(b0,b1));	
			String res1=sub(p1,add(p2,p0));
			
			int n1=na;
			//appending just zeroes at the end since we are working with 10's complement for p2
			while (n1>0)
				{
					p2=p2+'0';
					n1--;					
				}
			
			n1=na/2;
			//appending zeroes at end in middle term
			while (n1>0)
			{
				res1=res1+'0';
				n1--;
			}
			
			
			res=add(p2,res1);
			res=add(res,p0);		
			
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
		
	public static String sub(String num1,String num2)
	{		
		
		if(num1.equals(num2))
			return "0";
			
		
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
		
		String num3=checkSmall(num1,num2);
		
		if(num3.equals(num1))		
			//then implement num2-num1
			return "-"+subtract(num2,num1);	
			
		else
			return subtract(num1,num2);
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
	
public static String scl_multiply(String num1,String num2)
{
		
	String num3,num4;
	
	num4="";
	int res, carry=0,n1,n2=1;	
	
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
	
	return num4;
}

public static String checkSign(String num1,String num2)
{		
   if(((num1.charAt(0)!='-')&&(num2.charAt(0)=='-'))||((num1.charAt(0)=='-')&&(num2.charAt(0)!='-')))
		return ""+'-';			
	
	else 
		return "";		
}
}
