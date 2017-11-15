package de.unihamburg.informatik.nlp4web.tutorial.tut4.writer;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasConsumer_ImplBase;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.util.Level;

import de.tudarmstadt.ukp.dkpro.core.api.lexmorph.type.pos.POS;
public class POSWriter extends JCasConsumer_ImplBase {

	public static final String LF = System.getProperty("line.separator");

	@Override
    public void process(JCas jcas) throws AnalysisEngineProcessException {
		StringBuilder sb = new StringBuilder();
		sb.append("-- POS Tags --");
		sb.append(LF);

		for (POS p : JCasUtil.select(jcas, POS.class)) {
			sb.append("[" + p.getType().getShortName() + "] ");
			sb.append("(" + p.getBegin() + ", " + p.getEnd() + ") ");
			sb.append(p.getCoveredText());

			sb.append(LF);
		}

		sb.append(LF);

		getContext().getLogger().log(Level.INFO, sb.toString());
	}

}