package week1;


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

//program to implement Dijkstra's algorithm for finding the shortest path
public class GoalDirectedSearch  {
	
	Vertex2[] graph;

	public GoalDirectedSearch (int n)
	{		
		graph = new Vertex2[n];
	}
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);	
		Scanner in = new Scanner(System.in);		
	
		//first line of input contains number of vertices
		int n = Integer.parseInt(scan.nextLine());
		
		GoalDirectedSearch  obj = new GoalDirectedSearch (n);	
		
		//second line contains the set of vertices
		String set = scan.nextLine();
		
		String set1 = set.substring(1,set.length()-1);
		String [] set2 = set1.split(",");
		
		for(int i = 0;i<n;i++)
		{
			obj.graph[i] = new Vertex2();
			obj.graph[i].item = set2[i];
		}	
		
		//third line of input contains the source vertex
		String source = scan.nextLine();
		
		//fourth line contains the target vertex
		String target = scan.nextLine();	
		
		//fifth line onwards contains adjacency matrix
		
		for(int i = 0; i<n; i++)
		{
			String s = scan.nextLine();	
			if(!s.isEmpty())
				obj.insert(s, i);
		}
		
		
		
		//calling Dijkstra's algorithm for goal directed search
		
		int s=0,t =0;
		for(int i = 0; i<n; i++)
		{
			if(obj.graph[i].item.equals(source))
				s=i;
			
			if(obj.graph[i].item.equals(target))
				t=i;
		}	
		
		obj.Dijkstra(obj.graph[s],obj.graph[t]);
		obj.display(obj.graph[s], obj.graph[t]);
	
		
	}
	
	public void insert(String s, int count)
	{
		String[] s1 = s.split(" ");
		
		for(int i = 0;i<s1.length;i++)
		{
			/*if(s1[i].contains("-"))
			{
				System.out.println("Not possible.");
				System.exit(0);
			}*/
			if(Integer.parseInt(s1[i])!=0)
			{
				graph[count].children.add(graph[i]);				
				graph[count].weight.add(Integer.parseInt(s1[i]));				
			}			
		}
		
	}
	
	
	public void Dijkstra(Vertex2 s, Vertex2 t)
	{		
		Vertex2 u = new Vertex2();
		s.parent =s;
		s.dist = 0;
		
		Queue2 Q = new Queue2();

		Q.enqueue(s);	
		
		while(!Q.isEmpty())
		{
			u = Q.deleteMin();
			
			//terminating the search as soon as target is found
			if(u.item == t.item)
				break;
	 
			for(int i = 0;i<u.children.size();i++)
			{
				Vertex2 v = new Vertex2();
				v = u.children.get(i);
				
				if((u.dist+u.weight.get(i))<v.dist)
				{
					v.dist = u.dist+u.weight.get(i);
					v.parent = u;
				}
							
			
				if((v.scanned == false)&&(!Q.contains(v)))
					Q.enqueue(v);				
			}
			
			u.scanned = true;
		}
		
	}
	
	public void display(Vertex2 Source, Vertex2 target)
	{
		Vertex2 u = new Vertex2();
		int t =0;
		for(int i = 0;i<graph.length;i++)
		{
			if(graph[i].item == target.item)
			t = i;	
		}
		
	   if((graph[t].dist == 9999)||(graph[t].dist<=0))
	   {
		System.out.println("Not possible.");
			System.exit(0);
		}
		
		String s = "";
		
		u = graph[t];
        
        if(u.parent == null)
        {
            System.out.println("Not possible.");
			System.exit(0);
        }   
		s="->"+u.item;
		if(u.parent!= null)
		{				
			while(!u.parent.item.equals(Source.item))
			{				
				s= "->"+u.parent.item+s;
				u = u.parent;
			}
		}	
		s= Source.item + s;			
		
		System.out.println(s+ ":"+graph[t].dist);
	}
	
	
}

class Vertex2{
	
	Vertex2 parent ;
	boolean scanned;
	int dist;
	String item;
	ArrayList<Vertex2> children;
	ArrayList<Integer> weight;
	
	public Vertex2()
	{
		
		parent = null;
		scanned = false;
		dist = 9999;
		children = new ArrayList<Vertex2>();
		weight = new ArrayList<Integer>();	
		item ="";
	}
	public Vertex2(String item)
	{
		parent = null;
		scanned = false;
		dist = 9999;
		children = new ArrayList<Vertex2>();
		weight = new ArrayList<Integer>();	
		this.item =item;
	}
	
	
}

class Queue2 
{
	int size;
	Vertex2 arr[];
	int initcap;
	public Queue2()
	{
		initcap=10;
		arr=new Vertex2[initcap];
		size=0;
	}
	public void enqueue(Vertex2 element) {
		// TODO Auto-generated method stub
		if(size==arr.length)
		{
			changesize();
		}
		arr[size]=element;
		size++;
	
	}
	
	private void changesize() {
		// TODO Auto-generated method stub
		Vertex2[] obj=new Vertex2[2*initcap];
		for(int i=0;i<size;i++)
			obj[i]=arr[i];
		arr=obj;
	}

	public Vertex2 dequeue() {
		// TODO Auto-generated method stub
		for(int i=1;i<size;i++)
			arr[i-1]=arr[i];
		size--;
		return null;
	}	

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if(size==0)		
			return true;
		
		else
			return false;
	}


	public int size() {
		// TODO Auto-generated method stub
		return size;
	}	
	
	public boolean contains(Vertex2 arg) {
		// TODO Auto-generated method stub
		for(int i=0;i<size;i++)
		{
			if(arr[i].item.equals(arg.item))
				return true;
		}
		return false;
	}
	
	public Vertex2 deleteMin() {
		
		Vertex2 v = new Vertex2();
		int min = 0; 
		for(int i =0;i<size;i++)
		{
			if(arr[min].dist>=arr[i].dist)
				min = i;
		}
		
		v = arr[min];
		
		for(int i = min;i<size-1; i++)
		{
			arr[i] = arr[i+1];
		}
		size--;
		
		return v;
			
	}
	
	public void decreaseKey(Vertex2 v)
	{
		for(int i =0;i<size;i++)
		{
			if(arr[i].item.equals(v.item))
			{
				arr[i].dist = v.dist;
			}
		}
	}
	
}
