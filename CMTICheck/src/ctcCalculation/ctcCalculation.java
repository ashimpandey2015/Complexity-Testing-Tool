/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctcCalculation;

import cmticheck.Analysis;
import cmticheck.CodeContent;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Dell
 */
public class ctcCalculation {
     final private String ifREGX = "\\bif\\s*\\(.+\\)(\\s*|\\s*\\{)";
    final private String catchREGX = "\\bcatch\\s*\\(.+\\)(\\s*|\\s*\\{)";
    final private String operatorREGX = "( \\&\\& | \\|\\| | \\! | \\& | \\| | \\^ | \\~)";
    final private String loopREGX = "\\b(for|while)\\s*\\(.+\\)(\\s*|\\s*\\{)";
    final private String switchREGX = "\\bswitch\\s*\\(.+\\)(\\s*|\\s*\\{)";
    final private String caseREGX = "\\b(case\\s*.+:|default\\s*:)";
    final private String openBracketREGX = "\\{";
    final private String closeBracketREGX = "\\}";
    final private ArrayList<String> switchStatements = new ArrayList<String>();
    private ArrayList<CodeContent> copyResultSet = new ArrayList<CodeContent>(Analysis.coderesult.size());   
    private boolean switchFound = false;
    private int bracketCounter = 0;
    
    public ctcCalculation(){
        copyArrayList();
    } 

    public void calculateCtc(){

        Pattern ifPattern = Pattern.compile(ifREGX);
        Pattern loopPattern = Pattern.compile(loopREGX);
        Pattern lgOperatorsPattern = Pattern.compile(operatorREGX);
        Pattern catchPattern = Pattern.compile(catchREGX);
        Pattern switchPattern = Pattern.compile(switchREGX);
        Pattern casePattern = Pattern.compile(caseREGX);
        Pattern openBracketPattern = Pattern.compile(openBracketREGX);
        Pattern closeBracketPattern = Pattern.compile(closeBracketREGX);
        

        for(int i=0; i < Analysis.coderesult.size(); i++){
            
            int ctcValue = 0;
            int logMatchCount = 0;

            final String currentLine = Analysis.coderesult.get(i).getLineContent();
            System.out.println(currentLine);

            Matcher ifStatementMatcher = ifPattern.matcher(currentLine);
            Matcher logOperatorsMatcher = lgOperatorsPattern.matcher(currentLine);
            Matcher loopMatcher = loopPattern.matcher(currentLine);
            Matcher catchMatcher = catchPattern.matcher(currentLine);
            Matcher switchMatcher = switchPattern.matcher(currentLine);
            

            //Check if the statement contains a if statement
            if(ifStatementMatcher.find()){

                System.out.println("If match found");

                while(logOperatorsMatcher.find()){
                    logMatchCount++;
                }

                System.out.println("Log match count: " + logMatchCount);
                ctcValue = logMatchCount + 1;
                
                Analysis.coderesult.get(i).setCtcVal(ctcValue);
                
            }
            else if(loopMatcher.find()) { //Check if the statement contains a do | while | for loop

                System.out.println("Loop match found");

                while(logOperatorsMatcher.find()){
                    logMatchCount++;
                }

                System.out.println("Log match count: " + logMatchCount);
                ctcValue = ( logMatchCount + 1 ) * 2;
                Analysis.coderesult.get(i).setCtcVal(ctcValue);
            }
            else if(catchMatcher.find()){
                
                ctcValue = 1;
                Analysis.coderesult.get(i).setCtcVal(ctcValue);
            }
            else if(switchMatcher.find()){
                
                System.out.println("Switch detected");
                
                int bracketCounter = 0;
                int caseCounter = 0;
                ArrayList<Integer> cc = new ArrayList<Integer>();
                
                cc.add(i);
                
                for(int k=i; k < copyResultSet.size(); k++){
                    
                    String currentLine1 = copyResultSet.get(k).getLineContent();
                    System.out.println("Line " + k + "- " + currentLine1);
                    
                    Matcher caseMatcher = casePattern.matcher(currentLine1);
                    Matcher openBracketMatcher = openBracketPattern.matcher(currentLine1);
                    Matcher closeBracketMatcher = closeBracketPattern.matcher(currentLine1);
                    
                    if(openBracketMatcher.find()){
                        bracketCounter++;
                    }
                    
                    if(closeBracketMatcher.find() && bracketCounter > 1){
                        bracketCounter--;
                    }
                    
                    if(caseMatcher.find()){
                        caseCounter++;
                        cc.add(k);
                    }
                    
                    if(closeBracketMatcher.find() && bracketCounter == 1){
                        break;
                    }
                    
                }
                
                for(int j=0; j < cc.size(); j++){
                    
                    Analysis.coderesult.get(cc.get(j)).setCtcVal(caseCounter);
                }
                 
            }
            
            
            System.out.println("CTC VALUE: " + ctcValue);
        }
    }
    
    public void copyArrayList(){
        
        for(int i=0; i < Analysis.coderesult.size(); i++){
            
            copyResultSet.add(Analysis.coderesult.get(i));
        }
    }
}
