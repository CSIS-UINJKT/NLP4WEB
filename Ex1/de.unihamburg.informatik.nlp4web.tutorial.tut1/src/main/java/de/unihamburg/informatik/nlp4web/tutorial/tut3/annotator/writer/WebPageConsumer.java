package de.unihamburg.informatik.nlp4web.tutorial.tut3.annotator.writer;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasConsumer_ImplBase;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.Level;

import com.google.common.base.Joiner;

public class WebPageConsumer extends JCasConsumer_ImplBase {

	@Override
	public void initialize(UimaContext context) throws ResourceInitializationException {
		super.initialize(context);

	}

	@Override
	public void process(JCas arg0) throws AnalysisEngineProcessException {
		Iterator<Annotation> it = JCasUtil.select(arg0, Annotation.class).iterator();

		while (it.hasNext()) {
			Annotation a = it.next();
			Map<String, String> info = new LinkedHashMap<String, String>();
			info.put("Short Name", a.getType().getShortName());
			info.put("Begin", String.valueOf(a.getBegin()));
			info.put("End", String.valueOf(a.getEnd()));
			info.put("Covered Text", a.getCoveredText());
			getContext().getLogger().log(Level.INFO, Joiner.on("; ").withKeyValueSeparator(":").join(info) + "\n");

		}

	}

}
