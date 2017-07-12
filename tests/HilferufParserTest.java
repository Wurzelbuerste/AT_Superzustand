/**
 * 	 Diese Testklasse enthaelt die wichtigsten Testfaelle, mit denen Sie ueberpruefen koennen,
 *   ob der von Ihnen generierte Parser die Morsenachrichten gemaess den Anforderungen korrekt
 *   parst.
 *   
 *   @author Annika Wagner
 */

package hsfulda.bachelor.javacc.testat2.test;

import static org.junit.Assert.*;
import hsfulda.bachelor.javacc.testat2.generatedParser.HilferufParser;
import hsfulda.bachelor.javacc.testat2.generatedParser.ParseException;
import hsfulda.bachelor.javacc.testat2.generatedParser.TokenMgrError;

import java.io.BufferedReader;
import java.io.Reader;
import java.io.StringReader;

import org.junit.Test;



public class HilferufParserTest {
	
	private HilferufParser parser;

	/**
	 * Methode zur Initialisierung des Parsers
	 * @param str
	 */
	private void setUp(String str){
		StringReader sr = new StringReader(str);
		Reader r = new BufferedReader(sr);
		try {
		parser.ReInit(r);
		} catch (NullPointerException e){
			parser = new HilferufParser(r);
		}
	}
	
	
	/**
	 * Ab hier kommen die Morsenachrichten, die als syntaktisch korrekt erkannt werden sollen.
	 */
	
	@Test
	public void test_S_O_Koord_SOS() {
	
		setUp("##52°13.345#...#13°23.567#___##...#___#...##");
		try {
		  parser.Input();
		} catch (ParseException p) {
			fail(p.getMessage());	
		}	
	}
	
	@Test
	public void test_N_O_Koord_SOS() {
	
		setUp("##52°13.345#_.#13°23.567#___##...#___#...##");
		try {
		  parser.Input();
		} catch (ParseException p) {
			fail(p.getMessage());	
		}	
	}

	@Test
	public void test_S_W_Koord_SOS() {
	
		setUp("##52°13.345#...#13°23.567#.__##...#___#...##");
		try {
		  parser.Input();
		} catch (ParseException p) {
			fail(p.getMessage());	
		}	
	}
	
	@Test
	public void test_N_W_Koord_SOS() {
	
		setUp("##52°13.345#_.#13°23.567#.__##...#___#...##");
		try {
		  parser.Input();
		} catch (ParseException p) {
			fail(p.getMessage());	
		}	
	}
	
	// Mehr Morsezeichen vor Koordinaten (ohne S, N, O, W)
	@Test
	public void test_einfacherText_vor_Koord() {
	
		setUp("##..#.#__##52°13.345#_.#13°23.567#.__##...#___#...##");
		try {
		  parser.Input();
		} catch (ParseException p) {
			fail(p.getMessage());	
		}	
	}
	
	// Mehr Morsezeichen vor Koordinaten (mit S, N, O, W)
	@Test
	public void test_komplexerText_vor_Koord() {
		
		setUp("...##...#_.#___##52°13.345#_.#13°23.567#.__##...#___#...##");
		try {
		  parser.Input();
		} catch (ParseException p) {
			fail(p.getMessage());	
		}	
	}
	
	// Direkter Einstieg mit Koordinaten
	@Test
	public void test_direkt_Koord() {
		
		setUp("52°13.345#_.#13°23.567#.__##...#___#...##");
		try {
		  parser.Input();
		} catch (ParseException p) {
			fail(p.getMessage());	
		}	
	}
	
	// Mehr Morsezeichen nach SOS (E,A,I,R,U, ohne S, N, O, W)
	@Test
	public void test_einfacherText_nach_SOS_1() {
		
		setUp("##52°13.345#_.#13°23.567#.__##...#___#...##..#.#._#._.#.._##");
		try {
		  parser.Input();
		} catch (ParseException p) {
			fail(p.getMessage());	
		}	
	}

