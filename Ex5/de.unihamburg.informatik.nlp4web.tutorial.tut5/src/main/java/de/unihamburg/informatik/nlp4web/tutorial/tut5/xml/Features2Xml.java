package de.unihamburg.informatik.nlp4web.tutorial.tut5.xml;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.cleartk.ml.Feature;
import org.cleartk.ml.feature.extractor.CleartkExtractor;
import org.cleartk.ml.feature.extractor.CleartkExtractor.Following;
import org.cleartk.ml.feature.extractor.CleartkExtractor.Preceding;
import org.cleartk.ml.feature.extractor.CoveredTextExtractor;
import org.cleartk.ml.feature.extractor.FeatureExtractor1;
import org.cleartk.ml.feature.function.CapitalTypeFeatureFunction;
import org.cleartk.ml.feature.function.CharacterNgramFeatureFunction;
import org.cleartk.ml.feature.function.CharacterNgramFeatureFunction.Orientation;
import org.cleartk.ml.feature.function.FeatureFunction;
import org.cleartk.ml.feature.function.FeatureFunctionExtractor;
import org.cleartk.ml.feature.function.LowerCaseFeatureFunction;
import org.cleartk.ml.feature.function.NumericTypeFeatureFunction;

import com.thoughtworks.xstream.XStream;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.unihamburg.informatik.nlp4web.tutorial.tut5.feature.ListExtractor;
import de.unihamburg.informatik.nlp4web.tutorial.tut5.feature.RegExFeatureFunction;
import de.unihamburg.informatik.nlp4web.tutorial.tut5.feature.WikipediaExtractor;

public class Features2Xml {

	public static void generateFeatureExtractors(String filename) throws FileNotFoundException {

		List<FeatureExtractor1<Token>> features = new ArrayList<>();

		features.add(new FeatureFunctionExtractor<Token>(new CoveredTextExtractor<>(),
				new RegExFeatureFunction("[A-Za-z]", true)));

		features.add(new CleartkExtractor<Token, Token>(Token.class, new CoveredTextExtractor<Token>(),
				new Preceding(2), new Following(2)));

		features.add(new FeatureFunctionExtractor<>(new CoveredTextExtractor<>(), new NumericTypeFeatureFunction()));

		features.add(new FeatureFunctionExtractor<>(new CoveredTextExtractor<>(),
				new CharacterNgramFeatureFunction(Orientation.LEFT_TO_RIGHT, 0, 4)));

		features.add(new WikipediaExtractor());

		features.add(new FeatureFunctionExtractor<>(new CoveredTextExtractor<>(), new LowerCaseFeatureFunction()));

		features.add(new FeatureFunctionExtractor<>(new CoveredTextExtractor<>(), new CapitalTypeFeatureFunction()));

		features.add(new FeatureFunctionExtractor<Token>(new CoveredTextExtractor<>()));

		features.add(new FeatureFunctionExtractor<Token>(new CoveredTextExtractor<>(),
				new RegExFeatureFunction("[^A-Za-z 0-9]", false)));

		features.add(new FeatureFunctionExtractor<Token>(new CoveredTextExtractor<>(),
				new RegExFeatureFunction("[^A-Z]+", true), new StartEnd("ing", true)));

		features.add(new FeatureFunctionExtractor<Token>(new CoveredTextExtractor<>(), new StartEnd("land", true)));

		features.add(new FeatureFunctionExtractor<Token>(new CoveredTextExtractor<>(),
				new RegExFeatureFunction("[^A-Z]+", true), new StartEnd("ed", true)));

		features.add(new FeatureFunctionExtractor<Token>(new CoveredTextExtractor<>(),
				new RegExFeatureFunction("[^A-Z]+", true), new StartEnd("ly", true)));

		features.add(new FeatureFunctionExtractor<Token>(new CoveredTextExtractor<>(), new StartEnd("son", true)));

		features.add(new ListExtractor());

		XStream xstream = XStreamFactory.createXStream();
		String x = xstream.toXML(features);
		x = removeLogger(x);
		PrintStream ps = new PrintStream(filename);
		ps.println(x);
		ps.close();
	}

	/**
	 * To make the xml file more readable remove the logger elements that are'nt
	 * needed
	 *
	 * @param x
	 * @return
	 */
	private static String removeLogger(String x) {
		StringBuffer buffer = new StringBuffer();
		String[] lines = x.split("\n");
		boolean loggerFound = false;
		for (String l : lines) {
			if (l.trim().startsWith("<logger>")) {
				loggerFound = true;
			}
			if (!loggerFound) {
				buffer.append(l);
				buffer.append("\n");
			} else {
				if (l.trim().startsWith("</logger>")) {
					loggerFound = false;
				}
			}
		}

		return buffer.toString();
	}

	public static class TextLength implements FeatureFunction {

		@Override
		public List<Feature> apply(Feature feature) {

			String featureName = Feature.createName("len", feature.getName());
			Object featureValue = feature.getValue();
			if (featureValue instanceof String) {
				String value = featureValue.toString();
				return Collections.singletonList(new Feature(featureName, String.valueOf(value.length())));
			} else {
				return Collections.emptyList();
			}

		}

	}

	public static class StartEnd implements FeatureFunction {

		private boolean end;
		private String val;

		public StartEnd(String val, boolean end) {
			super();
			this.end = end;
			this.val = val;
		}

		@Override
		public List<Feature> apply(Feature feature) {

			String featureName = Feature.createName("ends", feature.getName());
			Object featureValue = feature.getValue();
			if (featureValue instanceof String) {
				String value = featureValue.toString();
				boolean ok = end ? value.endsWith(val) : value.startsWith(value);
				return Collections.singletonList(new Feature(featureName, String.valueOf(ok)));
			} else {
				return Collections.emptyList();
			}

		}

	}

	public static void main(String[] args) throws FileNotFoundException {
		String featureFileName = "src/main/resources/feature/features.xml";
		generateFeatureExtractors(featureFileName);
	}
}
