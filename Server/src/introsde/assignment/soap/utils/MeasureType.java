/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package introsde.assignment.soap.utils;

import java.util.List;

/**
 *
 * @author benhur
 */
//@XmlRootElement(name="measureType")
public class MeasureType {

  private List<MeasureTyp> measure;

  public void setMeasure(List<MeasureTyp> measure) {
    this.measure = measure;
  }

  public List<MeasureTyp> getMeasure() {
    return measure;
  }

} 