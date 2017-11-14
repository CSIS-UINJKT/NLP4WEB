package de.unihamburg.informatik.nlp4web.tutorial.tut3.annotator;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngine;
import static org.apache.uima.fit.factory.CollectionReaderFactory.createReader;

import java.io.IOException;

import org.apache.uima.UIMAException;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.collection.CollectionReader;
import org.apache.uima.fit.pipeline.SimplePipeline;

//import de.tudarmstadt.ukp.dkpro.core.tokit.BreakIteratorSegmenter;
import de.unihamburg.informatik.nlp4web.tutorial.tut3.annotator.reader.DummyReader;
import de.unihamburg.informatik.nlp4web.tutorial.tut3.annotator.writer.DummyWriter;

public class Pipeline3 {

	public static void main(String[] args) throws UIMAException, IOException {
		CollectionReader reader = createReader(DummyReader.class);

		AnalysisEngine seg = createEngine(BreakIteratorTokenizer.class);

		AnalysisEngine writer = createEngine(DummyWriter.class);

		SimplePipeline.runPipeline(reader, seg, writer);
	}
}
