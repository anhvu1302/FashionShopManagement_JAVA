/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package GUI;

import DAO.LoaiSanPhamChaDAO;
import DAO.LoaiSanPhamDAO;
import DAO.SQLServerDataProvider;
import POJO.LoaiSanPham;
import POJO.LoaiSanPhamCha;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Nah nah
 */
public class frmQLLoaiSanPham extends javax.swing.JInternalFrame {

    /**
     * Creates new form frmQLLoaiSanPham
     */
    DefaultTableModel dtmLSPC = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return column != 0;
        }
    };
    DefaultTableModel dtmLSP = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return column != 0;
        }
    };
    ArrayList<LoaiSanPhamCha> lstDeleteLSPC = new ArrayList<>();
    ArrayList<LoaiSanPhamCha> lstUpdateLSPC = new ArrayList<>();

    ArrayList<LoaiSanPham> lstDeleteLSP = new ArrayList<>();
    ArrayList<LoaiSanPham> lstUpdateLSP = new ArrayList<>();

    public frmQLLoaiSanPham() {
        initComponents();
        String[] tieuDe = {"Mã loại sản phẩm cha", "Tên loại sản phẩm cha"};
        dtmLSPC.setColumnIdentifiers(tieuDe);
        String[] tieuDe1 = {"Mã loại sản phẩm", "Tên loại sản phẩm", "Loại sản phẩm cha"};
        dtmLSP.setColumnIdentifiers(tieuDe1);
        loadtblLoaiSPCha();
        loadtblLoaiSanPham();
        txtmaLoaiSPCha.setEnabled(false);
        LoadCboLoaiSanPhamCha();
        txtmaLoaiSP.setEnabled(false);
    }

    private void setLoaiSanPhamChaModel(ArrayList<LoaiSanPhamCha> dsLSPCha) {
        dtmLSPC.setRowCount(0);
        for (LoaiSanPhamCha lspc : dsLSPCha) {
            Vector<Object> vec = new Vector<Object>();
            vec.add(lspc.getIdLoaiSPCha());
            vec.add(lspc.getTenLoaiSPCha());
            dtmLSPC.addRow(vec);
        }
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tbl_LoaiSPCha.getModel());
        tbl_LoaiSPCha.setRowSorter(sorter);
        tbl_LoaiSPCha.setAutoCreateRowSorter(true);
        tbl_LoaiSPCha.setModel(dtmLSPC);
    }

    private void loadtblLoaiSPCha() {
        ArrayList<LoaiSanPhamCha> dsLSPCha = LoaiSanPhamChaDAO.getAll();
        setLoaiSanPhamChaModel(dsLSPCha);
        //lấy giá trị dòng được sửa
        dtmLSPC.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE && e.getColumn() != TableModelEvent.ALL_COLUMNS && e.getLastRow() == e.getFirstRow()) {
                    int editedRow = e.getFirstRow();
                    int id = (Integer) tbl_LoaiSPCha.getValueAt(editedRow, 0);
                    String ten = (String) tbl_LoaiSPCha.getValueAt(editedRow, 1);
                    lstUpdateLSPC.add(new LoaiSanPhamCha(id, ten));
                }
            }
        });
        // Add popup delete
        final JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem deleteItem = new JMenuItem("Delete row");
        deleteItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tbl_LoaiSPCha.getSelectedRow();
                if (selectedRow != -1) {
                    int id = (Integer) tbl_LoaiSPCha.getValueAt(tbl_LoaiSPCha.getSelectedRow(), 0);
                    String ten = (String) tbl_LoaiSPCha.getValueAt(tbl_LoaiSPCha.getSelectedRow(), 1);
                    lstDeleteLSPC.add(new LoaiSanPhamCha(id, ten));
                    dtmLSPC.removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn dòng dữ liệu", "Thông báo", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        popupMenu.add(deleteItem);
        tbl_LoaiSPCha.setComponentPopupMenu(popupMenu);
    }

    private void setLoaiSanPhamModel(ArrayList<LoaiSanPham> dsLoaiSP) {
        dtmLSP.setRowCount(0);

        for (LoaiSanPham lsp : dsLoaiSP) {
            Vector<Object> vec = new Vector<Object>();
            vec.add(lsp.getIdLoaiSP());
            vec.add(lsp.getTenLoaiSP());
            vec.add(lsp.getLoaiSanPhamCha());
            dtmLSP.addRow(vec);
        }
    }

    private void loadtblLoaiSanPham() {
        ArrayList<LoaiSanPham> dsLoaiSP = LoaiSanPhamDAO.getAll();
        setLoaiSanPhamModel(dsLoaiSP);
        // Set sorter table
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tbl_LoaiSPCha.getModel());
        tbl_LoaiSP.setRowSorter(sorter);
        tbl_LoaiSP.setAutoCreateRowSorter(true);
        tbl_LoaiSP.setModel(dtmLSP);

        //lấy giá trị dòng được sửa
        dtmLSP.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE && e.getColumn() != TableModelEvent.ALL_COLUMNS && e.getLastRow() == e.getFirstRow()) {
                    int editedRow = e.getFirstRow();
                    int id = (Integer) tbl_LoaiSP.getValueAt(editedRow, 0);
                    String ten = (String) tbl_LoaiSP.getValueAt(editedRow, 1);
                    TableColumn idLoaiSPColumn = tbl_LoaiSP.getColumnModel().getColumn(2);
                    TableCellEditor cellEditor = idLoaiSPColumn.getCellEditor();
                    LoaiSanPhamCha lspc = null;
                    if (cellEditor instanceof DefaultCellEditor) {
                        Component component = ((DefaultCellEditor) cellEditor).getComponent();
                        if (component instanceof JComboBox) {
                            JComboBox<?> comboBox = (JComboBox<?>) component;
                            lspc = (LoaiSanPhamCha) comboBox.getSelectedItem();
                        }
                    }
                    lstUpdateLSP.add(new LoaiSanPham(id, ten, lspc.getIdLoaiSPCha()));
                }
            }
        });
        // Add popup delete
        final JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem deleteItem = new JMenuItem("Delete row");
        deleteItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tbl_LoaiSP.getSelectedRow();
                if (selectedRow != -1) {
                    int id = (Integer) tbl_LoaiSP.getValueAt(tbl_LoaiSP.getSelectedRow(), 0);
                    LoaiSanPham lsp = new LoaiSanPham();
                    lsp.setIdLoaiSP(id);
                    lstDeleteLSP.add(lsp);
                    dtmLSP.removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn dòng dữ liệu", "Thông báo", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        popupMenu.add(deleteItem);
        tbl_LoaiSP.setComponentPopupMenu(popupMenu);

        //Render combo box for cell of table
        ArrayList<LoaiSanPhamCha> dsLSPCha = LoaiSanPhamChaDAO.getAll();
        Object[] options = new Object[dsLSPCha.size()];
        for (int i = 0; i < dsLSPCha.size(); i++) {
            options[i] = dsLSPCha.get(i);
        }
        JComboBox<Object> comboBox = new JComboBox<>(options);
        DefaultCellEditor editor = new DefaultCellEditor(comboBox);
        tbl_LoaiSP.getColumnModel().getColumn(2).setCellEditor(editor);
    }

    private void LoadCboLoaiSanPhamCha() {
        cbo_LoaiSPCha.removeAllItems();

        ArrayList<LoaiSanPhamCha> dsLSPCha = LoaiSanPhamChaDAO.getAll();

        for (LoaiSanPhamCha lspc : dsLSPCha) {
            cbo_LoaiSPCha.addItem(lspc);
        }
    }

    private void ResetLoaiSanPhamCha() {
        txtmaLoaiSPCha.setText("");
        txttenLoaiSPCha.setText("");
        loadtblLoaiSPCha();
    }

    private void ResetLoaiSanPham() {
        txtmaLoaiSP.setText("");
        txttenLoaiSP.setText("");
        cbo_LoaiSPCha.setSelectedIndex(-1);
        loadtblLoaiSanPham();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtmaLoaiSPCha = new javax.swing.JTextField();
        txttenLoaiSPCha = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        btnResetLoaiSPCha = new javax.swing.JButton();
        btnthemLoaiSPCha = new javax.swing.JButton();
        btnupdateLoaiSPCha = new javax.swing.JButton();
        btndeleteLoaiSPCha = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_LoaiSPCha = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtSearchTenLSPC = new javax.swing.JTextField();
        btnSearchLSPC = new javax.swing.JButton();
        btnResetSearchLSPC = new javax.swing.JButton();
        btnLuuLSPC = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_LoaiSP = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtmaLoaiSP = new javax.swing.JTextField();
        txttenLoaiSP = new javax.swing.JTextField();
        cbo_LoaiSPCha = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btnthemLoaiSP = new javax.swing.JButton();
        btnupdateLoaiSP = new javax.swing.JButton();
        btndeleteLoaiSP = new javax.swing.JButton();
        btnresetLoaiSP = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtSearchTenLSP = new javax.swing.JTextField();
        btnSearchLSPC1 = new javax.swing.JButton();
        btnResetSearchLSPC1 = new javax.swing.JButton();
        btnLuuLSP = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(1536, 778));
        setMinimumSize(new java.awt.Dimension(1536, 778));
        setPreferredSize(new java.awt.Dimension(1536, 778));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(187, 54, 137));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("QUẢN LÝ LOẠI SẢN PHẨM");

        jPanel7.setMaximumSize(new java.awt.Dimension(1500, 300));
        jPanel7.setMinimumSize(new java.awt.Dimension(1500, 300));
        jPanel7.setPreferredSize(new java.awt.Dimension(1500, 300));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin loại sản phẩm cha", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(187, 54, 137));
        jLabel2.setText("Mã loại sản phẩm cha");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(187, 54, 137));
        jLabel3.setText("Tên loại sản phẩm cha");

        txtmaLoaiSPCha.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txttenLoaiSPCha.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnResetLoaiSPCha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/reset.png"))); // NOI18N
        btnResetLoaiSPCha.setText("Reset");
        btnResetLoaiSPCha.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnResetLoaiSPCha.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnResetLoaiSPCha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetLoaiSPChaActionPerformed(evt);
            }
        });

        btnthemLoaiSPCha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/add.png"))); // NOI18N
        btnthemLoaiSPCha.setText("Thêm");
        btnthemLoaiSPCha.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnthemLoaiSPCha.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnthemLoaiSPCha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemLoaiSPChaActionPerformed(evt);
            }
        });

        btnupdateLoaiSPCha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/updated.png"))); // NOI18N
        btnupdateLoaiSPCha.setText("Cập nhật");
        btnupdateLoaiSPCha.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnupdateLoaiSPCha.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnupdateLoaiSPCha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdateLoaiSPChaActionPerformed(evt);
            }
        });

        btndeleteLoaiSPCha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/delete.png"))); // NOI18N
        btndeleteLoaiSPCha.setText("Xoá");
        btndeleteLoaiSPCha.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btndeleteLoaiSPCha.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btndeleteLoaiSPCha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeleteLoaiSPChaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnResetLoaiSPCha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnthemLoaiSPCha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(btnupdateLoaiSPCha)
                .addGap(18, 18, 18)
                .addComponent(btndeleteLoaiSPCha)
                .addGap(28, 28, 28))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btndeleteLoaiSPCha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(btnResetLoaiSPCha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnthemLoaiSPCha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnupdateLoaiSPCha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txttenLoaiSPCha, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtmaLoaiSPCha, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtmaLoaiSPCha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txttenLoaiSPCha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jPanel7.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 530, 190));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách loại sản phẩm cha", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        tbl_LoaiSPCha.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbl_LoaiSPCha.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_LoaiSPCha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_LoaiSPChaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_LoaiSPCha);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(368, 368, 368))
        );

        jPanel7.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 530, 350));

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Hành động"));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(187, 54, 137));
        jLabel8.setText("Tên loại cha");

        txtSearchTenLSPC.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnSearchLSPC.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSearchLSPC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/find.png"))); // NOI18N
        btnSearchLSPC.setText("Tìm kiếm");
        btnSearchLSPC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchLSPCActionPerformed(evt);
            }
        });

        btnResetSearchLSPC.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnResetSearchLSPC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/reset.png"))); // NOI18N
        btnResetSearchLSPC.setText("Reset");
        btnResetSearchLSPC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetSearchLSPCActionPerformed(evt);
            }
        });

        btnLuuLSPC.setBackground(new java.awt.Color(13, 110, 253));
        btnLuuLSPC.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnLuuLSPC.setForeground(java.awt.Color.white);
        btnLuuLSPC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-save-30.png"))); // NOI18N
        btnLuuLSPC.setText("Lưu dữ liệu");
        btnLuuLSPC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuLSPCActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnResetSearchLSPC)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(28, 28, 28)
                        .addComponent(txtSearchTenLSPC, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(btnSearchLSPC)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                        .addComponent(btnLuuLSPC)
                        .addGap(17, 17, 17))))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnSearchLSPC)
                    .addComponent(txtSearchTenLSPC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLuuLSPC)
                    .addComponent(btnResetSearchLSPC))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel7.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 530, 130));

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách loại sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        tbl_LoaiSP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbl_LoaiSP.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_LoaiSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_LoaiSPMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_LoaiSP);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 741, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin loại sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N
        jPanel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(187, 54, 137));
        jLabel4.setText("Mã loại sản phẩm ");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(187, 54, 137));
        jLabel5.setText("Tên loại sản phẩm");

        txtmaLoaiSP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txttenLoaiSP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        cbo_LoaiSPCha.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbo_LoaiSPCha.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(187, 54, 137));
        jLabel6.setText("Loại sản phẩm cha");

        btnthemLoaiSP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnthemLoaiSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/add.png"))); // NOI18N
        btnthemLoaiSP.setText("Thêm");
        btnthemLoaiSP.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnthemLoaiSP.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnthemLoaiSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemLoaiSPActionPerformed(evt);
            }
        });

        btnupdateLoaiSP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnupdateLoaiSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/updated.png"))); // NOI18N
        btnupdateLoaiSP.setText("Cập nhật");
        btnupdateLoaiSP.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnupdateLoaiSP.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnupdateLoaiSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdateLoaiSPActionPerformed(evt);
            }
        });

        btndeleteLoaiSP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btndeleteLoaiSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/delete.png"))); // NOI18N
        btndeleteLoaiSP.setText("Xoá");
        btndeleteLoaiSP.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btndeleteLoaiSP.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btndeleteLoaiSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeleteLoaiSPActionPerformed(evt);
            }
        });

        btnresetLoaiSP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnresetLoaiSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/reset.png"))); // NOI18N
        btnresetLoaiSP.setText("Reset");
        btnresetLoaiSP.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnresetLoaiSP.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnresetLoaiSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnresetLoaiSPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnupdateLoaiSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnresetLoaiSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btndeleteLoaiSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnthemLoaiSP, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnthemLoaiSP, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(btnresetLoaiSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(26, 26, 26)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnupdateLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btndeleteLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(35, 35, 35)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtmaLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbo_LoaiSPCha, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txttenLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtmaLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cbo_LoaiSPCha, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttenLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(26, 26, 26))
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Hành động"));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(187, 54, 137));
        jLabel9.setText("Tên loại sản phẩm");

        txtSearchTenLSP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnSearchLSPC1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSearchLSPC1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/find.png"))); // NOI18N
        btnSearchLSPC1.setText("Tìm kiếm");
        btnSearchLSPC1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchLSPActionPerformed(evt);
            }
        });

        btnResetSearchLSPC1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnResetSearchLSPC1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/reset.png"))); // NOI18N
        btnResetSearchLSPC1.setText("Reset");
        btnResetSearchLSPC1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetSearchLSPC1ActionPerformed(evt);
            }
        });

        btnLuuLSP.setBackground(new java.awt.Color(13, 110, 253));
        btnLuuLSP.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnLuuLSP.setForeground(java.awt.Color.white);
        btnLuuLSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8-save-30.png"))); // NOI18N
        btnLuuLSP.setText("Lưu dữ liệu");
        btnLuuLSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuLSPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(52, 52, 52)
                        .addComponent(txtSearchTenLSP, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(111, 111, 111)
                        .addComponent(btnSearchLSPC1)
                        .addGap(127, 127, 127))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addComponent(btnResetSearchLSPC1)
                        .addGap(79, 79, 79)
                        .addComponent(btnLuuLSP)
                        .addGap(15, 15, 15))))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txtSearchTenLSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(btnSearchLSPC1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLuuLSP)
                    .addComponent(btnResetSearchLSPC1))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(553, 553, 553)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 564, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 706, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_LoaiSPChaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_LoaiSPChaMouseClicked
        int row = tbl_LoaiSPCha.getSelectedRow();
        txtmaLoaiSPCha.setText(tbl_LoaiSPCha.getValueAt(row, 0) + "");
        txttenLoaiSPCha.setText((String) tbl_LoaiSPCha.getValueAt(row, 1));
    }//GEN-LAST:event_tbl_LoaiSPChaMouseClicked

    private void tbl_LoaiSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_LoaiSPMouseClicked
        int row = tbl_LoaiSP.getSelectedRow();
        txtmaLoaiSP.setText(tbl_LoaiSP.getValueAt(row, 0) + "");
        txttenLoaiSP.setText((String) tbl_LoaiSP.getValueAt(row, 1));
        Object itemSelectObj = tbl_LoaiSP.getValueAt(row, 2);
        LoaiSanPhamCha itemSelect = (LoaiSanPhamCha) itemSelectObj;
        for (int i = 0; i < cbo_LoaiSPCha.getItemCount(); i++) {
            String cboValue = cbo_LoaiSPCha.getItemAt(i) + "";
            if (cboValue.equals(itemSelect.getTenLoaiSPCha())) {
                cbo_LoaiSPCha.setSelectedIndex(i);
                break;
            }
        }

    }//GEN-LAST:event_tbl_LoaiSPMouseClicked

    private void btnResetLoaiSPChaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetLoaiSPChaActionPerformed
        ResetLoaiSanPhamCha();
    }//GEN-LAST:event_btnResetLoaiSPChaActionPerformed

    private void btnresetLoaiSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnresetLoaiSPActionPerformed
