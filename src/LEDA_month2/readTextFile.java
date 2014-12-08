import java.io.File;
import java.io.FileNotFoundException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;
public class readTextFile 
{
	
	Enumeration<String> e;
	Hashtable<String,Integer> ht=new Hashtable<String,Integer>(); 
 
	public void insert(String s)
	{
		if(ht.containsKey(s))
		{
		 int n=ht.get(s);
		 ht.put(s,n+1);
		}
		else
			ht.put(s,1); 
	}
 
 public void sort()
 {
	 int max=1;
	 e=ht.keys();
	 while(e.hasMoreElements()) 
     {
		 String str = (String) e.nextElement();
	        int m=ht.get(str);
	        if(m>max)
	        	max=m;
     }
	 
	 while(max>=1)
	 {
		 e=ht.keys();
		 while(e.hasMoreElements()) 
	     { 
			 String str = (String) e.nextElement();
			 if(ht.get(str)==max)
			 {
			 System.out.println(str + ": " +ht.get(str));
			 }
	     }
		 --max;
	 }
 }
 
 public void display()
 {
	 int a,b;
	 e=ht.keys();
	 while(e.hasMoreElements()) 
     {
        String str = (String) e.nextElement();
        System.out.println(str + ": " +ht.get(str));
     }
	
 }

 public static void main(String[] args) throws FileNotFoundException
 {
	 readTextFile w= new readTextFile();
		Scanner in=new Scanner(new File("file.txt"));
		while (in.hasNext()) 
		{
			String s=in.next();
			s = s.toLowerCase();
			w.insert(s);
		}
		System.out.println("Unsorted Hash Table");
		w.display();
		System.out.println("Sorted Hash Table");
		w.sort();
		
 }

}
