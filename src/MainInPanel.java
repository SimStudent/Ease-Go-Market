import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import java.awt.*;
import java.util.ArrayList;

interface Observable{
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}

interface Observer{
    void update();
}


class MainInPanel extends JPanel implements Observable {

    private ClothesTable clothesTable; // 显示商品信息的表格

    // 窗体组件
    protected JTextField tfProductCode, tfPurchaseQuantity, tfStockOutValue;
    protected JTextField tfProductName, tfCategory, tfSupplier;
    protected JTextField tfSellPrice, tfPurchasePrice, tfChargeUnit, tfStock, tfStockValue;
    protected JLabel lblTotalPrice;

    private ArrayList<Observer> observers = new ArrayList<>();

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    MainInPanel(){
        super(new BorderLayout());

        // TODO 完全参数化
        clothesTable = new ClothesTable(Clothes.columnNames_cn);
        clothesTable.printTable();
        JScrollPane scrollPane = new JScrollPane(clothesTable);
        add(scrollPane, BorderLayout.CENTER);

        // 底部的操作区域
        JPanel actionPanel = new JPanel(null);
        actionPanel.setPreferredSize(new Dimension(800, 200));
        add(actionPanel, BorderLayout.SOUTH);

        /////
        JLabel lblPurchaseQuantity = new JLabel("入库数量");
        lblPurchaseQuantity.setBounds(20, 10, 80, 25);  
        actionPanel.add(lblPurchaseQuantity);

        tfPurchaseQuantity = new JTextField(10);
        tfPurchaseQuantity.setBounds(100, 10, 100, 25);   
        actionPanel.add(tfPurchaseQuantity);

        JLabel lblStockOutValue = new JLabel("入库单价");
        lblStockOutValue.setBounds(220, 10, 80, 25);    
        actionPanel.add(lblStockOutValue);

        tfStockOutValue = new JTextField(10);
        tfStockOutValue.setBounds(300, 10, 100, 25);  
        actionPanel.add(tfStockOutValue);

        JLabel lblProductCode = new JLabel("服装编号");
        lblProductCode.setBounds(420, 10, 80, 25);
        actionPanel.add(lblProductCode);

        tfProductCode = new JTextField(10);
        tfProductCode.setBounds(520, 10, 100, 25);
        actionPanel.add(tfProductCode);

        JButton btnSearch = new JButton("搜索");
        btnSearch.setBounds(640, 10, 80, 25);
        actionPanel.add(btnSearch);

        // JButton btnClear = new JButton("清空");
        // btnClear.setBounds(520, 10, 80, 25);
        // actionPanel.add(btnClear);

        JLabel lblProductName = new JLabel("服装名称");
        lblProductName.setBounds(20, 50, 80, 25);
        actionPanel.add(lblProductName);

        tfProductName = new JTextField(10);
        tfProductName.setBounds(100, 50, 100, 25);
        // tfProductName.setBackground(getBackground());
        tfProductName.setEditable(false);
        // tfProductName.setText("夏日限定");
        actionPanel.add(tfProductName);

        JLabel lblCategory = new JLabel("服装类别");
        lblCategory.setBounds(220, 50, 80, 25);
        actionPanel.add(lblCategory);

        tfCategory = new JTextField(10);
        tfCategory.setBounds(300, 50, 100, 25);
        tfCategory.setEditable(false);
        actionPanel.add(tfCategory);

        JLabel lblSupplier = new JLabel("服装供应商");
        lblSupplier.setBounds(420, 50, 80, 25);
        actionPanel.add(lblSupplier);

        tfSupplier = new JTextField(10);
        tfSupplier.setBounds(520, 50, 100, 25);
        tfSupplier.setEditable(false);
        actionPanel.add(tfSupplier);

        JButton btnStockIn = new JButton("入货");
        btnStockIn.setBounds(640, 50, 80, 25);
        actionPanel.add(btnStockIn);

        /* 第三行 */
        JLabel lblSellPrice = new JLabel("建议零售价");
        lblSellPrice.setBounds(20, 90, 80, 25);
        actionPanel.add(lblSellPrice);

        tfSellPrice = new JTextField(10);
        tfSellPrice.setBounds(100, 90, 100, 25);
        tfSellPrice.setEditable(false);
        actionPanel.add(tfSellPrice);

        JLabel lblPurchasePrice = new JLabel("建议进货价");
        lblPurchasePrice.setBounds(220, 90, 80, 25);
        actionPanel.add(lblPurchasePrice);

        tfPurchasePrice = new JTextField(10);
        tfPurchasePrice.setBounds(300, 90, 100, 25);
        tfPurchasePrice.setEditable(false);
        actionPanel.add(tfPurchasePrice);

        JLabel lblChargeUnit = new JLabel("计价单位");
        lblChargeUnit.setBounds(420, 90, 80, 25);
        actionPanel.add(lblChargeUnit);

        tfChargeUnit = new JTextField(10);
        tfChargeUnit.setBounds(520, 90, 100, 25);
        tfChargeUnit.setEditable(false);
        actionPanel.add(tfChargeUnit);

        JButton btnResetComponent = new JButton("重置");
        btnResetComponent.setBounds(640, 90, 80, 25);
        actionPanel.add(btnResetComponent);

        JLabel lblStock = new JLabel("库存数量");
        lblStock.setBounds(20, 130, 80, 25);
        actionPanel.add(lblStock);

        tfStock = new JTextField(10);
        tfStock.setBounds(100, 130, 100, 25);
        tfStock.setEditable(false);
        actionPanel.add(tfStock);

        clothesTable.readData();
        // 事件绑定
        btnSearch.addActionListener(e -> searchProduct());
        btnStockIn.addActionListener(e -> stockInProduct());
        btnResetComponent.addActionListener(e -> resetComponent());
        // btnClear.addActionListener(e -> clearFields());
        // btnConfirm.addActionListener(e -> confirmSale());
        // btnCancel.addActionListener(e -> JOptionPane.showMessageDialog(this, "销售已取消！"));
    }

