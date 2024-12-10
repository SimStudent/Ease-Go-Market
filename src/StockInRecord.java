import java.time.LocalDateTime;

public class StockInRecord {

    public static final String[] columnNames_en = {"RecordID", "ClothingID", "Quantity", "Price", "StockInDate"};
    public static final String[] columnNames_cn = {"记录ID", "服装ID", "数量", "入库价格", "入库时间"};


    private int recordID;
    private String clothingID;
    private int quantity;
    private double price;
    private LocalDateTime stockInDate;

    // 无参构造器
    public StockInRecord() {}

    // 带参数的构造器
    public StockInRecord(String clothingID, int quantity, double price) {
        this.clothingID = clothingID;
        this.quantity = quantity;
        this.price = price;
        this.stockInDate = LocalDateTime.now(); // 默认设置为当前时间
    }

    // Object Getters
    public Object[] getObject(){
        return new Object[]{recordID, clothingID, quantity, price, stockInDate};
    }

    
    // Getter 和 Setter 方法
    public int getRecordID() {
        return recordID;
    }

    public void setRecordID(int recordID) {
        this.recordID = recordID;
    }

    public String getClothingID() {
        return clothingID;
    }

    public void setClothingID(String clothingID) {
        this.clothingID = clothingID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getStockInDate() {
        return stockInDate;
    }

    public void setStockInDate(LocalDateTime stockInDate) {
        this.stockInDate = stockInDate;
    }

    @Override
    public String toString() {
        return "StockInRecord{" +
                "recordID=" + recordID +
                ", clothingID='" + clothingID + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", stockInDate=" + stockInDate +
                '}';
    }
}


class StockOutRecord {

    public static final String[] columnNames_en = {"RecordID", "ClothingID", "Quantity", "Price", "StockOutDate"};
    public static final String[] columnNames_cn = {"记录ID", "服装ID", "数量", "出库价格", "出库时间"};

    private int recordID;
    private String clothingID;
    private int quantity;
    private double price;
    private LocalDateTime stockOutDate;

    // 无参构造器
    public StockOutRecord() {}

    // 带参数的构造器
    public StockOutRecord(String clothingID, int quantity, double price) {
        this.clothingID = clothingID;
        this.quantity = quantity;
        this.price = price;
        this.stockOutDate = LocalDateTime.now(); // 默认设置为当前时间
    }

    // Object Getters
    public Object[] getObject(){
        return new Object[]{recordID, clothingID, quantity, price, stockOutDate};
    }


    // Getter 和 Setter 方法
    public int getRecordID() {
        return recordID;
    }

    public void setRecordID(int recordID) {
        this.recordID = recordID;
    }

    public String getClothingID() {
        return clothingID;
    }

    public void setClothingID(String clothingID) {
        this.clothingID = clothingID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getStockOutDate() {
        return stockOutDate;
    }

    public void setStockOutDate(LocalDateTime stockOutDate) {
        this.stockOutDate = stockOutDate;
    }

    @Override
    public String toString() {
        return "StockOutRecord{" +
                "recordID=" + recordID +
                ", clothingID='" + clothingID + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", stockOutDate=" + stockOutDate +
                '}';
    }
}