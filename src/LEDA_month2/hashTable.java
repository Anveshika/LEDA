import java.util.*;

class hashTable<K,V>
{
	Node<K,V> head[];
	Node<K,V>[] temp;
	int size=0;
	public hashTable()
	{
		head=new Node[10];
		for(int i=0;i<head.length;i++)
			head[i]=new Node();
	}
	
	public int hash(K k)
	{		
		return (int)k%(head.length);
		
	}
	public void put(K k,V v)
	{
		Node<K,V> n=new Node(k,v);
	   int i=hash(k);
	   if(size==0)
	   {
		   head[i].next=n;
		   size++;
	   }
	   else
	   {
		   n.next=head[i].next;
		  head[i].next=n; 
		  size++;
	   }
		
	}
	public V remove(K k)
	{
		int i=hash(k);
		Node<K,V> temp=head[i];
		while(temp.next!=null)
		{
			if(temp.next.key==k)
			{
				temp.next=temp.next.next;
				size--;
				return (V)temp.value;
			}
			else
			{
				temp=temp.next;
				size--;
			}
		}
		return null;
	}
	
	public V get(K k)
	{
		int x=hash(k);
		Node<K,V> temp=head[x].next;
		while(temp!=null)
		{
			if(temp.key==k)
				return (V)temp.value;
			else
				temp=temp.next;
		}
		return null;
	}
	
	public void display()
	{
		for(int i=0;i<head.length;i++)
		{

			if(head[i].next!=null)
			{				
				Node<K,V> temp=head[i].next;
				while(temp!=null)
				{
					System.out.println(temp.key+","+temp.value);
					temp=temp.next;
				}
			}
		}
	}

	
	
	public static void main(String[] args)
	{
		
		hashTable<Integer,String> ht=new hashTable<Integer,String>();
		
		Scanner scan=new Scanner(System.in); 
		
		System.out.println("Enter the key and value");
		
		String input=scan.next();
		while(!input.equals("end"))
		{
			
			String input1[]=input.split(",");			
			
			ht.put(Integer.parseInt(input1[0]),input1[1]);
			input=scan.next();
		}	
		
		ht.display();
		
		System.out.println("Enter a key to get the value");
		int key= scan.nextInt();
		
		System.out.println(ht.get(key));
		
		System.out.println("Enter a key to remove the value");
		key= scan.nextInt();
		
		System.out.println("removed value is"+ht.remove(key));
		
		ht.display();
	}
	
}	


class Node<K,V>
{
	Object key,value;	
	Node<K,V> next;
	
	public Node()
	{
		this.value=0;
		this.key=0;
		this.next=null;
	}
	
	public Node(Object key,Object value)
	{
		this.value=value;
		this.key=key;
		this.next=null;
	}
}


