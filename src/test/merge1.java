package test;
import java.util.Scanner;

//import DListNode;

public class merge1 {

	public static void main(String[] args)
	{
		Scanner scan=new Scanner(System.in);
		System.out.println("Enter two lists in sorted order(increasing)");
		
		//initializing the objects of list
		DList d1=new DList(),
				d2=new DList(),
				d3=new DList();		//final list which will contain merged list		
		
		System.out.println("How many items in  list?");
		int n=scan.nextInt();
		
		//input first list
		System.out.println("Enter the first list");
		for(int i=0;i<n;i++)
			d1.insertEnd(scan.nextInt());
		
		for(int i=1;i<=d1.size;i++)
			System.out.println("d1.i="+d1.findFirst().nth(i).item);
		
		//input second list		
		System.out.println("Enter the second list");
		for(int i=0;i<n;i++)
			d2.insertEnd(scan.nextInt());	
		
		for(int i=1;i<=d1.size;i++)
			System.out.println("d2.i="+d2.findFirst().nth(i).item);
		
		System.out.println("\nMerged List");
		int pos1=1,pos2=1;
		
		//finding the first element of both the lists first
		DListNode n1=new DListNode(d1.findFirst().item);
		DListNode n2=new DListNode(d2.findFirst().item);		
		
		//filling the d3 list with the elements of d1 and d2
		while((pos1<=n)&&(pos2<=n))
		{	
			
			//size starts from 0
			if((d3.size+1)%2==1)
			{
				if(n1.item>=n2.item)		
				{
					d3.insertEnd(n2.item);
					pos2++;
					n2=d2.findNext(n2.item);
				}
				else if(n1.item<n2.item)
				{
					d3.insertEnd(n1.item);
					pos1++;
					n1=d1.findNext(n1.item);					
				}
			}
			
			else if((d3.size+1)%2==0)
			{
				if(n1.item<=n2.item)		
				{
					d3.insertEnd(n2.item);					
					pos2++;							
					n2=d2.findNext(n2.item);
					
				}
				else if(n1.item>n2.item)
				{
					d3.insertEnd(n1.item);					
					pos1++;					
					n1=d1.findNext(n1.item);					
				}
			}
			
		}
		
		//if loop ends without finishing all the elements of the list
		
			while(pos1<=n)
			{				
				d3.insertEnd(n1.item);
				pos1++;
				n1=d1.findNext(n1.item);				
			}
		
			while(pos2<=n)
			{
				d3.insertEnd(n2.item);
				pos2++;
				n2=d2.findNext(n2.item);
				
			}
		
			for(int i=1;i<=d3.size;i++)
				System.out.println("d3.i="+d3.findFirst().nth(i).item);

	}

}

class DListNode {

	int item;
	DListNode next;
	DListNode prev;
	
	public DListNode(int item,DListNode prev,DListNode next)
	{
		this.item=item;
		this.next=next;
		this.prev=prev;
	}
	
	public DListNode(int item)
	{
		this.item=item;
		this.next=null;
		this.prev=null;
	}
	

	public static void InsertAfter(DListNode node, DListNode newNode)
	{
		newNode.next=node.next;
		node.next=newNode;
		newNode.prev=node;
	}
	
	public DListNode nth(int position)
	{
		if(position==1)
			return this;
		else if((position<1)||(next==null))
			return null;
		else
			return next.nth(position-1);
	}
	
	//when nodes are to be created inside the method
	public void InsertBefore(int item)
	{
		prev=new DListNode(item,this,prev);
	}
	
	//method using already created nodes
	public void InsertBefore(DListNode node, DListNode newNode)
	{
		newNode.next=node;
		newNode.prev=node.prev;
		node.prev=newNode;
	}
}

class DList
{
	private DListNode head;
	private DListNode tail;
	int size=0;
	
	public DList()
	{
		head=null;
		tail=null;		
		
	}
	
	//deleting an item at the beginning of the list
	public void deleteFront()
	{
		if(head!=null)
		{
			head=head.next;
			size--;
		}			
	}
	
	//deleting an item at the end of the list
	public void deleteEnd()
	{
		tail.prev.next=null;
		tail=tail.prev;
		size--;
	}
	
	//inserting an item at the front of list
	public void insertFront(int item)
	{
		
		if(tail==null)
		{
			head=new DListNode(item,null,head);
			tail=head;
		}
		
		else
		{
			head.prev=new DListNode(item,null,head);
			head=head.prev;
		}
		size++;
	}
	
	//inserting an item at the end of a list
	public void insertEnd(int item)
	{
		if(head==null)
		{
			tail=new DListNode(item,tail,null);
			head=tail;		
		}
		
		else
		{
			tail.next=new DListNode(item,tail,null);	
			tail=tail.next;
		}		
		
		size++;
	}
	
	
	public DListNode findFirst()
	{		
		return this.head;
	}
	
	public DListNode findNext(int item)
	{
		DListNode node=new DListNode(item);
		node=head;
		while(node.item!=item)
		{
			node=node.next;				
		}
		return node.next;	
	}
	
}