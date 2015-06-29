package week4;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

//program to implement Dijkstra's algorithm for finding the shortest path
public class Graphs {
	
	Vertex[] graph;
	
	public Graphs(int n)
	{		
		graph = new Vertex[n];
	}
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);	
		Scanner in = new Scanner(System.in);		
	
		int n = Integer.parseInt(scan.nextLine());
		
		Graphs obj = new Graphs(n);		
		
		String source = scan.nextLine();
		
		String set = scan.nextLine();
		
		String set1 = set.substring(1,set.length()-1);
		String [] set2 = set1.split(",");
		
		for(int i = 0;i<n;i++)
		{
			obj.graph[i] = new Vertex();
			obj.graph[i].item = set2[i];
		}		
		
		for(int i = 0; i<n; i++)
		{
			String s = scan.nextLine();		
			obj.insert(s, i);
		}
		
		for(int i = 0; i<n; i++)
		{
			if(obj.graph[i].item.equals(source))
			{
				obj.Dijkstra(obj.graph[i]);
				obj.display(obj.graph[i]);
				break;
			}
		}		
		
	}
	
	public void insert(String s, int count)
	{
		String[] s1 = s.split(",");
		
		for(int i = 0;i<s1.length;i++)
		{
			if(Integer.parseInt(s1[i])!=0)
			{
				graph[count].children.add(graph[i]);				
				graph[count].weight.add(Integer.parseInt(s1[i]));				
			}			
		}
		
	}
	
	public void Dijkstra(Vertex s)
	{		
		Vertex u = new Vertex();
		s.parent =s;
		s.dist = 0;
		Queue Q = new Queue();

		Q.enqueue(s);	
		
		while(!Q.isEmpty())
		{
			u = Q.deleteMin();
	
			for(int i = 0;i<u.children.size();i++)
			{
				Vertex v = new Vertex();
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
	
	public void display(Vertex Source)
	{
		Vertex u = new Vertex();
		
		for(int i = 0;i<graph.length;i++)
		{
			String s = "";
			
			u = graph[i];
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
			
			System.out.println(s+ ":"+graph[i].dist);
		}
	}
	
	
}

class Vertex{
	
	Vertex parent ;
	boolean scanned;
	int dist;
	String item;
	ArrayList<Vertex> children;
	ArrayList<Integer> weight;
	
	public Vertex()
	{
		
		parent = null;
		scanned = false;
		dist = 9999;
		children = new ArrayList<Vertex>();
		weight = new ArrayList<Integer>();	
		item ="";
	}
	public Vertex(String item)
	{
		parent = null;
		scanned = false;
		dist = 9999;
		children = new ArrayList<Vertex>();
		weight = new ArrayList<Integer>();	
		this.item =item;
	}
	
	
}

class Queue 
{
	int size;
	Vertex arr[];
	int initcap;
	public Queue()
	{
		initcap=10;
		arr=new Vertex[initcap];
		size=0;
	}
	public void enqueue(Vertex element) {
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
		Vertex[] obj=new Vertex[2*initcap];
		for(int i=0;i<size;i++)
			obj[i]=arr[i];
		arr=obj;
	}

	public Vertex dequeue() {
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
	
	public boolean contains(Vertex arg) {
		// TODO Auto-generated method stub
		for(int i=0;i<size;i++)
		{
			if(arr[i].item.equals(arg.item))
				return true;
		}
		return false;
	}
	
	public Vertex deleteMin() {
		
		Vertex v = new Vertex();
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
	
	public void decreaseKey(Vertex v)
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