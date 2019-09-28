/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmticheck;

import cncCalculation.cncCalculation;
import ctcCalculation.ctcCalculation;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import twCalculation.twCalculation;

/**
 *
 * @author Dell
 */
public class Analysis extends javax.swing.JFrame {

     private Dimension dimension = null;
    public static String RFilePath = null;
    private static String RCopyText = null;

    public static ArrayList<CodeContent> coderesult = null;

    
    /**
     * Creates new form Analysis
     */
    public Analysis(String RFilePath, String RcopyText) {
        
        initComponents();
        
        dimension = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dimension.width / 2 - this.getSize().width / 2, dimension.height / 2 - this.getSize().height / 2);
        
        if(RFilePath == null && RcopyText != null){
            this.RCopyText = RcopyText;
            TextDocRead();
        }
        else if(RFilePath != null && RcopyText == null){
            this.RFilePath = RFilePath;
            FileDocRead();
        }
        
  

        display();
        

    }

    
    final public void TextDocRead(){
        System.out.println("Reading from the inserted Text Document");
        
        coderesult = new ArrayList<CodeContent>();
        
        try {
        
        StringReader read = new StringReader(RCopyText);
        final BufferedReader breader = new BufferedReader(read); 
        
         String valLine;
            int count = 1; 

            //reading the whole file one line at a time
            while ((valLine = breader.readLine()) != null) 
            {
                CodeContent cc = new CodeContent();
                
                cc.setLineNo(count);
                cc.setLineContent(valLine);

                coderesult.add(cc);

                count++;

            }
            } catch (IOException ex) {
            Logger.getLogger(Analysis.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
    
    final public void FileDocRead(){
        System.out.println("Reading from the inserted File's Document");
        
        coderesult = new ArrayList<CodeContent>();

        try {

            //Setting file path 
            final File filePath = new File(RFilePath);
            //creating a file reader and enteing the fle variable
            final FileReader reader = new FileReader(filePath);
            //creating the buffered Reader and passing the file reader
            final BufferedReader breader = new BufferedReader(reader); 
            
            String valLine;
            int count = 1; 

            //reading the file content line by line
            while ((valLine = breader.readLine()) != null) 
            {
                CodeContent cc = new CodeContent();
                
                cc.setLineNo(count);
                cc.setLineContent(valLine);

                coderesult.add(cc);

                count++;

            }

            reader.close();

        } catch (IOException ex) {
            Logger.getLogger(Analysis.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    final public void display(){
    
        refresh();
        
         for (CodeContent cc : coderesult) {

            jTextArea1.append(String.valueOf(cc.getLineNo()));
            jTextArea1.append("\t");
            
            
            jTextArea1.append(cc.getLineContent());
            jTextArea1.append("\t");
            
            
            jTextArea1.append(String.valueOf(cc.getCtcVal()));
            jTextArea1.append("\t");
            
            
            jTextArea1.append(String.valueOf(cc.getCncVal()));
            jTextArea1.append("\t");
            
            
            jTextArea1.append(String.valueOf(cc.getCiVal()));
            jTextArea1.append("\t");
            
            
            jTextArea1.append(String.valueOf(cc.getTwVal()));
            jTextArea1.append("\n");
        
    }
    }
    
    final public void displayValuesOnly(){
    
        refresh();
        
            jTextArea3.append("Line No");
            jTextArea3.append("\t\t");
            
            
            jTextArea3.append("Line CTC Value");
            jTextArea3.append("\t\t");
            
            
            jTextArea3.append("Line CNC Value");
            jTextArea3.append("\t");
            
            
            jTextArea3.append("Line CI Value");
            jTextArea3.append("\t\t");
            
            
            jTextArea3.append("Line TW Value");
            jTextArea3.append("\n");
        
         for (CodeContent cc : coderesult) {
           
            jTextArea3.append(String.valueOf(cc.getLineNo()));
            jTextArea3.append("\t\t");
            
            
            jTextArea3.append(String.valueOf(cc.getCtcVal()));
            jTextArea3.append("\t\t");
            
            
            jTextArea3.append(String.valueOf(cc.getCncVal()));
            jTextArea3.append("\t\t");
            
            
            jTextArea3.append(String.valueOf(cc.getCiVal()));
            jTextArea3.append("\t\t");
            
            
            jTextArea3.append(String.valueOf(cc.getTwVal()));
            jTextArea3.append("\n");
        
         }
    }
    
     public void calculateCnCValues() {

        //Const init 
        cncCalculation cncCalculation = new cncCalculation();

        ArrayList<Integer> Cnc_units = cncCalculation.coreBracketMapper();

        for (int i = 0; i < Cnc_units.size(); i++) {
            System.out.println("\t" + (i + 1) + " Line has " + Cnc_units.get(i) + " Cnc");
        }

        System.out.println("Total CNC --->  " + cncCalculation.getTotalCncPoints());
    }
     
     public void calculateCtcValue() {

        ctcCalculation ctcCalculation = new ctcCalculation();
        ctcCalculation.calculateCtc();

    }
     
    public void calculateTWValue() {
       twCalculation twCalculation = new twCalculation();
       twCalculation.calculateTWforProgramStatement();

    }
    
    public void calculateCiValue(){
       try {
            ciCalculation.ciCalculation.calc_ci();
        } catch (IOException ex) {
            Logger.getLogger(Analysis.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

 public void refresh(){
        calculateCnCValues();
        calculateCtcValue();
        calculateCiValue();
        calculateTWValue();
    }
     
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jButton1.setText("Values Only");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jScrollPane3.setViewportView(jTextArea3);

        jButton2.setText("Print Values");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("<<Back");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Check File Complexity");

        jButton4.setText("Log Out");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3)
                            .addComponent(jButton4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 246, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(274, 274, 274)
                        .addComponent(jButton1)
                        .addGap(22, 22, 22))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addContainerGap(327, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        displayValuesOnly();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try{
            boolean complete = jTextArea3.print();
            if(complete){
                 JOptionPane.showMessageDialog(null, "Done Printing!!", "Information", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "Printing!!", "Printer", JOptionPane.ERROR_MESSAGE);
            }
        }catch (PrinterException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.dispose();
            MainInterface main = new MainInterface();
            main.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        this.dispose();
            Login login = new Login();
            login.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
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
            java.util.logging.Logger.getLogger(Analysis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Analysis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Analysis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Analysis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Analysis(RFilePath,RCopyText).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    // End of variables declaration//GEN-END:variables


}
