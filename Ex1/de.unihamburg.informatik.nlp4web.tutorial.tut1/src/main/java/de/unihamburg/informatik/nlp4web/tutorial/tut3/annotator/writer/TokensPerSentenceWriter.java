package de.unihamburg.informatik.nlp4web.tutorial.tut3.annotator.writer;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasConsumer_ImplBase;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.Level;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence;
import de.unihamburg.informatik.nlp4web.tutorial.tut3.type.BIToken;

public class TokensPerSentenceWriter extends JCasConsumer_ImplBase {

	@Override
	public void initialize(UimaContext context) throws ResourceInitializationException {
		super.initialize(context);

	}

	@Override
	public void process(JCas arg0) throws AnalysisEngineProcessException {
		Iterator<Sentence> it = JCasUtil.select(arg0, Sentence.class).iterator();

		while (it.hasNext()) {
			Sentence a = it.next();
			List<BIToken> covered = JCasUtil.selectCovered(arg0, BIToken.class, a);
			Map<String, String> info = new LinkedHashMap<String, String>();
			getContext().getLogger().log(Level.INFO, "BITokens in Sentence: " + covered.size());

		}

	}

}

