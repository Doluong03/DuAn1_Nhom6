

import com.poly.it17326.group6.service.ThongKeService;
import com.poly.it17326.group6.service.impl.ThongKeServiceImpl;
import static com.poly.it17326.group6.view.NewJPanel.year2;
import java.awt.BorderLayout;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Hp
 */
public class NewJFrame1 extends javax.swing.JFrame {
 private ThongKeService thongKeService = new ThongKeServiceImpl();
    /**
     * Creates new form NewJFrame1
     */
    public NewJFrame1() {
        initComponents();
     
    }

    private void load(){
        BigDecimal  val1=new BigDecimal(0);
        BigDecimal  val2=new BigDecimal(0);
        BigDecimal  val3=new BigDecimal(1110);
        BigDecimal  val4=new BigDecimal(10000);
       
        DefaultCategoryDataset dataset=new DefaultCategoryDataset();
        dataset.setValue(val1, "", "THÁNG 1");
        dataset.setValue(val2, "", "THÁNG 2");
        dataset.setValue(val3, "", "THÁNG 3");
      
        
        
        JFreeChart oChart = ChartFactory.createBarChart("BIỂU ĐỒ DOANH THU NĂM "+"", "Quý 1", "DOANH THU(VND)", dataset, PlotOrientation.VERTICAL,false,false,false);

        ChartPanel oPanel = new ChartPanel(oChart);
        jpan1.setLayout(new java.awt.BorderLayout());
        jpan1.add(oPanel);
        jpan1.validate();

    }

    private void load3(){
        String year2 = cbNam.getSelectedItem().toString();
       
        List<BigDecimal> listThang4 = thongKeService.getListDtThang(year2, "12");
         if (cbQuy.getSelectedItem().equals("quý 1")) {
      
        
      
    
    
     int val1 = 0 ;
     int val2 = 10 ;
     int val3 = 12 ;
       BigDecimal val4 = new BigDecimal(0);
             for (BigDecimal bigDecimal : listThang4) {
                  val4 = bigDecimal;
             }
             System.out.println(""+val4);
     
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.setValue(val1, "", "THÁNG 1");
        dataset.setValue(val2, "", "THÁNG 2");
        dataset.setValue(val3, "", "THÁNG 3");
         dataset.setValue(val4, "", "THÁNG 4");

        JFreeChart oChart = ChartFactory.createBarChart("BIỂU ĐỒ DOANH THU NĂM " + "", "Quý 1", "DOANH THU(VND)", dataset, PlotOrientation.VERTICAL, false, false, false);

        ChartPanel oPanel = new ChartPanel(oChart);
        jpan1.setLayout(new java.awt.BorderLayout());
        jpan1.add(oPanel);
        jpan1.validate();
         }  
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpan1 = new javax.swing.JLayeredPane();
        jLabel1 = new javax.swing.JLabel();
        cbQuy = new javax.swing.JComboBox<>();
        cbNam = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jpan1Layout = new javax.swing.GroupLayout(jpan1);
        jpan1.setLayout(jpan1Layout);
        jpan1Layout.setHorizontalGroup(
            jpan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jpan1Layout.setVerticalGroup(
            jpan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 207, Short.MAX_VALUE)
        );

        jLabel1.setText("bieu do");

        cbQuy.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "quý 1", "quý 2", "quý 3", "quý 4" }));
        cbQuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbQuyActionPerformed(evt);
            }
        });

        cbNam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2022", " " }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpan1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(91, 91, 91))
            .addGroup(layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(cbQuy, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addComponent(cbNam, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(242, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(jLabel1)
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbQuy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(jpan1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbQuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbQuyActionPerformed
       load3();
    }//GEN-LAST:event_cbQuyActionPerformed

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
            java.util.logging.Logger.getLogger(NewJFrame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbNam;
    private javax.swing.JComboBox<String> cbQuy;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLayeredPane jpan1;
    // End of variables declaration//GEN-END:variables
}
