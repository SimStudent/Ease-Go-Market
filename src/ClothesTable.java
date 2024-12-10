import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import JDBC.LocalhostJDBC;


class InventoryTable extends JTable{

    protected String name;

    protected String[] columnNames;
    protected DefaultTableModel tableModel;

    // protected List<Object[]> dataList = new ArrayList<>();

    InventoryTable(String[] columnNames){
        this.columnNames = columnNames;
        tableModel = new DefaultTableModel(columnNames, 0);
        super.setModel(tableModel);

        // 设置居中样式
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for(int i = 0;i<columnNames.length;i++){    // 获取 tableModel 并且设置居中样式
            this.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }


    }

    public void printTable(){
        for(String a:columnNames)System.out.print(a+" | ");
        System.out.println();
    }


    // 重写函数，使表格不可编辑 ？ TODO:真奇怪，竟然用方法来封装这个属性
    @Override
    public boolean isCellEditable(int row, int column) {
        // return super.isCellEditable(row, column);
        return false;
    }
}

public class ClothesTable extends InventoryTable implements Observer {

    public ClothesTable() {
        this(Clothes.columnNames_cn);
    }

    public ClothesTable(String[] columnNames) {
        super(columnNames);
    }
    
    @Override
    public void update(){
        readData();
    }


    public List<Clothes> readData(){
        tableModel.setRowCount(0);
        List<Clothes> output = new ArrayList<>();
        try{
            List<Object[]> dataList = new ArrayList<>();
            // 创立连接
            Connection conn = new LocalhostJDBC().getConnection();
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("select * from clothes");
            while(rs.next()){
                Clothes A = new Clothes();
                A.setClothingID(rs.getString(1));
                A.setClothingName(rs.getString(2));
                A.setCategory(rs.getString(3));
                A.setSupplier(rs.getString(4));
                A.setUnits(rs.getString(5));
                A.setPurchase(rs.getDouble(6));
                A.setSelling(rs.getDouble(7));
                A.setInventory(rs.getInt(8));
                dataList.add(A.getObject());    // Add a row.
                output.add(A);
            }
            for(Object[] row : dataList){
                tableModel.addRow(row);
            }
            conn.close();   
            // TODO 设置表格宽度
            // setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return output;
    }

    public Boolean stockOut(String clothingID, int quantity,double value){
        // 添加数量检测，如果查询 知道库中存在，就进行出库，否则报错
        // Clothes clothes = new Clothes();
        // clothes.setClothingID(clothes.getClothingID());
        // if(clothes.getInventory() < quantity){
        //     System.out.println("库存不足！");
        //     return false;
        // }
        try{
            Connection conn = new LocalhostJDBC().getConnection();
            Statement stmt = conn.createStatement();
            String sql = "select inventory from clothes where clothingID = '" + clothingID + "'";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                int inventory = rs.getInt(1);
                if(inventory < quantity){
                    System.out.println("库存不足！");
                    return false;
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }


        try{
            // 创立连接
            Connection conn = new LocalhostJDBC().getConnection();
            Statement stmt = conn.createStatement();

            String sql = "update clothes set inventory = inventory - " + quantity + " where clothingID = '" + clothingID + "'";
            stmt.executeUpdate(sql);
            // 记录出库记录
            sql = "insert into StockOutRecord values(null,'" + clothingID + "'," + quantity + "," + value + ",now())";
            stmt.executeUpdate(sql);
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public Boolean stockIn(String clothingID, int quantity,double value){
        // 添加数量检测，如果查询 知道库中存在，就进行出库，否则报错
        // try{
        //     Connection conn = new LocalhostJDBC().getConnection();
        //     Statement stmt = conn.createStatement();
        //     String sql = "select inventory from clothes where clothingID = '" + clothingID + "'";
        //     ResultSet rs = stmt.executeQuery(sql);
        //     if(rs.next()){
        //         int inventory = rs.getInt(1);
        //         if(inventory < quantity){
        //             System.out.println("库存不足！");
        //             return false;
        //         }
        //     }
        // }
        // catch(Exception e){
        //     e.printStackTrace();
        // }

        try{
            // 创立连接
            Connection conn = new LocalhostJDBC().getConnection();
            Statement stmt = conn.createStatement();

            String sql = "update clothes set inventory = inventory + " + quantity + " where clothingID = '" + clothingID + "'";
            stmt.executeUpdate(sql);
            // 记录出库记录
            sql = "insert into StockInRecord values(null,'" + clothingID + "'," + quantity + "," + value + ",now())";
            stmt.executeUpdate(sql);
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }
}

class StockInRecordTable extends InventoryTable implements Observer {

    protected String name = "StockInRecord";

    public StockInRecordTable() {
        this(StockInRecord.columnNames_cn);
    }
    public StockInRecordTable(String[] columnNames) {
        super(columnNames);
    }

    @Override
    public void update(){
        readData();
    }

    public void readData(){
        try{
            tableModel.setRowCount(0);
            List<Object[]> dataList = new ArrayList<>();
            // 创立连接
            Connection conn = new LocalhostJDBC().getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from " + name);
            while(rs.next()){
                StockInRecord A = new StockInRecord();
                A.setRecordID(rs.getInt(1));
                A.setClothingID(rs.getString(2));
                A.setQuantity(rs.getInt(3));
                A.setPrice(rs.getDouble(4));
                A.setStockInDate(rs.getTimestamp(5).toLocalDateTime());
                System.out.println(A.getStockInDate());
                dataList.add(A.getObject());    // Add a row.
            }
            for(Object[] row : dataList){
                tableModel.addRow(row);
            }
            conn.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}

class StockOutRecordTable extends InventoryTable implements Observer {

    protected String name = "StockOutRecord";

    public StockOutRecordTable() {
        this(StockInRecord.columnNames_cn);
    }
    public StockOutRecordTable(String[] columnNames) {
        super(columnNames);
    }

    @Override
    public void update(){
        readData();
    }
    public void readData(){
        try{
            tableModel.setRowCount(0);
            List<Object[]> dataList = new ArrayList<>();
            // 创立连接
            Connection conn = new LocalhostJDBC().getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from " + name);
            while(rs.next()){
                StockOutRecord A = new StockOutRecord();
                A.setRecordID(rs.getInt(1));
                A.setClothingID(rs.getString(2));
                A.setQuantity(rs.getInt(3));
                A.setPrice(rs.getDouble(4));
                A.setStockOutDate(rs.getTimestamp(5).toLocalDateTime());
                System.out.println(A.getStockOutDate());
                dataList.add(A.getObject());    // Add a row.
            }
            for(Object[] row : dataList){
                tableModel.addRow(row);
            }
            conn.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}