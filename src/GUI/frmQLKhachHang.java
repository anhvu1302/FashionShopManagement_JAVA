/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package GUI;

import DAO.KhachHangDAO;
import DAO.SQLServerDataProvider;
import POJO.KhachHang;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class frmQLKhachHang extends javax.swing.JInternalFrame {

    /**
     * Creates new form frmQLKhachHang
     */
    DefaultTableModel dtmKH = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return column != 0;
        }
    };
    ArrayList<KhachHang> lstDeleteKH = new ArrayList<>();
    ArrayList<KhachHang> lstUpdateKH = new ArrayList<>();

    public frmQLKhachHang() {
        initComponents();
        String[] tieuDe = {"Mã khách hàng", "Tên khách hàng", "Giới tính", "Số điện thoại", "Email", "Điểm","Ngày thêm"};
        dtmKH.setColumnIdentifiers(tieuDe);
        load_tblKhachHang();
        buttonGroup1.clearSelection();
    }

    private void setKhachHangModel(ArrayList<KhachHang> dsKH) {
        dtmKH.setRowCount(0);
        for (KhachHang kh : dsKH) {
            Vector<Object> vec = new Vector<Object>();
            vec.add(kh.getIdKhachHang());
            vec.add(kh.getTenKhachHang());
            vec.add(kh.getGioiTinh());
            vec.add(kh.getSoDienThoai());
            vec.add(kh.getEmail());
            vec.add(kh.getDiem());
            vec.add(kh.getNgayThem());
            dtmKH.addRow(vec);
        }
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tblKH.getModel());
        tblKH.setRowSorter(sorter);
        tblKH.setAutoCreateRowSorter(true);
        tblKH.setModel(dtmKH);
    }

    private void load_tblKhachHang() {
        ArrayList<KhachHang> dsKH = KhachHangDAO.getAll();
        setKhachHangModel(dsKH);
        //lấy giá trị dòng được sửa
        dtmKH.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE && e.getColumn() != TableModelEvent.ALL_COLUMNS && e.getLastRow() == e.getFirstRow()) {
                    int editedRow = e.getFirstRow();
                    long id = (Long) tblKH.getValueAt(editedRow, 0);
                    String ten = (String) tblKH.getValueAt(editedRow, 1);
                    String gTinh = (String) tblKH.getValueAt(editedRow, 2);
                    String sdt = (String) tblKH.getValueAt(editedRow, 3);
                    String mail = (String) tblKH.getValueAt(editedRow, 4);
                    Long diem = (Long) tblKH.getValueAt(editedRow, 5);
                    lstUpdateKH.add(new KhachHang(id, ten, gTinh, sdt, mail, diem, new Date()));
                }
            }
        });
        // Add popup delete
        final JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem deleteItem = new JMenuItem("Delete row");
        deleteItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tblKH.getSelectedRow();
                if (selectedRow != -1) {
                    long id = (Long) tblKH.getValueAt(tblKH.getSelectedRow(), 0);
                    String ten = (String) tblKH.getValueAt(tblKH.getSelectedRow(), 1);
                    String gTinh = (String) tblKH.getValueAt(tblKH.getSelectedRow(), 2);
                    String sdt = (String) tblKH.getValueAt(tblKH.getSelectedRow(), 3);
                    String mail = (String) tblKH.getValueAt(tblKH.getSelectedRow(), 4);
                    Long diem = (Long) tblKH.getValueAt(tblKH.getSelectedRow(), 5);
                    lstDeleteKH.add(new KhachHang(id, ten, gTinh, sdt, mail, diem,new Date()));
                    dtmKH.removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn dòng dữ liệu", "Thông báo", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        popupMenu.add(deleteItem);
        tblKH.setComponentPopupMenu(popupMenu);
    }

    private void ResetKhachHang() {
        txtmaKH.setText("");
        txttenKhachHang.setText("");
        txtsdt.setText("");
        rbNam.setSelected(false);
        rbNu.setSelected(true);
        txtEmail.setText("");
        txtDiem.setText("");
        buttonGroup1.clearSelection();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtmaKH = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txttenKhachHang = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtsdt = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtDiem = new javax.swing.JTextField();
        btnThemKH = new javax.swing.JButton();
        btnXoaKh = new javax.swing.JButton();
        btnUpdateTTKH = new javax.swing.JButton();
        btnTim = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        rbNam = new javax.swing.JRadioButton();
        rbNu = new javax.swing.JRadioButton();
        btnLuu = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKH = new javax.swing.JTable();

        setMaximumSize(new java.awt.Dimension(1536, 778));
        setMinimumSize(new java.awt.Dimension(1536, 778));
        setPreferredSize(new java.awt.Dimension(1536, 778));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(187, 54, 137));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("DANH SÁCH KHÁCH HÀNG");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(187, 54, 137));
        jLabel5.setText("Mã khách hàng");

        txtmaKH.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtmaKH.setEnabled(false);

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(187, 54, 137));
        jLabel16.setText("Số điện thoại");

        txtEmail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(187, 54, 137));
        jLabel11.setText("Tên khách hàng");

        txttenKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(187, 54, 137));
        jLabel15.setText("Giới tính");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(187, 54, 137));
        jLabel17.setText("Email");

        txtsdt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(187, 54, 137));
        jLabel18.setText("Điểm");

        txtDiem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnThemKH.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnThemKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/add.png"))); // NOI18N
        btnThemKH.setText("Thêm");
        btnThemKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemKHActionPerformed(evt);
            }
        });

        btnXoaKh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnXoaKh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/delete.png"))); // NOI18N
        btnXoaKh.setText("Xoá");
        btnXoaKh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaKhActionPerformed(evt);
            }
        });

        btnUpdateTTKH.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnUpdateTTKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/updated.png"))); // NOI18N
        btnUpdateTTKH.setText("Cập nhật");
        btnUpdateTTKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateTTKHActionPerformed(evt);
            }
        });

        btnTim.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnTim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/find.png"))); // NOI18N
        btnTim.setText("Tìm");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        btnReset.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/reset.png"))); // NOI18N
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbNam);
        rbNam.setText("Nam");

        buttonGroup1.add(rbNu);
        rbNu.setText("Nữ");

        btnLuu.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnLuu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-save-30.png"))); // NOI18N
        btnLuu.setText("Lưu dữ liệu");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(35, 35, 35)
                        .addComponent(txtmaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtsdt, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel17)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel18)
                                        .addGap(96, 96, 96)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addComponent(btnTim))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnReset)
                                    .addComponent(jLabel11))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnThemKH)
                                .addGap(18, 18, 18)
                                .addComponent(btnXoaKh))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(77, 77, 77)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(rbNam)
                                        .addGap(18, 18, 18)
                                        .addComponent(rbNu))
                                    .addComponent(txttenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnUpdateTTKH)
                            .addComponent(btnLuu))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtmaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txttenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(rbNam)
                    .addComponent(rbNu))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtsdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTim))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtDiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemKH)
                    .addComponent(btnXoaKh)
                    .addComponent(btnUpdateTTKH)
                    .addComponent(btnReset))
                .addGap(51, 51, 51)
                .addComponent(btnLuu)
                .addContainerGap(188, Short.MAX_VALUE))
        );

        tblKH.setModel(new javax.swing.table.DefaultTableModel(
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
        tblKH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKHMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblKH);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 949, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 608, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(604, 604, 604)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpdateTTKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateTTKHActionPerformed
        if (txtmaKH.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm muốn cập nhật ở danh sách!");
        } else {
            int maKH = Integer.parseInt(txtmaKH.getText());
            String ten = txttenKhachHang.getText();
            String gtinh;
            if (rbNam.isSelected()) {
                gtinh = "Nam";
            } else if (rbNu.isSelected()) {
                gtinh = "Nữ";
            } else {
                gtinh = ""; // Không chọn giới tính
            }
            String mail = txtEmail.getText();
            String sdt = txtsdt.getText();
            Long diem = Long.valueOf(txtDiem.getText());

            KhachHang kh = new KhachHang();
            kh.setIdKhachHang(maKH);
            kh.setTenKhachHang(ten);
            kh.setGioiTinh(gtinh);
            kh.setSoDienThoai(sdt);
            kh.setEmail(mail);
            kh.setDiem(diem);
            boolean rs = KhachHangDAO.updateById(kh);
            if (rs) {
                JOptionPane.showMessageDialog(this, "Cập nhật thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                load_tblKhachHang();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }

        }
    }//GEN-LAST:event_btnUpdateTTKHActionPerformed

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        String searchValue = txtsdt.getText();
        if (searchValue.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập giá trị tìm kiếm!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        } else {
            ArrayList<KhachHang> lst = new ArrayList<>();
            lst.add(KhachHangDAO.getBySDT(searchValue));
            setKhachHangModel(lst);
        }

    }//GEN-LAST:event_btnTimActionPerformed

    private void btnThemKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemKHActionPerformed
        txtmaKH.setText("");
        String ten = txttenKhachHang.getText();
        String gtinh;
        if (rbNam.isSelected()) {
            gtinh = "Nam";
        } else if (rbNu.isSelected()) {
            gtinh = "Nữ";
        } else {
            gtinh = ""; // Không chọn giới tính
        }
        String mail = txtEmail.getText();
        String sdt = txtsdt.getText();
        String diem = txtDiem.getText();
        if (ten.length() == 0 || mail.length() == 0 || sdt.length() == 0 || diem.length() == 0 || gtinh.trim().length() == 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin");
        } else {
            if (KhachHangDAO.checkTrungMailorSDT(mail, sdt)) {
                JOptionPane.showMessageDialog(this, "Email '" + mail + " hoặc SĐT '" + sdt + "' đã tồn tại");
            } else {
                KhachHang kh = new KhachHang();
                kh.setTenKhachHang(ten);
                kh.setGioiTinh(gtinh);
                kh.setSoDienThoai(sdt);
                kh.setEmail(mail);
                kh.setDiem(Long.parseLong(diem));
                KhachHangDAO.add(kh);
                JOptionPane.showMessageDialog(this, "Thêm khách hàng thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                load_tblKhachHang();

            }
        }
    }//GEN-LAST:event_btnThemKHActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        ResetKhachHang();
    }//GEN-LAST:event_btnResetActionPerformed

    private void tblKHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKHMouseClicked
        int row = tblKH.getSelectedRow();
        txtmaKH.setText(tblKH.getValueAt(row, 0) + "");
        txttenKhachHang.setText((String) tblKH.getValueAt(row, 1));
        String gtinh = (String) tblKH.getValueAt(row, 2);
        if (gtinh.trim().equals("Nam")) {
            rbNam.setSelected(true);
            rbNu.setSelected(false);
        } else if (gtinh.trim().equals("Nữ")) {
            rbNam.setSelected(false);
            rbNu.setSelected(true);
        }