    private void searchProduct() {
        String productCode = tfProductCode.getText().trim();
        for (Clothes clothes : clothesTable.readData()) {
            if (clothes.getClothingID().equals(productCode)) {
                tfProductName.setText(clothes.getClothingName());
                tfCategory.setText(clothes.getCategory());
                tfSupplier.setText(clothes.getSupplier());
                tfSellPrice.setText(String.valueOf(clothes.getSelling()));
                tfPurchasePrice.setText(String.valueOf(clothes.getPurchase()));
                tfChargeUnit.setText(clothes.getUnits());
                tfStock.setText(String.valueOf(clothes.getInventory()));
            }
        }
    }

    private void stockInProduct() {
        if (tfProductCode.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "请输入服装编号！");
            return;
        }
        if (tfPurchaseQuantity.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "请输入入库数量！");
            return;
        }
        if (tfStockOutValue.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "请输入入库价格！");
            return;
        }
        String productCode = tfProductCode.getText().trim();
        Boolean isStockOut = clothesTable.stockIn(productCode, Integer.parseInt(tfPurchaseQuantity.getText()), Double.parseDouble(tfStockOutValue.getText()));
        if(!isStockOut)JOptionPane.showMessageDialog(this, "商品库存不足，请重新输入！","错误",JOptionPane.ERROR_MESSAGE);
        else {
            JOptionPane.showMessageDialog(this, productCode + "商品入库成功！");
            notifyObservers();
        }
    }

    private void resetComponent() {
        tfProductCode.setText("");
        tfProductName.setText("");
        tfCategory.setText("");
        tfSupplier.setText("");
        tfSellPrice.setText("");
        tfPurchasePrice.setText("");
        tfChargeUnit.setText("");
        tfStock.setText("");
        tfStockOutValue .setText("");
        tfPurchaseQuantity.setText("");
    }

    public ClothesTable getClothesTable() { return clothesTable; }
}

/*
 * 
 * This is Main Out Panel
 * 
 * 
 */
class MainOutPanel extends JPanel implements Observable {

    private ClothesTable clothesTable; // 显示商品信息的表格

    // 窗体组件
    protected JTextField tfProductCode, tfPurchaseQuantity, tfStockOutValue;
    protected JTextField tfProductName, tfCategory, tfSupplier;
    protected JTextField tfSellPrice, tfPurchasePrice, tfChargeUnit, tfStock, tfStockValue;
    protected JLabel lblTotalPrice;

