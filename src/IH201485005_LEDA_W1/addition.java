import java.io.*;
import java.lang.String;
public class addition {

	public static void main(String[] args) throws IOException 
	{
		String num1, num2,num3;
		num3="";
		int res, carry=0;
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));


		num1=br.readLine();	
		num2=br.readLine();	
		
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
		System.out.println(num3);
	}
}
