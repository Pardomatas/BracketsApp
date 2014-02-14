// brackets.java
// stacks used to check matching brackets

import java.io.*;
import java.util.Arrays;
/////////////////////////////////////////////////////////////////////////////////////
class StackX
{
	private int maxSize;
	private char[] stackArray;
	private char[] backArr;
	private int top;
//------------------------------------------------------------------------------------
	public StackX(int s)		// constructor
	{
		maxSize = s;
		stackArray = new char[maxSize];
		backArr = new char[maxSize];
		top = -1;
	}
//------------------------------------------------------------------------------------
	public void push(char j)	//put item on top of stack
	{
		stackArray[++top] = j;
	}
//--------------------------------------------------------------------------------------
	public char pop()	//take item from top of stack
	{
		return stackArray[top--];
	}
//---------------------------------------------------------------------------------------
	public char peek() // peek at top of stack
	{
		return stackArray[top];
	}
//--------------------------------------------------------------------------------------
	public boolean isEmpty()		//true if stack is empty
	{
		return (top == -1);
	}
	public boolean isFull()		//checks if full
	{
		return (top == maxSize - 1);
	}
	public void palindrome()		//checks for palindrome
	{
		boolean same;
		int p = 0;
		for (int i = stackArray.length - 1; i >= 0; i--) 
		{
			backArr[p] = stackArray[i];
			p++;
		}
		same = Arrays.equals(stackArray,backArr);
		
		if (same == true){
			System.out.println("Palindrome.");
		}
		else
			System.out.println("Not a Palindrome.");
	}
//--------------------------------------------------------------------------------------
}	//end class StackX
//////////////////////////////////////////////////////////////////////////////////////////////////
class BracketChecker
{
	private String input;		//input string
//--------------------------------------------------------------------------------------------------
	public BracketChecker(String in)		//constructor
	{ input = in; }
//--------------------------------------------------------------------------------------------------
	public void check()
	{
		int stackSize = input.length();		//get max stack size
		StackX theStack = new StackX(stackSize);	//make stack
		
		for(int j = 0; j < input.length(); j++)		//get chars in turn
		{
			char ch = input.charAt(j);		//get char
			switch(ch)
			{
				case '{':			// opening symbols
				case '[':
				case '(':
					theStack.push(ch);		//push them
					
					break;
				case '}':			//closing symbols
				case ']':
				case ')':
					if( !theStack.isEmpty() )	//if stack not empty
					{
						char chx = theStack.pop();	//pop and check
						if( (ch=='}' && chx!='{') ||
							(ch==']' && chx!='[') ||
							(ch==')' && chx!='(') )
							System.out.println("Error: "+ch+" at "+j);
					}
					else		//prematurely empty
						System.out.println("Error: "+ch+" at "+j);
					break;
				default:		//no action on other characters
					break;
			}	//end switch
		}	//end for
	// at this point, all characters have been processed
	if( !theStack.isEmpty() )
		System.out.println("Enter: missing right delimiter");
	theStack.palindrome();
	}	//end check()
//--------------------------------------------------------------------------------------
}	//end class BracketChecker
////////////////////////////////////////////////////////////////////////////////////////////
class BracketsApp
{
	public static void main(String[] args) throws IOException
	{
		String input;
		while(true)
		{
			System.out.print("Enter string containing delimiters: ");
			System.out.flush();
			input = getString();		//read a string from kbd
			if( input.equals("") )		// quit if [Enter]
				break;
										//make a bracketchecker
			BracketChecker theChecker = new BracketChecker(input);
			theChecker.check();	//check brackets
		}	//end while
	}	//end main()
//-----------------------------------------------------------------------------------------
	public static String getString() throws IOException
	{
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}
//---------------------------------------------------------------------------------------------
}	// end class BracketsApp