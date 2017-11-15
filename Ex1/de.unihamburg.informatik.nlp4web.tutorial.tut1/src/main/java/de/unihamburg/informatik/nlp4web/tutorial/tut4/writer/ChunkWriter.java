package de.unihamburg.informatik.nlp4web.tutorial.tut4.writer;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasConsumer_ImplBase;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.util.Level;

import de.tudarmstadt.ukp.dkpro.core.api.syntax.type.constituent.NP;
import de.tudarmstadt.ukp.dkpro.core.api.syntax.type.constituent.VP;

public class ChunkWriter extends JCasConsumer_ImplBase {

	public static final String LF = System.getProperty("line.separator");

	@Override
    public void process(JCas jcas) throws AnalysisEngineProcessException {
		StringBuilder sb = new StringBuilder();

		sb.append("-- Chunks --");
		sb.append(LF);

		for (Annotation a : JCasUtil.select(jcas, Annotation.class)) {
			if (a instanceof NP || a instanceof VP) {
				sb.append("[" + a.getType().getShortName() + "] ");
				sb.append("(" + a.getBegin() + ", " + a.getEnd() + ") ");
				sb.append(a.getCoveredText());
				sb.append(LF);
			}
		}

		sb.append(LF);

		getContext().getLogger().log(Level.INFO, sb.toString());
	}

}