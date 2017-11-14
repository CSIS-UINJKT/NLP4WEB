package de.unihamburg.informatik.nlp4web.tutorial.tut3.annotator.reader;

//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.uima.UimaContext;
//import org.apache.uima.collection.CollectionException;
//import org.apache.uima.fit.component.JCasCollectionReader_ImplBase;
//import org.apache.uima.jcas.JCas;
//import org.apache.uima.resource.ResourceInitializationException;
//import org.apache.uima.util.Progress;
//import org.apache.uima.util.ProgressImpl;


//public class DummyReader extends JCasCollectionReader_ImplBase {
//	List<String> texts;
//	int idx = 0;
//
//	@Override
//	public void initialize(UimaContext context) throws ResourceInitializationException {
//		super.initialize(context);
//
//		
//		texts = new ArrayList<String>();
//		ClassLoader classLoader = getClass().getClassLoader();
//		System.out.println(classLoader);
////		File file = new File(classLoader.getResource("news.txt").getFile());
//		File file = new File("src/main/resources/news.txt");
//		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
//		    String line;
//		    while ((line = br.readLine()) != null) {
//		    	texts.add(line);
//		    }
//		} catch (IOException e) {
//			System.out.println(e.getMessage());
//		}
//	}
//
//	@Override
//	public void getNext(JCas jcas) throws IOException, CollectionException {
//		jcas.setDocumentText(texts.get(idx));
//		idx++;
//	}
//
//	@Override
//	public Progress[] getProgress() {
//		return new Progress[] { new ProgressImpl(idx + 1, texts.size(), Progress.ENTITIES) };
//	}
//
//	@Override
//	public boolean hasNext() throws IOException, CollectionException {
//		return idx < texts.size();
//	}
//
//}

//package de.unihamburg.informatik.nlp4web.tutorial.tut1.reader;
//
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.uima.UimaContext;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.fit.component.JCasCollectionReader_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.Progress;
import org.apache.uima.util.ProgressImpl;


public class DummyReader extends JCasCollectionReader_ImplBase {
	List<String> texts;
	int idx = 0;

	@Override
	public void initialize(UimaContext context) throws ResourceInitializationException {
		super.initialize(context);

		
	  texts = new ArrayList<>();
      texts.add("I saw a man.  \"I'm Sam,\" he said.");
	}

	@Override
	public void getNext(JCas jcas) throws IOException, CollectionException {
		jcas.setDocumentText(texts.get(idx));
		idx++;
	}

	@Override
	public Progress[] getProgress() {
		return new Progress[] { new ProgressImpl(idx + 1, texts.size(), Progress.ENTITIES) };
	}

	@Override
	public boolean hasNext() throws IOException, CollectionException {
		return idx < texts.size();
	}

}