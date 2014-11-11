package test;

import java.util.Scanner;

public class que2 
{

	public static void main(String[] args) {
		
		Scanner scan=new Scanner(System.in);
		SList rec_list=new SList();
		
		System.out.println("Enter the number of records");
		int n=scan.nextInt();
		
		System.out.println("Enter the max CGPA value");
		int k=scan.nextInt()+1;
		
		System.out.println("Enter the records");
		for(int i=0;i<n;i++)
			rec_list.insertEnd(scan.nextInt());
		
		for(int i=0;i<rec_list.size;i++)
			System.out.println("rec_list."+(i+1)+"="+rec_list.getHead().nth(i+1).item);
			
		rec_list=sortList(rec_list,k);
		
		for(int i=1;i<=rec_list.size;i++)
			System.out.println(rec_list.getHead().nth(i).item);

	}
	
	public static SList sortList(SList rec_list,int k)
	{
		for(int i=0;i<k;i++)
			for(int j=1;j<=rec_list.size;j++)
			{					
				if(rec_list.getHead().nth(j).item==i)
				{
					if(j==1)
					{						
						rec_list.getHead().nth(rec_list.size).next=new SListNode(i);	
						rec_list.shiftHead();
					}
					
					else if(j<rec_list.size)
					{						
						rec_list.getHead().nth(rec_list.size).next=new SListNode(i);	
						rec_list.getHead().nth(j-1).next=rec_list.getHead().nth(j+1);
					}						
				}
			}
		
		return rec_list;
	}
	
}
class SList
{
	private SListNode head;
	public int size;
	
	public SList()
	{
		head=null;
		size=0;
	}
	
	//insert a new item at the beginning of the list
	public void insertFront(int Item)
	{
		head=new SListNode(Item,head);		
		size++;
	}
	
	public void insertEnd(int Item)
	{
		if(size==0)
			head=new SListNode(Item);
		else
			head.nth(size).next=new SListNode(Item);
			
		size++;
	}
	
	public void deleteFront()
	{
		head=head.next;
		size--;
	}
	
	public void deleteEnd()
	{		
		head.nth(size-1).next=null;
		size--;
	}
	
	public SListNode findNext(SListNode node)
	{
		return node.next;
	}
	
	public SListNode getHead()
	{
		return head;
	}
	
	//to change head without changing the size of the list
	public void shiftHead()
	{
		head=head.next;
	}
	
}

class SListNode 
{
	public int item;
	public SListNode next;

	public SListNode(int item, SListNode next)
	{
		this.item= item;
		this.next=next;
	}
	
	public SListNode(int item)
	{
		this(item,null);
	}
	public static void main(String[] args)
	{
		//creating a single linked list with 6 nodes
		
		SListNode l1=new SListNode(1),
				  l2=new SListNode(2), 
				  l3=new SListNode(3);		
		
		l1.next=l2;
		l2.next=l3;	

		SListNode l4= new SListNode(7, new SListNode(0,new SListNode(6)));	
		
		l3.next=l4;
		
		for(int i=10;i<13;i++)
		{
			l2.insertAfter(i);
			l2=l2.next;
		}
		
		//printing all items of the list
		for(int pos=1;pos<10;pos++)
		{
			System.out.println(l1.nth(pos).item);
		}
	}	
	
	//insert an item after a particular item
	public void insertAfter(int item)
	{
		next=new SListNode(item,next);
	}

	//finding the nth item of a single linked list
	public SListNode nth(int position)
	{
		if(position==1)
			return this;
		
		else if((position<1)||(next==null))
			return null;
		
		else
			return next.nth(position-1);
	}
	
}	