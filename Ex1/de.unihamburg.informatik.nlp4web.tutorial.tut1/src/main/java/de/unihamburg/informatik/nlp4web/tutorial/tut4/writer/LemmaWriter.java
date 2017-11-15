package de.unihamburg.informatik.nlp4web.tutorial.tut4.writer;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasConsumer_ImplBase;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.util.Level;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Lemma;

public class LemmaWriter extends JCasConsumer_ImplBase {

	public static final String LF = System.getProperty("line.separator");

	@Override
    public void process(JCas jcas) throws AnalysisEngineProcessException {
		StringBuilder sb = new StringBuilder();
		sb.append("-- Lemmata --");
		sb.append(LF);

		for (Lemma l : JCasUtil.select(jcas, Lemma.class)) {
			sb.append("[" + l.getType().getShortName() + "] ");
			sb.append("(" + l.getBegin() + ", " + l.getEnd() + ") ");
			sb.append(l.getCoveredText() + " -> ");
			sb.append(l.getValue());

			sb.append(LF);
		}

		sb.append(LF);

		getContext().getLogger().log(Level.INFO, sb.toString());
	}

}