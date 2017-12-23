package de.unihamburg.informatik.nlp4web.tutorial.tut5.feature;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.uima.jcas.JCas;
import org.cleartk.ml.Feature;
import org.cleartk.ml.feature.extractor.CleartkExtractorException;
import org.cleartk.ml.feature.extractor.NamedFeatureExtractor1;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;

/**
 * Reads some dictionary lists from github
 *
 * @author mirco
 *
 */
public class ListExtractor implements NamedFeatureExtractor1<Token> {

	private final Map<String, String> lookup = new TreeMap<>();

	public ListExtractor() {

		Util.readLines("from_github/business_names").forEach(word -> lookup.put(word.toLowerCase(), "I-ORG"));
		Util.readLines("from_github/names").forEach(word -> lookup.put(word.toLowerCase(), "I-PER"));
		Util.readLines("from_github/city_details", ",").forEach(word -> lookup.put(word.toLowerCase(), "I-LOC"));
	}

	@Override
	public List<Feature> extract(JCas view, Token focusAnnotation) throws CleartkExtractorException {
		String coveredText = focusAnnotation.getCoveredText();

		if (lookup.containsKey(coveredText.toLowerCase())) {
			return Collections.singletonList(new Feature("gh", lookup.get(coveredText)));
		} else {
			return Collections.emptyList();
		}

	}

	@Override
	public String getFeatureName() {
		return "foobar";
	}
}
