public class SList
{
	private SListNode head;
	private int size;
	
	public SList()
	{
		head=null;
		size=0;
	}
	
	//insert a new item at the beginning of the list
	public void insertFront(Object Item)
	{
		head=new SListNode(Item,head);		
		size++;
	}
	
	public void insertEnd(Object Item)
	{
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
	
}


	

