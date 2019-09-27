
package spmFull;

import spmsize.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.TransferHandler;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Ashim
 */
public class spmFull extends javax.swing.JFrame {

    static void setIcon(ImageIcon img) {
        throw new UnsupportedOperationException("Not supported."); //To changesss the body of the generated sssmethods, please choose Tools | Templates.
    }

                 int TCsCountNo = 0;
                 int TCiCountNo= 0;
                 int TCtCountNo =0;      
                 int TCrCountNo =0;
                 int TwCountNo=0;
                 int CpsCountNo=0;
                 int CpCountNo=0; 
                 
                 int keywordsCountNo = 0;
                 int arithOCountNo= 0;
                 int relatOCountNo= 0;
                 int logicOCountNo= 0;
                 int assOCountNo= 0;
                 int bitwOCountNo= 0;
                 int manipCountNo= 0;
                 int miscOCountNo= 0;
                 int numberCountNo= 0;
                 int textCountNo =0;
                 int cppCountNo = 0;
                 int ccountNo = 0;
                 int specialCountNo =0;
                 
                 //nesting for the
                int incrementNoForIf = 0;
                int incrementNoForOPerators = 0;
                int incrementNoForIterative = 0;
                int incrementNoForCatch = 0;
                int incrementNoForSwitch = 0;
                int countCatchNo = 0;
                int countCaseNo = 0;
                int iLoopNo = 0;
                String lineNo;
                String[] codeLineNo = null;
                
                
                
             // This will reference to alll the one lineNo at a time
             String lineNo4 = null;
             String lineNo3 =null;
             String lineNo2 =null;
             String lineNo1=null;
         
             
             String[] key = new String[] { "long","fibonacci","number", "void", "double", "int", "float", "String", "cout","cin",
                           "if", "for", "while","do-while","switch","case", "main","System","out","println","args","operator","bark","count","accessFiles","FileNotFoundException","FileReader","f","catch","e","std","this","Dog","Animal"};
             int keywordSize = key.length;
             
             String[] inheritWord=new String[]{"extends","implements",":"};
             int inheritSize = inheritWord.length;
       
             String[] cppKey=new String[]{"&","*","new","delete","throw","throws"};
             int cppKeywordSize = cppKey.length;
             
             String[] arithmeticOperators = new String[] { "+", "-", "*", "%", "++", "--" };
             int arithOSize = arithmeticOperators.length;
             //divider has been seperated

             String[] realOperators = new String[] { "==", "!=", ">", "<", ">=", "<=" };
             int relatOSize = realOperators.length;
             

             String[] logicalOperators = new String[] { "&&", "||", "!" };
             int logicOSize = logicalOperators.length;
             

             String[] assigningOperators = new String[] { "=","+=", "-=", "*=", "/=", ">>>=", "|=", "&=", "<<=", ">>=", "%=",
                           "^=" };
             int assOSize = assigningOperators.length;
             

             String[] bitwiseeOperators = new String[] { "|" ,"^", "~", "<<", ">>", "<<<", ">>>" };
             int bitwOSize = bitwiseeOperators.length;
             
             String[] manipulate = new String[] { "\n", "endl"};
             int manipSize = manipulate.length;
             
             String[] miscellaneousz  = new String[] {  "->",".","::","~"};
             int miscOSize = miscellaneousz.length;
 
             String[] printwQ  = new String[] {};
             
             
             ArrayList<String> printQ = new ArrayList<String>();
             // create object of table and table model
            DefaultTableModel model;
            



    /**
     * Creates new form dragDrop
     */
    public spmFull() {
        initComponents();
        modifyLabel();
        model = new DefaultTableModel();
        display.setModel(model);
        model.addColumn("Line");
        model.addColumn("Statement");
        model.addColumn("Key words");
        model.addColumn("Cs");
        model.addColumn("Ci");
        model.addColumn("Ct");
        model.addColumn("Tw");
        model.addColumn("Cps");
        model.addColumn("Cp");
        
        
        

        display.getColumn("Line").setMaxWidth(30);
        display.getColumn("Statement").setMaxWidth(250);
        display.getColumn("Key words").setMaxWidth(400);
        display.getColumn("Cs").setMaxWidth(30);
        display.getColumn("Ci").setMaxWidth(30);
        display.getColumn("Ct").setMaxWidth(30);
        display.getColumn("Tw").setMaxWidth(30);
        display.getColumn("Cps").setMaxWidth(30);
        display.getColumn("Cp").setMaxWidth(30);
    }

