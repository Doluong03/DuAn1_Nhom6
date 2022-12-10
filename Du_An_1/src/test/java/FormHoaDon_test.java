/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */


import com.poly.it17326.group6.response.HoaDonCTResponse;
import com.poly.it17326.group6.response.HoaDonresponse;
import com.poly.it17326.group6.service.HoaDonChiTietService;
import com.poly.it17326.group6.service.HoaDonService;
import com.poly.it17326.group6.service.SanPhamService;
import com.poly.it17326.group6.service.impl.HOaDonChiTietServiceIplm;
import com.poly.it17326.group6.service.impl.HoaDonServecieIplm;
import com.poly.it17326.group6.service.impl.SanPhamServiceIplm;
import java.awt.Color;
import java.awt.Cursor;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author DUC-DU
 */
public class FormHoaDon_test extends javax.swing.JFrame {

    private HoaDonService hoaDonService = new HoaDonServecieIplm();
    private HoaDonChiTietService hoaDonChiTietService = new HOaDonChiTietServiceIplm();
    private SanPhamService sanPhamService = new SanPhamServiceIplm();
    private List<HoaDonresponse> list = hoaDonService.getListsHD();
    private List<HoaDonCTResponse> listhdct;
    private DefaultTableModel defaultTableModel = new DefaultTableModel();
    private DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel();

    public FormHoaDon_test() {
        initComponents();
        setLocationRelativeTo(null);
        loadData(list);

    }

    private void loadData(List<HoaDonresponse> list) {
        defaultTableModel = (DefaultTableModel) tblHoaDon.getModel();
        defaultTableModel.setRowCount(0);
        defaultTableModel.setColumnIdentifiers(new String[]{"Mã hóa đơn", "Ngày tạo", "SĐT", "Mã khách hàng", "Tên khách hàng", "Tên nhân viên", "Tình trạng"});
        for (HoaDonresponse hoaDonresponse : list) {
            defaultTableModel.addRow(new Object[]{hoaDonresponse.getMaHD(), hoaDonresponse.getNgayTao(), hoaDonresponse.getSdt(), hoaDonresponse.getMaKH(), hoaDonresponse.getTenKH(), hoaDonresponse.getTenNV(), hoaDonresponse.trangThai()});
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jFrame1 = new javax.swing.JFrame();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblhdct = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        btnSeach = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txttimkiem = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cbotrangthai = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        btnhuy = new javax.swing.JButton();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin sản phẩm"));

        tblhdct.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tblhdct);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 662, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setText("Quản Lý Hóa Đơn");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin sản phẩm"));

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
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
        tblHoaDon.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseMoved(evt);
            }
        });
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHoaDon);

        btnSeach.setText("Seach");
        btnSeach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeachActionPerformed(evt);
            }
        });

        jLabel1.setText("Tìm kiếm hóa đơn");

        jButton1.setText("Xuất danh sách");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Trạng thái hóa đơn");

        cbotrangthai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đã thanh toán", "Chờ thanh toán", " Đã hủy" }));
        cbotrangthai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbotrangthaiActionPerformed(evt);
            }
        });

        jButton2.setText("<<");

        jButton3.setText("<");

        jButton4.setText(">");

        jButton5.setText(">>");

        btnhuy.setText("Hủy");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txttimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(btnSeach)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cbotrangthai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(437, 437, 437)))
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnhuy)
                        .addGap(70, 70, 70))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 689, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txttimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(btnSeach))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbotrangthai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(86, 86, 86)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton3)
                    .addComponent(jButton2)
                    .addComponent(jButton5)
                    .addComponent(btnhuy))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(268, 268, 268)
                        .addComponent(jLabel4))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.getAccessibleContext().setAccessibleName("Hóa đơn");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

