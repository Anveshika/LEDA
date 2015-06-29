package week4;
import java.util.*;

public class Acyclic {
	public static void main(String[] args) {
		Scanner in=new Scanner(System.in);
		String vert=in.nextLine();
		String itype=in.nextLine();
		String root=in.nextLine();
		String val=in.nextLine();
		String[] vertexs=val.split(",");
		ArrayList<String> s=new ArrayList<String>();
		gnode[] a=new gnode[Integer.parseInt(vert)];
		for(int i=0;i<Integer.parseInt(vert);i++)
			s.add(in.nextLine());
		if(itype.equals("1")) {
			a=matrixlist(s,Integer.parseInt(vert),vertexs);
		}else {
			a=makelist(s,Integer.parseInt(vert),vertexs);
		}
		dfss d=new dfss();
		d.dfs(a,root);
			if(d.checkcycle(a))
				System.out.println("Graph is cyclic");
			else
				System.out.println("Acyclic");
	}
	
	public static gnode[] makelist(ArrayList<String> s,int n, String[] vertexs) {
		gnode[] a=new gnode[n];
		for(int i=0;i<a.length;i++)
			a[i]=new gnode();
		for(int i=0;i<n;i++) {
			String sa[]=s.get(i).split("->");
			a[i].val=sa[0];
		}
		for(int i=0;i<n;i++) {
			String sa[]=s.get(i).split("->");
			for(int j=1;j<sa.length;j++) {
				int p=getindex(a,sa[j]);
				a[i].child.add(a[p]);
				//a[p].p=a[i];
			}
		}
		return a;
	}

	public static int getindex(gnode[] a,String s) {
		
		for(int i=0;i<a.length;i++) {
			//System.out.println(s+"      "+a[i].val);
			if(a[i].val.equals(s))
				return i;
		}
			return -1;
	}
	
	
	public static gnode[] matrixlist(ArrayList<String> s,int n, String[] vertexs) {
		//System.out.println("in matrix");
		gnode[] a=new gnode[n];
		for(int i=0;i<n;i++)  {
			a[i]=new gnode();
			a[i].val=vertexs[i];
		}
		//System.out.println(s.size()+"hdfgbugdfhg");
		for(int i=0;i<s.size();i++) {
			String s11=s.get(i);
			//System.out.println(s11);
            String[] s12=s11.split(",");
            //System.out.println(s12.length+" lengthh");
			for(int j=0;j<s12.length;j++) {
				String s2=s12[j];
				if(s2.equals("1") ){
			//		System.out.println("i am in");
					String x=vertexs[j];
					int index=getindex(a,x);
					a[i].child.add(a[index]);
				}
			}
		}	
		/*for (int i = 0; i < a.length; i++) {
			//System.out.print(a[i].val+" >>");
			for (int j = 0; j < a[i].child.size(); j++) {
				//System.out.print(a[i].child.get(j)+" ");
			}
			//System.out.println();
		}*/
		return a;
	}
}
class gnode {
	boolean mark=false;
	String val;
	gnode p=null;
	int d=-1;
	int dnum=-1,ftime=-1;
	ArrayList<gnode> child=new ArrayList<gnode>();
	public gnode() {
		this.val="";
		this.p=null;
	}
}

class dfss{
	int dnum=0,fin=0;
	public boolean dfs(gnode[] a,String s) {
		int p=getindex(a,s);
		a[p].mark=true;
		this.dnum++;
		a[p].dnum=this.dnum;
		//System.out.println(dnum+" increased");
		return dfs1(a[p]);
	}
	
	public  boolean dfs1(gnode a) {
		//System.out.println(a.val);
		//System.out.println("in the "+ this.dnum);
		ArrayList<gnode> c=new ArrayList<gnode>();
		c=a.child;
		//System.out.println(c.size());
		for(int i=0;i<c.size();i++) {
			//System.out.println("in loop");
			gnode b=c.get(i);
			if(!b.mark) {
				b.mark=true;
				this.dnum++;
				b.dnum=this.dnum;
				dfs1(b);
			}
		}
		//System.out.println(this.fin+" finish time ");
		this.fin++;
		a.ftime=this.fin;
		for(int i=0;i<c.size();i++) {
			gnode b=c.get(i);
			if((a.ftime<=b.ftime)&&(a.dnum>=b.dnum))
				return true;
		}		
		return false;
	}
	
	public boolean checkcycle(gnode[] a) {
		for(int i=0;i<a.length;i++) {
			int n1=a[i].child.size();
			for(int j=0;j<n1;j++)  {
				gnode b=a[i].child.get(j);
				if((a[i].ftime<=b.ftime)&&(a[i].dnum>=b.dnum))
					return true;
			}
		}
		return false;
	}
	
	public int getindex(gnode[] a,String s) {
		
		for(int i=0;i<a.length;i++) {
			if(a[i].val.equals(s))
				return i;
		}
			return -1;
	}
}