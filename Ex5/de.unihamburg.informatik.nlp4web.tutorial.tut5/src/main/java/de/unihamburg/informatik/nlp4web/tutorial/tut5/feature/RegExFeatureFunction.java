package de.unihamburg.informatik.nlp4web.tutorial.tut5.feature;

import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.cleartk.ml.Feature;
import org.cleartk.ml.feature.function.FeatureFunction;

/**
 * Matches the entire string to the given regex; feature is true/false
 *
 * @author mirco
 *
 */
public class RegExFeatureFunction implements FeatureFunction {

	private final Pattern pattern;
	private final String fName;
	private final boolean matchComplete;

	public RegExFeatureFunction(String regex, boolean matchComplete) {
		pattern = Pattern.compile(regex);
		this.matchComplete = matchComplete;
		fName = regex;
	}

	@Override
	public List<Feature> apply(Feature feature) {
		Object featureValue = feature.getValue();
		if (featureValue == null) {
			return Collections.emptyList();
		} else if (featureValue instanceof String) {
			String value = featureValue.toString();
			Matcher matcher = pattern.matcher(value);
			if (matchComplete) {
				return Collections.singletonList(new Feature(fName, "only" + fName));
			} else {
				int i = 0;
				while (matcher.find()) {
					i++;
				}

				return Collections.singletonList(new Feature(fName, String.valueOf(i)));
			}

		}
		return Collections.emptyList();
	}

}
