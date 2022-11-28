
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author Hp
 */
public class testFileFdf extends javax.swing.JFrame {

    /**
     * Creates new form testFileFdf
     */
    public testFileFdf() {
        initComponents();

        FakeData();
        loasdtTbale();
    }

    List<khachhang> lists = new ArrayList<>();

    private void FakeData() {
        lists.add(new khachhang("huy", "20", "nam", "20/10/2002"));
        lists.add(new khachhang("huy2", "20", "nam", "20/10/2002"));
        lists.add(new khachhang("huy3", "20", "nam", "20/10/2002"));
    }

    private void loasdtTbale() {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"ten", "ngay", "gioi tinh", "tuoi"});

        for (khachhang list : lists) {
            model.addRow(new Object[]{list.getTen(), list.getNgay(), list.getGioiTinh(), list.getTuoi()});
        }
        tblbang.setModel(model);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jlbTen = new javax.swing.JLabel();
        jlbNgay = new javax.swing.JLabel();
        jlbTuoi = new javax.swing.JLabel();
        jlbGioitinh = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblbang = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jten = new javax.swing.JLabel();
        jngay = new javax.swing.JLabel();
        jtuoi = new javax.swing.JLabel();
        jgioitinh = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jlbTen.setText("jLabel1");

        jlbNgay.setText("jLabel2");

        jlbTuoi.setText("jLabel3");

        jlbGioitinh.setText("jLabel4");

        tblbang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblbang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblbangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblbang);

        jButton1.setText("ExtraFile");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jten.setText("ten");

        jngay.setText("ngay");

        jtuoi.setText("tuoi");

        jgioitinh.setText("gioitinh");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(35, 35, 35)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(210, 210, 210)
                            .addComponent(jButton1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jten, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jngay, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlbNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jgioitinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlbTen, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jtuoi, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlbGioitinh, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlbTuoi, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbTen)
                    .addComponent(jlbTuoi)
                    .addComponent(jten)
                    .addComponent(jtuoi))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jlbNgay)
                        .addComponent(jlbGioitinh)
                        .addComponent(jgioitinh))
                    .addComponent(jngay))
                .addGap(65, 65, 65)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jButton1)
                .addContainerGap(66, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblbangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblbangMouseClicked
        int index = tblbang.getSelectedRow();
        jlbTen.setText(tblbang.getValueAt(index, 0).toString());
        jlbNgay.setText(tblbang.getValueAt(index, 1).toString());
        jlbGioitinh.setText(tblbang.getValueAt(index, 2).toString());
        jlbTuoi.setText(tblbang.getValueAt(index, 3).toString());
    }//GEN-LAST:event_tblbangMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String path = "C:\\Users\\Hp\\Desktop\\du_ann1\\hd.pdf"; // duowng daan
   
        float col = 280f;
        float columnWidth[] = {col, col};

        PdfWriter writer;
        try {
            writer = new PdfWriter(path);
            PdfDocument pdfDocument = new PdfDocument(writer);
            Document doc = new Document(pdfDocument);
            pdfDocument.setDefaultPageSize(PageSize.A4);
            Table tb = new Table(columnWidth);
            tb.addCell(new Cell().add("Invoice")
                    .setTextAlignment(TextAlignment.CENTER)
                    .setVerticalAlignment(VerticalAlignment.MIDDLE)
                    .setMarginTop(30f)
                    .setMarginBottom(30f)
                    .setFontSize(30f)
                    .setBorder(Border.NO_BORDER));
            tb.addCell(new Cell().add("ajljkacjkba\n#23744327 \n ashcash")
                    .setTextAlignment(TextAlignment.RIGHT)
                    .setMarginTop(30f)
                    .setMarginBottom(30f)
                    .setBorder(Border.NO_BORDER)
                    .setMarginRight(10f));
            tb.setBackgroundColor(new DeviceRgb(63, 169, 219))
                    .setFontColor(com.itextpdf.kernel.color.Color.WHITE);

            float colwidth[] = {80, 300, 100, 80};
            Table customInforTbale = new Table(colwidth);
            customInforTbale.addCell(new Cell(0, 4).add("custominfor")
                    .setBorder(Border.NO_BORDER));
            customInforTbale.addCell(new Cell().add("Name")
                    .setBorder(Border.NO_BORDER));
            customInforTbale.addCell(new Cell().add("hiuy")
                    .setBorder(Border.NO_BORDER));
            customInforTbale.addCell(new Cell().add("Invoi no")
                    .setBorder(Border.NO_BORDER));
            customInforTbale.addCell(new Cell().add("2345")
                    .setBorder(Border.NO_BORDER));
            customInforTbale.addCell(new Cell().add("Mo no")
                    .setBorder(Border.NO_BORDER));
            customInforTbale.addCell(new Cell().add("093273428")
                    .setBorder(Border.NO_BORDER));
            customInforTbale.addCell(new Cell().add("893737-dshds")
                    .setBorder(Border.NO_BORDER));

            float itemInforWidth[] = {140, 140, 140, 140};
            Table itemTbale = new Table(itemInforWidth);

            itemTbale.addCell(new Cell().add("service")
                    .setBackgroundColor(new DeviceRgb(63, 169, 219))
                    .setFontColor(com.itextpdf.kernel.color.Color.WHITE));
            itemTbale.addCell(new Cell().add("Hrs")
                    .setBackgroundColor(new DeviceRgb(63, 169, 219))
                    .setFontColor(com.itextpdf.kernel.color.Color.WHITE));
            itemTbale.addCell(new Cell().add("Unit price")
                    .setBackgroundColor(new DeviceRgb(63, 169, 219))
                    .setFontColor(com.itextpdf.kernel.color.Color.WHITE)
                    .setTextAlignment(TextAlignment.RIGHT));
            itemTbale.addCell(new Cell().add("Amout")
                    .setBackgroundColor(new DeviceRgb(63, 169, 219))
                    .setFontColor(com.itextpdf.kernel.color.Color.WHITE)
                    .setTextAlignment(TextAlignment.RIGHT));

            itemTbale.addCell(new Cell().add("App cretion"));
            itemTbale.addCell(new Cell().add("20"));
            itemTbale.addCell(new Cell().add("200")
                    .setTextAlignment(TextAlignment.RIGHT));
            itemTbale.addCell(new Cell().add("" + 20 + 200)
                    .setTextAlignment(TextAlignment.RIGHT));

            itemTbale.addCell(new Cell().add("Webdiginte"));
            itemTbale.addCell(new Cell().add("15"));
            itemTbale.addCell(new Cell().add("150")
                    .setTextAlignment(TextAlignment.RIGHT));
            itemTbale.addCell(new Cell().add("" + 15 + 150)
                    .setTextAlignment(TextAlignment.RIGHT));

            itemTbale.addCell(new Cell().add("")
                    .setBackgroundColor(new DeviceRgb(63, 169, 219))
                    .setFontColor(com.itextpdf.kernel.color.Color.WHITE)
                    .setBorder(Border.NO_BORDER));
            itemTbale.addCell(new Cell().add("")
                    .setBackgroundColor(new DeviceRgb(63, 169, 219))
                    .setFontColor(com.itextpdf.kernel.color.Color.WHITE)
                    .setBorder(Border.NO_BORDER));
            itemTbale.addCell(new Cell().add("Total amount")
                    .setBackgroundColor(new DeviceRgb(63, 169, 219))
                    .setFontColor(com.itextpdf.kernel.color.Color.WHITE)
                    .setBorder(Border.NO_BORDER)
                    .setTextAlignment(TextAlignment.RIGHT));
            itemTbale.addCell(new Cell().add("" + 4000)
                    .setBackgroundColor(new DeviceRgb(63, 169, 219))
                    .setFontColor(com.itextpdf.kernel.color.Color.WHITE)
                    .setBorder(Border.NO_BORDER)
                    .setTextAlignment(TextAlignment.RIGHT));

            doc.add(tb);
            doc.add(new Paragraph("\n"));
            doc.add(customInforTbale);
            doc.add(new Paragraph("\n"));
            doc.add(itemTbale);
            doc.close();
            System.out.println("in thanh cong");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(testFileFdf.class.getName()).log(Level.SEVERE, null, ex);
        }

//            for (int i = 0; i < tblbang.getRowCount(); i++) {
//                String ten = tblbang.getValueAt(i, 0).toString();
//                String ngay = (tblbang.getValueAt(i, 1).toString());
//                String gioitinh = (tblbang.getValueAt(i, 2).toString());
//                String tuoi = (tblbang.getValueAt(i, 3).toString());
//                
//                tb.addCell(ten);
//                tb.addCell(ngay);
//                tb.addCell(gioitinh);
//                tb.addCell(tuoi);
//            }

    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(testFileFdf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(testFileFdf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(testFileFdf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(testFileFdf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new testFileFdf().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel jgioitinh;
    private javax.swing.JLabel jlbGioitinh;
    private javax.swing.JLabel jlbNgay;
    private javax.swing.JLabel jlbTen;
    private javax.swing.JLabel jlbTuoi;
    private javax.swing.JLabel jngay;
    private javax.swing.JLabel jten;
    private javax.swing.JLabel jtuoi;
    private javax.swing.JTable tblbang;
    // End of variables declaration//GEN-END:variables
}
