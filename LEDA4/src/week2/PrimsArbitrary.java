package week2;

import java.util.ArrayList;
import java.util.Scanner;
public class PrimsArbitrary {
	
	graph g;
	
	public PrimsArbitrary(Edge e[], Vertex v[]) {
		
	g = new graph(e,v);

	}
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);				
		
		//first line input will be number of vertices
		int n = Integer.parseInt(scan.nextLine());
		
		//set of vertices separated by commas
		String set = scan.nextLine();
		String set1 = set.substring(1,set.length()-1);
		String [] set2 = set1.split(",");
		
		Vertex vert[] = new Vertex[set2.length];
		for(int i = 0;i< vert.length;i++)		
			vert[i] = new Vertex(set2[i]);			
		
		//taking array-list as the number of edges are unknown
		ArrayList<Edge> ed = new ArrayList<Edge>();
		for(int i = 0; i<n; i++)
		{
			String s = scan.nextLine();		
			String[] s1 = s.split(" ");
			
			for(int j = i; j< s1.length ; j++)
			{
				if(!s1[j].equals("0"))				
				{
					ed.add(new Edge(vert[i],vert[j],Integer.parseInt(s1[j])));		
					//System.out.println("("+vert[i].item+","+vert[j].item+","+s1[j]+")");
				}
			}
		}
		
		//converting array-list into array
		Edge e[] = new Edge[ed.size()];
		ed.toArray(e);
				
		PrimsArbitrary obj = new PrimsArbitrary(e , vert);		
				
		obj.Prims(obj.g);
		obj.printParent();
		
	}
	
	public void Prims(graph g)
	{
		int totalcost = 0;
		Vertex u = new Vertex();
		u = g.vrtx[0];
		u.parent = u;
		u.dist = 0;
		Queue Q = new Queue();
		Q.enqueue(g.vrtx[0]);

		while(!Q.isEmpty())
		{
			u = Q.deleteMin();	
			totalcost+= u.dist;
			
			Vertex v = new Vertex();
			for(int i = 0; i<g.edg.length;i++)
			{
				
				v = g.edg[i].v1;
				if(v.item.equals(u.item))
				{
					if(g.edg[i].weight<v.dist)
					{
						v.dist = g.edg[i].weight; 
						v.parent = u;
					}					
				}
				
				if((v.scanned == false)&&(!Q.contains(v)))
					Q.enqueue(v);
			}				
								
			u.scanned = true;
		}
		
		//printdeque(b);
		//System.out.println(a);
		//return a;
	}
	
	public void printParent()
	{
		for(int i = 0;i<g.vrtx.length-1; i++)
			System.out.print(g.vrtx[i].parent+",");
		
		System.out.println(g.vrtx[g.vrtx.length-1].parent);
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

class graph{
	
	Edge edg[];
	Vertex vrtx[];
	
	public graph(Edge e[], Vertex v[])
	{
		edg = new Edge[e.length];
		edg = e;
		vrtx = new Vertex[v.length];
		vrtx = v;
	}
}

class Edge{
	
	Vertex v1;
	Vertex v2;
	int weight;
	
	public Edge(Vertex u, Vertex v, int w)
	{
		v1 = new Vertex();
		v1 = u;
		v2 = new Vertex();
		v2 = v;	
		weight = w;		
	}
}

class Vertex{
	
	String item = "";
	Vertex parent;
	int rank;
	int dist;
	boolean scanned;
	
	public Vertex()
	{
		item = "";		
		rank = 0;
		dist = 9999;
		scanned = false;
	}
	
	public Vertex(String s)
	{
		item = item + s;
		//Vertex parent = new Vertex();
		rank = 0;
		dist = 9999;
		scanned = false;
	}
}


