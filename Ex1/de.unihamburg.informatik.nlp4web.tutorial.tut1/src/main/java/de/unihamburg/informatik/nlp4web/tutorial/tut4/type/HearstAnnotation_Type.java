
/* First created by JCasGen Sun Nov 19 21:53:24 CET 2017 */
package de.unihamburg.informatik.nlp4web.tutorial.tut4.type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Sun Nov 19 21:53:27 CET 2017
 * @generated */
public class HearstAnnotation_Type extends Annotation_Type {
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = HearstAnnotation.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("de.unihamburg.informatik.nlp4web.tutorial.tut4.type.HearstAnnotation");
 
  /** @generated */
  final Feature casFeat_Hyponym;
  /** @generated */
  final int     casFeatCode_Hyponym;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getHyponym(int addr) {
        if (featOkTst && casFeat_Hyponym == null)
      jcas.throwFeatMissing("Hyponym", "de.unihamburg.informatik.nlp4web.tutorial.tut4.type.HearstAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_Hyponym);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setHyponym(int addr, String v) {
        if (featOkTst && casFeat_Hyponym == null)
      jcas.throwFeatMissing("Hyponym", "de.unihamburg.informatik.nlp4web.tutorial.tut4.type.HearstAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_Hyponym, v);}
    
  
 
  /** @generated */
  final Feature casFeat_Hyperonym;
  /** @generated */
  final int     casFeatCode_Hyperonym;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getHyperonym(int addr) {
        if (featOkTst && casFeat_Hyperonym == null)
      jcas.throwFeatMissing("Hyperonym", "de.unihamburg.informatik.nlp4web.tutorial.tut4.type.HearstAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_Hyperonym);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setHyperonym(int addr, String v) {
        if (featOkTst && casFeat_Hyperonym == null)
      jcas.throwFeatMissing("Hyperonym", "de.unihamburg.informatik.nlp4web.tutorial.tut4.type.HearstAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_Hyperonym, v);}
    
  
 
  /** @generated */
  final Feature casFeat_TypeOf;
  /** @generated */
  final int     casFeatCode_TypeOf;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getTypeOf(int addr) {
        if (featOkTst && casFeat_TypeOf == null)
      jcas.throwFeatMissing("TypeOf", "de.unihamburg.informatik.nlp4web.tutorial.tut4.type.HearstAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_TypeOf);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setTypeOf(int addr, String v) {
        if (featOkTst && casFeat_TypeOf == null)
      jcas.throwFeatMissing("TypeOf", "de.unihamburg.informatik.nlp4web.tutorial.tut4.type.HearstAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_TypeOf, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public HearstAnnotation_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_Hyponym = jcas.getRequiredFeatureDE(casType, "Hyponym", "uima.cas.String", featOkTst);
    casFeatCode_Hyponym  = (null == casFeat_Hyponym) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Hyponym).getCode();

 
    casFeat_Hyperonym = jcas.getRequiredFeatureDE(casType, "Hyperonym", "uima.cas.String", featOkTst);
    casFeatCode_Hyperonym  = (null == casFeat_Hyperonym) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Hyperonym).getCode();

 
    casFeat_TypeOf = jcas.getRequiredFeatureDE(casType, "TypeOf", "uima.cas.String", featOkTst);
    casFeatCode_TypeOf  = (null == casFeat_TypeOf) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_TypeOf).getCode();

  }
}



    