package week1;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

//program to implement Bellman Ford's algorithm for finding the shortest path
public class AllPairShortestPath {
	
	Vertex1[] graph;
	
	public AllPairShortestPath(int n)
	{		
		graph = new Vertex1[n];
	}
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);	
		Scanner in = new Scanner(System.in);		
	
		//first line input will be number of vertices
		int n = Integer.parseInt(scan.nextLine());
		
		//creating space for dummy node also(n+1)
		AllPairShortestPath obj = new AllPairShortestPath(n+1);		
		
		//set of vertices separated by commas with dummy d
		String set = scan.nextLine()+",ds";		
		
		String [] set2 = set.split(",");
		
		//populating the graph with vertices
		for(int i = 0;i<n+1;i++)
		{
			obj.graph[i] = new Vertex1();
			obj.graph[i].item = set2[i];			
		}
		
		
		//finding the children and weight of each edge 
		//by checking the adjacency matrix
		for(int i = 0; i<n; i++)
		{
			String s = scan.nextLine()+",9999";				
			obj.insert(s, i);
		}
		
		//adding the last row with all elements "0"
		String s = "";
		for(int i =0; i<n; i++)
			s = s+"0,";
		s= s+ 9999;		
		obj.insert(s, n);
		
		//performing bellman on the given graph vertices
		//with dummy as the source
		obj.Bellman(obj.graph[n],n,obj);
			
		//calculating the potential and reduced cost for every edge
		obj.reduceCost();
			
		//performing Dijkstras algorithm for finding reduced shortest path 
		//from every node to every other node
		for(int i = 0; i<n; i++)
		{
			obj.reset();
			obj.Dijkstra(obj.graph[i],i);
			obj.storeRed_shortestDistance(obj.graph[i]);
			
		}
		
		//translating distance back to original cost function
		for(int i = 0; i<n; i++)		
			obj.reduced_to_original(obj.graph[i]);	
		
		obj.display();
		
	}
	
	//considering input to be an adjacency matrix
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
	
	public void Bellman(Vertex1 s, int num_vert, AllPairShortestPath obj)
	{			
		//running the for loop |V| times and relaxing the edges
		//in every iteration
		for(int n=1; n<num_vert-1; n++)		
		{			
			relax(s);	
			for(int i = 0; i<num_vert; i++)
			{		
				graph[i].scanned = false;
				
			}		
		}
		
		//post-processing
		
		Vertex1 u = new Vertex1();
		s.parent =s;
		s.dist = 0;
		Queue1 Q = new Queue1();

		Q.enQueue(s);			
		
		while(!Q.isEmpty())
		{
			u = Q.deleteMin();
	
			for(int i = 0;i<u.children.size();i++)
			{
				Vertex1 v = new Vertex1();
				v = u.children.get(i);
				
				if((u.dist+u.weight.get(i))<v.dist)				
					infect(v);										
				
				if((v.scanned == false)&&(!Q.contains(v)))
					Q.enQueue(v);				
			}
			
			u.scanned = true;
		}	
		
	}
	
	public void relax(Vertex1 s)
	{
		Vertex1 u = new Vertex1();
		s.parent =s;
		s.dist = 0;
		Queue1 Q = new Queue1();

		Q.enQueue(s);			
		
		while(!Q.isEmpty())
		{
			u = Q.deleteMin();
	
			for(int i = 0;i<u.children.size();i++)
			{
				Vertex1 v = new Vertex1();
				v = u.children.get(i);
				
				if((u.dist+u.weight.get(i))<v.dist)
				{
					v.dist = u.dist+u.weight.get(i);
					v.parent = u;
				}							
				
				if((v.scanned == false)&&(!Q.contains(v)))
					Q.enQueue(v);				
			}
			
			u.scanned = true;
		}
	}
	
	public void infect(Vertex1 v)
	{
		v.dist = -9999;
		for(int i = 0;i<v.children.size();i++)
		{
			Vertex1 u = v.children.get(i);
			infect(u);
		}
	}
	
	public void reduceCost()
	{		
		//assigning the potential to every node
		for(int i = 0; i<graph.length-1;i++)		
		{
			graph[i].potential = graph[i].dist;
			reset();
		}
		
		//finding the reduced cost
		Vertex1 u = new Vertex1();		
		u = graph[0];	
		Queue1 Q = new Queue1();

		Q.enQueue(u);	
		
		while(!Q.isEmpty())
		{
			u = Q.deQueue();
			
			for(int i = 0;i<u.children.size();i++)
			{
				Vertex1 v = new Vertex1();
				v = u.children.get(i);				
				
				u.red_weight.add(u.potential-v.potential+u.weight.get(i));
			
				if((v.scanned == false)&&(!Q.contains(v)))
					Q.enQueue(v);				
			}
			
			u.scanned = true;
		}
		
	}
		
	public void Dijkstra(Vertex1 s,int count)
	{
		//initially setting the variable 'scanned' of each vertex to false		
		
		Vertex1 u = new Vertex1();
		s.parent =s;
		s.dist = 0;
		Queue1 Q = new Queue1();

		Q.enQueue(s);	
		
		while(!Q.isEmpty())
		{
			u = Q.deleteMin();
	
			for(int i = 0;i<u.children.size();i++)
			{
				Vertex1 v = new Vertex1();
				v = u.children.get(i);
				
				//finding reduced distances on the basis of reduced cost
				if((u.dist+u.red_weight.get(i))<v.dist)
				{
					v.dist = u.dist+u.red_weight.get(i);
					v.parent = u;
				}
							
			
				if((v.scanned == false)&&(!Q.contains(v)))
					Q.enQueue(v);				
			}
			
			u.scanned = true;
		}		
		
	}
	
	//function storing the reduced shortest distance 
	//for every vertex in an array list
	public void storeRed_shortestDistance(Vertex1 s)
	{
		for(int i=0; i<graph.length-1;i++)
		{			
			s.red_shortest_dist.add(graph[i].dist);
		}
	}
	
	public void reset()
	{
		for(int i = 0; i<graph.length-1;i++)			
		{
			graph[i].scanned = false;
			graph[i].dist = 9999;
			graph[i].parent = null;
		}
	}
	
	
	public void reduced_to_original(Vertex1 s)
	{		
		for(int i=0; i<graph.length-1; i++)		
		{			
			for(int j=0; j<graph[i].red_shortest_dist.size(); j++)
			{
				int org_dist = graph[i].red_shortest_dist.get(j)+ graph[j].potential - graph[i].potential;
				graph[i].original_shortest_dist.add(org_dist);				
				
			}
		}	
	}
	
	public void display()
	{
		for(int i=0; i<graph.length-1; i++)		
		{
			int j = 0;
			for(j=0; j<graph[i].red_shortest_dist.size()-1; j++)			
				System.out.print(graph[i].original_shortest_dist.get(j)+",");		
			System.out.print(graph[i].original_shortest_dist.get(j));	
			System.out.println();
			
		}
	}
	
}

