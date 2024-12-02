import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SupermarketManager extends JFrame {
    private JTable table; // 显示商品信息的表格
    private DefaultTableModel tableModel;

    // 窗体组件
    private JTextField tfProductCode, tfPurchaseQuantity;
    private JTextField tfProductName, tfCategory, tfSupplier;
    private JTextField tfSellPrice, tfPurchasePrice, tfChargeUnit, tfStock, tfStockValue;
    private JLabel lblTotalPrice;

    public SupermarketManager() {
        this.initUI();
        this.loadData();
        this.setVisible(true);
    }

    private void initUI() {
        setTitle("超市信息管理");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();
        add(tabbedPane);

        // 创建销售操作面板
        JPanel salesPanel = createSalesPanel();
        tabbedPane.addTab("销售操作", salesPanel);

        // 创建入库操作面板
        JPanel stockInPanel = createStockInPanel();
        tabbedPane.addTab("入库操作", stockInPanel);

        // 创建销售记录面板
        JPanel salesRecordsPanel = createSalesRecordsPanel();
        tabbedPane.addTab("销售记录", salesRecordsPanel);

        // 创建入库记录面板
        JPanel stockInRecordsPanel = createStockInRecordsPanel();
        tabbedPane.addTab("入库记录", stockInRecordsPanel);
    }

    private JPanel createSalesPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        // 顶部的表格部分
        String[] columnNames = {"序号", "商品编号", "商品名称", "商品类别", "商品供应商", "商品单价", "计价单位", "库存数量", "日期", "流水号"};
        tableModel = new DefaultTableModel(columnNames, 0);  // rowCount 是数据行
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // 底部的操作区域
        JPanel actionPanel = new JPanel(null);
        actionPanel.setPreferredSize(new Dimension(800, 200));
        mainPanel.add(actionPanel, BorderLayout.SOUTH);

        JLabel lblProductCode = new JLabel("商品编号");
        lblProductCode.setBounds(20, 10, 80, 25);
        actionPanel.add(lblProductCode);

        tfProductCode = new JTextField(10);
        tfProductCode.setBounds(100, 10, 100, 25);
        actionPanel.add(tfProductCode);

        JLabel lblPurchaseQuantity = new JLabel("购买数量");
        lblPurchaseQuantity.setBounds(220, 10, 80, 25);
        actionPanel.add(lblPurchaseQuantity);

        tfPurchaseQuantity = new JTextField(10);
        tfPurchaseQuantity.setBounds(300, 10, 100, 25);
        actionPanel.add(tfPurchaseQuantity);

        JButton btnSearch = new JButton("搜索");
        btnSearch.setBounds(420, 10, 80, 25);
        actionPanel.add(btnSearch);

        JButton btnClear = new JButton("清空");
        btnClear.setBounds(520, 10, 80, 25);
        actionPanel.add(btnClear);

        JLabel lblProductName = new JLabel("商品名称");
        lblProductName.setBounds(20, 50, 80, 25);
        actionPanel.add(lblProductName);

        tfProductName = new JTextField(10);
        tfProductName.setBounds(100, 50, 100, 25);
        actionPanel.add(tfProductName);

        JLabel lblCategory = new JLabel("商品类别");
        lblCategory.setBounds(220, 50, 80, 25);
        actionPanel.add(lblCategory);

        tfCategory = new JTextField(10);
        tfCategory.setBounds(300, 50, 100, 25);
        actionPanel.add(tfCategory);

        JLabel lblSupplier = new JLabel("商品供应商");
        lblSupplier.setBounds(420, 50, 80, 25);
        actionPanel.add(lblSupplier);

        tfSupplier = new JTextField(10);
        tfSupplier.setBounds(520, 50, 100, 25);
        actionPanel.add(tfSupplier);

        JLabel lblSellPrice = new JLabel("商品售价");
        lblSellPrice.setBounds(20, 90, 80, 25);
        actionPanel.add(lblSellPrice);

        tfSellPrice = new JTextField(10);
        tfSellPrice.setBounds(100, 90, 100, 25);
        actionPanel.add(tfSellPrice);

        JLabel lblPurchasePrice = new JLabel("商品进价");
        lblPurchasePrice.setBounds(220, 90, 80, 25);
        actionPanel.add(lblPurchasePrice);

        tfPurchasePrice = new JTextField(10);
        tfPurchasePrice.setBounds(300, 90, 100, 25);
        actionPanel.add(tfPurchasePrice);

        JLabel lblChargeUnit = new JLabel("计价单位");
        lblChargeUnit.setBounds(420, 90, 80, 25);
        actionPanel.add(lblChargeUnit);

        tfChargeUnit = new JTextField(10);
        tfChargeUnit.setBounds(520, 90, 100, 25);
        actionPanel.add(tfChargeUnit);

        JLabel lblStock = new JLabel("库存数量");
        lblStock.setBounds(20, 130, 80, 25);
        actionPanel.add(lblStock);

        tfStock = new JTextField(10);
        tfStock.setBounds(100, 130, 100, 25);
        actionPanel.add(tfStock);

        JLabel lblStockValue = new JLabel("库存金额");
        lblStockValue.setBounds(220, 130, 80, 25);
        actionPanel.add(lblStockValue);

        tfStockValue = new JTextField(10);
        tfStockValue.setBounds(300, 130, 100, 25);
        actionPanel.add(tfStockValue);

        lblTotalPrice = new JLabel("应付金额：");
        lblTotalPrice.setBounds(420, 130, 100, 25);
        actionPanel.add(lblTotalPrice);

        JButton btnConfirm = new JButton("销售确认");
        btnConfirm.setBounds(520, 130, 100, 25);
        actionPanel.add(btnConfirm);

        JButton btnCancel = new JButton("销售取消");
        btnCancel.setBounds(640, 130, 100, 25);
        actionPanel.add(btnCancel);

        // 事件绑定
        btnSearch.addActionListener(e -> searchProduct());
        btnClear.addActionListener(e -> clearFields());
        btnConfirm.addActionListener(e -> confirmSale());
        btnCancel.addActionListener(e -> JOptionPane.showMessageDialog(this, "销售已取消！"));

        return mainPanel;
    }

    private JPanel createStockInPanel() {
        JPanel panel = new JPanel();
        // TODO
        panel.add(new JLabel("入库操作功能未实现。"));
        return panel;
    }

    private JPanel createSalesRecordsPanel() {
        JPanel panel = new JPanel();

        // TODO
        panel.add(new JLabel("销售记录功能未实现。"));
        return panel;
    }

    private JPanel createStockInRecordsPanel() {
        JPanel panel = new JPanel();

        // TODO
        panel.add(new JLabel("入库记录功能未实现。"));
        return panel;
    }

    private void loadData() {
        // 模拟数据加载
        Object[][] data = {
                {1, "3", "眼霜", "日常用品", "兰芝", 199.0, "盒", 5, "2017-12-13", 15},
                {2, "4", "口红", "日常用品", "YSL", 299.0, "支", 6, "2017-12-13", 16},
                {3, "5", "面膜", "日常用品", "科颜氏", 255.0, "支", 7, "2017-12-13", 17}
        };
        for (Object[] row : data) {
            tableModel.addRow(row);
        }
    }

    private void searchProduct() {
        String productCode = tfProductCode.getText().trim();
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (tableModel.getValueAt(i, 1).toString().equals(productCode)) {
                tfProductName.setText(tableModel.getValueAt(i, 2).toString());
                tfCategory.setText(tableModel.getValueAt(i, 3).toString());
                tfSupplier.setText(tableModel.getValueAt(i, 4).toString());
                tfSellPrice.setText(tableModel.getValueAt(i, 5).toString());
                tfChargeUnit.setText(tableModel.getValueAt(i, 6).toString());
                tfStock.setText(tableModel.getValueAt(i, 7).toString());
                tfStockValue.setText(String.valueOf(
                        Double.parseDouble(tfSellPrice.getText()) * Double
                        .parseDouble(tfStock.getText())));
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "未找到商品！");
    }

    private void clearFields() {
        tfProductCode.setText("");
        tfPurchaseQuantity.setText("");
        tfProductName.setText("");
        tfCategory.setText("");
        tfSupplier.setText("");
        tfSellPrice.setText("");
        tfPurchasePrice.setText("");
        tfChargeUnit.setText("");
        tfStock.setText("");
        tfStockValue.setText("");
        lblTotalPrice.setText("应付金额：");
    }

    private void confirmSale() {
        try {
            int purchaseQuantity = Integer.parseInt(tfPurchaseQuantity.getText().trim());
            double sellPrice = Double.parseDouble(tfSellPrice.getText().trim());
            double totalPrice = purchaseQuantity * sellPrice;
            lblTotalPrice.setText("应付金额：" + totalPrice);
            JOptionPane.showMessageDialog(this, "销售确认成功，金额：" + totalPrice);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "请输入正确的购买数量和商品价格！", "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SupermarketManager());
    }
}
