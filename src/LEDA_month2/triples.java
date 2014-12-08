import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class triples { 
	Hashtable<String,Integer> table = new Hashtable<String,Integer>();
	 Enumeration<String> e;
	 
	 public void insert(String s,int price)
	 {
		 int n;
		 if(table.containsKey(s))
		 {
			  n=table.get(s);
			 table.put(s,n+price);
		 }
		 else
			 table.put(s,price); 
	 }
	 public void display()
	 {
		
		Vector v=new Vector(table.keySet());
         Collections.sort(v);
         Iterator k=v.iterator();
         while(k.hasNext())
           {
        	 String s2=(String) k.next();
             System.out.println(s2+"-"+table.get(s2));
            }
		
	 }



    public static void main(String args[] ) throws Exception {
      
         triples t= new triples();
		 String[] a;
			Scanner in=new Scanner(System.in);
        String s1=in.nextLine();
			while (in.hasNext()) 
			{
                
				String s=in.nextLine();
                if(!s.equals("end"))
                    {
				a=s.split(",");
				int price=Integer.parseInt(a[2]);
				t.insert(a[1],price);
                }
			}
			t.display();
    }
}
