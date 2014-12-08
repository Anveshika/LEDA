import java.util.Scanner;
public class paranthesisMatching {
	String[] stack;
	int size=0;
	
	public paranthesisMatching()
	{
		stack= new String[20];
		
	}

	public static void main(String[] args) {
		
		Scanner scan= new Scanner(System.in);
		String input = scan.nextLine();
		
		String[] input1=input.split("");
		
		paranthesisMatching st = new paranthesisMatching();
		
		boolean flag = false;
		for(int i=0;i<input1.length;i++)
		{
			if((input1[i].equals("("))||(input1[i].equals("{"))||(input1[i].equals("["))||(input1[i].equals("<")))
			{
				st.pushback(input1[i]);	
			}
			
			else if((input1[i].equals(")"))||(input1[i].equals("}"))||(input1[i].equals("]"))||(input1[i].equals(">")))
            {
                
                if(st.stack[st.size-1].equals("(") && input1[i].equals(")"))
                    st.popback();
                else if(st.stack[st.size-1].equals("{") && input1[i].equals("}"))
                    st.popback();
                else if(st.stack[st.size-1].equals("<") && input1[i].equals(">"))
                    st.popback();
                else if(st.stack[st.size-1].equals("[") && input1[i].equals("]"))
                    st.popback();
                else
                {
                    break;
                }
            }
			
		}
		 if(st.size==0)
	            System.out.println("true");
	        else
	            System.out.println("false");
		//st.display();
		
		//System.out.println(st.check());

	}
	
    public void	pushback(String s)
    {    	
    	stack[size]=s;
    	size++;
    }
    
    public void popback()
    {
    	if(size>=1)
    	size--;   	
    	
    }
    
    public void display()
    {
    	for(int i=0;i<size;i++)
    		System.out.println(stack[i]);
    }
    
   

}
