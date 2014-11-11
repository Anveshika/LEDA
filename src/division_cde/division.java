
import java.util.Scanner;


public class division 
{

	public static void main(String[] args) throws Exception
	{
		String num,den;
		Scanner scan=new Scanner(System.in);
		num=scan.next();
		den=scan.next();
		
		den=den.replaceFirst("^0*","");
		
		divide(num,den);

		
	}
	
	public static void divide(String num,String den)
	{
		int ln=num.length();		
		int ld=den.length();
		
		
		
		String a0="",a1="";
		String n="0";
		String quo="",rem="";		
		
		
		//if the numerator is less than the denominator then 
		//quotient is zero and remainder is the numerator itself
		if(!compare(num,den))
		{

			quo="0";
			rem+=num;
			System.exit(0);
		}
		
		
		//if number of digits in numerator>number of digits in denominator
		if(ln>=ld)
		{
			a0=num.substring(0,ld);
			a1=num.substring(ld);

			
			//if a0 is less than den then concatenate another digit to it
			if(!compare(a0,den))
			{
				a0+=a1.charAt(0);
				a1=a1.substring(1);
				
			}
		}
		
		
		while(a0.length()>0)
		{
			rem="";
			 n="0";
			String b=multiply(den,n);
			
			while(compare(subtract(a0,b),den))
			{
				
				
				n=add(n,"1");
				
				
				b=multiply(den,n);
				System.out.println("b= "+b);
				
				
			}
			
			quo=quo+n;
		
			rem=subtract(a0,b);			
			
			while(rem.charAt(0)=='0')
			{
				if(rem.length()==1)
					break;
				rem=rem.substring(1);
			}	
			
			
			if(a1.length()>1)
			{
				a0=rem+a1.charAt(0);
				a1=a1.substring(1);
			}
			
			else if(a1.length()==1)
			{
				a0=rem+a1.charAt(0);
				a1="";
			}
			
			else if(a1.length()==0) break;
			
			
			else if(!compare(a0,den))
			{
				
				quo=quo+'0';
	
				 if(a1.length()>=2)
				{
					a0=a0+a1.charAt(0);	
					a1=a1.substring(1);
				}
				
				else if(a1.length()==1)
					{
						a0=a0+a1.charAt(0);	
						a1="";
					}				
							
			}	
			
		}		
			
		System.out.println("quo="+quo);	
		System.out.println("rem="+rem);

	}
	
	
	



public static boolean compare(String num1,String num2)
{
	
	int n=0;
	
	//if num2 is an empty string 
		if(num2.length()==0)
			return true;
	if(num2.length()<num1.length())
	{
		int diff=num1.length()-num2.length();
		while(diff!=0)
		{
			num2="0"+num2;
			diff--;
		}
	}	
	
	
	while(n<num1.length())
	{
		//comparing each digit of the two strings starting from the left hand side of the strings
		char c1=num1.charAt(n);		
		char c2=num2.charAt(n);
		
		int n1=Integer.parseInt(""+c1);
		int n2=Integer.parseInt(""+c2);
		n++;
		
		if(n1<n2)
			{
				
				return false;
			}
		else if(n1>n2)
			return true;
		else
			continue;
		
	}
		return true;
}

public static String subtract(String s1,String s2)
{
	//if the strings are equal
	if(s1.equals(s2))	
		return "0";
	
	
	
	//appending zeros to num2 if length of num2 is less
	if(s2.length()<s1.length())
	{
		int n=s1.length()-s2.length();
		while(n!=0)
		{
			s2="0"+s2;
			n--;
		}
	}

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

	public static String multiply(String num1,String num2)
	{
		String num3,num4;
		
		num4="";
		int res, carry=0,n1,n2=1;		
		
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
		
		
		return num4;
	}
}
