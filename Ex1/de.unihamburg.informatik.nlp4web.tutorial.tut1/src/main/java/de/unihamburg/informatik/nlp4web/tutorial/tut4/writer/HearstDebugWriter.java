package de.unihamburg.informatik.nlp4web.tutorial.tut4.writer;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasConsumer_ImplBase;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.util.Level;

import de.unihamburg.informatik.nlp4web.tutorial.tut4.type.HearstAnnotation;

public class HearstDebugWriter extends JCasConsumer_ImplBase {

	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		getContext().getLogger().log(Level.INFO, "### Hearst Annotations ###");
		for (HearstAnnotation l : JCasUtil.select(aJCas, HearstAnnotation.class)) {
			StringBuffer buffer = new StringBuffer();
			buffer.append("[Text]").append(l.getCoveredText()).append("\n").append("[Type] ").append(l.getTypeOf())
					.append(" [Begin] ").append(l.getBegin()).append(" [End] ").append(l.getEnd()).append("\n")
					.append("[Hyperonym] ").append(l.getHyperonym()).append(" [Hypoym] ").append(l.getHyponym())
					.append("\n");
			getContext().getLogger().log(Level.INFO, buffer.toString());

		}

	}

}