	// Mehr Morsezeichen nach SOS (T,M,G,K,D ohne S, N, O, W)
	@Test
	public void test_einfacherText_nach_SOS_2() {
			
		setUp("##52°13.345#_.#13°23.567#.__##...#___#...##_#__#__.#_._#_..##");
		try {
		  parser.Input();
		} catch (ParseException p) {
			fail(p.getMessage());	
		}	
	}	

	// Mehr Morsezeichen nach SOS (Q,Z,Y,C,X,B ohne S, N, O, W)
	@Test
	public void test_einfacherText_nach_SOS_3() {
			
		setUp("##52°13.345#_.#13°23.567#.__##...#___#...##__._#__..#_.__#_._.#_.._#_...##");
		try {
		  parser.Input();
		} catch (ParseException p) {
			fail(p.getMessage());	
		}	
	}	

	// Mehr Morsezeichen nach SOS (J,P,L,F,V,H ohne S, N, O, W)
	@Test
	public void test_einfacherText_nach_SOS_4() {
			
		setUp("##52°13.345#_.#13°23.567#.__##...#___#...##.___#.__.#._..#.._.#..._#....##");
		try {
		  parser.Input();
		} catch (ParseException p) {
			fail(p.getMessage());	
		}	
	}	
		
	// Mehr Morsezeichen nach SOS (mit S, N, O, W)
	@Test
	public void test_komplexerText_nach_SOS() {
			
		setUp("##52°13.345#_.#13°23.567#.__##...#___#...##...##...#.__##");
		try {
		  parser.Input();
		} catch (ParseException p) {
			fail(p.getMessage());	
		}	
	}	
	
	// Mehr Morsezeichen nach SOS, aber ohne Wortende
	@Test
	public void test_abgebrochenerText_nach_SOS() {
				
		setUp("##52°13.345#_.#13°23.567#.__##...#___#...##...#...#.__#");
		try {
		  parser.Input();
		} catch (ParseException p) {
			fail(p.getMessage());	
		}	
	}
	
	// 2 Personen: SS0SS
	@Test
	public void test_SSOSS() {
	
		setUp("##52°13.345#_.#13°23.567#.__##...#...#___#...#...##");
		try {
		  parser.Input();
		} catch (ParseException p) {
			fail(p.getMessage());	
		}	
	}
	
	// 5 Personen: SSSSS0SSSSS
	@Test
	public void test_SSSSSOSSSSS() {
		
		setUp("##52°13.345#_.#13°23.567#.__##...#...#...#...#...#___#...#...#...#...#...##");
		try {
		  parser.Input();
		} catch (ParseException p) {
			fail(p.getMessage());	
		}	
	}
	
	// Sued Koordinate Gradzahl einstellig
	@Test
	public void test_verk_S_Grad_SOS() {
	
		setUp("##5°13.345#...#13°23.567#___##...#___#...##");
		try {
		  parser.Input();
		} catch (ParseException p) {
			fail(p.getMessage());	
		}	
	}
	
	// Ost Koordinate Gradzahl einstellig
	@Test
	public void test_verk_O_Grad_SOS() {
	
		setUp("##55°13.345#...#3°23.567#___##...#___#...##");
		try {
		  parser.Input();
		} catch (ParseException p) {
			fail(p.getMessage());	
		}	
	}
	
	// Koordinaten Gradzahl 0
	@Test
	public void test_null_Koord() {
		
		setUp("0°13.345#_.#0°23.567#.__##...#___#...##");
		try {
		  parser.Input();
		} catch (ParseException p) {
			fail(p.getMessage());	
		}	
	}
	
	// Koordinaten Minuten 0
	@Test
	public void test_nullMinuten_Koord() {
		
		setUp("20°0.345#_.#30°0.567#.__##...#___#...##");
		try {
		  parser.Input();
		} catch (ParseException p) {
			fail(p.getMessage());	
		}	
	}

	// Koordinaten Minuten 99 bzw. einstellig
	@Test
	public void test_MinMaxMinuten_Koord() {
			
		setUp("20°99.345#_.#30°9.567#.__##...#___#...##");
		try {
		  parser.Input();
		} catch (ParseException p) {
			fail(p.getMessage());	
		}	
	}	

