package hsfulda.bachelor.fixedParser;

import java.text.ParseException;
import java.util.Stack;

public interface I_SpecificParser {
	
	/*
	 * startet das Parsen von wort
	 * liefert true, wenn wort syntaktisch korrekt bzgl. der aktuellen Grammatik
	 */
	public void startLLParser(String wort) throws ParseException;
	
	// aufgerufen wenn S als oberstes Stacksymbol zu verarbeiten ist
	public void S(Character left, Stack zuErsetzen) throws ParseException;
	
	// aufgerufen, wenn T als oberstes Stacksymbol zu vearbeiten ist
	public void T(Character left, Stack zuErsetzen) throws ParseException;
	
	// aufgerufen, wenn X als oberstes Stacksymbol zu vearbeiten ist
	public void X(Character left, Stack zuErsetzen) throws ParseException;
	
	// aufgerufen, wenn Y als oberstes Stacksymbol zu vearbeiten ist
	public void Y(Character left, Stack zuErsetzen) throws ParseException;
	
}
