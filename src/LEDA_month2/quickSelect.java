
//implement quickselect and find the kth smallest element
import java.util.*;
public class quickSelect {   

    public static void main(String[] args) {

    	quickSelect q = new quickSelect();
        Scanner scan=new Scanner(System.in);
        String input=scan.next();
        String[] input1 = input.split(",");
        
         ArrayList<Integer> arr=new ArrayList<Integer>();
        for(int i=0;i<input1.length;i++)
        {
            arr.add(Integer.parseInt(input1[i]));
        }
        //System.out.println("enter k");
        int k=scan.nextInt();
        int j=1;
        for(int i=0;i<k-1;i++)
            {
            int res=q.select(arr,j);
            j++;
            System.out.print(res+",");
        }
        
       
        int res=q.select(arr,k);
        System.out.print(res);
        
    }
    
    public int select(ArrayList<Integer> arr,int k)
    {
       
        ArrayList<Integer> a=new ArrayList<Integer>();
       ArrayList<Integer> b=new ArrayList<Integer>();
       ArrayList<Integer> c=new ArrayList<Integer>();
        if(arr.size()>=k)
        {     
            int last=a.size()-1;
            int start=0;
            int middle=start+(last-start)/2;
            
            int p=arr.get(middle);
     
            for(int i=0;i<arr.size();i++)
            {
                if(arr.get(i)<p)
                {
                    a.add(arr.get(i));
                }
                else if(arr.get(i)==p)
                {
                    b.add(arr.get(i));
                }
                else
                    c.add(arr.get(i));
            }
            if(a.size()>=k)
            {
                
                return select(a,k);
            }
            else if((a.size()+b.size())>=k)
                return p;
            
        }
        
            
            int ab=k-a.size()-b.size();
            return select(c,ab);
        
    }

}