    private ArrayList<Observer> observers = new ArrayList<>();

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }



    MainOutPanel(){
        super(new BorderLayout());


        // TODO 完全参数化
        clothesTable = new ClothesTable(Clothes.columnNames_cn);
        clothesTable.printTable();
        JScrollPane scrollPane = new JScrollPane(clothesTable);
        add(scrollPane, BorderLayout.CENTER);

        // 底部的操作区域
        JPanel actionPanel = new JPanel(null);
        actionPanel.setPreferredSize(new Dimension(800, 200));
        add(actionPanel, BorderLayout.SOUTH);

        /////
        JLabel lblPurchaseQuantity = new JLabel("出库数量");
        lblPurchaseQuantity.setBounds(20, 10, 80, 25);  
        actionPanel.add(lblPurchaseQuantity);

        tfPurchaseQuantity = new JTextField(10);
        tfPurchaseQuantity.setBounds(100, 10, 100, 25);   
        actionPanel.add(tfPurchaseQuantity);


        JLabel lblStockOutValue = new JLabel("出库单价");
        lblStockOutValue.setBounds(220, 10, 80, 25);    
        actionPanel.add(lblStockOutValue);

        tfStockOutValue = new JTextField(10);
        tfStockOutValue.setBounds(300, 10, 100, 25);  
        actionPanel.add(tfStockOutValue);

        JLabel lblProductCode = new JLabel("服装编号");
        lblProductCode.setBounds(420, 10, 80, 25);
        actionPanel.add(lblProductCode);

        tfProductCode = new JTextField(10);
        tfProductCode.setBounds(520, 10, 100, 25);
        actionPanel.add(tfProductCode);

        JButton btnSearch = new JButton("搜索");
        btnSearch.setBounds(640, 10, 80, 25);
        actionPanel.add(btnSearch);

        // JButton btnClear = new JButton("清空");
        // btnClear.setBounds(520, 10, 80, 25);
        // actionPanel.add(btnClear);

        JLabel lblProductName = new JLabel("服装名称");
        lblProductName.setBounds(20, 50, 80, 25);
        actionPanel.add(lblProductName);

        tfProductName = new JTextField(10);
        tfProductName.setBounds(100, 50, 100, 25);
        // tfProductName.setBackground(getBackground());
        tfProductName.setEditable(false);
        // tfProductName.setText("夏日限定");
        actionPanel.add(tfProductName);

        JLabel lblCategory = new JLabel("服装类别");
        lblCategory.setBounds(220, 50, 80, 25);
        actionPanel.add(lblCategory);

        tfCategory = new JTextField(10);
        tfCategory.setBounds(300, 50, 100, 25);
        tfCategory.setEditable(false);
        actionPanel.add(tfCategory);

        JLabel lblSupplier = new JLabel("服装供应商");
        lblSupplier.setBounds(420, 50, 80, 25);
        actionPanel.add(lblSupplier);

        tfSupplier = new JTextField(10);
        tfSupplier.setBounds(520, 50, 100, 25);
        tfSupplier.setEditable(false);
        actionPanel.add(tfSupplier);

        JButton btnStockOut = new JButton("出货");
        btnStockOut.setBounds(640, 50, 80, 25);
        actionPanel.add(btnStockOut);

        /* 第三行 */
        JLabel lblSellPrice = new JLabel("建议零售价");
        lblSellPrice.setBounds(20, 90, 80, 25);
        actionPanel.add(lblSellPrice);

        tfSellPrice = new JTextField(10);
        tfSellPrice.setBounds(100, 90, 100, 25);
        tfSellPrice.setEditable(false);
        actionPanel.add(tfSellPrice);

        JLabel lblPurchasePrice = new JLabel("建议进货价");
        lblPurchasePrice.setBounds(220, 90, 80, 25);
        actionPanel.add(lblPurchasePrice);

        tfPurchasePrice = new JTextField(10);
        tfPurchasePrice.setBounds(300, 90, 100, 25);
        tfPurchasePrice.setEditable(false);
        actionPanel.add(tfPurchasePrice);

        JLabel lblChargeUnit = new JLabel("计价单位");
        lblChargeUnit.setBounds(420, 90, 80, 25);
        actionPanel.add(lblChargeUnit);

        tfChargeUnit = new JTextField(10);
        tfChargeUnit.setBounds(520, 90, 100, 25);
        tfChargeUnit.setEditable(false);
        actionPanel.add(tfChargeUnit);

        JButton btnResetComponent = new JButton("重置");
        btnResetComponent.setBounds(640, 90, 80, 25);
        actionPanel.add(btnResetComponent);


        JLabel lblStock = new JLabel("库存数量");
        lblStock.setBounds(20, 130, 80, 25);
        actionPanel.add(lblStock);

        tfStock = new JTextField(10);
        tfStock.setBounds(100, 130, 100, 25);
        tfStock.setEditable(false);
        actionPanel.add(tfStock);



        clothesTable.readData();
        // 事件绑定
        btnSearch.addActionListener(e -> searchProduct());
        btnStockOut.addActionListener(e -> stockOutProduct());
        btnResetComponent.addActionListener(e -> resetComponent());
        // btnClear.addActionListener(e -> clearFields());
        // btnConfirm.addActionListener(e -> confirmSale());
        // btnCancel.addActionListener(e -> JOptionPane.showMessageDialog(this, "销售已取消！"));
    }

    private void searchProduct() {
        String productCode = tfProductCode.getText().trim();
        for (Clothes clothes : clothesTable.readData()) {
            if (clothes.getClothingID().equals(productCode)) {
                tfProductName.setText(clothes.getClothingName());
                tfCategory.setText(clothes.getCategory());
                tfSupplier.setText(clothes.getSupplier());
                tfSellPrice.setText(String.valueOf(clothes.getSelling()));
                tfPurchasePrice.setText(String.valueOf(clothes.getPurchase()));
                tfChargeUnit.setText(clothes.getUnits());
                tfStock.setText(String.valueOf(clothes.getInventory()));

            }
        }
    }

    // private void stockOutProduct(StockOutRecordPanel stockOutRecordPanel) {
    private void stockOutProduct() {

        if (tfProductCode.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "请输入服装编号！");
            return;
        }
        if (tfPurchaseQuantity.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "请输入出库数量！");
            return;
        }
        if (tfStockOutValue.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "请输入出库价格！");
            return;
        }
        String productCode = tfProductCode.getText().trim();
        Boolean isStockOut = clothesTable.stockOut(productCode, Integer.parseInt(tfPurchaseQuantity.getText()), Double.parseDouble(tfStockOutValue.getText()));
        if(!isStockOut)JOptionPane.showMessageDialog(this, "商品库存不足，请重新输入！","错误",JOptionPane.ERROR_MESSAGE);
        else {
            JOptionPane.showMessageDialog(this, productCode + "商品出库成功！");
            notifyObservers();
        }
    }

    private void resetComponent() {
        tfProductCode.setText("");
        tfProductName.setText("");
        tfCategory.setText("");
        tfSupplier.setText("");
        tfSellPrice.setText("");
        tfPurchasePrice.setText("");
        tfChargeUnit.setText("");
        tfStock.setText("");
        tfStockOutValue .setText("");
        tfPurchaseQuantity.setText("");
    }
    public ClothesTable getClothesTable() { return clothesTable; }
}


class StockInRecordPanel extends JPanel {
    private StockInRecordTable stockInRecordTable; // 显示商品信息的表格
    // 窗体组件
    StockInRecordPanel(){
        super(new BorderLayout());
        // 顶部的表格部分
        // table = new JTable(tableModel);
        stockInRecordTable = new StockInRecordTable(StockInRecord.columnNames_cn);
        stockInRecordTable.printTable();
        JScrollPane scrollPane = new JScrollPane(stockInRecordTable);
        add(scrollPane, BorderLayout.CENTER);
        stockInRecordTable.readData();
    }
    public StockInRecordTable getStockInRecordTable() { return stockInRecordTable; }

}

class StockOutRecordPanel extends JPanel {
    private StockOutRecordTable stockOutRecordTable;
    StockOutRecordPanel(){
        super(new BorderLayout());
        stockOutRecordTable = new StockOutRecordTable(StockOutRecord.columnNames_cn);
        stockOutRecordTable.printTable();
        JScrollPane scrollPane = new JScrollPane(stockOutRecordTable);
        add(scrollPane, BorderLayout.CENTER);
        stockOutRecordTable.readData();
    }
    public StockOutRecordTable getStockOutRecordTable() { return stockOutRecordTable; }
}