package de.unihamburg.informatik.nlp4web.tutorial.tut3.task1_2;

import java.text.BreakIterator;
import java.util.Locale;
import java.util.StringTokenizer;

public class TokenizerExample {

	public static void main(String[] args) {
		String s = "I saw a man.  \"I'm Sam,\" he said.";

		System.out.println("--- WhiteSpaceTokenize ---");
		WhitespaceTokenize(s);

		System.out.println("--- BreakIteratorTokenize ---");
		BreakIteratorTokenize(s);
	}

	// Problem 1: Examine and run the following tokenizer. What problems
	// do you see in the output?
	private static void WhitespaceTokenize(String document) {
		StringTokenizer tok = new StringTokenizer(document);
		while (tok.hasMoreTokens()) {
			System.out.println(tok.nextElement());
		}
	}

	// Problem 2: Implement a tokenizer using java.text.BreakIterator.
	// What are the improvements over the previous approach? What issues
	// still remain?
	private static void BreakIteratorTokenize(String document) {
		BreakIterator bi = BreakIterator.getWordInstance(Locale.ENGLISH);
		bi.setText(document);
		int start = bi.first();
		for (int end = bi.next(); end != BreakIterator.DONE; start = end, end = bi.next()) {
			System.out.println(document.substring(start, end));
		}
	}
}
