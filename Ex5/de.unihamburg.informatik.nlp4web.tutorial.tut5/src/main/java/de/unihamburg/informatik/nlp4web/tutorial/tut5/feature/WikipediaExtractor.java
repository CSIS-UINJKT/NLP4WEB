package de.unihamburg.informatik.nlp4web.tutorial.tut5.feature;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.uima.jcas.JCas;
import org.cleartk.ml.Feature;
import org.cleartk.ml.feature.extractor.CleartkExtractorException;
import org.cleartk.ml.feature.extractor.NamedFeatureExtractor1;

import com.google.common.collect.Lists;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;

public class WikipediaExtractor implements NamedFeatureExtractor1<Token> {

	private final Map<String, String> neToTag = new HashMap<>();

	private final WikipediaListBuilder wb = new WikipediaListBuilder();
	int handled = 0;
	int m = 0;
	int w = 0;

	@Override
	public List<Feature> extract(JCas view, Token focusAnnotation) throws CleartkExtractorException {
		String coveredText = focusAnnotation.getCoveredText();

		String tag = neToTag.get(coveredText.toLowerCase());
		handled++;
		if ((tag == null)) {
			tag = wb.getTag(coveredText);
			neToTag.put(coveredText.toLowerCase(), tag);

		} else {
			return Collections.emptyList();
		}

		return Lists.newArrayList(new Feature("wiki", tag));

	}

	@Override
	public String getFeatureName() {
		return "wikiFeature";
	}
}
