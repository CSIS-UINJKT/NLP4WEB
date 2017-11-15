package de.unihamburg.informatik.nlp4web.tutorial.tut3.task4;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngine;
import static org.apache.uima.fit.factory.CollectionReaderFactory.createReader;

import java.io.IOException;

import org.apache.uima.UIMAException;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.collection.CollectionReader;
import org.apache.uima.fit.pipeline.SimplePipeline;

import de.unihamburg.informatik.nlp4web.tutorial.tut3.task1_2.WebReader;
import de.unihamburg.informatik.nlp4web.tutorial.tut3.task3.BreakIteratorTokenizer;

public class SentenceSplitterPipeline {

	public static void main(String[] args) throws UIMAException, IOException {

		CollectionReader reader = createReader(WebReader.class, WebReader.PARAM_URL,
				"http://www.gutenberg.org/files/1342/1342-0.txt");

		AnalysisEngine sentence = createEngine(SentenceSplitter.class);
		AnalysisEngine bi = createEngine(BreakIteratorTokenizer.class);

		AnalysisEngine writer = createEngine(TokensPerSentenceWriter.class);

		SimplePipeline.runPipeline(reader, sentence, bi, writer);
	}

}
