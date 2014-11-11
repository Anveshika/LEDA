public class SListNode 
{
	public Object item;
	public SListNode next;

	public SListNode(Object item, SListNode next)
	{
		this.item= item;
		this.next=next;
	}
	
	public SListNode(Object item)
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
	public void insertAfter(Object item)
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