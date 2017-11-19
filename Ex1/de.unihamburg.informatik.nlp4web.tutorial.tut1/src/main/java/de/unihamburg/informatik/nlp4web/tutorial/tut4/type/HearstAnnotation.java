

/* First created by JCasGen Sun Nov 19 21:53:24 CET 2017 */
package de.unihamburg.informatik.nlp4web.tutorial.tut4.type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Sun Nov 19 21:53:27 CET 2017
 * XML source: /home/alvin/Documents/CSIS-UINJKT/NLP4WEB/Ex1/de.unihamburg.informatik.nlp4web.tutorial.tut1/src/main/resources/desc/type/HearstAnnotation.xml
 * @generated */
public class HearstAnnotation extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(HearstAnnotation.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected HearstAnnotation() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public HearstAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public HearstAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public HearstAnnotation(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: Hyponym

  /** getter for Hyponym - gets 
   * @generated
   * @return value of the feature 
   */
  public String getHyponym() {
    if (HearstAnnotation_Type.featOkTst && ((HearstAnnotation_Type)jcasType).casFeat_Hyponym == null)
      jcasType.jcas.throwFeatMissing("Hyponym", "de.unihamburg.informatik.nlp4web.tutorial.tut4.type.HearstAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((HearstAnnotation_Type)jcasType).casFeatCode_Hyponym);}
    
  /** setter for Hyponym - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setHyponym(String v) {
    if (HearstAnnotation_Type.featOkTst && ((HearstAnnotation_Type)jcasType).casFeat_Hyponym == null)
      jcasType.jcas.throwFeatMissing("Hyponym", "de.unihamburg.informatik.nlp4web.tutorial.tut4.type.HearstAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((HearstAnnotation_Type)jcasType).casFeatCode_Hyponym, v);}    
   
    
  //*--------------*
  //* Feature: Hyperonym

  /** getter for Hyperonym - gets 
   * @generated
   * @return value of the feature 
   */
  public String getHyperonym() {
    if (HearstAnnotation_Type.featOkTst && ((HearstAnnotation_Type)jcasType).casFeat_Hyperonym == null)
      jcasType.jcas.throwFeatMissing("Hyperonym", "de.unihamburg.informatik.nlp4web.tutorial.tut4.type.HearstAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((HearstAnnotation_Type)jcasType).casFeatCode_Hyperonym);}
    
  /** setter for Hyperonym - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setHyperonym(String v) {
    if (HearstAnnotation_Type.featOkTst && ((HearstAnnotation_Type)jcasType).casFeat_Hyperonym == null)
      jcasType.jcas.throwFeatMissing("Hyperonym", "de.unihamburg.informatik.nlp4web.tutorial.tut4.type.HearstAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((HearstAnnotation_Type)jcasType).casFeatCode_Hyperonym, v);}    
   
    
  //*--------------*
  //* Feature: TypeOf

  /** getter for TypeOf - gets 
   * @generated
   * @return value of the feature 
   */
  public String getTypeOf() {
    if (HearstAnnotation_Type.featOkTst && ((HearstAnnotation_Type)jcasType).casFeat_TypeOf == null)
      jcasType.jcas.throwFeatMissing("TypeOf", "de.unihamburg.informatik.nlp4web.tutorial.tut4.type.HearstAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((HearstAnnotation_Type)jcasType).casFeatCode_TypeOf);}
    
  /** setter for TypeOf - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setTypeOf(String v) {
    if (HearstAnnotation_Type.featOkTst && ((HearstAnnotation_Type)jcasType).casFeat_TypeOf == null)
      jcasType.jcas.throwFeatMissing("TypeOf", "de.unihamburg.informatik.nlp4web.tutorial.tut4.type.HearstAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((HearstAnnotation_Type)jcasType).casFeatCode_TypeOf, v);}    
  }

    