class Vertex1{
	
	Vertex1 parent ;
	boolean scanned;
	int dist;
	String item;
	ArrayList<Vertex1> children;
	ArrayList<Integer> weight;
	ArrayList<Integer> red_weight;
	int red_dist;
	int potential;
	ArrayList<Integer> red_shortest_dist;
	ArrayList<Integer> original_shortest_dist;
	
	public Vertex1()
	{
		
		parent = null;
		scanned = false;
		dist = 9999;
		children = new ArrayList<Vertex1>();
		weight = new ArrayList<Integer>();	
		red_weight = new ArrayList<Integer>();
		item ="";
		red_dist = 0;
		potential = 0;
		red_shortest_dist = new ArrayList<Integer>();
		original_shortest_dist = new ArrayList<Integer>();
	}
	public Vertex1(String item)
	{
		parent = null;
		scanned = false;
		dist = 9999;
		children = new ArrayList<Vertex1>();
		weight = new ArrayList<Integer>();
		red_weight = new ArrayList<Integer>();
		this.item =item;
		red_dist = 0;
		potential = 0;
		red_shortest_dist = new ArrayList<Integer>();
		original_shortest_dist = new ArrayList<Integer>();
	}
	
	
}

class Queue1 
{
	int size;
	Vertex1 arr[];
	int initcap;
	public Queue1()
	{
		initcap=10;
		arr=new Vertex1[initcap];
		size=0;
	}
	public void enQueue(Vertex1 element) {
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
		Vertex1[] obj=new Vertex1[2*initcap];
		for(int i=0;i<size;i++)
			obj[i]=arr[i];
		arr=obj;
	}

	public Vertex1 deQueue() {
		// TODO Auto-generated method stub
		Vertex1 u = new Vertex1();
		u = arr[0];		
		for(int i=1;i<size;i++)
			arr[i-1]=arr[i];
		
		size--;
		return u;
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
	
	public boolean contains(Vertex1 arg) {
		// TODO Auto-generated method stub
		for(int i=0;i<size;i++)
		{
			if(arr[i].item.equals(arg.item))
				return true;
		}
		return false;
	}
	
	public Vertex1 deleteMin() {
		
		Vertex1 v = new Vertex1();
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
	
	public void decreaseKey(Vertex1 v)
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
