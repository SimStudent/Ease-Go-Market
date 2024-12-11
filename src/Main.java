import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main extends JFrame {

    protected static String programName = new String("Ease-Go-Market 进销存管理系统");
    protected static String version = new String("v0.1 Beta");
    protected static Boolean isShowVersionOnWindows = true; // 是否在窗体显示版本号

    // JVM获取类加载器，再getSource获得文件的相对坐标？
    public static ImageIcon getIconFromFile(String iconName) {
        java.net.URL iconURL = Main.class.getClassLoader().getResource("icon/" + iconName);  // TODO What it means URL?
        if (iconURL != null) {
            return new ImageIcon(iconURL);
        } else {
            System.err.println("Icon not found: " + iconName);
            return null;
        }
    }

    public Main() {
        this.setFrame();
        this.initUI();
        this.setVisible(true);
    }
    private void setFrame(){
        String title;
        if (isShowVersionOnWindows)
            title = programName + " " + version;
        else title = programName;
        setTitle(title);
        setSize(800, 600);
        setIconImage(getIconFromFile("database_x64.png").getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void initUI() {
        JTabbedPane tabbedPane = new JTabbedPane();
        add(tabbedPane);

        // 入库操作面板
        MainInPanel stockInPanel = new MainInPanel();
        tabbedPane.addTab("入库操作", stockInPanel);

        // 出库操作面板
        MainOutPanel stockOutPanel = new MainOutPanel();
        tabbedPane.addTab("出库操作", stockOutPanel);

        // 添加商品面板
        MainInsertPanel insertPanel = new MainInsertPanel();
        tabbedPane.addTab("添加服装", insertPanel);

        // 入库记录面板
        StockInRecordPanel stockInRecordPanel = new StockInRecordPanel();
        tabbedPane.addTab("入库记录", stockInRecordPanel);

        // 出库记录面板
        StockOutRecordPanel stockOutRecordPanel = new StockOutRecordPanel();
        tabbedPane.addTab("出库记录", stockOutRecordPanel);

        stockInPanel.addObserver(stockInRecordPanel.getStockInRecordTable());
        stockInPanel.addObserver(stockOutRecordPanel.getStockOutRecordTable());
        stockInPanel.addObserver(stockInPanel.getClothesTable());
        stockInPanel.addObserver(stockOutPanel.getClothesTable());
        stockInPanel.addObserver(insertPanel.getClothesTable());
        
        // 注册 stockOutPanel 的观察者
        stockOutPanel.addObserver(stockInRecordPanel.getStockInRecordTable());
        stockOutPanel.addObserver(stockOutRecordPanel.getStockOutRecordTable());
        stockOutPanel.addObserver(stockInPanel.getClothesTable());
        stockOutPanel.addObserver(stockOutPanel.getClothesTable());
        stockOutPanel.addObserver(insertPanel.getClothesTable());

        // 注册 insertPanel 的观察者
        insertPanel.addObserver(stockInRecordPanel.getStockInRecordTable());
        insertPanel.addObserver(stockOutRecordPanel.getStockOutRecordTable());
        insertPanel.addObserver(stockInPanel.getClothesTable());
        insertPanel.addObserver(stockOutPanel.getClothesTable());
        insertPanel.addObserver(insertPanel.getClothesTable());
    }

    public static void main(String[] args) {
        new LoginUI();
        SwingUtilities.invokeLater(() -> new Main());
    }
}