	// Koordinaten Sekunden 0
	@Test
	public void test_nullSekunden_Koord() {
		
		setUp("20°10.0#_.#30°20.0#.__##...#___#...##");
		try {
		  parser.Input();
		} catch (ParseException p) {
			fail(p.getMessage());	
		}	
	}	

	// Koordinaten Sekunden 1 bzw. 10
	@Test
	public void test_wenigSekunden_Koord() {
		
		setUp("20°10.1#_.#30°20.10#.__##...#___#...##");
		try {
		  parser.Input();
		} catch (ParseException p) {
			fail(p.getMessage());	
		}	
	}	
	
	// Koordinaten Sekunden 999
	@Test
	public void test_maxSekunden_Koord() {
		
		setUp("20°10.999#_.#30°20.999#.__##...#___#...##");
		try {
		  parser.Input();
		} catch (ParseException p) {
			fail(p.getMessage());	
		}	
	}
	
	// S-Koordinaten Gradzahl 90 (Suedpol)
	@Test
	public void test_S_Koord_max() {
		
		setUp("90°13.345#...#10°23.567#.__##...#___#...##");
		try {
		  parser.Input();
		} catch (ParseException p) {
			fail(p.getMessage());	
		}	
	}
	
	// O-Koordinaten Gradzahl 180
	@Test
	public void test_O_Koord_max() {
		
		setUp("19°13.345#...#180°23.567#___##...#___#...##");
		try {
		  parser.Input();
		} catch (ParseException p) {
			fail(p.getMessage());	
		}	
	}

	
	
	/**
	 * Ab hier kommen die Morsenachrichten, die nicht syntaktisch korrekt sind.
	 * Da sie verbotene Zeichen bzw. nicht existierende Kombinationen von . und _ enthalten,
	 * werden Sie bereits vom TokenManager bemerkt.
	 */
	
	// 5x kurz ist kein Morsezeichen
	@Test(expected = TokenMgrError.class)
	public void test_keinMorsezeichen_vorab() {
		
		setUp(".....##52°13.345#...#13°23.567#___##...#___#...##");
		
		try {
		  parser.Input();
		} catch (ParseException p) {
			;
		}	
	}
	
	// 5x kurz ist kein Morsezeichen
	@Test(expected = TokenMgrError.class)
	public void test_keinMorsezeichen_Mitte() {
		
		setUp("##52°13.345#...#13°23.567#___##.....##...#___#...##");
		
		try {
		  parser.Input();
		} catch (ParseException p) {
			;
		}	
	}
	
	// 5x kurz ist kein Morsezeichen
	@Test(expected = TokenMgrError.class)
	public void test_keinMorsezeichen_Ende() {
		
		setUp("##52°13.345#...#13°23.567#___##...#___#...##.....##");
		
		try {
		  parser.Input();
		} catch (ParseException p) {
			;
		}	
	}
	
	// 5x lang ist kein Morsezeichen
	@Test(expected = TokenMgrError.class)
	public void test_keinMorsezeichen_Ende2() {
		
		setUp("##52°13.345#...#13°23.567#___##...#___#...##_____##");
		
		try {
		  parser.Input();
		} catch (ParseException p) {
			;
		}	
	}
	
	// 4x lang für CH als Morsezeichen nicht erlaubt
	@Test(expected = TokenMgrError.class)
	public void test_keinCH() {
		
		setUp("##52°13.345#...#13°23.567#___##...#___#...##____##");
		
		try {
		  parser.Input();
		} catch (ParseException p) {
			;
		}	
	}
	
	// Morsezeichen für Oe nicht erlaubt
	@Test(expected = TokenMgrError.class)
	public void test_kein_Oe() {
		
		setUp("___.##52°13.345#...#13°23.567#___##...#___#...##");
		
		try {
		  parser.Input();
		} catch (ParseException p) {
			;
		}	
	}

