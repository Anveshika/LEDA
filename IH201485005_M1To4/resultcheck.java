
import java.io.IOException;
import java.util.Scanner;


public class resultcheck {

	public static void main(String[] args) throws IOException
	{
		String num1, num2,num3;
		
		Scanner br=new Scanner(System.in);		
		String sum2,sum3,sum1="";
		num1=br.next();			
		num2=br.next();	
		num3=br.next();	
		br.close();
		
		sum1=sum(num1);
		System.out.println(sum1);
		sum2=sum(num2);
		System.out.println(sum2);
		sum3=sum(num3);
		System.out.println(sum3);		

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
	
	public static String sum(String num)
	{
		String sum="";
		int index;
		while(num.length()>1)
		{
			index=0;
			sum="";
			while(index<num.length())
			{
				sum=add(""+num.charAt(index),sum);				
				index++;
			}
			
			num=sum;
		}	
		return sum;
	}

}
