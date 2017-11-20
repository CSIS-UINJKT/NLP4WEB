package de.unihamburg.informatik.nlp4web.tutorial.tut4.starterCode;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngine;

import java.io.IOException;

import org.apache.uima.UIMAException;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.fit.factory.JCasFactory;
import org.apache.uima.fit.pipeline.SimplePipeline;
import org.apache.uima.jcas.JCas;

import de.tudarmstadt.ukp.dkpro.core.stanfordnlp.StanfordLemmatizer;
import de.tudarmstadt.ukp.dkpro.core.stanfordnlp.StanfordParser;
import de.tudarmstadt.ukp.dkpro.core.stanfordnlp.StanfordPosTagger;
import de.tudarmstadt.ukp.dkpro.core.stanfordnlp.StanfordSegmenter;
import de.unihamburg.informatik.nlp4web.tutorial.tut4.writer.ChunkWriter;
import de.unihamburg.informatik.nlp4web.tutorial.tut4.writer.LemmaWriter;
import de.unihamburg.informatik.nlp4web.tutorial.tut4.writer.POSWriter;

public class Pipeline4 {
	public static void main(String[] args) throws UIMAException, IOException {

		JCas jcas = JCasFactory.createJCas();

		jcas.setDocumentLanguage("en");

		jcas.setDocumentText(
				"The head is enclosed in a hard, heavily sclerotized, unsegmented, exoskeletal head capsule, or epicranium, which contains most of the sensing organs, "
						+ "including the antennae, ocellus or eyes, and the mouthparts. Of all the insect orders, Orthoptera displays the most features found in other insects, "
						+ "including the sutures and sclerites. Here, the vertex, or the apex (dorsal region), is situated between the compound eyes for insects with a "
						+ "hypognathous and opisthognathous head. In prognathous insects, the vertex is not found between the compound eyes, but rather, where the ocelli are normally."
						+ " This is because the primary axis of the head is rotated 90° to become parallel to the primary axis of the body. In some species, this region"
						+ " is modified and assumes a different name.");
	}
}
