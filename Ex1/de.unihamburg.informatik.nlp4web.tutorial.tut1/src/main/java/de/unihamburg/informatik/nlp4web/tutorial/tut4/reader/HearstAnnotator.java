package de.unihamburg.informatik.nlp4web.tutorial.tut4.reader;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.PeekingIterator;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence;
import de.unihamburg.informatik.nlp4web.tutorial.tut4.type.CNoun;
import de.unihamburg.informatik.nlp4web.tutorial.tut4.type.HearstAnnotation;

public class HearstAnnotator extends JCasAnnotator_ImplBase {

	@Override
	public void process(JCas jcas) throws AnalysisEngineProcessException {

		Collection<Sentence> sentences = JCasUtil.select(jcas, Sentence.class);
		for (Sentence sentence : sentences) {

			PeekingIterator<CNoun> nps = Iterators.peekingIterator(JCasUtil.selectCovered(CNoun.class, sentence).iterator());
			while (nps.hasNext()) {
				Annotation first = nps.next();
				Annotation preceding = Iterables.getLast(JCasUtil.selectPreceding(Annotation.class, first, 1), null);

				if (nps.hasNext()) {
					Annotation second = nps.peek();
					List<Object> original = JCasUtil.selectBetween(Annotation.class, first, second).stream()
							.map(a -> a.getCoveredText()).collect(Collectors.toList());
					String inBetween = Joiner.on(" ").join(new LinkedHashSet<>(original));

					if (inBetween.contains("such as")) {
						addAnnotation(jcas, first, second, "NP such as NP");
					} else if (inBetween.contains(", including")) {
						addAnnotation(jcas, first, second, "NP, including NP");
					} else if (inBetween.contains(", especially")) {
						addAnnotation(jcas, first, second, "NP, especially NP");
					} else if (inBetween.contains("and other") || inBetween.contains("or other")) {
						HearstAnnotation ann = addAnnotation(jcas, second, first, "NP and/or other NP");
						ann.setBegin(first.getBegin());
						ann.setEnd(second.getEnd());
					} else if ((preceding != null) && preceding.getCoveredText().toLowerCase().equals("such")
							&& inBetween.contains("as")) {
						HearstAnnotation ann = addAnnotation(jcas, first, second, "such NP as NP");
						ann.setBegin(preceding.getBegin());

					}

				}
			}
		}
	}

	private HearstAnnotation addAnnotation(JCas jcas, Annotation hyper, Annotation hypo, String type) {
		HearstAnnotation hearst2 = new HearstAnnotation(jcas);
		hearst2.setBegin(hyper.getBegin());
		hearst2.setEnd(hypo.getEnd());
		hearst2.setHyperonym(hyper.getCoveredText().toLowerCase());
		hearst2.setHyponym(hypo.getCoveredText().toLowerCase());
		hearst2.setTypeOf(type);
		hearst2.addToIndexes();
		return hearst2;

	}
}
