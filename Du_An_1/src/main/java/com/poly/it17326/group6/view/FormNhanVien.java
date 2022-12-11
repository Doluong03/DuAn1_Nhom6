/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.poly.it17326.group6.view;

import com.poly.it17326.group6.domainmodel.ChucVu;
import com.poly.it17326.group6.domainmodel.TaiKhoan;
import com.poly.it17326.group6.service.TaiKhoanService;
import com.poly.it17326.group6.service.impl.TaiKhoanServiceImpl;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.awt.Frame;
import java.awt.Window;

import java.awt.event.KeyEvent;
import java.awt.print.Book;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jboss.logging.Logger;

/**
 *
 * @author 123
 */
public class FormNhanVien extends javax.swing.JPanel {

    private TaiKhoanService taiKhoanService = new TaiKhoanServiceImpl();
    private DefaultTableModel model = new DefaultTableModel();
    private SimpleDateFormat dateFormat = new SimpleDateFormat();

    /**
     * Creates new form FormNV
     */
    public FormNhanVien() {
        initComponents();
        LoadData(taiKhoanService.getAll());
        loadCb();
        btnThemCv.setIcon(new ImageIcon("D:\\Nhom6_PRO1041\\Anh\\add_form.png"));
        loadCBtt();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    private void LoadData(List<TaiKhoan> taiKhoans) {
        model = (DefaultTableModel) tblTaiKh.getModel();
        model.setRowCount(0);
        model.setColumnIdentifiers(new String[]{"ID", "Mã", "Họ tên", "Giới tính", "Ngày sinh", "Địa chỉ", "Chức vụ", "SDT", "Mật khẩu", "Email"});
        for (TaiKhoan tk : taiKhoans) {
            model.addRow(new Object[]{tk.getId(), tk.getMaNV(), tk.getHoTenNV(), tk.getGioiTinh(), tk.getNgaySinh(),
                tk.getDiaChi(), tk.getChucVu().getTen(), tk.getSdt(), tk.getMatKhau(), tk.getEmail()});
        }
//      
    }

    private void loadCb() {
        cbChucVu.removeAllItems();
        cbVaiTro.removeAllItems();
        cbVaiTro.addItem("Vai trò");
        for (ChucVu chucVu : taiKhoanService.getListCB()) {
            cbChucVu.addItem(chucVu.getTen());
            cbVaiTro.addItem(chucVu.getTen());
        }
    }

    private void loadCBtt() {
        cbTrangThai.removeAllItems();
        cbTrangThai.addItem("Trạng thái");
        for (Boolean tk : taiKhoanService.getListCBTT()) {
            if (tk == true) {
                cbTrangThai.addItem("Đang làm việc");
            } else {
                cbTrangThai.addItem("Đã nghỉ việc");
            }
        }
    }

    public boolean valiDate() {
        if (txtName.getText().equals("") || txtMa.getText().equals("") || txtDiaC.getText().equals("")
                || txtSDT.getText().equals("") || txtMail.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Du lieu khong duoc de trong");
            return false;
        }
        return true;
    }
    Random rd = new Random();
    int pass = rd.nextInt(899999) + 100000;
       private static String generateRandomString() {
           String asciiUpperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String asciiLowerCase = asciiUpperCase.toLowerCase();
         String digits = "1234567890";
        String asciiChars = asciiUpperCase + asciiLowerCase + digits;
        StringBuilder sb = new StringBuilder();
        int i = 0;
        Random rand = new Random();
        while (i < 10) {
            sb.append(asciiChars.charAt(rand.nextInt(asciiChars.length())));
            i++;
        }
        return sb.toString();
    }
               String randomString = generateRandomString();
  public String sendEmail(String id, String email) throws MessagingException, UnsupportedEncodingException {

        //Email gửi đi
        String fromEmail = "tunganh.devj@gmail.com";

        //Password của email
        String passwordEmail = "advdwwljqyzlihsb";

        //Email người nhận
        String toEmail = email;

        //Tiêu đề email
        String subject = "Cửa hàng bán sữa";

        //Nội dung
        String body = "Test";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, passwordEmail);
            }
        };
        jakarta.mail.Session session = jakarta.mail.Session.getInstance(props, authenticator);
        MimeMessage message = new MimeMessage(session);

        message.addHeader("Content-type", "Text/HTML; charset=UTF-8");
        message.addHeader("format", "flowed");
        message.addHeader("Content-Transfer-Encoding", "8bit");
        message.setFrom(new InternetAddress(fromEmail, "Acount employee"));
        message.setReplyTo(InternetAddress.parse(fromEmail, false));
        message.setSubject(subject, "UTF-8");
        message.setText("UserName: "+ txtMa.getText()+" \n password: " + randomString, "UTF-8");
        message.setSentDate(new Date());
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));

        Transport.send(message);
        return "Send email successfully, please check your email!";
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTaiKh = new javax.swing.JTable();
        txtTenNV = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        cbVaiTro = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        cbTrangThai = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        dpkNgayS = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        txtDiaC = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtMail = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        cbChucVu = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnThemCv = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(226, 215, 214));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tblTaiKh.setModel(new javax.swing.table.DefaultTableModel(
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
        tblTaiKh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTaiKhMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblTaiKh);

        txtTenNV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTenNVKeyPressed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 102, 153));
        jLabel12.setText("Tên nhân viên");

        cbVaiTro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbVaiTro.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbVaiTroItemStateChanged(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 102, 153));
        jLabel13.setText("Chức vụ");

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 102, 153));
        jLabel14.setText("Trạng thái");

        cbTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbTrangThai.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbTrangThaiItemStateChanged(evt);
            }
        });

        jButton1.setText("Export Excel");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(111, 111, 111)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(48, 48, 48)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbVaiTro, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addGap(63, 63, 63)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cbTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(54, 54, 54)
                                .addComponent(jButton1)))))
                .addContainerGap(322, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(163, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbVaiTro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))))
                .addGap(16, 16, 16)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );

        jPanel2.setBackground(new java.awt.Color(226, 215, 214));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 153));
        jLabel3.setText("Mã:");

        txtMa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 102, 153));
        jLabel4.setText("Họ Tên:");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 153));
        jLabel5.setText("Giới Tính:");

        rdoNam.setText("Nam");

        rdoNu.setText("Nữ");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 102, 153));
        jLabel6.setText("Ngày Sinh:");

        dpkNgayS.setDateFormatString("yyyy-MM-dd");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 102, 153));
        jLabel7.setText("Địa Chỉ:");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 102, 153));
        jLabel8.setText("Số Điện Thoại:");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 102, 153));
        jLabel10.setText("Email:");

        btnThem.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnThem.setForeground(new java.awt.Color(0, 102, 153));
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnSua.setForeground(new java.awt.Color(0, 102, 153));
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnXoa.setForeground(new java.awt.Color(0, 102, 153));
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        cbChucVu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 102, 153));
        jLabel11.setText("Chức vụ:");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 153));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("QUẢN LÝ TÀI KHOẢN");

        btnThemCv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemCvActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(125, Short.MAX_VALUE)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(127, 127, 127))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtMa, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(rdoNam, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                                .addComponent(rdoNu, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtName, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dpkNgayS, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDiaC, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMail, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbChucVu, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSDT))
                        .addGap(29, 29, 29)
                        .addComponent(btnThemCv, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(jLabel1)
                .addGap(98, 98, 98)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdoNam)
                            .addComponent(rdoNu)
                            .addComponent(jLabel5)))
                    .addComponent(jLabel3))
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(dpkNgayS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(txtDiaC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11))
                    .addComponent(btnThemCv, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoa)
                    .addComponent(btnSua)
                    .addComponent(btnThem))
                .addContainerGap(251, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblTaiKhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTaiKhMouseClicked
        try {
            int i = tblTaiKh.getSelectedRow();
            txtMa.setText(tblTaiKh.getValueAt(i, 1).toString());
            txtName.setText(tblTaiKh.getValueAt(i, 2).toString());
            Date newDate = new SimpleDateFormat("yyyy-MM-dd").parse(tblTaiKh.getValueAt(i, 4).toString());
            dpkNgayS.setDate(newDate);
            txtDiaC.setText(tblTaiKh.getValueAt(i, 5).toString());
            txtSDT.setText(tblTaiKh.getValueAt(i, 7).toString());
         //   tpxPass.setToolTipText(tblTaiKh.getValueAt(i, 8).toString());
            if (tblTaiKh.getValueAt(i, 3).toString().equalsIgnoreCase("Nam")) {
                rdoNam.setSelected(true);
            } else {
                rdoNu.setSelected(true);
            }
            cbChucVu.setSelectedItem(tblTaiKh.getValueAt(i, 6).toString());
            //  txtCre.setText(tblTaiKh.getValueAt(i, 7).toString());
            txtMail.setText(tblTaiKh.getValueAt(i, 9).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tblTaiKhMouseClicked

    private void txtTenNVKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTenNVKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            List<TaiKhoan> list = taiKhoanService.getCheckTen(txtTenNV.getText());
            LoadData(list);
        }
    }//GEN-LAST:event_txtTenNVKeyPressed

    private void cbVaiTroItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbVaiTroItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == 1) {
            if (cbVaiTro.getSelectedIndex() == 0) {
                LoadData(taiKhoanService.getAll());
            } else {
                List<TaiKhoan> listTimKiem = taiKhoanService.findCv(cbVaiTro.getSelectedItem().toString());
                LoadData(listTimKiem);
            }
        }
    }//GEN-LAST:event_cbVaiTroItemStateChanged

    private void cbTrangThaiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbTrangThaiItemStateChanged
        // TODO add your handling code here:

        if (evt.getStateChange() == 1) {
            if (cbVaiTro.getSelectedIndex() == 0) {
                LoadData(taiKhoanService.getAll());
            } else {
                if (cbTrangThai.getSelectedItem().toString().equals("Đang làm việc")) {
                    List<TaiKhoan> listTimKiem1 = taiKhoanService.findTT(true);
                    LoadData(listTimKiem1);
                } else {
                    tblTaiKh.removeAll();
                    List<TaiKhoan> listTimKiem2 = taiKhoanService.findTT(false);
                    System.out.println("sad");
                    LoadData(listTimKiem2);
                }
            }
        }
    }//GEN-LAST:event_cbTrangThaiItemStateChanged

    private void txtMaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        TaiKhoan tk = new TaiKhoan();
        tk.setMaNV(txtMa.getText());
        tk.setHoTenNV(txtName.getText());
        if (rdoNam.isSelected()) {
            tk.setGioiTinh("Nam");
        } else {
            tk.setGioiTinh("Nữ");
        }
        tk.setNgaySinh(dpkNgayS.getDate());
        tk.setDiaChi(txtDiaC.getText());
        tk.setSdt(txtSDT.getText());
       tk.setMatKhau(randomString);
        tk.setCreateAt(new Date());
        tk.setEmail(txtMail.getText());
        for (ChucVu chucVu : taiKhoanService.getListCB()) {
            if (chucVu.getTen().equals(cbChucVu.getSelectedItem())) {
               tk.setChucVu(chucVu);
            }
        }
        if (taiKhoanService.them(tk)) {
            
            LoadData(taiKhoanService.getAll());
        }
        try {
            sendEmail(txtMa.getText(), txtMail.getText());
            JOptionPane.showMessageDialog(this, "Tài khoản và mật khẩu đã được gửi đến email đăng ký");
        } catch (MessagingException ex) {
            java.util.logging.Logger.getLogger(FormNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            java.util.logging.Logger.getLogger(FormNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
        valiDate();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        TaiKhoan tk = new TaiKhoan();
        int i = tblTaiKh.getSelectedRow();
        String id = tblTaiKh.getValueAt(i, 0).toString();
        tk.setId(Integer.parseInt(id));
        tk.setMaNV(txtMa.getText());
        tk.setHoTenNV(txtName.getText());
        if (rdoNam.isSelected()) {
            tk.setGioiTinh("Nam");
        } else {
            tk.setGioiTinh("Nữ");
        }
        for (ChucVu chucVu : taiKhoanService.getListCB()) {
            if (chucVu.getTen().equals(cbChucVu.getSelectedItem())) {
                tk.setId(chucVu.getId());
            }
        }
        tk.setNgaySinh(dpkNgayS.getDate());
        tk.setDiaChi(txtDiaC.getText());
        tk.setSdt(txtSDT.getText());
       // tk.setMatKhau(tpxPass.getToolTipText());
        tk.setEmail(txtMail.getText());
      //  tk.setMatKhau(tpxPass.getText());
        tk.setUpdateAt(new Date());
        if (taiKhoanService.sua(tk)) {
            JOptionPane.showMessageDialog(this, "Sua thanh cong");
            LoadData(taiKhoanService.getAll());
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        TaiKhoan tk = new TaiKhoan();
        int i = tblTaiKh.getSelectedRow();
        String id = tblTaiKh.getValueAt(i, 0).toString();
        tk.setId(Integer.parseInt(id));

        if (taiKhoanService.xoa(tk)) {
            JOptionPane.showMessageDialog(this, "Xoa thanh cong");
            LoadData(taiKhoanService.getAll());
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnThemCvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemCvActionPerformed
        // TODO add your handling code here:
        Window wd = SwingUtilities.windowForComponent(this);
        DialogChucVu dialogSp = new DialogChucVu((Frame) wd, true);
        dialogSp.setVisible(true);
        loadCb();
        loadCBtt();
    }//GEN-LAST:event_btnThemCvActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
          try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Thongke");
            XSSFRow row = null;
            Cell cell = null;
            row = sheet.createRow(3);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("STT");
            
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Mã NV");

            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Họ tên");

            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Giới tính");

            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Ngày sinh");

            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("Địa chỉ");
            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue("Chức vụ");
            cell = row.createCell(7, CellType.STRING);
            cell.setCellValue("SDT");
            cell = row.createCell(8, CellType.STRING);
            cell.setCellValue("Mật khẩu");
            cell = row.createCell(9, CellType.STRING);
            cell.setCellValue("Email");

            for (int i = 0; i < tblTaiKh.getRowCount(); i++) {

                row = sheet.createRow(5 + i);
                cell = row.createCell(0, CellType.NUMERIC);
                cell.setCellValue(tblTaiKh.getValueAt(i, 0).toString());

                cell = row.createCell(1, CellType.NUMERIC);
                cell.setCellValue(tblTaiKh.getValueAt(i, 1).toString());

                cell = row.createCell(2, CellType.NUMERIC);
                cell.setCellValue(tblTaiKh.getValueAt(i, 2).toString());

                cell = row.createCell(3, CellType.NUMERIC);
                cell.setCellValue(tblTaiKh.getValueAt(i, 3).toString());

                cell = row.createCell(4, CellType.NUMERIC);
                cell.setCellValue(tblTaiKh.getValueAt(i, 4).toString());
                
                cell = row.createCell(5, CellType.NUMERIC);
                cell.setCellValue(tblTaiKh.getValueAt(i, 5).toString());
                
                cell = row.createCell(6, CellType.NUMERIC);
                cell.setCellValue(tblTaiKh.getValueAt(i, 6).toString());
                
                cell = row.createCell(7, CellType.NUMERIC);
                cell.setCellValue(tblTaiKh.getValueAt(i, 7).toString());
                
                cell = row.createCell(8, CellType.NUMERIC);
                cell.setCellValue(tblTaiKh.getValueAt(i, 8).toString());
                
                cell = row.createCell(9, CellType.NUMERIC);
                cell.setCellValue(tblTaiKh.getValueAt(i, 9).toString());

            }

            File file = new File("D:\\Nhom6_PRO1041\\ThongKe\\TaiKhoan.xlsx");
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThemCv;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cbChucVu;
    private javax.swing.JComboBox<String> cbTrangThai;
    private javax.swing.JComboBox<String> cbVaiTro;
    private com.toedter.calendar.JDateChooser dpkNgayS;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTable tblTaiKh;
    private javax.swing.JTextField txtDiaC;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtMail;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenNV;
    // End of variables declaration//GEN-END:variables
}
