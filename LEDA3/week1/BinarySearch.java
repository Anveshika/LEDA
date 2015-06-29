package week1;

import java.util.Scanner;

public class BinarySearch {
	
	BinaryTreeNode root = new BinaryTreeNode(0);		
	int size = 0;
	
	public static void main(String[] args) 
	{
		Scanner in = new Scanner(System.in);
		
		System.out.println("Enter a sequence of numbers");
		String input = in.nextLine();		
		System.out.println("hello "+input);
		in.close();
		
		String[] input1 = input.split(",");		
		System.out.println(input1.length);
		BinarySearch obj = new BinarySearch();
		
		for(int i=0;i<input1.length;i++)
        {
			System.out.println("hi "+i);
           if(input1[i].charAt(0)=='I')
               obj.insert(Integer.parseInt(input1[i].substring(1)));
           
//           else if(input1[i].charAt(0)=='S')
//               obj.locate(Integer.parseInt(input1[i].substring(1)),obj.root);
//           
//           else
//           {
//               obj.remove(Integer.parseInt(input1[i].substring(1)),obj);
//               obj.root.inorder();
//           }
        }
		


	}
	
	public void insert(int item)
	{
		BinaryTreeNode node = new BinaryTreeNode(item);	
		BinaryTreeNode temp = new BinaryTreeNode(0);
		System.out.println("size="+size);
		if(size == 0)
		{
			root = node;
			size++;
		}
		
		temp = root ;	
		while(temp!=null)
		{
			if(temp.item <= node.item)
			{
				if(temp.right==null)
				{
					node.parent = temp;
					temp.right = node;
					size++;
					return;
				}
				
				else
				temp = temp.right;
			}
			
			else
			{
				if(temp.left==null)
				{
					node.parent = temp;
					temp.left = node;
					size++;
					return;
				}
				
				else
					temp = temp.left;
			}
				
		}		
		
	}
	
	public BinaryTreeNode locate(int key, BinaryTreeNode temp1)
	{
		if(key == temp1.item)
			return temp1;
		
		else if(key < temp1.item)
		{
			if(temp1.left != null)
				return locate(key, temp1.left);
			
			else
				return temp1;
		}
		
		else
		{
			if(temp1.right != null)
				return locate(key, temp1.right);
			
			else
				return temp1;
		}
			
	}
	
	public void remove(int key, BinarySearch obj)
	{
		BinaryTreeNode node = new BinaryTreeNode(0);
		int max;
		
		node = obj.locate(key, root);
		
		if(node.item!= key)
		{
			System.out.println("element does not exist");
			return;
		}
		
		max = obj.findLeftMax(node);
		
		node.item = max;
		size--;
		
		
		
		
	}
	
	public int findLeftMax(BinaryTreeNode node)
	{		
		if(node.left!=null)
			return findLeftMax(node.left);
		else
		{
			int max = node.item;
			node.parent.left = null;			
			return max;		
		}
	}
	
	

}

class BinaryTreeNode
{
	int item;
	BinaryTreeNode parent;
	BinaryTreeNode left;
	BinaryTreeNode right;
	
	public BinaryTreeNode(int item,BinaryTreeNode left,BinaryTreeNode right)
	{
		this.item=item;
		this.left=left;
		this.right=right;
	}
	
	public BinaryTreeNode(int item)
	{
		this.item=item;
		this.left=null;
		this.right=null;
		this.parent=null;
	}	
	
	public void preorder()
	{
		this.visit();
		
		if(left!=null)
			left.preorder();
		
		if(right!=null)
			right.preorder();
	}
	
	public void postorder()
	{
		if(left!= null)
			left.postorder();		
		
		if(right!=null)
			right.postorder();
		
		this.visit();
	}
	
	public void inorder()
	{
		if(left!= null)
			left.postorder();
		
		this.visit();
		
		if(right!=null)
			right.postorder();
	}
	
	public void visit()
	{
		System.out.print(this.item+" ");
	}
	
	public void leftRotate(BinarySearch obj)
	{
		BinaryTreeNode dummy = new BinaryTreeNode(0);
		if(parent == null)
		{
			dummy = right;
			right = dummy.left;
			dummy.left = this;
			parent = dummy;
			obj.root = dummy;
		}
		
		else
		{
			dummy = right;
			right = dummy.left;
			dummy.left = this;
			dummy.parent = parent;
			parent = dummy;
			
		}
	}
	
	public void rightRotate(BinarySearch obj)
	{
		BinaryTreeNode dummy = new BinaryTreeNode(0);
		if(parent == null)
		{
			dummy = left;
			left = dummy.right;
			dummy.right = this;
			parent = dummy;
			obj.root = dummy;
		}
		
		else
		{
			dummy = left;
			left = dummy.right;
			dummy.right = this;
			dummy.parent = parent;
			parent = dummy;
			
		}
	}
	
	
	
}

