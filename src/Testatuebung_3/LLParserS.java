package hsfulda.bachelor.fixedParser;

import java.text.ParseException;
import java.util.Stack;

public class LLParserS implements I_SpecificParser 
{	
	@Override
	public void startLLParser(String wort) throws ParseException 
	{
		Stack<Character> stackAlphabet = new Stack<Character>();    // Stack for delta functions
		Stack<Character> eingabeAlphabet = new Stack<Character>();  // Stack for word to be processed in parser
		String validTokenChars = "abc";                             // String of all valid characters (no multi-char tokens allowed)
		
		// Move word to own stack
		for (int i = (wort.length() - 1); i >= 0; i--)
		{
			eingabeAlphabet.push(wort.charAt(i));
		}

		// Initialize delta function stack with default delta function S
		stackAlphabet.push('S');
		
		while (!(eingabeAlphabet.isEmpty() && stackAlphabet.isEmpty()))
		{
			// Check token to be valid
			Character token;
			try {
				token = eingabeAlphabet.pop();
			} catch (Exception e) {
				throw new ParseException("ParseException Missing character", 0);
			}
			
			if (validTokenChars.indexOf(token) == -1)
			{
				throw new ParseException("ParseException Invalid character", 0);
			}
			
			// Check top stack symbol to be existent
			Character stackSymbol;
			try
			{
				stackSymbol = stackAlphabet.pop();
			}
			catch (Exception e)
			{
				throw new ParseException("ParseException Unexpected character", 0);
			}
			
			// Call correct delta transition function
			if (stackSymbol.equals((Character)'S'))
			{
				S(token, stackAlphabet);
			}
			else if (stackSymbol.equals((Character)'T'))
			{
				T(token, stackAlphabet);
			}
		}
		System.out.println("Das Parsen ist geglueckt!");
	}

	@Override
	public void S(Character left, Stack zuErsetzen) throws ParseException 
	{
		if (left.equals((Character) 'a'))
		{
			zuErsetzen.push('T');
			zuErsetzen.push('S');
		}
		else if (left.equals((Character) 'c')) {}
		else 
		{
			throw new ParseException("ParseException in Method S", 0);
		}
	}

	@Override
	public void T(Character left, Stack zuErsetzen) throws ParseException 
	{
		if (left.equals((Character)'a'))
		{
			zuErsetzen.push('T');
		}
		else if (left.equals((Character)'b')) {}
		else
		{
			throw new ParseException("ParseException in Method S", 0);
		}
	}

	@Override
	public void X(Character left, Stack zuErsetzen) throws ParseException 
	{
		throw new ParseException("ParseException in Method X\n Symbol (X) not defined", 0);
	}

	@Override
	public void Y(Character left, Stack zuErsetzen) throws ParseException 
	{
		throw new ParseException("ParseException in Method Y\n Symbol (Y) not defined", 0);
	}
	
	public static void main(String[] args) 
	{
		LLParserS parser = new LLParserS();
		try 
		{
			parser.startLLParser("acb");
		}
		catch (ParseException pe)
		{
			System.err.println("Das Parsen ist verunglueckt.\n" + pe.getMessage());
		}
	}
}
