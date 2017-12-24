package de.unihamburg.informatik.nlp4web.tutorial.tut5.ner;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngine;
import static org.apache.uima.fit.pipeline.SimplePipeline.runPipeline;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.uima.UIMAException;
import org.apache.uima.UIMAFramework;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.Level;
import org.cleartk.ml.jar.DefaultDataWriterFactory;
import org.cleartk.ml.jar.DirectoryDataWriterFactory;
import org.cleartk.ml.jar.GenericJarClassifierFactory;
import org.cleartk.ml.mallet.MalletCrfStringOutcomeDataWriter;
import org.cleartk.util.cr.FilesCollectionReader;

import de.tudarmstadt.ukp.dkpro.core.snowball.SnowballStemmer;
import de.unihamburg.informatik.nlp4web.tutorial.tut5.reader.NERReader;


public class ExecuteNER {

	public static String OUTPUT_FILE = "training_results.txt";

	public static void writeModel(File posTagFile, String modelDirectory, String language)
			throws ResourceInitializationException, UIMAException, IOException {
		System.out.println("##### WRITE MODEL #####");
		runPipeline(FilesCollectionReader.getCollectionReaderWithSuffixes(posTagFile.getAbsolutePath(),
				NERReader.CONLL_VIEW, posTagFile.getName()),

				createEngine(NERReader.class),
				createEngine(SnowballStemmer.class, SnowballStemmer.PARAM_LANGUAGE, language),
				createEngine(NERAnnotator.class, NERAnnotator.PARAM_FEATURE_EXTRACTION_FILE,
						"src/main/resources/feature/features.xml", NERAnnotator.PARAM_IS_TRAINING, true,
						DirectoryDataWriterFactory.PARAM_OUTPUT_DIRECTORY, modelDirectory,
						DefaultDataWriterFactory.PARAM_DATA_WRITER_CLASS_NAME, MalletCrfStringOutcomeDataWriter.class));

	}

	public static void trainModel(String modelDirectory) throws Exception {
		System.out.println("##### TRAIN MODEL #####");
		org.cleartk.ml.jar.Train.main(modelDirectory);

	}

	public static void classifyTestFile(String modelDirectory, File testPosFile, String language)
			throws ResourceInitializationException, UIMAException, IOException {
		System.out.println("##### CLASSIFY MODEL #####");
		runPipeline(
				FilesCollectionReader.getCollectionReaderWithSuffixes(testPosFile.getAbsolutePath(),
						NERReader.CONLL_VIEW, testPosFile.getName()),
				createEngine(NERReader.class),
				createEngine(SnowballStemmer.class, SnowballStemmer.PARAM_LANGUAGE, language),
				createEngine(NERAnnotator.class, NERAnnotator.PARAM_FEATURE_EXTRACTION_FILE,
						"src/main/resources/feature/features.xml",
						GenericJarClassifierFactory.PARAM_CLASSIFIER_JAR_PATH, modelDirectory + "model.jar"));
	}

	public static void createAnalyzableFile() {

	}

	public static void main(String[] args) throws Exception {

		long start = System.currentTimeMillis();
		String modelDirectory = "src/test/resources/model/";
		String language = "en";
		File posTagFile = new File("src/main/resources/ner/ner_eng.combi");
		File testPosFile = new File("src/main/resources/ner/ner_eng_final.test");
		new File(modelDirectory).mkdirs();
		writeModel(posTagFile, modelDirectory, language);
		trainModel(modelDirectory);
		classifyTestFile(modelDirectory, testPosFile, language);

		long now = System.currentTimeMillis();
		UIMAFramework.getLogger().log(Level.INFO, "Time: " + TimeUnit.MILLISECONDS.toMinutes(now - start) + "m");
	}
}