package de.unihamburg.informatik.nlp4web.tutorial.tut3.annotator;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;

import de.unihamburg.informatik.nlp4web.tutorial.tut3.type.WSToken;


public class WhitespaceTokenizer extends JCasAnnotator_ImplBase {

	// Problem 3.1: Adapt the whitespace tokenizer into a UIMA annotator
	@Override
	public void process(JCas jcas) throws AnalysisEngineProcessException {
		String document = jcas.getDocumentText();
		int len = document.length(), start = 0, end = 0;

		// The following code creates an annotation and adds it to
		// the index (otherwise it is not added to the CAS). You'll need execute
		// it for each token you find.
		WSToken tokenAnnotation = new WSToken(jcas);
		tokenAnnotation.setBegin(start);
		tokenAnnotation.setEnd(end);
		tokenAnnotation.addToIndexes();
	}

}
 