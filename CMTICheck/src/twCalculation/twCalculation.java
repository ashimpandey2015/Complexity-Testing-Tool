/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twCalculation;

import cmticheck.Analysis;
import cmticheck.CodeContent;

/**
 *
 * @author Dell
 */
public class twCalculation {
    
    public void calculateTWforProgramStatement(){
    
     for (CodeContent codec : Analysis.coderesult){
        System.out.println("Ci -> "+codec.getCiVal() + " CnC -> "+codec.getCncVal()+" Ctc Value ->"+codec.getCtcVal());
        codec.setTwVal(codec.getCiVal()+codec.getCncVal()+codec.getCtcVal());
    }
    }
}
    

