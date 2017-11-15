package de.unihamburg.informatik.nlp4web.tutorial.tut3.task1_2;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngine;
import static org.apache.uima.fit.factory.CollectionReaderFactory.createReader;

import java.io.IOException;
import java.util.Locale;

import org.apache.uima.UIMAException;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.collection.CollectionReader;
import org.apache.uima.fit.pipeline.SimplePipeline;

import de.tudarmstadt.ukp.dkpro.core.jazzy.JazzyChecker;
import de.tudarmstadt.ukp.dkpro.core.stanfordnlp.StanfordSegmenter;

public class PipelineOld {
	public static void main(String[] args) throws UIMAException, IOException {

		CollectionReader reader = createReader(WebReader.class, WebReader.PARAM_URL, "http://kjrihamburg.de/test.html");
		AnalysisEngine seg = createEngine(StanfordSegmenter.class, StanfordSegmenter.PARAM_LANGUAGE,
				Locale.ENGLISH.getLanguage());
		AnalysisEngine jazzy = createEngine(JazzyChecker.class, JazzyChecker.PARAM_MODEL_LOCATION,
				"/usr/share/dict/words");
		AnalysisEngine writer = createEngine(WebPageConsumer.class);

		SimplePipeline.runPipeline(reader, seg, jazzy, writer);
	}

}
