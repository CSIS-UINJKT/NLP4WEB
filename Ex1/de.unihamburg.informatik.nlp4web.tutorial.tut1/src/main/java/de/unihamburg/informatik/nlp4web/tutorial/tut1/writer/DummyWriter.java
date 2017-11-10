package de.unihamburg.informatik.nlp4web.tutorial.tut1.writer;

import java.util.Iterator;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasConsumer_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.util.Level;



public class DummyWriter
	extends JCasConsumer_ImplBase
{
    
    public static final String LF = System.getProperty("line.separator");
    

	public void process(JCas jcas)
		throws AnalysisEngineProcessException
	{
		StringBuilder sb = new StringBuilder();
		sb.append("=== CAS ==="); sb.append(LF);
		sb.append("-- Document Text --"); sb.append(LF);
		sb.append(jcas.getDocumentText()); sb.append(LF);
		sb.append("-- Annotations --"); sb.append(LF);
		
		for (Iterator<Annotation> i = jcas.getAnnotationIndex().iterator(); i.hasNext(); )
		{
			Annotation a = i.next();
			sb.append("[" + a.getType().getShortName() + "] ");
			sb.append("(" + a.getBegin() + ", " + a.getEnd() + ") ");
			sb.append(a.getCoveredText());
			sb.append(LF);
		}
		
		sb.append(LF);
		
        getContext().getLogger().log(Level.INFO, sb.toString());
	}
}
