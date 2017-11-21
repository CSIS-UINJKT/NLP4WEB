package de.unihamburg.informatik.nlp4web.tutorial.tut4.reader;

import java.util.Collection;
import java.util.List;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;

import de.tudarmstadt.ukp.dkpro.core.api.lexmorph.type.pos.NN;
import de.tudarmstadt.ukp.dkpro.core.api.syntax.type.constituent.NP;
import de.unihamburg.informatik.nlp4web.tutorial.tut4.type.CNoun;

/**
 * Cleans the noun phrases like "other animals" to "animals".
 *
 * @author mirco
 *
 */
public class RemoveOtherAnd extends JCasAnnotator_ImplBase {

	@Override
	public void process(JCas jcas) throws AnalysisEngineProcessException {
		Collection<NP> npChunks = JCasUtil.select(jcas, NP.class);
		for (NP np : npChunks) {

			List<NN> nn = JCasUtil.selectCovered(NN.class, np);
			for (NN noun : nn) {

				CNoun cAnn = new CNoun(jcas, noun.getBegin(), noun.getEnd());
				cAnn.addToIndexes();

			}

		}

	}

}
