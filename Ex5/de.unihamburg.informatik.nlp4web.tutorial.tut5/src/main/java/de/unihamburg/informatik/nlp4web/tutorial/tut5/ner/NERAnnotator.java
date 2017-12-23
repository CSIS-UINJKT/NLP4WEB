package de.unihamburg.informatik.nlp4web.tutorial.tut5.ner;

import static org.apache.uima.fit.util.JCasUtil.select;
import static org.apache.uima.fit.util.JCasUtil.selectCovered;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.cleartk.ml.CleartkSequenceAnnotator;
import org.cleartk.ml.Instance;
import org.cleartk.ml.feature.extractor.CleartkExtractor;
import org.cleartk.ml.feature.extractor.FeatureExtractor1;

import com.thoughtworks.xstream.XStream;

import de.tudarmstadt.ukp.dkpro.core.api.ner.type.NamedEntity;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.unihamburg.informatik.nlp4web.tutorial.tut5.xml.XStreamFactory;

public class NERAnnotator extends CleartkSequenceAnnotator<String> {

	public static final String PARAM_FEATURE_EXTRACTION_FILE = "FeatureExtractionFile";

	/**
	 * if a feature extraction/context extractor filename is given the xml file
	 * is parsed and the features are used, otherwise it will not be used
	 */
	@ConfigurationParameter(name = PARAM_FEATURE_EXTRACTION_FILE, mandatory = false)
	private String featureExtractionFile = null;

	List<FeatureExtractor1<Token>> features = new ArrayList<>();
	private File resultFile = new File(ExecuteNER.OUTPUT_FILE);

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(UimaContext context) throws ResourceInitializationException {
		super.initialize(context);

		XStream xstream = XStreamFactory.createXStream();
		features = (List<FeatureExtractor1<Token>>) xstream.fromXML(new File(featureExtractionFile));
		try {
			FileUtils.write(resultFile, "");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public void process(JCas jCas) throws AnalysisEngineProcessException {
		try {

			for (Sentence sentence : select(jCas, Sentence.class)) {
				List<Instance<String>> instances = new ArrayList<Instance<String>>();
				List<Token> tokens = selectCovered(jCas, Token.class, sentence);
				for (Token token : tokens) {
					Instance<String> instance = new Instance<String>();

					for (FeatureExtractor1<Token> extractor : this.features) {
						if (extractor instanceof CleartkExtractor) {
							instance.addAll((((CleartkExtractor<Token, Token>) extractor).extractWithin(jCas, token,
									sentence)));
						} else {
							instance.addAll(extractor.extract(jCas, token));
						}
					}

					if (this.isTraining()) {
						NamedEntity goldNE = JCasUtil.selectCovered(jCas, NamedEntity.class, token).get(0);
						instance.setOutcome(goldNE.getValue());
					}
					// add the instance to the list !!!
					instances.add(instance);
				}
				// differentiate between training and classifying
				if (this.isTraining()) {
					this.dataWriter.write(instances);
				} else {
					List<String> namedEntities = this.classify(instances);
					int i = 0;
					for (Token token : tokens) {
						NamedEntity namedEntity = new NamedEntity(jCas, token.getBegin(), token.getEnd());
						namedEntity.setValue(namedEntities.get(i++));
						namedEntity.addToIndexes();
						NamedEntity goldNE = JCasUtil.selectCovered(jCas, NamedEntity.class, token).get(0);
						FileUtils.write(resultFile, token.getCoveredText() + " " + goldNE.getValue() + " "
								+ namedEntity.getValue() + System.lineSeparator(), true);

					}
					FileUtils.write(resultFile, System.lineSeparator(), true);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