//        JFileChooser chooser = new JFileChooser();
//        chooser.showOpenDialog(null);
//        File f = chooser.getSelectedFile();
//        String filename = f.getAbsolutePath();
//        HoaDonExport.exportData(list, filename + ".xlsx");
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("HoaDon");
            XSSFRow row = null;
            Cell cell = null;
            row = sheet.createRow(3);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("Stt");

            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Mã hóa đơn");

            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Ngày tạo");

            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("SĐT");

            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Mã khách hàng");

            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("Tên khách hàng");

            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue("Tên nhân viên");

            cell = row.createCell(7, CellType.STRING);
            cell.setCellValue("Tình trạng");

            for (int i = 0; i < tblHoaDon.getRowCount(); i++) {

                row = sheet.createRow(5 + i);
                cell = row.createCell(0, CellType.NUMERIC);
                cell.setCellValue(i + 1);

                cell = row.createCell(1, CellType.NUMERIC);
                cell.setCellValue(tblHoaDon.getValueAt(i, 0).toString());

                cell = row.createCell(2, CellType.NUMERIC);
                cell.setCellValue(tblHoaDon.getValueAt(i, 1).toString());

                if (tblHoaDon.getValueAt(i, 2) == null) {
                    cell = row.createCell(3, CellType.NUMERIC);
                    cell.setCellValue("Trống");
                }else
                {
                    cell = row.createCell(3, CellType.NUMERIC);
                    cell.setCellValue(tblHoaDon.getValueAt(i, 2).toString());
                }
                cell = row.createCell(4, CellType.NUMERIC);
                cell.setCellValue(tblHoaDon.getValueAt(i, 3).toString());

                cell = row.createCell(5, CellType.NUMERIC);
                cell.setCellValue(tblHoaDon.getValueAt(i, 4).toString());

                cell = row.createCell(6, CellType.NUMERIC);
                cell.setCellValue(tblHoaDon.getValueAt(i, 5).toString());

                cell = row.createCell(7, CellType.NUMERIC);
                cell.setCellValue(tblHoaDon.getValueAt(i, 6).toString());

            }

            File file = new File("D:\\Nhom6_PRO1041\\Export_HD\\HoaDon.xlsx");
            try {
                FileOutputStream fis = new FileOutputStream(file);
                workbook.write(fis);
                fis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "Xuat thanh cong");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Xuat that bai");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnSeachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeachActionPerformed
        String maHD = txttimkiem.getText();
        String sdt = txttimkiem.getText();
        loadData(hoaDonService.timKiemHD(maHD, sdt));
    }//GEN-LAST:event_btnSeachActionPerformed
    public void loadTableHoaDonChiTiet() {
        int row = this.tblHoaDon.getSelectedRow();
        if (row == -1) {
            return;
        }
        String mahd = this.tblHoaDon.getValueAt(row, 0).toString();
        defaultTableModel = (DefaultTableModel) tblhdct.getModel();
        defaultTableModel.setColumnIdentifiers(new String[]{"Mã hóa đơn", "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Đơn giá", "Tổng tiền sản phẩm", "Tổng tiền hóa đơn "});
        defaultTableModel.setRowCount(0);
        listhdct = hoaDonService.getListHDCT(mahd);
        if (listhdct == null) {
            return;
        }
        for (HoaDonCTResponse hdct : listhdct) {
            defaultTableModel.addRow(new Object[]{hdct.getMaHD(), hdct.getMaSP(), hdct.getTen(), hdct.getSoLuong(), hdct.getDonGia(), hdct.getGiaBan(), hdct.getTongTien()});
        }
    }
    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        loadTableHoaDonChiTiet();
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void tblHoaDonMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseMoved
        tblHoaDon.setCursor(new Cursor(Cursor.HAND_CURSOR));
        int row = tblHoaDon.rowAtPoint(evt.getPoint());
        if (row > -1) {
            // easiest way:
            tblHoaDon.clearSelection();
            tblHoaDon.setRowSelectionInterval(row, row);
        } else {
            tblHoaDon.setSelectionBackground(Color.blue);
        }
    }//GEN-LAST:event_tblHoaDonMouseMoved

    private void cbotrangthaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbotrangthaiActionPerformed
        int tt = 1;

        if (cbotrangthai.getSelectedItem().equals("Đã thanh toán")) {
            List<HoaDonresponse> list = hoaDonService.timKiemTT(tt);
            loadData(list);
            return;
        } else if (cbotrangthai.getSelectedItem().equals("Chờ thanh toán")) {
            tt = 0;
            List<HoaDonresponse> list = hoaDonService.timKiemTT(tt);
            loadData(list);
            return;
        } else if (cbotrangthai.getSelectedItem().equals("Đã hủy")) {
            tt = 2;
            List<HoaDonresponse> list = hoaDonService.timKiemTT(tt);
            loadData(list);

        }
    }//GEN-LAST:event_cbotrangthaiActionPerformed

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
            java.util.logging.Logger.getLogger(FormHoaDon_test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormHoaDon_test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormHoaDon_test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormHoaDon_test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormHoaDon_test().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSeach;
    private javax.swing.JButton btnhuy;
    private javax.swing.JComboBox<String> cbotrangthai;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblhdct;
    private javax.swing.JTextField txttimkiem;
    // End of variables declaration//GEN-END:variables
}
