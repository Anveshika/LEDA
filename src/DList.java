
public class DList
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
	
	public DListNode findNext(Object item)
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