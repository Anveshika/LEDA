
public class insertionSort {

	public static void main(String[] args) {
		
		int arr[]={4,3,6,12,25,13,20};
		
		insertionSort s=new insertionSort();
		
		s.sort(arr);
		for(int i=0;i<arr.length;i++)
		System.out.println(arr[i]);
		
				
		}
	
	public void sort(int[] arr)
	{
		int temp=0;
		int i=0,k=1,j=0;
		while(j!=arr.length-1)
		{
			j++;
			k=j-1;
			i=j;
			while(k>=0)
			{
				
				if(arr[i]<arr[k])
				{
					temp=arr[k];
					arr[k]=arr[i];
					arr[i]=temp;
					k--; i--;
				}
				
				else
					break;
				
			}		
			
		}
	}

			

}
