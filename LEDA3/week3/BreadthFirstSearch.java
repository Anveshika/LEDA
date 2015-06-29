package week3;
import java.util.ArrayList;
import java.util.Scanner;
//program to implement breadth first search
public class BreadthFirstSearch {
	
	public VertexBfs[] graph1;
	
	public BreadthFirstSearch(int n)
	{		
		graph1 = new VertexBfs[n];
	}
	
	
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);	
		Scanner in = new Scanner(System.in);
		int n = Integer.parseInt(scan.nextLine());
		
		BreadthFirstSearch obj = new BreadthFirstSearch(n);		
		
		int input = Integer.parseInt(scan.nextLine());
		
		for(int i = 0;i<n;i++)
		{
			obj.graph1[i] = new VertexBfs();
			obj.graph1[i].item = i+1;
			
		}		
		
		for(int i = 0; i<n; i++)
		{
			String s = scan.nextLine();
			if(input == 0)				
				obj.insert0(s, i);
			
			
			else
				obj.insert1(s, i);
		}
		
		
		
		obj.bfs(obj.graph1[0]);
		
	}
	
	public void bfs(VertexBfs u)
	{
		u.visit(null);
		u.visited = true;
		
		Queue Q = new Queue();
		
		Q.enqueue(u);
		
		while(!Q.isEmpty())
		{
			VertexBfs v = new VertexBfs();
			v = Q.dequeue();
			System.out.print(v.item+" ");
			for(int i = 0; i<v.children.size(); i++)
			{
				VertexBfs w = new VertexBfs();
				w = v.children.get(i);
				
				if(!w.visited)
				{
					w.visit(v);
					
					w.visited = true;
					Q.enqueue(w);
				}							
			}
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
				graph1[count].children.add(graph1[i]);			
								
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
//			System.out.println("num= "+ num);
//			System.out.println("count= "+count);
			
//			if(graph1[count]==null)
//				System.out.println("null at "+ count);
			graph1[count].children.add(graph1[num-1]);								
						
		}		
		
	}

}

class VertexBfs{
	
	protected VertexBfs parent;
	protected int depth;
	protected int item;
	protected boolean visited;
	protected ArrayList<VertexBfs> children ;
	
	public VertexBfs()
	{
		parent = null;
		depth = 0;
		visited = false;
		children = new ArrayList<VertexBfs>();
	}
	
	
	
	public void visit(VertexBfs origin)
	{
		this.parent = origin;
		
		if(origin == null)
			this.depth = 0;
		else
			this.depth = origin.depth + 1;
	}
}

class Queue 
{
	int size;
	VertexBfs arr[];
	int initcap;
	public Queue()
	{
		initcap=10;
		arr=new VertexBfs[initcap];
		size=0;
	}
	public void enqueue(VertexBfs element) {
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
		VertexBfs[] obj=new VertexBfs[2*initcap];
		for(int i=0;i<size;i++)
			obj[i]=arr[i];
		arr=obj;
	}

	public VertexBfs dequeue() {
		// TODO Auto-generated method stub
		VertexBfs v = new VertexBfs();
		v = arr[0];
		
		for(int i=1;i<size;i++)
			arr[i-1]=arr[i];
		size--;
		return v ;
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
	
	public boolean contains(VertexBfs arg) {
		// TODO Auto-generated method stub
		for(int i=0;i<size;i++)
		{
			if(arr[i].item==arg.item)
				return true;
		}
		return false;
	}
	
}
