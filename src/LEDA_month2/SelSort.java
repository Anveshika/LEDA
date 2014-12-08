
public class SelSort {

	public static void main(String[] args) 
	{
		int[] arr={4,3,6,12,25,13,20};
		SelSort s=new SelSort();
		s.sort(arr);
		for(int i=0;i<arr.length;i++)
		System.out.println(arr[i]);
	}
	
	public void sort(int[] arr)
	{
		int temp=0;
		for(int i=0;i<arr.length;i++)
		{
			int min = arr[i];	
			int j=i;
			for(j=i;j<arr.length;j++)
				if(min>=arr[j])
				{
					min=arr[j];
					temp=j;
				}
						
			int temp1=arr[i];
			arr[i]=arr[temp];
			arr[temp]=temp1;			
			
		}
	}

}
