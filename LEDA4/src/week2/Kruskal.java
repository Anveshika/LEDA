
import java.util.*;
import java.util.Scanner;

public class Kruskal {
	
	graph g;
	
	public Kruskal(Edge e[], Vertex v[]) {
		
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
		
		Kruskal obj = new Kruskal(e , vert);		
		
		obj.ImpKruskal(obj.g);
		
	}
	
	public void ImpKruskal(graph g)
	{
		int totalcost = 0;
		for(int i = 0; i< g.vrtx.length; i++)
			makeset(g.vrtx[i]);
		
		sort(g.edg);

		for(int i = 0; i< g.edg.length; i++)
		{
			Vertex root1 = new Vertex();
			Vertex root2 = new Vertex();
			
			root1 = find(g.edg[i].v1);
			root2 = find(g.edg[i].v2);
			
			if(!root1.item.equals(root2.item))
			{
				Union(root1,root2);
				totalcost+= g.edg[i].weight;
				System.out.println("("+g.edg[i].v1.item+","+g.edg[i].v2.item+")");
			}
		}
		
		System.out.println(totalcost);
		
	}
	
	public Vertex find(Vertex v)
	{
		if(v.parent == v)
			return v;
		else
			return find(v.parent);
	}
	
	public void Union(Vertex v1, Vertex v2)
	{
		if(v1.rank > v2.rank)
			v2.parent = v1;
		else if(v1.rank< v2.rank)
			v1.parent = v2;
		else
		{
			v1.parent = v2;
			v2.rank ++;
		}
	}
	
	public void makeset(Vertex v)
	{
		v.parent = new Vertex();
		v.parent = v;		
	}
	
	//implementing insertion sort
	public void sort( Edge e[])
	{
		int N = e.length;
        int i, j;
        Edge temp = new Edge(null,null,0);
        for (i = 1; i< N; i++) 
        {
            j = i;
            temp = e[i];    
            while (j > 0 && temp.weight < e[j-1].weight)
            {
                e[j] = e[j-1];
                j = j-1;
            }
            e[j] = temp;            
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
