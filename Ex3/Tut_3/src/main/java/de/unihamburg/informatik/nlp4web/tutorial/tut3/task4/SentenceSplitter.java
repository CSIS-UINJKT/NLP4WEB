package de.unihamburg.informatik.nlp4web.tutorial.tut3.task4;

import java.text.BreakIterator;
import java.util.Locale;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence;

public class SentenceSplitter extends JCasAnnotator_ImplBase {

	@Override
	public void process(JCas jcas) throws AnalysisEngineProcessException {
		String document = jcas.getDocumentText();

		BreakIterator bi = BreakIterator.getSentenceInstance(Locale.ENGLISH);
		bi.setText(document);
		int start = bi.first();
		for (int end = bi.next(); end != BreakIterator.DONE; start = end, end = bi.next()) {
			Sentence tokenAnnotation = new Sentence(jcas);
			tokenAnnotation.setBegin(start);
			tokenAnnotation.setEnd(end);
			tokenAnnotation.addToIndexes();
		}

	}

}
