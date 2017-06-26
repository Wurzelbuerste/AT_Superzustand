package hsfulda.bachelor.fixedParser;

import java.text.ParseException;
import java.util.Stack;

public class LLParserS implements I_SpecificParser 
{

	// Datenfelder
	private Stack<Character> eingabeAlphabet = new Stack<Character>();

	
	@Override
	public void startLLParser(String wort) throws ParseException 
	{
		Stack<Character> stackAlphabet = new Stack<Character>();
		
		StringBuilder wortNeu = new StringBuilder(wort.length());		
		for (int i = (wort.length() - 1); i >= 0; i--)
		{
			wortNeu.append(wort.charAt(i));
		}
		
		for (int i = 0; i < wortNeu.length(); i++)
		{
			eingabeAlphabet.push(wortNeu.charAt(i));
			System.out.println("eingabeAlphabet an Stelle " + i + " ist :" + eingabeAlphabet.peek());
		}
		
//		for (int i = 0; i < wort.length(); i++)
//		{
//			eingabeAlphabet.push(wortNeu.charAt(i));
//			System.out.println("eingabeAlphabet an Stelle " + i + " ist :" + eingabeAlphabet.peek());
//		}
		// Initiales S auf den Stack pushen
		stackAlphabet.push('S');
		
		while (!eingabeAlphabet.isEmpty())
		{
			System.out.println("Testausgabe - Oberstes Stacksymbol: " + stackAlphabet.peek());
			if (stackAlphabet.peek().equals((Character)'S'))
			{
				S(eingabeAlphabet.peek(), stackAlphabet);
				System.out.println("Oberstes Stacksymbol: " + stackAlphabet.peek());
			}
			else if (stackAlphabet.peek().equals((Character)'T'))
			{
				T(eingabeAlphabet.peek(), stackAlphabet);
			}
		}
		if (eingabeAlphabet.isEmpty() && stackAlphabet.isEmpty())
		{
			System.out.println("Das Parsen ist geglueckt!");
		}
		else
		{
			System.out.println("Das Parsen ist verunglueckt!");
		}
	}

	@Override
	public void S(Character left, Stack zuErsetzen) throws ParseException 
	{
		System.out.println("Aufruf der Methode S");
		
		
		if ((left.equals((Character) 'a')) || (left.equals((Character) 'c')))
		{
			if (left.equals((Character) 'a'))
			{
				zuErsetzen.pop();		// S wird vom Stack genommen
				eingabeAlphabet.pop();	// das forderste Zeichen des Wortes wird weggenommen
				
				zuErsetzen.push('T');
				System.out.println("Oberstes Stacksymbol: " + zuErsetzen.peek());
				System.out.println("Es wurde: " + zuErsetzen.peek() + " auf den Stacksymbole-Stack gepusht");
				zuErsetzen.push('S');
				System.out.println("Es wurde: " + zuErsetzen.peek() + " auf den Stacksymbole-Stack gepusht");
			}
			else
			{
				zuErsetzen.pop();			// S wird vom Stack genommen
				eingabeAlphabet.pop();		// c wird vom Wort entfernt
			}
		}
		else 
		{
			throw new ParseException("ParseException in Method S", 0);
		}
		
		
		
		
		
//		System.out.println("Aufruf der Methode S");
//		System.out.println("Methode S: top() Element vom eingabeAlphabet ist: " + eingabeAlphabet.peek());
//		if (eingabeAlphabet.peek().equals('a') || eingabeAlphabet.peek().equals('c'))
//		{
//			if (eingabeAlphabet.peek().equals((Character)'a'))
//			{
//				eingabeAlphabet.pop();		//a wird entfernt
//				
//				zuErsetzen.push('T');	//
//				zuErsetzen.push('S');
//			}
//			else
//			{
//				eingabeAlphabet.pop();
//			}
//		}
//		else
//		{
//			throw new ParseException("ParseException in Method S", 0);
//		}

	}

	@Override
	public void T(Character left, Stack zuErsetzen) throws ParseException 
	{
		System.out.println("Aufruf der Methode T");
		if (left.equals((Character)'a') || left.equals((Character)'b'))
		{
			if (left.equals((Character)'a'))
			{
				zuErsetzen.pop();
				eingabeAlphabet.pop();
				zuErsetzen.push('T');
			}
			else
			{
				zuErsetzen.pop();			// Entfernen von T
				eingabeAlphabet.pop();
				
			}
		}
		
//		System.out.println("Aufruf der Methode T");
//		if (eingabeAlphabet.peek().equals('a') || eingabeAlphabet.peek().equals('b'))
//		{
//			if (eingabeAlphabet.peek().equals((Character)'a'))
//			{
//				eingabeAlphabet.pop();		//a wird entfernt
//				zuErsetzen.push('T');
//			}
//			else
//			{
//				eingabeAlphabet.pop();
//			}
//		}
//		else
//		{
//			throw new ParseException("ParseException in Method T", 0);
//		}
//		System.out.println("Anzahl der Elemente auf dem zuErsetzen Stack" + zuErsetzen.size());
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
		LLParserS p = new LLParserS();
		try 
		{
			p.startLLParser("acb");
		}
		catch (ParseException pe)
		{
			System.err.println(pe.getMessage());
		}
	}
	

}
