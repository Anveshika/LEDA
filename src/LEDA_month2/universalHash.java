//class to implement universal hashing
public class universalHash<K,V>{

	Node<K,V> head[];
	Node<K,V>[] temp;
	int size=0;
	
	//random vector a to be filled with random numbers	
	int[] a=new int[20];
	
	public universalHash()
	{
		//initializing the head array 
		head=new Node[257];
		for(int i=0;i<head.length;i++)
			head[i]=new Node();
		
		//initializing the random vector
		for(int i=0;i<20;i++)		
			a[i]=(int)(Math.random()*head.length);
	}
	
	public int hash(K k, int m)
	{	
		//dividing the string key into pieces of 1 char each
		//assuming one char equal to 8 bits 
		int w=8;
		char[] arr=((String)k).toCharArray();
		int[] x=new int[arr.length];
		
		for(int i=0;i<((String)k).length();i++)
			x[i]=((int)(arr[i]))%((int)(Math.pow(2, w)-1));			
		
		//implementing the scalar product
		int sum=0;
		for(int i=0;i<x.length;i++)
			sum+=(a[i]*x[i]);
		
		sum=sum%m;
		
		return sum;

	}
	public void put(K k,V v)
	{
		Node<K,V> n=new Node(k,v);
	   int i=hash(k,head.length);
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
		int i=hash(k,head.length);
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
		int x=hash(k,head.length);
		System.out.println("x="+x);
		Node<K,V> temp=head[x].next;
		System.out.println("temp="+temp.key);
		
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
				System.out.println("temp="+temp.key);
				System.out.println("i="+i);
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
		universalHash<String,String> uh=new universalHash<String,String>();
		uh.put("ahgiorw", "hoeisrjh");
		uh.put("airj", "hiawr");
		uh.put("witpipoi", "awhoiur");
		
		uh.display();
		System.out.println("using get: (airj) "+uh.get("airj"));
	}
	
	
}	


class Node1<K,V>
{
	Object key,value;	
	Node<K,V> next;
	
	public Node1()
	{
		this.value=0;
		this.key=0;
		this.next=null;
	}
	
	public Node1(Object key,Object value)
	{
		this.value=value;
		this.key=key;
		this.next=null;
	}
}
