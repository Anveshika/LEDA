
public class test2 {

	public static void main(String[] args) 
	{
		int a=2; int b=3;
		int n=100,count=0;
		int count1=0;
		for(int i=1;i<=50;i++)
		{
			int num=a+i*b;
			System.out.println("num="+num);
			if(checkprime(num))
				count1++;
			//System.out.println(" "+);
			count++;
		}
		System.out.println("the total number of terms= "+count);
		System.out.println("total primes "+count1);
	}
	
	public static boolean checkprime(int n)
	{
		//boolean flag=false;
		
		for(int i=2;i<n/2;i++)
		{
			if(n%i==0)
				return false;
		}
		return true;
		
	}
}