	// Morsezeichen für Ae nicht erlaubt
	@Test(expected = TokenMgrError.class)
	public void test_kein_Ae() {
		
		setUp("._._##52°13.345#...#13°23.567#___##...#___#...##");
		
		try {
		  parser.Input();
		} catch (ParseException p) {
			;
		}	
	}
	
	// Morsezeichen für Ue nicht erlaubt
	@Test(expected = TokenMgrError.class)
	public void test_kein_Ue() {
		
		setUp("..__##52°13.345#...#13°23.567#___##...#___#...##");
		
		try {
		  parser.Input();
		} catch (ParseException p) {
			;
		}	
	}
	
	// Himmelsrichtung in Koordination muss Morsezeichen sein 
	@Test(expected = TokenMgrError.class)
	public void test_S_inKoord() {
		
		setUp("##52°13.345#S#13°23.567#___##...#___#...##");
		
		try {
		  parser.Input();
		} catch (ParseException p) {
			;
		}	
	}
	
	// Himmelsrichtung in Koordinaten muss Morsezeichen sein
	@Test(expected = TokenMgrError.class)
	public void test_O_inKoord() {
		
		setUp("##52°13.345#...#13°23.567#O##...#___#...##");
		
		try {
		  parser.Input();
		} catch (ParseException p) {
			;
		}	
	}
	
	// Semikolon verboten; steht nicht am Ende der Eingabe
	@Test(expected = TokenMgrError.class)
	public void test_SemikolonEnde() {
		
		setUp("##52°13.345#...#13°23.567#___##...#___#...##;");
		
		try {
		  parser.Input();
		} catch (ParseException p) {
			;
		}	
	}
	
	// Nord-Sued Angabe fehlt Hash
	@Test(expected = TokenMgrError.class)
	public void test_KoordFehltHash1() {
		
		setUp("##13°23.567#...52°13.345#___##...#___#...##");
		
		try {
		  parser.Input();
		} catch (ParseException p) {
			;
		}	
	}

	
	/**
	 * Ab hier kommen die Morsenachrichten, die nicht syntaktisch korrekt sind.
	 * Sie enthalten jedoch nur korrekte Zeichen, aber in der falschen Kombination.
	 * Der TokenManager sollte daher fehlerfrei durchlaufen, während der Parser den Fehler bemerkt.
	 */
	
	// G keine erlaubte Nord-Sued Angabe
	@Test(expected = ParseException.class)
	public void test_G_als_NS_Koord() throws ParseException{
		
		setUp("##52°13.345#__.#13°23.567#___##...#___#...##");
		
		parser.Input();
	}

	// E keine erlaubte Ost-West Angabe
	@Test(expected = ParseException.class)
	public void test_E_als_OW_Koord() throws ParseException{
		
		setUp("##52°13.345#_.#13°23.567#.##...#___#...##");
		
		parser.Input();
	}
	
	// Nord-Sued Angabe muss vor Ost-West Angabe stehen
	@Test(expected = ParseException.class)
	public void test_Koord_vertauscht() throws ParseException{
		
		setUp("##13°23.567#___#52°13.345#_.##...#___#...##");
		
		parser.Input();
	}
	
	// Ost-West Angabe fehlt
	@Test(expected = ParseException.class)
	public void test_OW_Angabe_fehlt() throws ParseException{
		
		setUp("##13°23.567#...#52°13.345##...#___#...##");
		
		parser.Input();
	}
	
	// Nord-Sued Angabe fehlt
	@Test(expected = ParseException.class)
	public void test_NS_Angabe_fehlt() throws ParseException{
		
		setUp("##13°23.567#52°13.345#___##...#___#...##");
		
		parser.Input();
	}
	
	// Nord-Sued und Ost-West Angabe sollen EIN Wort sein
	@Test(expected = ParseException.class)
	public void test_Koord_zweiWoerter() throws ParseException{
		
		setUp("##13°23.567#...##52°13.345#___##...#___#...##");
		
		parser.Input();
	}
	
