public class Clothes {

    public static final String[] columnNames_en = {"ClothingID", "ClothingName", "Category", "Supplier", "Units", "Purchase", "Selling", "Inventory"};
    public static final String[] columnNames_cn = {"服装ID", "服装名称", "分类", "供应商", "单位", "建议进货价", "建议零售价", "库存数量"};

    private String clothingID;      // 服装ID
    private String clothingName;    // 服装名称
    private String category;        // 分类
    private String supplier;        // 供应商
    private String units;           // 单位
    private double purchase;        // 理论进货价
    private double selling;         // 理论售价
    private int inventory;          // 库存数量

    // Constructor
    public Clothes(){}

    public Clothes(String clothingID, String clothingName, String category, String supplier,
                    String units, double purchase, double selling, int inventory) {
        this.clothingID = clothingID;
        this.clothingName = clothingName;
        this.category = category;
        this.supplier = supplier;
        this.units = units;
        this.purchase = purchase;
        this.selling = selling;
        this.inventory = inventory;
    }

    // Object Getters
    public Object[] getObject(){
        return new Object[]{clothingID, clothingName, category, supplier, units, purchase, selling, inventory};
    }

    // Getters and Setters
    public String getClothingID() {
        return clothingID;
    }

    public void setClothingID(String clothingID) {
        this.clothingID = clothingID;
    }

    public String getClothingName() {
        return clothingName;
    }

    public void setClothingName(String clothingName) {
        this.clothingName = clothingName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public double getPurchase() {
        return purchase;
    }

    public void setPurchase(double purchase) {
        this.purchase = purchase;
    }

    public double getSelling() {
        return selling;
    }

    public void setSelling(double selling) {
        this.selling = selling;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    // toString method for better debugging
    @Override
    public String toString() {
        return "Clothing{" +
                "clothingID='" + clothingID + '\'' +
                ", clothingName='" + clothingName + '\'' +
                ", category='" + category + '\'' +
                ", supplier='" + supplier + '\'' +
                ", units='" + units + '\'' +
                ", purchase=" + purchase +
                ", selling=" + selling +
                ", inventory=" + inventory +
                '}';
    }
}
