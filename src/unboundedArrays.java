import java.util.Scanner;

public class unboundedArrays 
{
	int alpha,beta,n,w;
	int[] arr;
	public unboundedArrays()
	{
		alpha=4;
		beta=2;
		n=0;
		w=3;
		arr= new int[w];
	}
	public static void main(String[] args) 
	{
		unboundedArrays ar=new unboundedArrays();
		
		Scanner scan=new Scanner(System.in);
		
		
		System.out.println("Enter 5 elements");
		for(int i=0;i<5;i++)
			ar.pushBack(scan.nextInt());
		
		ar.display();
		
		//deleting 4 elements
		for(int i=0;i<4;i++)
			ar.popBack();
		ar.display();
		

	}
	
	public void pushBack(int element)
	{
		System.out.println("n="+n);
		System.out.println("w="+w);
		if(n==w)
		{			
			reallocate(beta*n);
			System.out.println("len="+arr.length);
		}		
		
		arr[n]=element;
		n++;
		
	}
	
	public void popBack()
    {
        if(n>0)
            n--;
        
        if((alpha*n<= w) && (n>0))
            reallocate(beta*n);        
    }
    
	public void reallocate(int n1)
    {
        w = n1;
        
        int[] arr1=new int[n1]; 
        
        for(int i=0;i<n;i++)
            arr1[i]=arr[i];
       
        arr=arr1;
        arr1=null;
    }  
   
    
    public void display(){
        System.out.println();
        for(int i=0;i<n;i++){
            System.out.print(arr[i] + " ");
        }
    }
    

}
