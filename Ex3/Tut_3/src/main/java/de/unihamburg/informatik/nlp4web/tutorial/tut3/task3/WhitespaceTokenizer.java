package de.unihamburg.informatik.nlp4web.tutorial.tut3.task3;

import java.util.StringTokenizer;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;

import de.unihamburg.informatik.nlp4web.tutorial.tut3.type.WSToken;

public class WhitespaceTokenizer extends JCasAnnotator_ImplBase {

	@Override
	public void process(JCas jcas) throws AnalysisEngineProcessException {
		String document = jcas.getDocumentText();

		int start = 0;
		StringTokenizer tok = new StringTokenizer(document);
		while (tok.hasMoreTokens()) {

			String token = tok.nextToken();
			WSToken tokenAnnotation = new WSToken(jcas);
			tokenAnnotation.setBegin(start);
			tokenAnnotation.setEnd(start + token.length());
			tokenAnnotation.addToIndexes();
			start += token.length();
		}

	}

}
