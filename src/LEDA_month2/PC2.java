/*program to sort the chains as the numbers are entered in the hash table*/

import java.util.Scanner;
public class PC2 {

	public static void main(String[] args) {
		
		Scanner scan=new Scanner(System.in);
		
		hashingWithChaining ht = new hashingWithChaining();
		
		System.out.println("Enter the array: ");
		String input=scan.nextLine();		
		
		String[] input1 = input.split(",");				
	  
		for(int i=0;i<input1.length;i++)
		{			
			if(input1[i].substring(0,1).equals("I"))
			{				
				ht.put(Integer.parseInt(input1[i].substring(1)));
			}
			
			else
			{
				ht.remove(Integer.parseInt(input1[i].substring(1)));
			}
		}
		
		ht.display();
		scan.close();
	}

}

class hashingWithChaining
{
	Node1 table[];
	Node1 temp[];
	int size=0;
	public hashingWithChaining()
	{
		table=new Node1[10];
		for(int i=0;i<table.length;i++)
			table[i]=new Node1();
	}
	
	public int hash(int k)
	{		
		return k%10;
		
	}
	public void put(int v)
	{
		
		Node1 n=new Node1(v);
		Node1 n1 = new Node1();
		
	   int i=hash(v);
	   if(table[i].next==null)
	   {
		   table[i].next=n;
		   size++;
	   }
	   else if(table[i].next.value>n.value)
	   {
		   n.next=table[i].next;
		  table[i].next=n; 
		  size++;
	   }
	   
	   else if(table[i].next.value<n.value)
	   {
		   n1=table[i].next;
		   while(n1.next!=null)
		   {
			   n1=n1.next;
			   if(n1.value<n.value)
				   continue;
			   else
				   break;
		   }
		   
		   if(n1.next==null)
			   n1.next=n;		   
		  
	   }
	   
	   display();   
		
	}
	public int remove(int v)
	{		
		int i=hash(v);
		Node1 temp=table[i];
		while(temp.next!=null)
		{
			if(temp.next.value==v)
			{
				temp.next=temp.next.next;
				size--;
				return temp.value;
			}
			else
			{
				temp=temp.next;
				size--;
			}
		}
		return 0;
	}
	
	public int get(int v)
	{
		int x=hash(v);
		Node1 temp=table[x].next;
		while(temp!=null)
		{
			if(temp.value==v)
				return temp.value;
			else
				temp=temp.next;
		}
		return 0;
	}
	
	public void display()
	{
		
		for(int i=0;i<table.length;i++)
		{
			System.out.print(i+":");
			if(table[i].next!=null)
			{				
				Node1 temp=table[i].next;				
				while(temp!=null)
				{
					System.out.print(","+temp.value);
					temp=temp.next;
				}				
				
			}
			
			System.out.println();
		}
	}
	
}

class Node1
{
	int value;	
	Node1 next;
	
	public Node1()
	{
		this.value=0;		
		this.next=null;
	}
	
	public Node1(int value)
	{
		this.value=value;		
		this.next=null;
	}
}


