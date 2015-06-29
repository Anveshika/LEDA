package week3;

import java.util.ArrayList;
import java.util.Scanner;

//program to implement depth first search
public class Graphs {
	
	Vertex[] graph;
	
	public Graphs(int n)
	{		
		graph = new Vertex[n];
	}
	
	
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);	
		Scanner in = new Scanner(System.in);
		int n = Integer.parseInt(scan.nextLine());
		
		Graphs obj = new Graphs(n);		
		
		int input = Integer.parseInt(scan.nextLine());
		
		for(int i = 0;i<n;i++)
		{
			obj.graph[i] = new Vertex();
			obj.graph[i].item = i+1;
			
		}	
		
		for(int i = 0; i<n; i++)
		{
			String s = scan.nextLine();
			if(input == 0)
				obj.insert0(s, i);
			
			else
				obj.insert1(s, i);
		}		
		
		obj.dfs(obj.graph[0]);
		
	}
	
	public void dfs(Vertex u)
	{
		u.visit();
		u.visited = true;
		
		for(int i = 0; i<u.children.size(); i++)
		{
			Vertex v = new Vertex();
			v = u.children.get(i);
			
			if(!v.visited)
				dfs(v);				
		}
		
	}
	
	//when input is an adjacency matrix
	public void insert1(String s, int count)
	{
		String[] s1 = s.split(" ");
		
		for(int i = 0;i<s1.length;i++)
		{
			if(Integer.parseInt(s1[i])!=0)
			{
				graph[count].children.add(graph[i]);			
								
			}			
		}
	}
	
	//when input is adjacency list
	public void insert0(String s, int count)
	{
		String[] s1 = s.split("->");
		
		for(int i = 0;i<s1.length;i++)
		{	
			int num = Integer.parseInt(s1[i]);			
			graph[count].children.add(graph[num-1]);								
						
		}		
		
	}

}

class Vertex{
	
	protected Vertex parent;
	protected int depth;
	protected int item;
	protected boolean visited;
	protected ArrayList<Vertex> children ;
	
	public Vertex()
	{
		parent = null;
		depth = 0;
		visited = false;
		children = new ArrayList<Vertex>();
	}
	
	public void visit(){
		System.out.print(this.item+" ");
	}
	
	public void visit(Vertex origin)
	{
		this.parent = origin;
		
		if(origin == null)
			this.depth = 0;
		else
			this.depth = origin.depth + 1;
	}
}

