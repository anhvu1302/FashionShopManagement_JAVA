/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package GUI;

import DAO.*;
import POJO.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.util.ArrayList;
import java.util.Vector;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author Nah nah
 */
public class frmQLSanPham extends javax.swing.JInternalFrame {

    /**
     * Creates new form frmQLSP
     */
    DefaultTableModel dtmSanPham = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return column != 0 && column != 6;
        }
    };
    DefaultTableModel dtmKieuSanPham = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return column != 0;
        }
    };
    ArrayList<SanPham> lstDeleteSP = new ArrayList<>();
    ArrayList<SanPham> lstUpdateSP = new ArrayList<>();

    public frmQLSanPham() {
        initComponents();
        AutoCompleteDecorator.decorate(cboLoaiSanPham);
        LoadCboLoaiSanPham();
        LoadCboSanPham();

        String[] tieuDe = {"Id", "Tên sản phẩm", "Loại sản phẩm", "Giá bán", "Giảm giá", "Mô tả", "Ngày thêm", "Tồn tại"};
        dtmSanPham.setColumnIdentifiers(tieuDe);
        loadTblSanPham();
        String[] tieuDe1 = {"Id", "BarCode", "Sản phẩm", "Màu", "Ảnh", "Size", "Số lượng tồn"};
        dtmKieuSanPham.setColumnIdentifiers(tieuDe1);
        tblKieuSanPham.setModel(dtmKieuSanPham);

    }

    private void setSanPhamModel(ArrayList<SanPham> dsSp) {
        dtmSanPham.setRowCount(0);
        for (SanPham sp : dsSp) {
            Vector<Object> vec = new Vector<Object>();
            vec.add(sp.getIdSanPham());
            vec.add(sp.getTenSanPham());
            vec.add(sp.getLoaiSanPham());
            vec.add(sp.getGiaBan());
            vec.add(sp.getGiamGia());
            vec.add(sp.getMoTa());
            vec.add(sp.getNgayThem());
            vec.add(sp.isTonTai() + "");
            dtmSanPham.addRow(vec);
        }
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tblSanPham.getModel());
        tblSanPham.setRowSorter(sorter);
        tblSanPham.setAutoCreateRowSorter(true);
        tblSanPham.setModel(dtmSanPham);
    }

    private void setKieuSanPhamModel(ArrayList<KieuSanPham> dsKSP) {
        dtmKieuSanPham.setRowCount(0);
        for (KieuSanPham ksp : dsKSP) {
            Vector<Object> vec = new Vector<Object>();
            vec.add(ksp.getIdKieuSanPham());
            vec.add(ksp.getBarCode());
            vec.add(ksp.getSanPham());
            vec.add(ksp.getMau());
            vec.add(ksp.getAnhSP());
            vec.add(ksp.getSize());
            vec.add(ksp.getSoLuongTonKho());
            dtmKieuSanPham.addRow(vec);
        }
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tblKieuSanPham.getModel());
        tblKieuSanPham.setRowSorter(sorter);
        tblKieuSanPham.setAutoCreateRowSorter(true);
        tblKieuSanPham.setModel(dtmKieuSanPham);

    }

    private void loadTblSanPham() {
        ArrayList<SanPham> dsSanPham = SanPhamDao.getAll();
        setSanPhamModel(dsSanPham);
        //lấy giá trị dòng được sửa
        dtmSanPham.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE && e.getColumn() != TableModelEvent.ALL_COLUMNS && e.getLastRow() == e.getFirstRow()) {
                    int editedRow = e.getFirstRow();
                    long id = (Long) tblSanPham.getValueAt(editedRow, 0);
                    String ten = (String) tblSanPham.getValueAt(editedRow, 1);
                    LoaiSanPham loaiSp = (LoaiSanPham) tblSanPham.getValueAt(editedRow, 2);
                    TableColumn idLoaiSpColumn = tblSanPham.getColumnModel().getColumn(2);
                    TableCellEditor cellEditor = idLoaiSpColumn.getCellEditor();
                    LoaiSanPham lsp = null;
                    if (cellEditor instanceof DefaultCellEditor) {
                        Component component = ((DefaultCellEditor) cellEditor).getComponent();
                        if (component instanceof JComboBox) {
                            JComboBox<?> comboBox = (JComboBox<?>) component;
                            lsp = (LoaiSanPham) comboBox.getSelectedItem();
                        }
                    }
                    long giaBan = Long.parseLong(tblSanPham.getValueAt(editedRow, 3) + "");
                    int giamGia = Integer.parseInt(tblSanPham.getValueAt(editedRow, 4) + "");
                    String moTa = (String) tblSanPham.getValueAt(editedRow, 5);
                    Date ngayThem = (Date) tblSanPham.getValueAt(editedRow, 6);
                    String tt = (String) tblSanPham.getValueAt(editedRow, 7);
                    String tonTai = null;
                    TableColumn tonTaiCbo = tblSanPham.getColumnModel().getColumn(7);
                    TableCellEditor cellEditor2 = tonTaiCbo.getCellEditor();
                    if (cellEditor instanceof DefaultCellEditor) {
                        Component component = ((DefaultCellEditor) cellEditor2).getComponent();
                        if (component instanceof JComboBox) {
                            JComboBox<String> comboBox = (JComboBox<String>) component;
                            tonTai = (String) comboBox.getSelectedItem();
                        }
                    }
                    lstUpdateSP.add(new SanPham(id, ten, loaiSp.getIdLoaiSP(), giaBan, giamGia, moTa, ngayThem, tt.contains("true") ? true : false));
                }
            }
        });
        // Add popup delete
        final JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem deleteItem = new JMenuItem("Delete row");
        deleteItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tblSanPham.getSelectedRow();
                if (selectedRow != -1) {
                    int id = (Integer) tblSanPham.getValueAt(tblSanPham.getSelectedRow(), 0);
                    SanPham sp = new SanPham();
                    sp.setIdSanPham(id);
                    lstDeleteSP.add(sp);
                    dtmSanPham.removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn dòng dữ liệu", "Thông báo", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        popupMenu.add(deleteItem);
        tblSanPham.setComponentPopupMenu(popupMenu);

        //Render combo box for cell of table
        ArrayList<LoaiSanPham> dsLSP = LoaiSanPhamDAO.getAll();
        Object[] options = new Object[dsLSP.size()];
        for (int i = 0; i < dsLSP.size(); i++) {
            options[i] = dsLSP.get(i);
        }
        JComboBox<Object> comboBox = new JComboBox<>(options);
        DefaultCellEditor editor = new DefaultCellEditor(comboBox);
        tblSanPham.getColumnModel().getColumn(2).setCellEditor(editor);

        String[] tonTais = {"true", "false"};
        JComboBox<String> comboBox2 = new JComboBox<>(tonTais);
        DefaultCellEditor editor2 = new DefaultCellEditor(comboBox2);
        tblSanPham.getColumnModel().getColumn(7).setCellEditor(editor2);
    }

    private void LoadCboLoaiSanPham() {
        cboLoaiSanPham.removeAllItems();
        cboSearchSPByLSP.removeAllItems();
        ArrayList<LoaiSanPham> dsLoai = LoaiSanPhamDAO.getAll();

        for (LoaiSanPham lsp : dsLoai) {
            cboLoaiSanPham.addItem(lsp);
            cboSearchSPByLSP.addItem(lsp);
        }
    }

    private void LoadCboSanPham() {
        cboSanPham.removeAllItems();

        ArrayList<SanPham> dsSanPhams = SanPhamDao.getAll();

        for (SanPham sp : dsSanPhams) {
            cboSanPham.addItem(sp);
        }
    }

    private void resetFieldsTTSP() {
        txtMaSP.setText("");
        txtTenSP.setText("");
        cboLoaiSanPham.setSelectedIndex(-1);
        txtGiaBan.setText("");
        txtGiamGia.setText("");
        txtMoTa.setText("");
        txtNgayThem.setText("");
        btnGroupTonTai.clearSelection();
    }

    private void resetFieldsTTKSP() {
        txtMaKSP.setText("");
        txtBarCode.setText("");
        txtMau.setText("");
        txtSize.setText("");
        txtImageUrl.setText("");
        labelAnh.setIcon(null);
        txtSoLuongTon.setText("");
    }

    private void showAnh(String imgUrl) {
        String projectDir = System.getProperty("user.dir");
        Path srcPath = Paths.get(projectDir, "src", "Image", "product");
        String filePath = srcPath.toString();
        try {
            File file = new File(filePath + "\\" + imgUrl);
            if (file.exists()) {
                BufferedImage image = ImageIO.read(file);

                if (image != null) {
                    int width = 120;
                    int height = 160;
                    Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);

                    ImageIcon icon = new ImageIcon(scaledImage);

                    labelAnh.setIcon(icon);
                } else {
                    labelAnh.setText("Không thể đọc ảnh từ tệp");
                    System.err.println("ImageIO.read(file) returned null for file: " + filePath + "\\" + imgUrl);
                }
            } else {
                labelAnh.setText("Không tìm thấy tài nguyên ảnh");
                System.err.println("File does not exist: " + filePath + "\\" + imgUrl);
            }

        } catch (IOException ex) {
            labelAnh.setText("Lỗi khi đọc ảnh");
            Logger.getLogger(frmQLSanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGroupTonTai = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtNgayThem = new javax.swing.JFormattedTextField();
        txtGiamGia = new javax.swing.JTextField();
        txtGiaBan = new javax.swing.JTextField();
        cboLoaiSanPham = new javax.swing.JComboBox<>();
        txtMaSP = new javax.swing.JTextField();
        txtTenSP = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        btnThemSp = new javax.swing.JButton();
        btnXoaSp = new javax.swing.JButton();
        btnCapNhatSp = new javax.swing.JButton();
        resetFiledsTTSP = new javax.swing.JButton();
        rdbCo = new javax.swing.JRadioButton();
        rdbKhong = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtMaKSP = new javax.swing.JTextField();
        txtBarCode = new javax.swing.JTextField();
        txtMau = new javax.swing.JTextField();
        txtSize = new javax.swing.JTextField();
        txtSoLuongTon = new javax.swing.JTextField();
        labelAnh = new javax.swing.JLabel();
        btnThemKSP = new javax.swing.JButton();
        btnXoaKSP = new javax.swing.JButton();
        btnCapNhatKSP = new javax.swing.JButton();
        resetFieldsTTKSP = new javax.swing.JButton();
        cboSanPham = new javax.swing.JComboBox<>();
        btnUpload = new javax.swing.JButton();
        txtImageUrl = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblKieuSanPham = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        txtSearchSPByTen = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        btnSearchSpByTen = new javax.swing.JButton();
        btnSearchSPByLSP = new javax.swing.JButton();
        cboSearchSPByLSP = new javax.swing.JComboBox<>();
        btnResetSearchSP = new javax.swing.JButton();
        btnLuuSP = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtBatDau = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtKetThuc = new javax.swing.JTextField();
        btnSearchSpByGia = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(1536, 778));
        setMinimumSize(new java.awt.Dimension(1536, 778));
        setPreferredSize(new java.awt.Dimension(1536, 778));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(187, 54, 137));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("QUẢN LÝ SẢN PHẨM");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Mã sản phẩm");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 33, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Tên sản phẩm");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(293, 33, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Loại sản phẩm");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(633, 33, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Tồn tại");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(73, 118, -1, 28));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Giá bán");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(68, 72, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Giảm giá");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(326, 72, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Mô tả");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(344, 122, -1, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Ngày thêm");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(655, 72, -1, -1));

        txtNgayThem.setEditable(false);
        txtNgayThem.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT))));
        txtNgayThem.setEnabled(false);
        jPanel1.add(txtNgayThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(743, 68, 159, 28));

        txtGiamGia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtGiamGia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtGiamGiaKeyTyped(evt);
            }
        });
        jPanel1.add(txtGiamGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 70, 178, -1));

        txtGiaBan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtGiaBan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtGiaBanKeyTyped(evt);
            }
        });
        jPanel1.add(txtGiaBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(134, 69, 130, -1));

        cboLoaiSanPham.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboLoaiSanPham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(cboLoaiSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(743, 30, 159, -1));

        txtMaSP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaSP.setEnabled(false);
        jPanel1.add(txtMaSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(134, 30, 130, -1));

        txtTenSP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel1.add(txtTenSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 30, 178, -1));

        txtMoTa.setColumns(20);
        txtMoTa.setLineWrap(true);
        txtMoTa.setRows(5);
        jScrollPane1.setViewportView(txtMoTa);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(412, 118, 490, 50));

        btnThemSp.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnThemSp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/add.png"))); // NOI18N
        btnThemSp.setText("Thêm");
        btnThemSp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSpActionPerformed(evt);
            }
        });
        jPanel1.add(btnThemSp, new org.netbeans.lib.awtextra.AbsoluteConstraints(282, 180, 115, -1));

        btnXoaSp.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnXoaSp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/delete.png"))); // NOI18N
        btnXoaSp.setText("Xoá");
        btnXoaSp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSpActionPerformed(evt);
            }
        });
        jPanel1.add(btnXoaSp, new org.netbeans.lib.awtextra.AbsoluteConstraints(704, 180, 103, -1));

        btnCapNhatSp.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnCapNhatSp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/updated.png"))); // NOI18N
        btnCapNhatSp.setText("Cập nhật");
        btnCapNhatSp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatSpActionPerformed(evt);
            }
        });
        jPanel1.add(btnCapNhatSp, new org.netbeans.lib.awtextra.AbsoluteConstraints(492, 180, -1, -1));

        resetFiledsTTSP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        resetFiledsTTSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/reset.png"))); // NOI18N
        resetFiledsTTSP.setText("Reset");
        resetFiledsTTSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetFiledsTTSPActionPerformed(evt);
            }
        });
        jPanel1.add(resetFiledsTTSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(124, 180, 103, -1));

        btnGroupTonTai.add(rdbCo);
        rdbCo.setText("Có");
        jPanel1.add(rdbCo, new org.netbeans.lib.awtextra.AbsoluteConstraints(134, 123, -1, -1));

        btnGroupTonTai.add(rdbKhong);
        rdbKhong.setText("Không");
        jPanel1.add(rdbKhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(189, 123, -1, -1));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin kiểu sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Mã kiểu sản phẩm");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 33, -1, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("BarCode");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(306, 33, -1, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Sản phẩm");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 82, -1, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Màu");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 132, -1, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Ảnh");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(321, 77, 39, 31));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("Size");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 179, -1, -1));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setText("Số lượng tồn kho");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 226, -1, -1));

        txtMaKSP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaKSP.setEnabled(false);
        jPanel2.add(txtMaKSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 30, 150, -1));

        txtBarCode.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtBarCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBarCodeKeyTyped(evt);
            }
        });
        jPanel2.add(txtBarCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(366, 30, 164, -1));

        txtMau.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel2.add(txtMau, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 130, 150, -1));

        txtSize.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel2.add(txtSize, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 176, 150, -1));

        txtSoLuongTon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSoLuongTon.setEnabled(false);
        jPanel2.add(txtSoLuongTon, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 223, 150, -1));

        labelAnh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelAnh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.add(labelAnh, new org.netbeans.lib.awtextra.AbsoluteConstraints(376, 129, 120, 160));

        btnThemKSP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnThemKSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/add.png"))); // NOI18N
        btnThemKSP.setText("Thêm");
        btnThemKSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemKSPActionPerformed(evt);
            }
        });
        jPanel2.add(btnThemKSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(194, 261, 111, -1));

        btnXoaKSP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnXoaKSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/delete.png"))); // NOI18N
        btnXoaKSP.setText("Xoá");
        btnXoaKSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaKSPActionPerformed(evt);
            }
        });
        jPanel2.add(btnXoaKSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(194, 310, 111, -1));

        btnCapNhatKSP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnCapNhatKSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/updated.png"))); // NOI18N
        btnCapNhatKSP.setText("Cập nhật");
        btnCapNhatKSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatKSPActionPerformed(evt);
            }
        });
        jPanel2.add(btnCapNhatKSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 310, -1, -1));

        resetFieldsTTKSP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        resetFieldsTTKSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/reset.png"))); // NOI18N
        resetFieldsTTKSP.setText("Reset");
        resetFieldsTTKSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetFieldsTTKSPActionPerformed(evt);
            }
        });
        jPanel2.add(resetFieldsTTKSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 261, 115, -1));

        cboSanPham.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboSanPham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboSanPham.setEnabled(false);
        jPanel2.add(cboSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 79, 150, -1));

        btnUpload.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnUpload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/upload.png"))); // NOI18N
        btnUpload.setText("Upload");
        btnUpload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUploadActionPerformed(evt);
            }
        });
        jPanel2.add(btnUpload, new org.netbeans.lib.awtextra.AbsoluteConstraints(382, 301, -1, -1));

        txtImageUrl.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtImageUrl.setEnabled(false);
        jPanel2.add(txtImageUrl, new org.netbeans.lib.awtextra.AbsoluteConstraints(366, 79, 164, -1));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
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
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblSanPham);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách kiểu sản phẩm"));

        tblKieuSanPham.setModel(new javax.swing.table.DefaultTableModel(
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
        tblKieuSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKieuSanPhamMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblKieuSanPham);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 519, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Hành động"));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setText("Tên sản phẩm");

        txtSearchSPByTen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setText("Loại sản phẩm");

        btnSearchSpByTen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSearchSpByTen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/find.png"))); // NOI18N
        btnSearchSpByTen.setText("Tìm");
        btnSearchSpByTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchSpByTenActionPerformed(evt);
            }
        });

        btnSearchSPByLSP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSearchSPByLSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/find.png"))); // NOI18N
        btnSearchSPByLSP.setText("Tìm");
        btnSearchSPByLSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchSPByLSPActionPerformed(evt);
            }
        });

        cboSearchSPByLSP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboSearchSPByLSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnResetSearchSP.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnResetSearchSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/reset.png"))); // NOI18N
        btnResetSearchSP.setText("Reset");
        btnResetSearchSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetSearchSPActionPerformed(evt);
            }
        });

        btnLuuSP.setBackground(new java.awt.Color(13, 110, 253));
        btnLuuSP.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnLuuSP.setForeground(java.awt.Color.white);
        btnLuuSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-save-30.png"))); // NOI18N
        btnLuuSP.setText("Lưu dữ liệu");
        btnLuuSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuSPActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setText("Tìm theo giá");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel20.setText("Bắt đầu");

        txtBatDau.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtBatDau.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBatDauKeyTyped(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel21.setText("Kết thúc");

        txtKetThuc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtKetThuc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtKetThucKeyTyped(evt);
            }
        });

        btnSearchSpByGia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSearchSpByGia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/find.png"))); // NOI18N
        btnSearchSpByGia.setText("Tìm");
        btnSearchSpByGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchSpByGiaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnResetSearchSP)
                        .addGap(50, 50, 50)
                        .addComponent(btnLuuSP))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtSearchSPByTen, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addComponent(btnSearchSpByTen))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel19))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnSearchSpByGia))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel21)
                                .addGap(31, 31, 31)
                                .addComponent(txtKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(58, 58, 58)
                        .addComponent(jLabel18)
                        .addGap(30, 30, 30)
                        .addComponent(cboSearchSPByLSP, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(btnSearchSPByLSP)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnSearchSPByLSP)
                    .addComponent(jLabel17)
                    .addComponent(txtSearchSPByTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearchSpByTen)
                    .addComponent(jLabel18)
                    .addComponent(cboSearchSPByLSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(btnSearchSpByGia))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnLuuSP)
                            .addComponent(btnResetSearchSP))
                        .addGap(7, 7, 7))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(txtBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21)
                            .addComponent(txtKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(561, 561, 561))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(41, 41, 41))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCapNhatSpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatSpActionPerformed
        if (txtMaSP.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm muốn cập nhật ở danh sách dưới!");
        } else {
            String ten = txtTenSP.getText();
            LoaiSanPham selected = (LoaiSanPham) cboLoaiSanPham.getSelectedItem();
            String giaBan = txtGiaBan.getText();
            String giamGia = txtGiamGia.getText();

            if (ten.length() == 0) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập tên sản phẩm");
                return;
            }
            if (selected == null) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn loại sản phẩm");
                return;
            }
            if (giaBan.length() == 0) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập giá bán");
                return;
            }
            if (giamGia.length() == 0) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập giảm giá");
                return;
            }
            if (btnGroupTonTai.getSelection() == null) {
                JOptionPane.showMessageDialog(this, "Vui chọn tồn tại");
                return;
            }
            SanPham sp = new SanPham();
            sp.setIdSanPham(Integer.parseInt(txtMaSP.getText()));
            sp.setTenSanPham(ten);
            sp.setIdLoaiSP(selected.getIdLoaiSP());
            sp.setGiaBan(Long.parseLong(giaBan));
            sp.setGiamGia(Integer.parseInt(giamGia));
            sp.setMoTa(txtMoTa.getText());
            boolean tonTai = false;
            if (rdbCo.isSelected()) {
                tonTai = true;
            } else {
                tonTai = false;
            }
            sp.setTonTai(tonTai);

            boolean result = SanPhamDao.updateById(sp);
            if (result) {
                JOptionPane.showMessageDialog(this, "Cập nhật thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                loadTblSanPham();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnCapNhatSpActionPerformed

    private void btnCapNhatKSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatKSPActionPerformed
        if (txtMaKSP.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn kiểu sản phẩm muốn xóa ở danh sách dưới!");
        } else {
            String barCodeStr = txtBarCode.getText();
            SanPham selected = (SanPham) cboSanPham.getSelectedItem();
            String mau = txtMau.getText();
            String anhUrl = txtImageUrl.getText();
            String size = txtSize.getText();

            if (barCodeStr.length() == 0) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập barcode");
                return;
            }
            if (mau.length() == 0) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập màu");
                return;
            }
            if (anhUrl.length() == 0) {
                JOptionPane.showMessageDialog(this, "Vui lòng tải hình ảnh");
                return;
            }
            if (size.length() == 0) {
                JOptionPane.showMessageDialog(this, "Vui chọn nhập size");
                return;
            }
            int barCode = Integer.parseInt(barCodeStr);
            KieuSanPham ksp = new KieuSanPham();
            ksp.setIdKieuSanPham(Long.parseLong(txtMaKSP.getText()));
            ksp.setBarCode(barCode);
            ksp.setIdSanPham(selected.getIdSanPham());
            ksp.setMau(mau);
            ksp.setAnhSP(anhUrl);
            ksp.setSize(size);
            boolean result = KieuSanPhamDAO.updateById(ksp);
            if (result) {
                JOptionPane.showMessageDialog(this, "Cập nhật kiểu sản phẩm thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                ArrayList<KieuSanPham> lsdKSP = KieuSanPhamDAO.getByIdSanPham(selected.getIdSanPham());
                setKieuSanPhamModel(lsdKSP);
                resetFieldsTTKSP();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật kiểu sản phẩm thất bại", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        }

    }//GEN-LAST:event_btnCapNhatKSPActionPerformed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        resetFieldsTTKSP();
        int row = tblSanPham.getSelectedRow();
        txtMaSP.setText(tblSanPham.getValueAt(row, 0) + "");
        txtTenSP.setText((String) tblSanPham.getValueAt(row, 1));
        Object itemSelectObj = tblSanPham.getValueAt(row, 2);
        LoaiSanPham itemSelect = (LoaiSanPham) itemSelectObj;
        for (int i = 0; i < cboLoaiSanPham.getItemCount(); i++) {
            String cboValue = cboLoaiSanPham.getItemAt(i) + "";
            if (cboValue.equals(itemSelect.getTenLoaiSP())) {
                cboLoaiSanPham.setSelectedIndex(i);
                break;
            }
        }
        txtGiaBan.setText(tblSanPham.getValueAt(row, 3) + "");
        txtGiamGia.setText(tblSanPham.getValueAt(row, 4) + "");
        txtMoTa.setText((String) tblSanPham.getValueAt(row, 5));
        txtNgayThem.setText(tblSanPham.getValueAt(row, 6) + "");
        if (((String) tblSanPham.getValueAt(row, 7)).contains("true")) {
            rdbCo.setSelected(true);
        } else {
            rdbKhong.setSelected(true);
        }

        ArrayList<KieuSanPham> lsdKSP = KieuSanPhamDAO.getByIdSanPham((long) tblSanPham.getValueAt(row, 0));
        setKieuSanPhamModel(lsdKSP);

    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void resetFiledsTTSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetFiledsTTSPActionPerformed
        resetFieldsTTSP();
    }//GEN-LAST:event_resetFiledsTTSPActionPerformed

    private void resetFieldsTTKSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetFieldsTTKSPActionPerformed
        resetFieldsTTKSP();
    }//GEN-LAST:event_resetFieldsTTKSPActionPerformed

    private void tblKieuSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKieuSanPhamMouseClicked
        int row = tblKieuSanPham.getSelectedRow();
        txtMaKSP.setText(tblKieuSanPham.getValueAt(row, 0) + "");
        txtBarCode.setText(tblKieuSanPham.getValueAt(row, 1) + "");
        Object itemSelectObj = tblKieuSanPham.getValueAt(row, 2);
        SanPham itemSelect = (SanPham) itemSelectObj;
        for (int i = 0; i < cboSanPham.getItemCount(); i++) {
            String cboValue = cboSanPham.getItemAt(i) + "";
            if (cboValue.equals(itemSelect.getTenSanPham())) {
                cboSanPham.setSelectedIndex(i);
                break;
            }
        }
        txtMau.setText(tblKieuSanPham.getValueAt(row, 3) + "");
        txtImageUrl.setText(tblKieuSanPham.getValueAt(row, 4) + "");
        showAnh(tblKieuSanPham.getValueAt(row, 4) + "");
        txtSize.setText(tblKieuSanPham.getValueAt(row, 5) + "");
        txtSoLuongTon.setText(tblKieuSanPham.getValueAt(row, 6) + "");

    }//GEN-LAST:event_tblKieuSanPhamMouseClicked

    private void btnUploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUploadActionPerformed
        JFileChooser fileChooser = new JFileChooser();

        FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes());

        fileChooser.setFileFilter(imageFilter);

        String userHome = System.getProperty("user.home");
        File defaultDirectory = new File(userHome, "Downloads");
        fileChooser.setCurrentDirectory(defaultDirectory);

        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                String projectDir = System.getProperty("user.dir");

                String newFileName = System.currentTimeMillis() + ".jpg";
                Path destinationPath = Paths.get(projectDir, "src", "Image", "product", newFileName);

                // Ensure the directory exists
                Files.createDirectories(destinationPath.getParent());

                Files.copy(selectedFile.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
                txtImageUrl.setText(newFileName);
                showAnh(newFileName);
                JOptionPane.showMessageDialog(null, "Tải ảnh thành công!");
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error uploading file: " + ex.getMessage());
            }
        }


    }//GEN-LAST:event_btnUploadActionPerformed

    private void btnThemKSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemKSPActionPerformed
        String barCodeStr = txtBarCode.getText();
        SanPham selected = (SanPham) cboSanPham.getSelectedItem();
        String mau = txtMau.getText();
        String anhUrl = txtImageUrl.getText();
        String size = txtSize.getText();

        if (barCodeStr.length() == 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập barcode");
            return;
        }
        if (mau.length() == 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập màu");
            return;
        }
        if (anhUrl.length() == 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng tải hình ảnh");
            return;
        }
        if (size.length() == 0) {
            JOptionPane.showMessageDialog(this, "Vui chọn nhập size");
            return;
        }
        int barCode = Integer.parseInt(barCodeStr);
        if (KieuSanPhamDAO.isBarcodeExists(barCode)) {
            JOptionPane.showMessageDialog(this, "Barcode đã tồn tại. Vui lòng nhập mã khác");
            return;
        }
        if (KieuSanPhamDAO.isKieuSanPhamExists(selected.getIdSanPham(), mau, size)) {
            JOptionPane.showMessageDialog(this, "Kiểu sản phẩm này đã tồn tại. Vui lòng đổi kiểu khác.");
            return;
        }

        KieuSanPham ksp = new KieuSanPham();
        ksp.setBarCode(barCode);
        ksp.setIdSanPham(selected.getIdSanPham());
        ksp.setMau(mau);
        ksp.setAnhSP(anhUrl);
        ksp.setSize(size);
        boolean result = KieuSanPhamDAO.add(ksp);
        if (result) {
            JOptionPane.showMessageDialog(this, "Thêm kiểu sản phẩm thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            ArrayList<KieuSanPham> lsdKSP = KieuSanPhamDAO.getByIdSanPham(selected.getIdSanPham());
            setKieuSanPhamModel(lsdKSP);
            resetFieldsTTKSP();
        } else {
            JOptionPane.showMessageDialog(this, "Thêm kiểu sản phẩm thất bại", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnThemKSPActionPerformed

    private void btnXoaKSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaKSPActionPerformed
        if (txtMaKSP.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọnkiểu sản phẩm muốn xóa ở danh sách dưới!");
        } else {
            long maKSP = Long.parseLong(txtMaKSP.getText());
            if (KieuSanPhamDAO.isForeignKeyExists(maKSP)) {
                JOptionPane.showMessageDialog(this, "Kiểu sản phẩm cha đang được sử dụng và không thể xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } else {
                boolean success = KieuSanPhamDAO.deleteById(maKSP);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    SanPham selected = (SanPham) cboSanPham.getSelectedItem();
                    ArrayList<KieuSanPham> lsdKSP = KieuSanPhamDAO.getByIdSanPham(selected.getIdSanPham());
                    setKieuSanPhamModel(lsdKSP);
                    resetFieldsTTKSP();
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa không thành công!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_btnXoaKSPActionPerformed

    private void btnThemSpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSpActionPerformed
        String ten = txtTenSP.getText();
        LoaiSanPham selected = (LoaiSanPham) cboLoaiSanPham.getSelectedItem();
        String giaBan = txtGiaBan.getText();
        String giamGia = txtGiamGia.getText();

        if (ten.length() == 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên sản phẩm");
            return;
        }
        if (selected == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn loại sản phẩm");
            return;
        }
        if (giaBan.length() == 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập giá bán");
            return;
        }
        if (giamGia.length() == 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập giảm giá");
            return;
        }
        if (btnGroupTonTai.getSelection() == null) {
            JOptionPane.showMessageDialog(this, "Vui chọn tồn tại");
            return;
        }
        if (SanPhamDao.isTenExists(ten)) {
            JOptionPane.showMessageDialog(this, "Tên này đã tồn tại. VUi lòng chọn tên khác");
            return;
        }
        SanPham sp = new SanPham();
        sp.setTenSanPham(ten);
        sp.setIdLoaiSP(selected.getIdLoaiSP());
        sp.setGiaBan(Long.parseLong(giaBan));
        sp.setGiamGia(Integer.parseInt(giamGia));
        sp.setMoTa(txtMoTa.getText());
        boolean tonTai = false;
        if (rdbCo.isSelected()) {
            tonTai = true;
        } else {
            tonTai = false;
        }
        sp.setTonTai(tonTai);
        boolean result = SanPhamDao.add(sp);
        if (result) {
            JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            loadTblSanPham();
        } else {
            JOptionPane.showMessageDialog(this, "Thêm sản phẩm thất bại", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnThemSpActionPerformed

    private void btnXoaSpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSpActionPerformed
        if (txtMaSP.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm muốn xóa ở danh sách dưới!");
        } else {
            int maSp = Integer.parseInt(txtMaSP.getText());
            if (SanPhamDao.isForeignKeyExists(maSp)) {
                JOptionPane.showMessageDialog(this, "Sản phẩm đang được sử dụng và không thể xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } else {
                boolean success = SanPhamDao.deleteById(maSp);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    resetFieldsTTSP();
                    loadTblSanPham();
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa không thành công!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_btnXoaSpActionPerformed

    private void txtGiaBanKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGiaBanKeyTyped
        char c = evt.getKeyChar();
        if (!((c >= '0' && c <= '9') || (c == evt.VK_BACK_SPACE) || (c == evt.VK_DELETE))) {
            evt.consume();
        }
    }//GEN-LAST:event_txtGiaBanKeyTyped

    private void txtGiamGiaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGiamGiaKeyTyped
        char c = evt.getKeyChar();
        if (!((c >= '0' && c <= '9') || (c == evt.VK_BACK_SPACE) || (c == evt.VK_DELETE))) {
            evt.consume();
        }
    }//GEN-LAST:event_txtGiamGiaKeyTyped

    private void txtBarCodeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBarCodeKeyTyped
        char c = evt.getKeyChar();
        if (!((c >= '0' && c <= '9') || (c == evt.VK_BACK_SPACE) || (c == evt.VK_DELETE))) {
            evt.consume();
        }
    }//GEN-LAST:event_txtBarCodeKeyTyped

    private void btnSearchSpByTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchSpByTenActionPerformed
        String searchValue = txtSearchSPByTen.getText();
        if (searchValue.length() == 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập giá trị tìm kiếm!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        } else {
            ArrayList<SanPham> lst = SanPhamDao.getByTen(searchValue);
            setSanPhamModel(lst);
        }
    }//GEN-LAST:event_btnSearchSpByTenActionPerformed

    private void btnSearchSPByLSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchSPByLSPActionPerformed
        LoaiSanPham selected = (LoaiSanPham) cboSearchSPByLSP.getSelectedItem();

        if (selected == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập giá trị tìm kiếm!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        } else {
            ArrayList<SanPham> lst = SanPhamDao.getByLoaiSanPham(selected.getIdLoaiSP());
            setSanPhamModel(lst);
        }
    }//GEN-LAST:event_btnSearchSPByLSPActionPerformed

    private void btnResetSearchSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetSearchSPActionPerformed
        ArrayList<SanPham> lst = SanPhamDao.getAll();
        setSanPhamModel(lst);
    }//GEN-LAST:event_btnResetSearchSPActionPerformed

    private void btnLuuSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuSPActionPerformed
        SQLServerDataProvider provider = new SQLServerDataProvider();
        if (!lstUpdateSP.isEmpty() || !lstDeleteSP.isEmpty()) {

            try {
                provider.open();
                provider.startTransaction();

                SanPhamDao.updateByList(lstUpdateSP);
                SanPhamDao.deleteByList(lstDeleteSP);

                provider.commitTransaction();
                lstUpdateSP.clear();
                lstDeleteSP.clear();
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

    }//GEN-LAST:event_btnLuuSPActionPerformed

    private void btnSearchSpByGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchSpByGiaActionPerformed
        String batDauStr = txtBatDau.getText();
        String ketThucStr = txtKetThuc.getText();

        if (batDauStr.length() == 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập giá trị bắt đầu!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (ketThucStr.length() == 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập giá trị kết thúc!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        long batDau = Long.parseLong(batDauStr);
        long ketThuc = Long.parseLong(ketThucStr);
        if (batDau > ketThuc) {
            JOptionPane.showMessageDialog(this, "Giá trị bắt đầu phải nhỏ hớn kết thúc!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        ArrayList<SanPham> lst = SanPhamDao.getByGia(batDau, ketThuc);
        setSanPhamModel(lst);
    }//GEN-LAST:event_btnSearchSpByGiaActionPerformed

    private void txtBatDauKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBatDauKeyTyped
        char c = evt.getKeyChar();
        if (!((c >= '0' && c <= '9') || (c == evt.VK_BACK_SPACE) || (c == evt.VK_DELETE))) {
            evt.consume();
        }
    }//GEN-LAST:event_txtBatDauKeyTyped

    private void txtKetThucKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKetThucKeyTyped
        char c = evt.getKeyChar();
        if (!((c >= '0' && c <= '9') || (c == evt.VK_BACK_SPACE) || (c == evt.VK_DELETE))) {
            evt.consume();
        }
    }//GEN-LAST:event_txtKetThucKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhatKSP;
    private javax.swing.JButton btnCapNhatSp;
    private javax.swing.ButtonGroup btnGroupTonTai;
    private javax.swing.JButton btnLuuSP;
    private javax.swing.JButton btnResetSearchSP;
    private javax.swing.JButton btnSearchSPByLSP;
    private javax.swing.JButton btnSearchSpByGia;
    private javax.swing.JButton btnSearchSpByTen;
    private javax.swing.JButton btnThemKSP;
    private javax.swing.JButton btnThemSp;
    private javax.swing.JButton btnUpload;
    private javax.swing.JButton btnXoaKSP;
    private javax.swing.JButton btnXoaSp;
    private javax.swing.JComboBox<Object> cboLoaiSanPham;
    private javax.swing.JComboBox<Object> cboSanPham;
    private javax.swing.JComboBox<Object> cboSearchSPByLSP;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel labelAnh;
    private javax.swing.JRadioButton rdbCo;
    private javax.swing.JRadioButton rdbKhong;
    private javax.swing.JButton resetFieldsTTKSP;
    private javax.swing.JButton resetFiledsTTSP;
    private javax.swing.JTable tblKieuSanPham;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtBarCode;
    private javax.swing.JTextField txtBatDau;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtGiamGia;
    private javax.swing.JTextField txtImageUrl;
    private javax.swing.JTextField txtKetThuc;
    private javax.swing.JTextField txtMaKSP;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtMau;
    private javax.swing.JTextArea txtMoTa;
    private javax.swing.JFormattedTextField txtNgayThem;
    private javax.swing.JTextField txtSearchSPByTen;
    private javax.swing.JTextField txtSize;
    private javax.swing.JTextField txtSoLuongTon;
    private javax.swing.JTextField txtTenSP;
    // End of variables declaration//GEN-END:variables
}
