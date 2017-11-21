package de.unihamburg.informatik.nlp4web.tutorial.tut4.writer;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasConsumer_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;

import com.google.common.collect.Ordering;

//import de.unihamburg.informatik.nlp4web.tutorial.tut4.writer.HearstWriter.Pair;
import de.unihamburg.informatik.nlp4web.tutorial.tut4.type.HearstAnnotation;

public class HearstWriter extends JCasConsumer_ImplBase {
	public static final String MODE = "MODE";
	public final static String MOST_PRODUCTIVE = "MOST_PRODUCTIVE_PATTERNS";
	public final static String MOST_FOUND = "MOST_FOUND";
	@ConfigurationParameter(name = MODE, description = "Output", mandatory = true, defaultValue = MOST_PRODUCTIVE)
	private String mode;

	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		if (mode.equals(MOST_FOUND)) {
			mostFound(aJCas);
		} else {
			mostProductive(aJCas);
		}

	}

	private void mostProductive(JCas jcas) {
		Map<String, Integer> found = new HashMap<String, Integer>();
		for (HearstAnnotation hearst : JCasUtil.select(jcas, HearstAnnotation.class)) {

			found.put(hearst.getTypeOf(), found.getOrDefault(hearst.getTypeOf(), 0) + 1);

		}
		LinkedHashMap<String, Integer> sorted = found.entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Ordering.natural().reverse()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v1, LinkedHashMap::new));
		System.err.println("Count\tHearst pattern");
		for (Entry<String, Integer> entry : sorted.entrySet()) {
			System.err.println(entry.getValue() + "\t" + entry.getKey());
		}

	}

	private void mostFound(JCas jcas) {
		Map<Pair, Integer> found = new HashMap<Pair, Integer>();
		for (HearstAnnotation hearst : JCasUtil.select(jcas, HearstAnnotation.class)) {
			Pair key = new Pair(hearst.getHyponym(), hearst.getHyperonym());
			found.put(key, found.getOrDefault(key, 0) + 1);
		}
		LinkedHashMap<Pair, Integer> sorted = found.entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Ordering.natural().reverse()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v1, LinkedHashMap::new));
		System.err.println("Count\tHyponym\tHyperonym");
		for (Entry<Pair, Integer> entry : sorted.entrySet()) {
			System.err.println(entry.getValue() + "\t" + entry.getKey().hyponym + "\t" + entry.getKey().hyperonym);
		}
	}

	private final class Pair {
		public final String hyponym;
		public final String hyperonym;

		public Pair(String hyponym, String hyperonym) {
			super();
			this.hyponym = hyponym;
			this.hyperonym = hyperonym;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj instanceof Pair) {
				return new EqualsBuilder().append(hyponym, ((Pair) obj).hyponym).append(hyponym, ((Pair) obj).hyponym)
						.append(hyperonym, ((Pair) obj).hyperonym).isEquals();
			}
			return false;
		}

		@Override
		public int hashCode() {
			return new HashCodeBuilder().append(hyperonym).append(hyponym).toHashCode();
		}
	}

}