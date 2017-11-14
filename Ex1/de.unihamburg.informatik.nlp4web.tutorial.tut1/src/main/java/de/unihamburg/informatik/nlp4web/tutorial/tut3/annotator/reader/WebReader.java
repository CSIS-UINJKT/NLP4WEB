package de.unihamburg.informatik.nlp4web.tutorial.tut3.annotator.reader;

import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;

import org.apache.uima.UimaContext;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.fit.component.JCasCollectionReader_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.Progress;
import org.apache.uima.util.ProgressImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class WebReader extends JCasCollectionReader_ImplBase {
	public static final String PARAM_URL = "PARAM_URL";
	@ConfigurationParameter(name = PARAM_URL, description = "URL. What else?", mandatory = true, defaultValue = "https://www.inf.uni-hamburg.de/en/inst/ab/lt.html")
	private String url;
	private Iterator<Element> it = Collections.emptyIterator();
	private int total = 0;
	private int current = 0;

	@Override
	public void initialize(UimaContext context) throws ResourceInitializationException {
		super.initialize(context);
		try {
			Document doc = Jsoup.connect(url).get();
			total = doc.getAllElements().size();
			it = doc.getAllElements().iterator();
		} catch (IOException e) {
			throw new ResourceInitializationException(e.getCause());
		}
	}

	public Progress[] getProgress() {
		return new Progress[] { new ProgressImpl(current, total, Progress.ENTITIES) };
	}

	public boolean hasNext() throws IOException, CollectionException {
		return it.hasNext();
	}

	@Override
	public void getNext(JCas jCas) throws IOException, CollectionException {
		jCas.setDocumentText(it.next().html());
		current++;

	}

}