//        rbNam.setText((String) tblKH.getValueAt(row, 2));
        txtsdt.setText((String) tblKH.getValueAt(row, 3));
        txtEmail.setText((String) tblKH.getValueAt(row, 4));
        txtDiem.setText(tblKH.getValueAt(row, 5) + "");
    }//GEN-LAST:event_tblKHMouseClicked

    private void btnXoaKhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaKhActionPerformed
        if (txtmaKH.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn thông tin của khách hàng muốn xóa ở danh sách!");
        } else {
            int maKH = Integer.parseInt(txtmaKH.getText());
            if (KhachHangDAO.isForeignKeyExists(maKH)) {
                JOptionPane.showMessageDialog(this, "Khách hàng đang được lưu ở hoá đơn và không thể xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } else {
                boolean success = KhachHangDAO.deleteById(maKH);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    load_tblKhachHang();
                    ResetKhachHang();
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa không thành công!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_btnXoaKhActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        SQLServerDataProvider provider = new SQLServerDataProvider();
        if (!lstDeleteKH.isEmpty() || !lstUpdateKH.isEmpty()) {
            try {
                provider.open();
                provider.startTransaction();

                KhachHangDAO.deleteByList(lstDeleteKH);
                KhachHangDAO.updateByList(lstUpdateKH);
                provider.commitTransaction();
                lstDeleteKH.clear();
                lstUpdateKH.clear();
                JOptionPane.showMessageDialog(this, "Cập nhật dữ liệu thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Cập nhật dữ liệu thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                provider.rollbackTransaction();
            } finally {
                provider.close();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Dữ liệu không có gì thay đổi!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

        }
    }//GEN-LAST:event_btnLuuActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnThemKH;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnUpdateTTKH;
    private javax.swing.JButton btnXoaKh;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rbNam;
    private javax.swing.JRadioButton rbNu;
    private javax.swing.JTable tblKH;
    private javax.swing.JTextField txtDiem;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtmaKH;
    private javax.swing.JTextField txtsdt;
    private javax.swing.JTextField txttenKhachHang;
    // End of variables declaration//GEN-END:variables
}
