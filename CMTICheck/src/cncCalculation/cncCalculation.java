/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cncCalculation;

import cmticheck.Analysis;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Dell
 */
public class cncCalculation {
   
    ArrayList<Integer> cncPointsArray;
    
    Integer brackets = 0;
    
    
    //Regex Strings
    String bracketsREGX = "\\b((if|while|for|do)(\\s+|\\().*\\{)";
    String singleLineREGX = "^(\\s*\\}\\s*)|^(\\s*)$";
    String openBracketsREGX = "\\{";
    String closedBracketREGX = "\\}";

     //Initialize LINE_OF_CODE 
    public cncCalculation() {//ArrayList<ProgramStatement> lineOfCodeList
        //this.lineOfCodeList = lineOfCodeList;
        cncPointsArray = new ArrayList<Integer>(Analysis.coderesult.size());
    }
    
    public void incrementBracketCount() {
        brackets++;
        System.out.println("increment count CNC");
    }

    public void decrementBracketCount() {
        
        if(this.brackets > 0){
            brackets--;
        }
        
        System.out.println("Decrement count CNC");

    }
    
    public ArrayList<Integer> coreBracketMapper() {
        
        //REGX PatternCompiler Heads
        Pattern bracketsPattern = Pattern.compile(bracketsREGX);
        Pattern bracketsSingle = Pattern.compile(singleLineREGX);
        Pattern bracketsClosed = Pattern.compile(closedBracketREGX);

        // Check each line for Cnc
        for (int i = 0; i < Analysis.coderesult.size(); i++) {
            int count = 0;
            
            //Fetch String Content
            String line = Analysis.coderesult.get(i).getLineContent();
            
            // check for conditions and loops
            Matcher matcher = bracketsPattern.matcher(line);
            while (matcher.find()) {
                incrementBracketCount();
                
            }
            
            //equaling the count to brackets
            count = brackets;

            Matcher close_m = bracketsClosed.matcher(line);
            while (close_m.find()) {
                decrementBracketCount();
            }
            
            //equaling the count to brackets
            count = brackets;
            
            // check for lines with brackets or empty line
            Matcher singleline = bracketsSingle.matcher(line);
            if (singleline.find()) {
                count = 0;
            }
            cncPointsArray.add(count);
        }
        
        setCnCValuesToHashMap();
        return cncPointsArray;
    }
    
     public int getTotalCncPoints() {
        int total = 0;

        for (int i = 0; i < cncPointsArray.size(); i++) {
            total += cncPointsArray.get(i);
        }

        return total;
    }
    
    public void setCnCValuesToHashMap() {
        for (int i = 0; i < cncPointsArray.size(); i++) {
            Analysis.coderesult.get(i).setCncVal(cncPointsArray.get(i));
        }
    }
}
