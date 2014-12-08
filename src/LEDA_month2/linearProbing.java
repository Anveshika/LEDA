
public class linearProbing<K,V>
{
	Entry<K,V> table[];
	Entry<K,V>[] temp;
	int size=0;
	public linearProbing()
	{
		table=new Entry[5];
		for(int i=0;i<table.length;i++)
			{
				table[i]=new Entry();				
			}
	}

	public static void main(String[] args)
	{
		linearProbing<Integer, Integer> ht=new linearProbing<Integer,Integer>();
		ht.put(1, 16);
		ht.put(2, 10);
		ht.put(3, 48);
		ht.put(5, 48);
		ht.put(6, 48);
		//ht.put(58, 48);
		//ht.put(43, 41);
		
		ht.display();		
		
		System.out.println("The value removed is "+ht.remove(2));
		System.out.println("hash table after removing");
		ht.display();
		
		System.out.println("ht.get(5)="+ht.get(5));

	}
	
	public int hash(K k)
	{
		return (int)k%table.length;
	}
	
	public void put(K k, V v)
	{	
		int i;
		
		//traversing the index from i to the length of table
		for(i=hash(k);i<table.length;i++)
		{
			if(table[i].key==null)
			{
				table[i]=new Entry(k,v);
				return;
			}
		}
		
		//going to start of the array to find 
		for(int j=0;j<hash(k)-1;j++)
		{
			if(table[j].key==null)
			{
				table[j].key=k;
				table[j].value=v;
				return;
			}
		}		
		
	}
	
	public void display()
	{
		for(int i=0;i<table.length;i++)
		{
			if(table[i].key!=null)
				System.out.println(table[i].key+","+table[i].value);
		}
	}
	
	public V remove(K k)
	{
		int i=hash(k);
		int count=0;
		V val=null;
		while(table[i]!=null)
		{
			if(count==2)
				break;
			if(table[i].key==k)
			{
				val= (V)table[i].value;
				table[i] = null;	
				break;
			}	
			
			i++;
			
			if(i==table.length)
			{
				i=0;
				count++;				
			}
		}		
	
		if(val==null)
			return val;
		
		for(int j=i+1;j<table.length;j++)
		{
			if(table[j].key!=null)
			{
				if(hash((K)table[j].key)<=i)
				{
				table[i]=new Entry(table[j].key,table[j].value);
				table[j].key=null;
				table[j].value=null;
				j=i;
				}
			}
			
			else
				break;
		}
			
		return val;
	}
	
	public V get(K k)
	{
		
		int i=hash(k);
		int count=0;
		while(table[i]!=null)
		{
			if(count==2)
				break;
			
			if(table[i].key==k)
				return (V)table[i].value;
			
			i++;
			
			if(i==table.length)
			{
				i=0;
				count++;				
			}
		}
		
		return null;

	}

}

class Entry<K,V>
{
	Object key;
	Object value;
	
	public Entry(K k, V v)
	{
		key=k;
		value=v;
	}
	
	public Entry()
	{
		this.key=null;
		this.value=null;
	}
}