	// SOS soll erst nach der Positionsangabe stehen
	@Test(expected = ParseException.class)
	public void test_SOS_nur_vor_Koord() throws ParseException{
		
		setUp("##...#___#...##13°23.567#...#52°13.345#___##");
		
		parser.Input();
	}

	// Unklare Personenzahl: SS O S
	@Test(expected = ParseException.class)
	public void test_S_vorn_mehr() throws ParseException{
		
		setUp("##52°13.345#...#13°23.567#___##...#...#___#...##");
		
		parser.Input();
	}
	
	// Unklare Personenzahl: SS O SSS
	@Test(expected = ParseException.class)
	public void test_S_hinten_mehr() throws ParseException{
			
		setUp("##52°13.345#...#13°23.567#___##...#...#___#...#...#...##");
			
		parser.Input();
	}
	
	// kein SOS, da O fehlt
	@Test(expected = ParseException.class)
	public void test_kein_SOS() throws ParseException{
				
		setUp("##52°13.345#...#13°23.567#___##...#...#...#...##");
				
		parser.Input();
	}
	
	// Morsezeichen zwischen Koordinaten und SOS
	@Test(expected = ParseException.class)
	public void test_Text_zwischen_Koord_und_SOS() throws ParseException{
				
		setUp("##52°13.345#_.#13°23.567#.__##...#.#__##...#___#...##");
				
		parser.Input();
	}
		
	// Fuehrende Nullen verboten!
	@Test(expected = ParseException.class)
	public void test_fuehrendeNull_1() throws ParseException{
					
		setUp("01°13.345#_.#13°23.567#___##...#___#...##");
					
		parser.Input();
	}

	// Fuehrende Nullen verboten!
	@Test(expected = ParseException.class)
	public void test_fuehrendeNull_2() throws ParseException{
					
		setUp("1°03.345#_.#13°23.567#___##...#___#...##");
					
		parser.Input();
	}
	
	// Fuehrende Nullen verboten!
	@Test(expected = ParseException.class)
	public void test_fuehrendeNull_3() throws ParseException{
					
		setUp("1°33.045#_.#13°23.567#___##...#___#...##");
					
		parser.Input();
	}
	
	// Fuehrende Nullen verboten!
	@Test(expected = ParseException.class)
	public void test_fuehrendeNull_4() throws ParseException{
						
		setUp("1°33.545#_.#03°23.567#___##...#___#...##");
						
		parser.Input();
	}
	
	// Fuehrende Nullen verboten!
	@Test(expected = ParseException.class)
	public void test_fuehrendeNull_5() throws ParseException{
							
		setUp("1°33.545#_.#23°03.567#___##...#___#...##");
							
		parser.Input();
	}
	
	// Fuehrende Nullen verboten!
	@Test(expected = ParseException.class)
	public void test_fuehrendeNull_6() throws ParseException{
								
		setUp("1°33.545#_.#23°63.067#___##...#___#...##");
								
		parser.Input();
	}
	
	// Nord-Sued Koordinate fehlt Hash
	@Test(expected = ParseException.class)
	public void test_KoordFehltHash2() throws ParseException{
					
		setUp("##13°23.567...#52°13.345#___##...#___#...##");
					
		parser.Input();
	}

	// Ost-West Koordinate fehlt Hash
	@Test(expected = ParseException.class)
	public void test_KoordFehltHash3() throws ParseException{
					
		setUp("##13°23.567#...#52°13.345___##...#___#...##");
		
		parser.Input();
	}	
	
	
	// Gradangabe nie mehr als 180	
	@Test(expected = ParseException.class)
	public void test_Gradzahl_zu_gross() throws ParseException{
					
		setUp("13°23.567#...#181°13.345#___##...#___#...##");
					
		parser.Input();
	}
	
	// Breitengrad kann nicht groesser 90 sein, nicht noerdlicher als Norpol!
	@Test(expected = ParseException.class)
	public void test_Breite_zu_gross() throws ParseException{
						
		setUp("91°13.345#_.#13°23.567#___##...#___#...##");
						
		parser.Input();
	}
	
}
