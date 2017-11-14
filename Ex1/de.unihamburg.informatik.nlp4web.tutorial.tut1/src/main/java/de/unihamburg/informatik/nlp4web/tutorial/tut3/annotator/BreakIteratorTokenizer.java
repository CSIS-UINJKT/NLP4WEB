package de.unihamburg.informatik.nlp4web.tutorial.tut3.annotator;

import java.text.BreakIterator;
import java.util.Locale;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;

import de.unihamburg.informatik.nlp4web.tutorial.tut3.type.BIToken;

public class BreakIteratorTokenizer extends JCasAnnotator_ImplBase {

	@Override
	public void process(JCas jcas) throws AnalysisEngineProcessException {
		String document = jcas.getDocumentText();

		BreakIterator bi = BreakIterator.getWordInstance(Locale.ENGLISH);
		bi.setText(document);
		int start = bi.first();
		for (int end = bi.next(); end != BreakIterator.DONE; start = end, end = bi.next()) {
			BIToken tokenAnnotation = new BIToken(jcas);
			tokenAnnotation.setBegin(start);
			tokenAnnotation.setEnd(end);
			tokenAnnotation.addToIndexes();
		}

	}

}
