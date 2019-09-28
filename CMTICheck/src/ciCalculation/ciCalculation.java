/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ciCalculation;




import cmticheck.Analysis;
import static cmticheck.Analysis.coderesult;
import cmticheck.CodeContent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

/**
 *
 * @author Dell
 */
public class ciCalculation {

    public static String GetStringsBetwen2Chars(String inputt, String STARTchar, String ENDchar) {
        try {
            int start = inputt.indexOf(STARTchar);
            if (start != -1) {
                int end = inputt.indexOf(ENDchar, start + STARTchar.length());
                if (end != -1) {
                    return inputt.substring(start + STARTchar.length(), end);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inputt; // return null; || return "" ;
    }

    public static int calc_ci() throws IOException {
        File f1 = new File(Analysis.RFilePath); //Creation of File Descriptor for input file
        
        String[] Words = null;
        String[] Words1 = null;//Intialize the word Array
        
        FileReader Frr = new FileReader(f1);  //Creation of File Reader object
        BufferedReader Brr = new BufferedReader(Frr); //Creation of BufferedReader object
        
        String s;        
        String start_Char = "implements ";
        String end_Char = "{";
        String inputt = "implements";
        String inputt1 = "extends";// Inputt word to be searched
        String inputt2 = "class";
        
        int count = 0;   //Intialize the word to zero
        int ci=0;
        
        while ((s = Brr.readLine()) != null) //Reading Content Frrom the file
        {
            Words = s.split(" ");  //Split the word using space
            //String Output = GetStringsBetwen2Chars(s,start_char,end_char);
            ///Words = Output.split(" ");
            for (String word : Words) {
                if (word.equals(inputt)) //Search for the given word
                {
                    String Output = GetStringsBetwen2Chars(s, start_Char, end_Char);
                    Words1 = Output.split(" ");
                    for (String word1 : Words1) {
                        count++;    //If Present increase the count by one                 
                    }
                }
                if (word.equals(inputt2)) //Search for the given word
                {
                    count++;
                }
                if (word.equals(inputt1)) {
                    count = count + 2;
                }

            }
            int cci = count + 1;//initializing the cci value
            ci=cci;

        }
        if (count != 0) //Check for count not equal to zero
        {
            System.out.println("Ci VALUE:: " + ci );

        } else {
            System.out.println("Wrong Ci Value");
        }

        Frr.close();
        //update ci value to global result set
        
        for(CodeContent cc : coderesult){
            cc.setCiVal(ci);
            //System.out.println("CI value:"+ps.getCiValue());
        }
        return ci;
    }

    public static void calc_line() throws IOException {
        File f1 = new File(Analysis.RFilePath); //Creation of File Descriptor for inputt file
        String[] Words = null;
        String[] Words1 = null;//Intialize the word Array
        FileReader Frr = new FileReader(f1);  //Creation of File Reader object
        BufferedReader Brr = new BufferedReader(Frr); //Creation of BufferedReader object
        //String a=Brr.lines().collect(Collectors.joining());
        //String b=a.replaceAll("\"/\\\\*(?:.|[\\\\n\\\\r])*?\\\\*/\"","");
        //System.out.println("removed new file//  " + b);
        String s;

        int count = 0;   //Intialize the word to zero
        int count1 = 0;

        while ((s = Brr.readLine()) != null) //Reading Content Frrom the file
        {
            Words = s.split(" ");  //Split the word using space

            innerloop:
            for (String word : Words) {
                if (word.equals("}") || word.equals("class") || word.equals("else {") || word.equals("import") || word.equals("package") )// || word.equals("} catch")) //Search for the given word
                {
                    //System.out.println("SKIPPING  " + s);
                    count1++;
                }
            }
            count++;
        }
        int total = count - count1;
        if (total != 0) //Check for count not equal to zero
        {
            //System.out.println("Line count" + total);
        } else {
            //System.out.println("No lines");
        }

        Frr.close();
    }
}


