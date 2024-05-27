/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package GUI;

import DAO.HoaDonDao;
import DAO.KhachHangDAO;
import DAO.KieuSanPhamDAO;
import DAO.SanPhamThongKeDAO;
import POJO.ChiTietHoaDon;
import POJO.HoaDon;
import POJO.KhachHang;
import POJO.KieuSanPham;
import POJO.NhanVienLogin;
import POJO.SanPhamThongKe;
import POJO.ThongTinHoaDon;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Nah nah
 */
public class frmThanhToan extends javax.swing.JInternalFrame {

    /**
     * Creates new form frmThanhToan
     */
    DefaultTableModel dtmSpThanhToan = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return column != 0;
        }
    };
    HoaDon hoaDon = new HoaDon();
    ChiTietHoaDon cthdSelected = null;
    KhachHang khCurrent = null;
    private Timer debounceTimer;
    private static final int DEBOUNCE_DELAY = 300;

    public frmThanhToan() {
        initComponents();

        txtTenNV.setText(NhanVienLogin.getNhanVienLogin().nhanVien.getTenNhanVien());

        String[] tieuDe = {"Barcode", "Tên sản phẩm", "Màu", "Size", "Đơn giá", "Giảm giá", "Số lượng", "Thành tiền"};
        dtmSpThanhToan.setColumnIdentifiers(tieuDe);
    }

    private void updateTTHang() {
        txtTongTienHang.setText(hoaDon.tinhTongTien() + "");
    }

    private void updateTTHD() {
        txtTongTienHD.setText((hoaDon.tinhTongTien() - Long.parseLong(txtTienGiam.getText()) + ""));
    }

    private void resetTTSanPham() {
        txtTenSP.setText("");
        txtMau.setText("");
        txtSize.setText("");
        txtSoLuong.setText("");
        txtDonGia.setText("");
        txtGiamGia.setText("");
        txtThanhTien.setText("");
        labelAnh.setIcon(null);
    }

    private void resetTTKhachHang() {
        txtTenKh.setText("");
        txtSDT.setText("");
        txtDiem.setText("");
        rdKhongSuDung.setSelected(true);
    }

    private void resetTTHoaDon() {
        cboPTTT.setSelectedIndex(1);
        txtTongTienHang.setText("");
        txtTienGiam.setText("0");
        txtTienKhachDua.setText("");
        txtTienThoi.setText("");
    }

    private void setSpThanhToanModel(ArrayList<ChiTietHoaDon> dsCTHD) {
        dtmSpThanhToan.setRowCount(0);
        for (ChiTietHoaDon cthd : dsCTHD) {
            Vector<Object> vec = new Vector<Object>();
            vec.add(cthd.getKieuSanPham().getBarCode());
            vec.add(cthd.getKieuSanPham().getSanPham().getTenSanPham());
            vec.add(cthd.getKieuSanPham().getMau());
            vec.add(cthd.getKieuSanPham().getSize());
            long donGia = cthd.getKieuSanPham().getSanPham().getGiaBan();
            vec.add(donGia);
            int giamGia = cthd.getKieuSanPham().getSanPham().getGiamGia();
            vec.add(giamGia);
            int soLuong = cthd.getSoLuong();
            vec.add(soLuong);
            vec.add(cthd.getDonGia() * soLuong);

            dtmSpThanhToan.addRow(vec);
        }
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tblSpThanhToan.getModel());
        tblSpThanhToan.setRowSorter(sorter);
        tblSpThanhToan.setAutoCreateRowSorter(true);
        tblSpThanhToan.setModel(dtmSpThanhToan);
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

        btnGroupDiem = new javax.swing.ButtonGroup();
        jPanel8 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtTenKh = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtDiem = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        rdSuDung = new javax.swing.JRadioButton();
        rdKhongSuDung = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSpThanhToan = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtBarcode = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtThanhTien = new javax.swing.JTextField();
        txtDonGia = new javax.swing.JTextField();
        txtGiamGia = new javax.swing.JTextField();
        labelAnh = new javax.swing.JLabel();
        btnReset = new javax.swing.JButton();
        btnthemSP = new javax.swing.JButton();
        btnupdate = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        txtMau = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtSize = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtTenSP = new javax.swing.JTextArea();
        btnXoaSp = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtTongTienHang = new javax.swing.JTextField();
        txtTienKhachDua = new javax.swing.JTextField();
        txtTienThoi = new javax.swing.JTextField();
        btnTaoHoaDon = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        txtTienGiam = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtTenNV = new javax.swing.JTextField();
        cboPTTT = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtTongTienHD = new javax.swing.JTextField();

        setMaximumSize(new java.awt.Dimension(1536, 778));
        setMinimumSize(new java.awt.Dimension(1536, 778));
        setPreferredSize(new java.awt.Dimension(1536, 778));

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(187, 54, 137));
        jLabel11.setText("Tên khách hàng");

        txtTenKh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTenKh.setEnabled(false);

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(187, 54, 137));
        jLabel16.setText("Số điện thoại");

        txtSDT.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSDT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSDTKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSDTKeyTyped(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(187, 54, 137));
        jLabel21.setText("Điểm");

        txtDiem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDiem.setEnabled(false);

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(187, 54, 137));
        jLabel22.setText("Sử dụng điểm?");

        btnGroupDiem.add(rdSuDung);
        rdSuDung.setText("Sử dụng");
        rdSuDung.setEnabled(false);
        rdSuDung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdSuDungActionPerformed(evt);
            }
        });

        btnGroupDiem.add(rdKhongSuDung);
        rdKhongSuDung.setSelected(true);
        rdKhongSuDung.setText("Không sử dụng");
        rdKhongSuDung.setEnabled(false);
        rdKhongSuDung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdKhongSuDungActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel22)
                    .addComponent(jLabel21)
                    .addComponent(jLabel11)
                    .addComponent(jLabel16))
                .addGap(31, 31, 31)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTenKh, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(rdSuDung)
                        .addGap(18, 18, 18)
                        .addComponent(rdKhongSuDung)))
                .addContainerGap(143, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtTenKh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txtDiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(rdSuDung)
                    .addComponent(rdKhongSuDung))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        tblSpThanhToan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblSpThanhToan.setModel(new javax.swing.table.DefaultTableModel(
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
        tblSpThanhToan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSpThanhToanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSpThanhToan);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(187, 54, 137));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("HOÁ ĐƠN BÁN HÀNG");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin mặt hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(187, 54, 137));
        jLabel2.setText("Barcode");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(187, 54, 137));
        jLabel3.setText("Số lượng");

        txtBarcode.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtBarcode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBarcodeKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBarcodeKeyTyped(evt);
            }
        });

        txtSoLuong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSoLuong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSoLuongKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSoLuongKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(187, 54, 137));
        jLabel4.setText("Tên sản phẩm");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(187, 54, 137));
        jLabel6.setText("Đơn giá");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(187, 54, 137));
        jLabel7.setText("Giảm giá");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(187, 54, 137));
        jLabel8.setText("Thành tiền");

        txtThanhTien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtThanhTien.setEnabled(false);

        txtDonGia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDonGia.setEnabled(false);

        txtGiamGia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtGiamGia.setEnabled(false);

        labelAnh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelAnh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/reset.png"))); // NOI18N
        btnReset.setText("Reset");
        btnReset.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnthemSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/add.png"))); // NOI18N
        btnthemSP.setText("Thêm sản phẩm");
        btnthemSP.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnthemSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemSPActionPerformed(evt);
            }
        });

        btnupdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/updated.png"))); // NOI18N
        btnupdate.setText("Cập nhật");
        btnupdate.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdateActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(187, 54, 137));
        jLabel19.setText("Màu");

        txtMau.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMau.setEnabled(false);

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(187, 54, 137));
        jLabel20.setText("Size");

        txtSize.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSize.setEnabled(false);

        txtTenSP.setColumns(20);
        txtTenSP.setLineWrap(true);
        txtTenSP.setRows(5);
        txtTenSP.setEnabled(false);
        jScrollPane2.setViewportView(txtTenSP);

        btnXoaSp.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnXoaSp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/delete.png"))); // NOI18N
        btnXoaSp.setText("Xoá");
        btnXoaSp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSpActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel7)
                        .addComponent(jLabel3))
                    .addComponent(jLabel2)
                    .addComponent(jLabel19))
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtBarcode, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(47, 47, 47)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtMau, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel20)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtThanhTien, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDonGia, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                    .addComponent(txtSize))
                .addGap(0, 185, Short.MAX_VALUE)
                .addComponent(labelAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(119, 119, 119)
                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81)
                .addComponent(btnthemSP)
                .addGap(94, 94, 94)
                .addComponent(btnupdate)
                .addGap(78, 78, 78)
                .addComponent(btnXoaSp, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(txtBarcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(txtMau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20)
                            .addComponent(txtSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(txtThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(btnthemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXoaSp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnupdate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin hóa đơn"));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(187, 54, 137));
        jLabel5.setText("Tiền hàng");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(187, 54, 137));
        jLabel17.setText("Tiền khách đưa:");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(187, 54, 137));
        jLabel18.setText("Tiền thối:");

        txtTongTienHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTongTienHang.setForeground(java.awt.Color.red);
        txtTongTienHang.setEnabled(false);

        txtTienKhachDua.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTienKhachDua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienKhachDuaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTienKhachDuaKeyTyped(evt);
            }
        });

        txtTienThoi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTienThoi.setEnabled(false);

        btnTaoHoaDon.setBackground(new java.awt.Color(153, 204, 255));
        btnTaoHoaDon.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnTaoHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-sent-30.png"))); // NOI18N
        btnTaoHoaDon.setText("Tạo hóa đơn");
        btnTaoHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHoaDonActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(187, 54, 137));
        jLabel23.setText("Tiền giảm:");

        txtTienGiam.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTienGiam.setText("0");
        txtTienGiam.setEnabled(false);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(187, 54, 137));
        jLabel10.setText("Tên nhân viên");

        txtTenNV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTenNV.setEnabled(false);

        cboPTTT.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboPTTT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tiền mặt", "Chuyển khoản" }));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(187, 54, 137));
        jLabel14.setText("Phương thức thanh toán");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(187, 54, 137));
        jLabel9.setText("Tổng tiền:");

        txtTongTienHD.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTongTienHD.setForeground(java.awt.Color.red);
        txtTongTienHD.setEnabled(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnTaoHoaDon)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel10)
                        .addComponent(jLabel14)
                        .addComponent(jLabel5)
                        .addComponent(jLabel23)
                        .addComponent(jLabel17)
                        .addComponent(jLabel18)
                        .addComponent(jLabel9)))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTienKhachDua)
                    .addComponent(txtTienGiam)
                    .addComponent(txtTenNV, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                    .addComponent(txtTongTienHang)
                    .addComponent(txtTienThoi, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cboPTTT, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTongTienHD))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(cboPTTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtTongTienHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(txtTienGiam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtTongTienHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTienThoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTaoHoaDon)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(473, 473, 473))
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        resetTTSanPham();
        txtBarcode.setText("");
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnthemSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemSPActionPerformed
        if (cthdSelected != null) {
            hoaDon.addCTHD(new ChiTietHoaDon(cthdSelected));
            setSpThanhToanModel(hoaDon.getChiTietHoaDons());
            updateTTHang();
            updateTTHD();
        } else {
            JOptionPane.showMessageDialog(this, "Sản phẩm không tồn tại. Vui lòng kiểm tra lại");
        }
    }//GEN-LAST:event_btnthemSPActionPerformed

    private void btnupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateActionPerformed
        if (cthdSelected != null) {
            for (ChiTietHoaDon cd : hoaDon.getChiTietHoaDons()) {
                if (cd.getIdKieuSanPham() == cthdSelected.getIdKieuSanPham()) {
                    cthdSelected.setSoLuong(Integer.parseInt(txtSoLuong.getText()));
                    cd = new ChiTietHoaDon(cthdSelected);
                }
            }
            setSpThanhToanModel(hoaDon.getChiTietHoaDons());
            updateTTHang();
            updateTTHD();
        } else {
            JOptionPane.showMessageDialog(this, "Sản phẩm không tồn tại. Vui lòng kiểm tra lại");
        }
    }//GEN-LAST:event_btnupdateActionPerformed

    private void btnTaoHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHoaDonActionPerformed

        if (dtmSpThanhToan.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Chưa có sản phẩm nào để thanh toán");
            return;
        }

        hoaDon.setIdNhanVien(NhanVienLogin.getNhanVienLogin().nhanVien.getIdNhanVien());
        if (khCurrent != null) {
            hoaDon.setIdKhachHang(khCurrent.getIdKhachHang());
        } else {
            hoaDon.setIdKhachHang(-1);
        }
        hoaDon.setDiemSuDung(Long.parseLong(txtTienGiam.getText()));
        hoaDon.setTongTien(hoaDon.tinhTongTien() - Long.parseLong(txtTienGiam.getText()));
        hoaDon.setPhuongThucThanhToan((String) cboPTTT.getSelectedItem());

        boolean result = HoaDonDao.add(hoaDon);
        if (result) {
            try {
                ArrayList<ThongTinHoaDon> dataList = new ArrayList<>();
                for (ChiTietHoaDon cthd : hoaDon.getChiTietHoaDons()) {
                    ThongTinHoaDon tt = new ThongTinHoaDon();
                    tt.setTenSanPham(cthd.getKieuSanPham().getSanPham().getTenSanPham());
                    tt.setMau(cthd.getKieuSanPham().getMau());
                    tt.setSize(cthd.getKieuSanPham().getSize());
                    long giaBan = cthd.getKieuSanPham().getSanPham().getGiaBan();
                    tt.setGiaBan(giaBan);
                    int giamGia = cthd.getKieuSanPham().getSanPham().getGiamGia();
                    tt.setGiamGia(giamGia);
                    tt.setSoLuong(cthd.getSoLuong());
                    tt.setThanhTien(giaBan * (100 - giamGia) / 100 * cthd.getSoLuong());
                    dataList.add(tt);
                }

                JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dataList);

                Map<String, Object> parameters = new HashMap<String, Object>();
                parameters.put("Parameter1", dataSource);
                parameters.put("tenNhanVien", NhanVienLogin.getNhanVienLogin().nhanVien.getTenNhanVien());
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                parameters.put("tgXuatHD", dateFormat.format(new Date()));

                parameters.put("tongTien", hoaDon.getTongTien());

                String projectDirectory = System.getProperty("user.dir");
                InputStream input = new FileInputStream(new File(projectDirectory + "\\src\\GUI\\ReportHoaDon.jrxml"));
                JasperDesign jasperDesign = JRXmlLoader.load(input);

                JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

                // Hiển thị báo cáo
                JasperViewer.viewReport(jasperPrint);
            } catch (JRException | FileNotFoundException ex) {
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            hoaDon = new HoaDon();
            resetTTHoaDon();
            resetTTKhachHang();
            resetTTSanPham();
            cthdSelected = null;
            setSpThanhToanModel(new ArrayList<ChiTietHoaDon>());
        } else {
            JOptionPane.showMessageDialog(this, "Thêm sản phẩm thất bại", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnTaoHoaDonActionPerformed

    private void btnXoaSpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSpActionPerformed
        if (txtBarcode.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm muốn xóa ở danh sách dưới!");
        } else {
            long barcode = Long.parseLong(txtBarcode.getText());
            for (Iterator<ChiTietHoaDon> iterator = hoaDon.getChiTietHoaDons().iterator(); iterator.hasNext();) {
                ChiTietHoaDon cthd = iterator.next();
                if (cthd.getKieuSanPham().getBarCode() == barcode) {
                    iterator.remove();
                    setSpThanhToanModel(hoaDon.getChiTietHoaDons());
                    updateTTHang();
                    updateTTHD();
                }
            }

        }
    }//GEN-LAST:event_btnXoaSpActionPerformed

    private void txtBarcodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBarcodeKeyReleased
        char keyChar = evt.getKeyChar();
        int keyCode = evt.getKeyCode();

        if (Character.isDigit(keyChar) || keyCode == KeyEvent.VK_DELETE || keyCode == KeyEvent.VK_BACK_SPACE) {
            if (debounceTimer != null && debounceTimer.isRunning()) {
                debounceTimer.stop();
            }

            debounceTimer = new Timer(DEBOUNCE_DELAY, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    if (txtBarcode.getText().isEmpty()) {
                        return;
                    }
                    KieuSanPham ksp = KieuSanPhamDAO.getByBarcode(Long.parseLong(txtBarcode.getText()));
                    if (ksp != null) {
                        cthdSelected = new ChiTietHoaDon();
                        txtTenSP.setText(ksp.getSanPham().getTenSanPham());
                        txtMau.setText(ksp.getMau());
                        txtSize.setText(ksp.getSize());
                        txtSoLuong.setText("1");
                        long donGia = ksp.getSanPham().getGiaBan();
                        int giamGia = ksp.getSanPham().getGiamGia();
                        txtDonGia.setText(donGia + "");
                        txtGiamGia.setText(giamGia + "");
                        txtThanhTien.setText((donGia * (100 - giamGia) / 100) + "");
                        showAnh(ksp.getAnhSP());
                        cthdSelected.setIdKieuSanPham(ksp.getIdKieuSanPham());
                        cthdSelected.setKieuSanPham(ksp);
                        cthdSelected.setDonGia(donGia * (100 - giamGia) / 100);
                        cthdSelected.setSoLuong(1);
                    } else {
                        cthdSelected = null;
                        resetTTSanPham();
                    }
                }
            });

            debounceTimer.setRepeats(false);
            debounceTimer.start();
        }
    }//GEN-LAST:event_txtBarcodeKeyReleased

    private void txtBarcodeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBarcodeKeyTyped
        char c = evt.getKeyChar();
        if (!((c >= '0' && c <= '9') || (c == evt.VK_BACK_SPACE) || (c == evt.VK_DELETE))) {
            evt.consume();
        }
    }//GEN-LAST:event_txtBarcodeKeyTyped

    private void txtSoLuongKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoLuongKeyTyped
        char c = evt.getKeyChar();
        if (!((c >= '0' && c <= '9') || (c == evt.VK_BACK_SPACE) || (c == evt.VK_DELETE))) {
            evt.consume();
        }
    }//GEN-LAST:event_txtSoLuongKeyTyped

    private void txtSoLuongKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoLuongKeyReleased
        char keyChar = evt.getKeyChar();
        int keyCode = evt.getKeyCode();

        if (Character.isDigit(keyChar) || keyCode == KeyEvent.VK_DELETE || keyCode == KeyEvent.VK_BACK_SPACE) {
            if (txtSoLuong.getText().isEmpty()) {
                return;
            }
            int soLuong = Integer.parseInt(txtSoLuong.getText());
            if (soLuong <= 0) {
                JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0");
                return;
            }
            cthdSelected.setSoLuong(soLuong);
            long giaBan = cthdSelected.getKieuSanPham().getSanPham().getGiaBan();
            long giamGia = cthdSelected.getKieuSanPham().getSanPham().getGiamGia();
            txtThanhTien.setText((giaBan * (100 - giamGia) / 100) * soLuong + "");
        }
    }//GEN-LAST:event_txtSoLuongKeyReleased

    private void txtSDTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSDTKeyReleased
        char keyChar = evt.getKeyChar();
        int keyCode = evt.getKeyCode();

        if (Character.isDigit(keyChar) || keyCode == KeyEvent.VK_DELETE || keyCode == KeyEvent.VK_BACK_SPACE) {
            if (debounceTimer != null && debounceTimer.isRunning()) {
                debounceTimer.stop();
            }

            debounceTimer = new Timer(DEBOUNCE_DELAY, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (txtSDT.getText().isEmpty()) {
                        return;
                    }
                    KhachHang kh = KhachHangDAO.getBySDT(txtSDT.getText());
                    if (kh != null) {
                        khCurrent = kh;
                        txtTenKh.setText(kh.getTenKhachHang());
                        txtDiem.setText(kh.getDiem() + "");
                        rdSuDung.setEnabled(true);
                        rdKhongSuDung.setEnabled(true);
                    }
                }
            });

            debounceTimer.setRepeats(false);
            debounceTimer.start();
        }
    }//GEN-LAST:event_txtSDTKeyReleased

    private void txtSDTKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSDTKeyTyped
        char c = evt.getKeyChar();
        if (!((c >= '0' && c <= '9') || (c == evt.VK_BACK_SPACE) || (c == evt.VK_DELETE))) {
            evt.consume();
        }
    }//GEN-LAST:event_txtSDTKeyTyped

    private void tblSpThanhToanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSpThanhToanMouseClicked
        resetTTSanPham();
        int row = tblSpThanhToan.getSelectedRow();
        ChiTietHoaDon newCTHD = null;
        for (ChiTietHoaDon cthd : hoaDon.getChiTietHoaDons()) {
            if (cthd.getKieuSanPham().getBarCode() == Long.parseLong(tblSpThanhToan.getValueAt(row, 0) + "")) {
                newCTHD = cthd;
            }
        }
        txtBarcode.setText(newCTHD.getKieuSanPham().getBarCode() + "");
        txtTenSP.setText(newCTHD.getKieuSanPham().getSanPham().getTenSanPham());
        txtMau.setText(newCTHD.getKieuSanPham().getMau());
        txtSize.setText(newCTHD.getKieuSanPham().getSize());
        int soLuong = newCTHD.getSoLuong();
        txtSoLuong.setText(soLuong + "");
        long giaBan = newCTHD.getKieuSanPham().getSanPham().getGiaBan();
        txtDonGia.setText(giaBan + "");
        int giamGia = newCTHD.getKieuSanPham().getSanPham().getGiamGia();

        txtGiamGia.setText(giamGia + "");
        txtThanhTien.setText((giaBan * (100 - giamGia) / 100 * soLuong) + "");
        showAnh(newCTHD.getKieuSanPham().getAnhSP());
        cthdSelected = newCTHD;
    }//GEN-LAST:event_tblSpThanhToanMouseClicked

    private void txtTienKhachDuaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienKhachDuaKeyTyped
        char c = evt.getKeyChar();
        if (!((c >= '0' && c <= '9') || (c == evt.VK_BACK_SPACE) || (c == evt.VK_DELETE))) {
            evt.consume();
        }
    }//GEN-LAST:event_txtTienKhachDuaKeyTyped

    private void txtTienKhachDuaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienKhachDuaKeyReleased
        char keyChar = evt.getKeyChar();
        int keyCode = evt.getKeyCode();

        if (Character.isDigit(keyChar) || keyCode == KeyEvent.VK_DELETE || keyCode == KeyEvent.VK_BACK_SPACE) {
            long tienKhachDua = Long.parseLong(txtTienKhachDua.getText());
            long tienGiam = Long.parseLong(txtTienGiam.getText());
            long tongTien = Long.parseLong(txtTongTienHang.getText());
            txtTienThoi.setText((tienKhachDua - tongTien + tienGiam) + "");
        }
    }//GEN-LAST:event_txtTienKhachDuaKeyReleased

    private void rdSuDungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdSuDungActionPerformed
        if (khCurrent.getDiem() == 0) {
            JOptionPane.showMessageDialog(this, "Không có điểm để sử dụng");
            rdKhongSuDung.setSelected(true);
            return;
        }
        txtTienGiam.setText(khCurrent.getDiem() + "");
        updateTTHD();
    }//GEN-LAST:event_rdSuDungActionPerformed

    private void rdKhongSuDungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdKhongSuDungActionPerformed
        txtTienGiam.setText(0 + "");
        updateTTHD();
    }//GEN-LAST:event_rdKhongSuDungActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btnGroupDiem;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnTaoHoaDon;
    private javax.swing.JButton btnXoaSp;
    private javax.swing.JButton btnthemSP;
    private javax.swing.JButton btnupdate;
    private javax.swing.JComboBox<String> cboPTTT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelAnh;
    private javax.swing.JRadioButton rdKhongSuDung;
    private javax.swing.JRadioButton rdSuDung;
    private javax.swing.JTable tblSpThanhToan;
    private javax.swing.JTextField txtBarcode;
    private javax.swing.JTextField txtDiem;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtGiamGia;
    private javax.swing.JTextField txtMau;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSize;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenKh;
    private javax.swing.JTextField txtTenNV;
    private javax.swing.JTextArea txtTenSP;
    private javax.swing.JTextField txtThanhTien;
    private javax.swing.JTextField txtTienGiam;
    private javax.swing.JTextField txtTienKhachDua;
    private javax.swing.JTextField txtTienThoi;
    private javax.swing.JTextField txtTongTienHD;
    private javax.swing.JTextField txtTongTienHang;
    // End of variables declaration//GEN-END:variables
}
