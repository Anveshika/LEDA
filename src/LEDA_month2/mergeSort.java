

public class mergeSort {

	public static void main(String[] args)	{
		
		
		
		int arr[]={5,3,5,2,6,6,7};
		
		mergeSort s=new mergeSort();
		//call mergesort1 for ascending order and mergesort2 for descending order
		arr= s.mergesort1(arr);
		for(int i=0;i<arr.length;i++)
			
		System.out.print(arr[i]+",");
		System.out.println();
		

	}
	//ascending order
	public int[] mergesort1(int[] arr)
	{
		if(arr.length==1)
			return arr;
		
		else
		{
			int[] arr1=new int[arr.length/2];
			int[] arr2=new int[arr.length-arr.length/2];
			int count=0;
			
			//dividing array into two halves
			for(int i=0;i<arr.length;i++)
			{
				if(i<arr.length/2)
					arr1[i]=arr[i];
				
				else
				{
					arr2[count]=arr[i];
					count++;
				}	
			}
							
			return merge1( mergesort1(arr1),mergesort1(arr2));
		}
		
	}	
	
	
	public int[] merge1(int[]a, int[] b)
	{
		int[] c=new int[a.length+b.length];
		
			
			int l=0,count1=0,count2=0; 
			
			while((count1<a.length)&&(count2<b.length))
			{
				if(a[count1]<b[count2])
				{
					c[l]=a[count1];
					count1++;					
				}
				else 
				{
					c[l]=b[count2];
					count2++;
				}
				l++;
		
			}
			
			if(count2<b.length)
			{				
				while(count2<b.length)
				{
					c[l]=b[count2];
					count2++;
					l++;
				}
			}
			
			if(count1<a.length)
			{				
				while(count1<a.length)
				{
					c[l]=a[count1];
					count1++;
					l++;					
				}
			}		
		
		
		return c;
}
	//descending order
	public int[] mergesort2(int[] arr)
	{
		if(arr.length==1)
			return arr;
		
		else
		{
			int[] arr1=new int[arr.length/2];
			int[] arr2=new int[arr.length-arr.length/2];
			int count=0;
			
			//dividing array into two halves
			for(int i=0;i<arr.length;i++)
			{
				if(i<arr.length/2)
					arr1[i]=arr[i];
				
				else
				{
					arr2[count]=arr[i];
					count++;
				}	
			}
							
			return merge2( mergesort2(arr1),mergesort2(arr2));
		}
		
	}
	
	public int[] merge2(int[]a, int[] b)
	{
		int[] c=new int[a.length+b.length];
		
			
			int l=0,count1=0,count2=0; 
			
			while((count1<a.length)&&(count2<b.length))
			{
				if(a[count1]>b[count2])
				{
					c[l]=a[count1];
					count1++;					
				}
				else 
				{
					c[l]=b[count2];
					count2++;
				}
				l++;
		
			}
			
			if(count2<b.length)
			{				
				while(count2<b.length)
				{
					c[l]=b[count2];
					count2++;
					l++;
				}
			}
			
			if(count1<a.length)
			{				
				while(count1<a.length)
				{
					c[l]=a[count1];
					count1++;
					l++;					
				}
			}		
		
		
		return c;
}


}
