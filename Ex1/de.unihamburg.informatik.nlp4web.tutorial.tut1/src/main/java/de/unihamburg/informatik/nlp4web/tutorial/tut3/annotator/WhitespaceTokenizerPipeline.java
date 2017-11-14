package de.unihamburg.informatik.nlp4web.tutorial.tut3.annotator;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngine;
import static org.apache.uima.fit.factory.CollectionReaderFactory.createReader;

import java.io.IOException;

import org.apache.uima.UIMAException;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.collection.CollectionReader;
import org.apache.uima.fit.pipeline.SimplePipeline;

import de.unihamburg.informatik.nlp4web.tutorial.tut3.annotator.writer.WebPageConsumer;
import de.unihamburg.informatik.nlp4web.tutorial.tut3.annotator.reader.WebReader;

public class WhitespaceTokenizerPipeline {

	public static void main(String[] args) throws UIMAException, IOException {

		CollectionReader reader = createReader(WebReader.class, WebReader.PARAM_URL,
				"http://kjrihamburg.de/test.html");

		AnalysisEngine seg = createEngine(WhitespaceTokenizer.class);

		AnalysisEngine writer = createEngine(WebPageConsumer.class);

		SimplePipeline.runPipeline(reader, seg, writer);
	}

}
