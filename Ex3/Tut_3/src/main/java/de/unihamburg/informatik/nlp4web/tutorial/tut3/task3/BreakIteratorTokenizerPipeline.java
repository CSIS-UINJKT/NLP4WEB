package de.unihamburg.informatik.nlp4web.tutorial.tut3.task3;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngine;
import static org.apache.uima.fit.factory.CollectionReaderFactory.createReader;

import java.io.IOException;

import org.apache.uima.UIMAException;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.collection.CollectionReader;
import org.apache.uima.fit.pipeline.SimplePipeline;

import de.unihamburg.informatik.nlp4web.tutorial.tut3.task1_2.WebPageConsumer;
import de.unihamburg.informatik.nlp4web.tutorial.tut3.task1_2.WebReader;

public class BreakIteratorTokenizerPipeline {

	public static void main(String[] args) throws UIMAException, IOException {

		CollectionReader reader = createReader(WebReader.class, WebReader.PARAM_URL, "http://www.civilized.de");

		AnalysisEngine seg = createEngine(BreakIteratorTokenizer.class);

		AnalysisEngine writer = createEngine(WebPageConsumer.class);

		SimplePipeline.runPipeline(reader, seg, writer);
	}

}