//        LoaiSanPhamCha selected = (LoaiSanPhamCha) cbo_LoaiSPCha.getSelectedItem();
        ResetLoaiSanPham();
    }//GEN-LAST:event_btnresetLoaiSPActionPerformed

    private void btnthemLoaiSPChaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemLoaiSPChaActionPerformed
        txtmaLoaiSPCha.setText("");
        String ten = txttenLoaiSPCha.getText();
        if (ten.length() == 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên loại sản phẩm cha");
        } else {
            if (LoaiSanPhamChaDAO.checkTenTonTai(ten)) {
                JOptionPane.showMessageDialog(this, "Tên loại '" + ten + "' đã tồn tại");
            } else {
                LoaiSanPhamCha lspc = new LoaiSanPhamCha();
                lspc.setTenLoaiSPCha(ten);
                LoaiSanPhamChaDAO.add(lspc);
                JOptionPane.showMessageDialog(this, "Thêm loại sản phẩm cha thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                loadtblLoaiSPCha();
                LoadCboLoaiSanPhamCha();
            }
        }
    }//GEN-LAST:event_btnthemLoaiSPChaActionPerformed

    private void btnupdateLoaiSPChaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateLoaiSPChaActionPerformed
        if (txtmaLoaiSPCha.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm cha muốn cập nhật ở danh sách dưới!");
        } else {
            int maLSPC = Integer.parseInt(txtmaLoaiSPCha.getText());
            String tenLoaiSPCha = txttenLoaiSPCha.getText();
            LoaiSanPhamCha lspc = new LoaiSanPhamCha(maLSPC, tenLoaiSPCha);
            lspc.setTenLoaiSPCha(tenLoaiSPCha);
            boolean result = LoaiSanPhamChaDAO.updateById(lspc);
            if (result) {

                JOptionPane.showMessageDialog(this, "Cập nhật thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                LoadCboLoaiSanPhamCha();
                loadtblLoaiSPCha();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnupdateLoaiSPChaActionPerformed

    private void btndeleteLoaiSPChaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteLoaiSPChaActionPerformed
        if (txtmaLoaiSPCha.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm cha muốn xóa ở danh sách dưới!");
        } else {
            int maLSPCha = Integer.parseInt(txtmaLoaiSPCha.getText());
            if (LoaiSanPhamChaDAO.isForeignKeyExists(maLSPCha)) {
                JOptionPane.showMessageDialog(this, "Loại sản phẩm cha đang được sử dụng và không thể xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } else {
                boolean success = LoaiSanPhamChaDAO.deleteById(maLSPCha);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    ResetLoaiSanPhamCha();
                    LoadCboLoaiSanPhamCha();
                    loadtblLoaiSPCha();
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa không thành công!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

    }//GEN-LAST:event_btndeleteLoaiSPChaActionPerformed

    private void btnthemLoaiSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemLoaiSPActionPerformed
        String tenLoaiSP = txttenLoaiSP.getText();
        LoaiSanPhamCha selected = (LoaiSanPhamCha) cbo_LoaiSPCha.getSelectedItem();
        if (selected == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn loại sản phẩm cha", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (tenLoaiSP == null || tenLoaiSP.length() == 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên loại sản phẩm", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        LoaiSanPham lsp = new LoaiSanPham();
        lsp.setTenLoaiSP(tenLoaiSP);
        lsp.setIdLoaiSPCha(selected.getIdLoaiSPCha());
        boolean success = LoaiSanPhamDAO.add(lsp);
        if (success) {
            JOptionPane.showMessageDialog(this, "Thêm loại sản phẩm thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            loadtblLoaiSanPham();
        } else {
            JOptionPane.showMessageDialog(this, "Thêm loại sản phẩm thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnthemLoaiSPActionPerformed

    private void btnupdateLoaiSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateLoaiSPActionPerformed
        if (txtmaLoaiSP.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm muốn cập nhật ở danh sách dưới!");
        } else {
            int maLSP = Integer.parseInt(txtmaLoaiSP.getText());
            String tenLoaiSP = txttenLoaiSP.getText();
            LoaiSanPhamCha selected = (LoaiSanPhamCha) cbo_LoaiSPCha.getSelectedItem();

            LoaiSanPham lsp = new LoaiSanPham();
            lsp.setIdLoaiSP(maLSP);
            lsp.setTenLoaiSP(tenLoaiSP);
            lsp.setIdLoaiSPCha(selected.getIdLoaiSPCha());

            boolean result = LoaiSanPhamDAO.updateById(lsp);
            if (result) {
                JOptionPane.showMessageDialog(this, "Cập nhật thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                loadtblLoaiSanPham();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật thất bại!", "Thông báo", JOptionPane.ERROR_MESSAGE);

            }
        }

    }//GEN-LAST:event_btnupdateLoaiSPActionPerformed

    private void btndeleteLoaiSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteLoaiSPActionPerformed
        if (txtmaLoaiSP.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm muốn xóa ở danh sách dưới!");
            return;
        } else {
            int maLSP = Integer.parseInt(txtmaLoaiSP.getText());
            if (LoaiSanPhamDAO.isForeignKeyExists(maLSP)) {
                JOptionPane.showMessageDialog(this, "Loại sản phẩm đang được sử dụng và không thể xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } else {
                boolean success = LoaiSanPhamDAO.deleteById(maLSP);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    loadtblLoaiSanPham();
                    ResetLoaiSanPham();
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa không thành công!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

    }//GEN-LAST:event_btndeleteLoaiSPActionPerformed

    private void btnLuuLSPCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuLSPCActionPerformed
        SQLServerDataProvider provider = new SQLServerDataProvider();
        if (!lstUpdateLSPC.isEmpty() || !lstDeleteLSPC.isEmpty()) {

            try {
                provider.open();
                provider.startTransaction();

                LoaiSanPhamChaDAO.updateByList(lstUpdateLSPC);
                LoaiSanPhamChaDAO.deleteByList(lstDeleteLSPC);

                provider.commitTransaction();
                lstDeleteLSPC.clear();
                lstUpdateLSPC.clear();
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
    }//GEN-LAST:event_btnLuuLSPCActionPerformed

    private void btnLuuLSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuLSPActionPerformed
        SQLServerDataProvider provider = new SQLServerDataProvider();
        for (LoaiSanPham loaiSanPham : lstDeleteLSP) {
            System.out.println(loaiSanPham.getTenLoaiSP());
        }

        if (!lstUpdateLSP.isEmpty() || !lstDeleteLSP.isEmpty()) {

            try {
                provider.open();
                provider.startTransaction();

                LoaiSanPhamDAO.updateByList(lstUpdateLSP);
                LoaiSanPhamDAO.deleteByList(lstDeleteLSP);

                provider.commitTransaction();
                lstDeleteLSP.clear();
                lstUpdateLSP.clear();
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
    }//GEN-LAST:event_btnLuuLSPActionPerformed

    private void btnSearchLSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchLSPActionPerformed
        String searchValue = txtSearchTenLSP.getText();
        if (searchValue.length() == 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập giá trị tìm kiếm!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        } else {
            ArrayList<LoaiSanPham> lst = LoaiSanPhamDAO.getByTen(searchValue);
            setLoaiSanPhamModel(lst);
        }
    }//GEN-LAST:event_btnSearchLSPActionPerformed

    private void btnSearchLSPCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchLSPCActionPerformed
        String searchValue = txtSearchTenLSPC.getText();
        if (searchValue.length() == 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập giá trị tìm kiếm!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        } else {
            ArrayList<LoaiSanPhamCha> lst = LoaiSanPhamChaDAO.getByTen(searchValue);
            setLoaiSanPhamChaModel(lst);
        }
    }//GEN-LAST:event_btnSearchLSPCActionPerformed

    private void btnResetSearchLSPCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetSearchLSPCActionPerformed
        ArrayList<LoaiSanPhamCha> dsLSPCha = LoaiSanPhamChaDAO.getAll();
        setLoaiSanPhamChaModel(dsLSPCha);
    }//GEN-LAST:event_btnResetSearchLSPCActionPerformed

    private void btnResetSearchLSPC1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetSearchLSPC1ActionPerformed
        ArrayList<LoaiSanPham> dsLSP = LoaiSanPhamDAO.getAll();
        setLoaiSanPhamModel(dsLSP);
    }//GEN-LAST:event_btnResetSearchLSPC1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLuuLSP;
    private javax.swing.JButton btnLuuLSPC;
    private javax.swing.JButton btnResetLoaiSPCha;
    private javax.swing.JButton btnResetSearchLSPC;
    private javax.swing.JButton btnResetSearchLSPC1;
    private javax.swing.JButton btnSearchLSPC;
    private javax.swing.JButton btnSearchLSPC1;
    private javax.swing.JButton btndeleteLoaiSP;
    private javax.swing.JButton btndeleteLoaiSPCha;
    private javax.swing.JButton btnresetLoaiSP;
    private javax.swing.JButton btnthemLoaiSP;
    private javax.swing.JButton btnthemLoaiSPCha;
    private javax.swing.JButton btnupdateLoaiSP;
    private javax.swing.JButton btnupdateLoaiSPCha;
    private javax.swing.JComboBox<Object> cbo_LoaiSPCha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tbl_LoaiSP;
    private javax.swing.JTable tbl_LoaiSPCha;
    private javax.swing.JTextField txtSearchTenLSP;
    private javax.swing.JTextField txtSearchTenLSPC;
    private javax.swing.JTextField txtmaLoaiSP;
    private javax.swing.JTextField txtmaLoaiSPCha;
    private javax.swing.JTextField txttenLoaiSP;
    private javax.swing.JTextField txttenLoaiSPCha;
    // End of variables declaration//GEN-END:variables
}
