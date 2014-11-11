

public class DListNode {

	Object item;
	DListNode next;
	DListNode prev;
	
	public DListNode(int item,DListNode prev,DListNode next)
	{
		this.item=item;
		this.next=next;
		this.prev=prev;
	}
	
	public DListNode(Object item)
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