    public void modifyLabel(){
        
        //#####drag and drop implementation //#####
        TransferHandler th = new TransferHandler(){
            
        public boolean canImport(JComponent comp,DataFlavor[] transferFlavors){
            return true;
        } 
       
        public boolean importData(JComponent comp,Transferable t){
            try {
                List<File> files = (List<File>)  t.getTransferData(DataFlavor.javaFileListFlavor);
                for (File file : files){
                System.out.println(file.getName());
                //rest of the codes
                if(files.size()==1){
                    File f = files.get(0);
                    dragContainer.setIcon(new ImageIcon(f.getPath()));
                    
                    //#####display the file path//#####
                    String fileName = f.getPath();
                    String path = f.getPath();
                    jLabel18.setText(path);
                    
                    //#####display the file name//##### 
                    String fname = f.getName();
                    int pos = fname.lastIndexOf(".");
                    if (pos > 0) {
                        fname = fname.substring(0, pos);
                        jLabel14.setText(fname);
                    }
                   
                  //#####display the file extension//#####
                   int q = fileName.lastIndexOf('.');
                    if (q >= 0) {
                        String extension = fileName.substring(q+1);
                        jLabel16.setText(extension);
                    }
                    
                    //##### FileReader reads the text files in the default encoding//#####
                    FileReader fileReader = new FileReader(fileName);
                    

                    
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    int lineCounter =0;
                    
                    //##### A line instancex is  created andc looped for ckeywords operators snested conditions//#####
                    while ((lineNo = bufferedReader.readLine()) != null) {
                        
                 
                        lineCounter++;
                        int TCs =0;
                        int TCi =0;
                        int TCt =0;
                        
                        int TCr =0;
                        int Tw=0;
                        int Cps=0;
                        int Cp=0; 
                        
                        
                        //displayd lineNo in thes table model withouta alteration
                        String displayLine = lineNo;
                         
                         //refining all the files 
                        lineNo = lineNo.replaceAll("//.*$","").replaceAll("[(]"," ").replaceAll("[)]"," ").replaceAll("[;]","").replaceAll("\\[|\\]","").replaceAll("count"," count ").replaceAll("[.]"," . ");            
                        
                        //refining all the cpp files  
                        lineNo = lineNo.replaceAll("[::]"," :: ").replaceAll("[*]"," * ").replaceAll("[==]"," == ");
                        
                        ArrayList<String> printQ = new ArrayList<String>();
                        
                        
                       
                            //###############Loop for C++ inheritance Keywords ###############
                        
                            Pattern pattern10 = Pattern.compile("implements(.*)"); 
                            Matcher matcher10 = pattern10.matcher(lineNo);
                            
                            while (matcher10.find()) { 
                                                         
                                System.out.println("contains the implementaions-----------------------" + matcher10.group());
                                String s = matcher10.group(); 
                                s =s.replaceAll("[,]"," ").replaceAll("[{]"," ").replaceAll("[.]"," ");
                                System.out.println("contains the implementaions-----------------------" +s);
                                String[] implClass = s.split(" ");
 
                                for (int a = 0; a < implClass.length; a++) {
                                    System.out.println("contains the implementaions-----------------------" + implClass[a]);
                                
                                }
                                //keyword count
                                TCi = implClass.length;
                                
                                printQ.add(matcher10.group());
                            }
                               
                            
                             Pattern pattern11 = Pattern.compile("(?<=implements).*"); 
                            Matcher matcher11 = pattern11.matcher(lineNo);
                            
                            while (matcher10.find()) { 
                                                         
                                System.out.println("contains the parent classes-----------------------" + matcher11.group());
                                String s = matcher10.group(); 
                                s =s.replaceAll("[,]"," ").replaceAll("[{]"," ").replaceAll("[.]"," ");
                                String[] extClass = s.split(" ");
                                
                                for (int a = 0; a < extClass.length; a++) {
                                    System.out.println("contains the parent classes-----------------------" + extClass[a]);
                                
                                }
                                //keyword count 
                                TCi= extClass.length ;                         
                                printQ.add(matcher10.group());
                            }
                        
                             //###############Loops for  Size Complexity   ###############
                             
                             
                              //###############Loop for key ###############
                            for (int k = 0; k < keywordSize; k++) {
                                 int a = 0;
                                 String[] split = lineNo.split(" ");

                                 for (a = 0; a < split.length; a++) {
                                   
                                        if (split[a].equals(key[k]) ) {
                                        System.out.println("contains keywords -----------------------" + key[k]);
                                        TCs++;
                                        keywordsCountNo++;
                                        printQ.add(split[a]);
                                        }
                                 
                                 }
                                 
                                 
                            }
                                                    
                             //###############Loop for key ###############                                      
                        
                            Pattern pattern = Pattern.compile("[0-9]+"); 
                            Matcher matcher = pattern.matcher(lineNo);
                            while (matcher.find()) {                            
                                System.out.println("Consists Numbers-----------------------" + matcher.group());
                                TCs++;
                                numberCountNo++;
                                printQ.add(matcher.group());
                            }
                           
                            
                                        
                                 
                           //###############Text is there with in double quotes###############                                         
                            Pattern pattern1 = Pattern.compile("\\\"(.*?)\\\""); 
                            Matcher matcher1 = pattern1.matcher(lineNo);                            
                           
                            while(matcher1.find() ) {
                                 if(matcher1.group(1)== null){
                               System.out.println("contains text within double quotation -----------------------" + matcher1.group(1));
                                TCs++;
                                textCountNo+=1;
                                printQ.add(matcher1.group(1));
                               }        
                                
                            }
                          
                        
                        //###############Loop for arith operators###############                                     
                           for (int k = 0; k < arithOSize; k++) {
                                 int a = 0;
                                 String[] split = lineNo.split(" ");

                                 for (a = 0; a < split.length; a++) {
                                        if (split[a].equals(arithmeticOperators[k])) {
                                               System.out.println("contains ariOperators -----------------------" + arithmeticOperators[k]);
                                               TCs++;
                                               printQ.add(split[a]);
                                        }
                                 }
                           } 
                           
                           
                        //###############Loop for relate  operators###############   
                       
                            for (int k = 0; k < relatOSize; k++) {
                                 int a = 0;
                                 String[] split = lineNo.split(" ");

                                 for (a = 0; a < split.length; a++) {
                                        if (split[a].equals(realOperators[k])) {
                                               System.out.println("contains relOperators -----------------------" +realOperators[k]);
                                               TCs++;
                                               printQ.add(split[a]);
                                        }
                                 }
                           } 
                            
                        //###############Loop for logic operators###############   

                               for (int k = 0; k < logicOSize; k++) {
                                 int a = 0;
                                 String[] split = lineNo.split(" ");

                                 for (a = 0; a < split.length; a++) {
                                        if (split[a].equals(logicalOperators[k])) {
                                               System.out.println("containss logicOperators -----------------------" +logicalOperators[k]);
                                               TCs++;
                                               printQ.add(split[a]);
                                        }
                                 }
                           } 
                           
                        //###############Loop for assign operators###############   
                           for (int k = 0; k < assOSize; k++){
                               int a = 0;
                                 String[] split = lineNo.split(" ");

                                 for (a = 0; a < split.length; a++) {
                                        if (split[a].equals(assigningOperators[k])) {
                                               System.out.println("containss assignOperators -----------------------" + assigningOperators[k]);
                                               TCs++;
                                               printQ.add(split[a]);
                                               
                                        }
                                 }    
                           }
                          
                         
                        //###############Loop for bit wise operators###############   
                           for (int k = 0; k < bitwOSize; k++) {
                                 int a = 0;
                                 String[] split = lineNo.split(" ");

                                 for (a = 0; a < split.length; a++) {
                                        if (split[a].equals(bitwiseeOperators[k])) {
                                               System.out.println("containss bitOperators -----------------------" + bitwiseeOperators[k]);
                                               TCs++;
                                               printQ.add(split[a]);
                                        }
                                 
                                }
                           }        
                
                           
                           
                        //###############Loop for cpp  keyword //###############   
                           for (int k = 0; k < cppKeywordSize; k++) {
                                 int a = 0;
                                 String[] split = lineNo.split(" ");

                                 for (a = 0; a < split.length; a++) {
                                        if (split[a].equals(cppKey[k])) {
                                               System.out.println("containss cppOperators -----------------------" + cppKey[k]);
                                               TCs+=2;
                                               printQ.add(split[a]);
                                        }
                                 
                                }
                           }        
                
                           
                            
                        //###############Loop for manip operators//###############   
                           for (int i = 0; i < manipSize; i++) {
                                 if (lineNo.contains(manipulate[i])) {
                                        System.out.println("contains manipulators -----------------------" + manipulate[i]);
                                        TCs++;
                                        printQ.add(manipulate[i]);
                                 }
                           }
                          
                        //###############Loop for misc operators//###############     
                           for (int i = 0; i < miscOSize; i++) {
                                 if (lineNo.contains(miscellaneousz[i])) {
                                        System.out.println("containss misc operators -----------------------" + miscellaneousz[i]);
                                        TCs++;
                                        printQ.add(miscellaneousz[i]);
                                 }
                           }
                           
                           
                    //###############Program for measuring nested complexity//###############           
                    ArrayList<String>array = new ArrayList<>(Arrays.asList(lineNo)) ;
        
                    String[] condi = {"if", "for", "while", "catch", "switch"};

                    for(iLoopNo = 0; iLoopNo < array.size(); iLoopNo++){
                           lineNo = array.get(iLoopNo);

                           //ignores comment lines
                           while(lineNo.contains("//")){
                               lineNo = array.get(iLoopNo = iLoopNo + 1);
                           }
                           //check for "if"
                           if(lineNo.contains(condi[0])){
                                String tempString; 
                                int i = 0 ;
                                while(i <= lineNo.length() - 2){
                                    tempString = String.valueOf(lineNo.charAt(i) + String.valueOf(lineNo.charAt(i+1)));
                                    if(tempString.equals("if")){
                                        incrementNoForIf++;
                                    }
                                    if(tempString.equals("&&") ||tempString.equals("||")){
                                        //System.the .out.println("Come .. to && or ||" );
                                        incrementNoForIf++;
                                        i += 2;
                                    }else if(lineNo.charAt(i) == '&' || lineNo.charAt(i) == '|'){
                                        //System.the .out.println("Come .. to else if " );
                                        incrementNoForIf++;
                                        i++;
                                    }else
                                        i++;

                                }
                                System.out.println("Count the value of if condition is" + incrementNoForIf );
                               
                                TCt +=incrementNoForIf ;
                                
                                
                            //check for the loop "for or while"
                           }else if(lineNo.contains(condi[1]) || lineNo.contains(condi[2])){
                               String theString;
                               String newLine = lineNo;
                               int i = 0;

                               while(i <= newLine.length() - 2){
                                   theString = String.valueOf(newLine.charAt(i) + String.valueOf(newLine.charAt(i+1)));
                                   if(newLine.contains(condi[1]) || newLine.contains(condi[2])){
                                       newLine = newLine.replaceFirst("for", "");
                                       newLine = newLine.replaceFirst("while", "");
                                       incrementNoForIterative += 2;
                                       i+=2;
                                   }
                                   if(theString.equals("&&")|| theString.equals("||")){
                                       incrementNoForIterative += 2; 
                                       i+=2;
                                   }else if(newLine.charAt(i) == '&' || newLine.charAt(i) == '|'){ //go to above if n doesnt come to here check it....
                                        incrementNoForIterative +=2;
                                        i++;
                                   }else
                                        i++;


                                }
                                    System.out.println("Count the value of incrementForIterative is " + incrementNoForIterative );
                                    
                                    TCt +=incrementNoForIterative;
                             //check for the "Catch"
                           }else if(lineNo.contains(condi[3])){
                               incrementNoForCatch++;
                               System.out.println("Count value " + incrementNoForCatch );
                                TCt +=incrementNoForCatch;
                               
                            //check for the switch   
                           }else if(lineNo.contains(condi[4])){
                               int n = 0;
                               while(!lineNo.contains("default")){
                                   lineNo = array.get(iLoopNo = iLoopNo + 1);
                                   if(!lineNo.contains("//") &&lineNo.contains("case")){ 
                                       n++;
                                   }
                               }
                               incrementNoForSwitch = n;
                               System.out.println("Count value is " + incrementNoForSwitch );
                               TCt +=incrementNoForSwitch;
                           }

                       }
                           
                           System.out.println("Ct value as Line wise: " + TCt);
                           System.out.println("Ci value as Line wise: " + TCi);
                           //total of Ct +Ci =  Tw
                           
                           Tw = TCi+TCt;
                           
                           System.out.println("Tw value as Line wise: " + Tw);
                           System.out.println("Cs value as Line wise: " + TCs);
                           
                           
                           Cps = TCs*Tw;
                           
                           
                          //total of the Cs * Tw =  Cps
                            System.out.println("Total of Cs value   : " + Cps);
                            
                           //if the recursion is present
                           //or else this
                           Cp = Cps;
                            
                            
                           
                           //converting the array list to the String
                           String print = String.join(", ", printQ);
                           System.out.println("complete line   : " +print);
                           printQ.clear();
                           
                           int line1 = lineCounter;
                           String statements = displayLine;
                           String keywords1 = print;
                           int printsCs = TCs;
                           int printsCi = TCi;
                           int printsCt = TCt;
                           int printsTw= Tw;
                           int printsCps = Cps;
                           int printsCp = Cp;
                           
                            TCsCountNo += TCs;
                            TCiCountNo += TCi;
                            TCtCountNo +=TCt;      
                            TCrCountNo +=TCr;
                            TwCountNo +=Tw;
                            CpsCountNo +=Cps;
                            CpCountNo +=Cp; 
                           
                           //
                           
                           //setting values for the table
                           model.addRow(new Object[]{ 
                              line1,statements ,keywords1,printsCs,printsCi,printsCt,printsTw,printsCps,printsCp
                            });
                           
                           
            }              //display. 
              
                    //setting values to display
                    
                    
                    
                    
                           
                            lineCsBox.setText(Integer.toString(TCsCountNo));
                            lineCiBox.setText(Integer.toString(TCiCountNo));
//                            lineCtBox.setText(Integer.toString(TCtCountNo));
//                            totalCrBox.setText(Integer.toString(TCrCountNo));
                            lineTwBox.setText(Integer.toString(TwCountNo));
                            totalCpsBox.setText(Integer.toString(CpsCountNo));
                            totalCpBox.setText(Integer.toString(CpCountNo));
                            
                            
                            
                            
                   

                    // Always the close files.
                    bufferedReader.close();
                
                
                
                
                }
                
                }
                
            } catch (UnsupportedFlavorException ex) {
                Logger.getLogger(spmFull.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(spmFull.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        }
            
        
    };
        dragContainer.setTransferHandler(th);
        
                
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        dragContainer = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lineCsBox = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        display = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        lineCiBox = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        lineTwBox = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        totalCpsBox = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        totalCpBox = new javax.swing.JLabel();
        breport2 = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "line No", "Statement", "keywords", "Cs"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Code Complexity Measurement  Tool - Size");
        setBackground(new java.awt.Color(51, 51, 51));

        dragContainer.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setText(" Drag and drop your code(.java, .txt formats are supported)");

        jLabel2.setText("--------------------- Code Complexity  -  Summary---------------------");
        jLabel2.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        jLabel6.setText("Total Size Complxity");
        jLabel6.setMaximumSize(new java.awt.Dimension(4, 10));
        jLabel6.setMinimumSize(new java.awt.Dimension(4, 10));
        jLabel6.setPreferredSize(new java.awt.Dimension(4, 10));

        lineCsBox.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lineCsBox.setMaximumSize(new java.awt.Dimension(4, 6));
        lineCsBox.setMinimumSize(new java.awt.Dimension(4, 6));
        lineCsBox.setPreferredSize(new java.awt.Dimension(4, 6));

        display.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "lineNoBox", "statementBox", "keyWordBox", "CsBox"
            }
        ));
        display.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(display);
        if (display.getColumnModel().getColumnCount() > 0) {
            display.getColumnModel().getColumn(0).setPreferredWidth(10);
            display.getColumnModel().getColumn(1).setPreferredWidth(40);
            display.getColumnModel().getColumn(2).setPreferredWidth(50);
            display.getColumnModel().getColumn(3).setPreferredWidth(10);
        }

        jLabel8.setText("File Name:");

        jLabel10.setText("File Type:");

        jLabel12.setText("File Path:");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N

        jLabel20.setText("Total Inheritance Complxity");
        jLabel20.setMaximumSize(new java.awt.Dimension(4, 10));
        jLabel20.setMinimumSize(new java.awt.Dimension(4, 10));
        jLabel20.setPreferredSize(new java.awt.Dimension(4, 10));

        lineCiBox.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lineCiBox.setMaximumSize(new java.awt.Dimension(4, 6));
        lineCiBox.setMinimumSize(new java.awt.Dimension(4, 6));
        lineCiBox.setPreferredSize(new java.awt.Dimension(4, 6));

        jLabel24.setText("Total Weight");
        jLabel24.setMaximumSize(new java.awt.Dimension(4, 10));
        jLabel24.setMinimumSize(new java.awt.Dimension(4, 10));
        jLabel24.setPreferredSize(new java.awt.Dimension(4, 10));

        lineTwBox.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lineTwBox.setMaximumSize(new java.awt.Dimension(4, 6));
        lineTwBox.setMinimumSize(new java.awt.Dimension(4, 6));
        lineTwBox.setPreferredSize(new java.awt.Dimension(4, 6));

        jLabel25.setText(" Complexity of a program statement");
        jLabel25.setMaximumSize(new java.awt.Dimension(4, 10));
        jLabel25.setMinimumSize(new java.awt.Dimension(4, 10));
        jLabel25.setPreferredSize(new java.awt.Dimension(4, 10));

        totalCpsBox.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        totalCpsBox.setMaximumSize(new java.awt.Dimension(4, 6));
        totalCpsBox.setMinimumSize(new java.awt.Dimension(4, 6));
        totalCpsBox.setPreferredSize(new java.awt.Dimension(4, 6));

        jLabel27.setText("TOTAL PROGRAM COMPLEXITY");
        jLabel27.setMaximumSize(new java.awt.Dimension(4, 10));
        jLabel27.setMinimumSize(new java.awt.Dimension(4, 10));
        jLabel27.setPreferredSize(new java.awt.Dimension(4, 10));

        totalCpBox.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        totalCpBox.setMaximumSize(new java.awt.Dimension(4, 6));
        totalCpBox.setMinimumSize(new java.awt.Dimension(4, 6));
        totalCpBox.setPreferredSize(new java.awt.Dimension(4, 6));

        breport2.setText("Report");
        breport2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                breport2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(4, 4, 4)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel12)
                                    .addGap(21, 21, 21)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(dragContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel8)
                                        .addComponent(jLabel10))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(7, 7, 7)
                                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(breport2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(lineCiBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(lineTwBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(totalCpBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(31, 31, 31)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(totalCpsBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lineCsBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 136, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dragContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 125, Short.MAX_VALUE)
                                        .addComponent(breport2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(7, 7, 7)
                                        .addComponent(jLabel10)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(99, 99, 99)
                                .addComponent(jLabel4))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lineCiBox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lineTwBox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lineCsBox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(13, 13, 13)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(totalCpsBox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(totalCpBox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)))
                .addGap(161, 161, 161))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void breport2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_breport2ActionPerformed
        // TODO add your handling code here:
        String inherits=lineCiBox.getText();
//        String nestings=lineCtBox.getText();
        String totalweight=lineTwBox.getText();
        String totalsize=lineCsBox.getText();
        String totalstatement=totalCpsBox.getText();
//        String recursives=totalCrBox.getText();
        String totalcomplexes=totalCpBox.getText();
        

        JFileChooser dial =new JFileChooser();
        dial.setSelectedFile(new File("Complexity Report"+".pdf"));
        int dialogresult=dial.showSaveDialog(null);
        if(dialogresult==JFileChooser.APPROVE_OPTION){

            String file=dial.getSelectedFile().getPath();

            try{

                Document document=new Document();
                PdfWriter.getInstance(document,new FileOutputStream(file));

                document.open();
                document.add(new Paragraph("Code Complexity",FontFactory.getFont(FontFactory.TIMES_BOLD,20,Font.BOLD)));
                document.add(new Paragraph(new Date().toString()));
                document.add(new Paragraph("",FontFactory.getFont(FontFactory.TIMES_BOLD,20,Font.BOLD)));
                document.add(new Paragraph("Code Complexity due to the inheritance",FontFactory.getFont(FontFactory.TIMES_BOLD,15,Font.BOLD)));
                document.add(new Paragraph("#############################################",FontFactory.getFont(FontFactory.TIMES_ROMAN,20,Font.PLAIN)));
                document.add(new Paragraph("Code complexity : "+inherits,FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.PLAIN)));
                document.add(new Paragraph("############################################",FontFactory.getFont(FontFactory.TIMES_ROMAN,20,Font.PLAIN)));
                document.add(new Paragraph("Code Complexity due to the Control structure",FontFactory.getFont(FontFactory.TIMES_BOLD,15,Font.BOLD)));
                document.add(new Paragraph("##############################################",FontFactory.getFont(FontFactory.TIMES_ROMAN,20,Font.PLAIN)));
//                document.add(new Paragraph("Code complexity: "+nestings,FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.PLAIN)));
                document.add(new Paragraph("###############################################",FontFactory.getFont(FontFactory.TIMES_ROMAN,20,Font.PLAIN)));
                document.add(new Paragraph("Code Complexity due to the Size",FontFactory.getFont(FontFactory.TIMES_BOLD,15,Font.BOLD)));
                document.add(new Paragraph("##############################################",FontFactory.getFont(FontFactory.TIMES_ROMAN,20,Font.PLAIN)));
                document.add(new Paragraph("Complexity of the code: "+totalsize,FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.PLAIN)));
                document.add(new Paragraph("##############################################",FontFactory.getFont(FontFactory.TIMES_ROMAN,20,Font.PLAIN)));
                document.add(new Paragraph("Total Weight",FontFactory.getFont(FontFactory.TIMES_BOLD,15,Font.BOLD)));
                document.add(new Paragraph("##############################################",FontFactory.getFont(FontFactory.TIMES_ROMAN,20,Font.PLAIN)));
                document.add(new Paragraph("Complexity of the code: "+totalweight,FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.PLAIN)));
                document.add(new Paragraph("##############################################",FontFactory.getFont(FontFactory.TIMES_ROMAN,20,Font.PLAIN)));
                document.add(new Paragraph("Complexity of program statement",FontFactory.getFont(FontFactory.TIMES_BOLD,15,Font.BOLD)));
                document.add(new Paragraph("##############################################",FontFactory.getFont(FontFactory.TIMES_ROMAN,20,Font.PLAIN)));
                document.add(new Paragraph("Complexity of the code: "+totalstatement,FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.PLAIN)));
                document.add(new Paragraph("##############################################",FontFactory.getFont(FontFactory.TIMES_ROMAN,20,Font.PLAIN)));
                document.add(new Paragraph("Complexity due to recursive",FontFactory.getFont(FontFactory.TIMES_BOLD,15,Font.BOLD)));
                document.add(new Paragraph("##############################################",FontFactory.getFont(FontFactory.TIMES_ROMAN,20,Font.PLAIN)));
