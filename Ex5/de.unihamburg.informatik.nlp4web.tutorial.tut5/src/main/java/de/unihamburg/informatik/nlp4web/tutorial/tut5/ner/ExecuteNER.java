package de.unihamburg.informatik.nlp4web.tutorial.tut5.ner;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.uima.UIMAException;
import org.apache.uima.UIMAFramework;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.Level;


public class ExecuteNER {

	public static String OUTPUT_FILE = "training_results.txt";

	public static void writeModel(File posTagFile, String modelDirectory, String language)
			throws ResourceInitializationException, UIMAException, IOException {
		System.out.println("##### WRITE MODEL #####");

	}

	public static void trainModel(String modelDirectory) throws Exception {
		System.out.println("##### TRAIN MODEL #####");
		org.cleartk.ml.jar.Train.main(modelDirectory);

	}

	public static void classifyTestFile(String modelDirectory, File testPosFile, String language)
			throws ResourceInitializationException, UIMAException, IOException {
		System.out.println("##### CLASSIFY MODEL #####");
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