//                document.add(new Paragraph("Code complexity : "+recursives,FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.PLAIN)));
                document.add(new Paragraph("##############################################",FontFactory.getFont(FontFactory.TIMES_ROMAN,20,Font.PLAIN)));
                document.add(new Paragraph("Total Complexity",FontFactory.getFont(FontFactory.TIMES_BOLD,15,Font.BOLD)));
                document.add(new Paragraph("##############################################",FontFactory.getFont(FontFactory.TIMES_ROMAN,20,Font.PLAIN)));
                document.add(new Paragraph("Total Complexity  : "+totalcomplexes,FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.PLAIN)));
                document.add(new Paragraph("##############################################",FontFactory.getFont(FontFactory.TIMES_ROMAN,20,Font.PLAIN)));
                document.close();
                JOptionPane.showMessageDialog(null, "Report Generated.");

            }catch(DocumentException | HeadlessException | FileNotFoundException | NumberFormatException e){
                JOptionPane.showMessageDialog(null, e);
            }finally{
                try{

                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, "erererere");
                }
            }

        }
    }//GEN-LAST:event_breport2ActionPerformed

    /**
     * @param args the command lineNo arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(spmFull.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(spmFull.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(spmFull.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(spmFull.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new spmFull().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton breport2;
    private javax.swing.JTable display;
    private javax.swing.JLabel dragContainer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lineCiBox;
    private javax.swing.JLabel lineCsBox;
    private javax.swing.JLabel lineTwBox;
    private javax.swing.JLabel totalCpBox;
    private javax.swing.JLabel totalCpsBox;
    // End of variables declaration//GEN-END:variables
}
