import javax.swing.*;

import java.sql.*;
import JDBC.LocalhostJDBC;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class LoginUI extends JDialog {

    private JTextField userField;
    private JPasswordField passwordField;

    public LoginUI(){
        super();
        initUI();
        setVisible(true);
    }

    public void getResize(){

    }

    public void initUI() {
        setTitle("进销存系统-请登录");
        setSize(310, 300);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);  // 直接杀掉进程
        setIconImage(Main.getIconFromFile("database_x64.png").getImage());
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                System.exit(0);
            }
        });

        setLocationRelativeTo(null);
        setModal(true); // 设置为模态对话框

        // Create header panel for the image
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Center the image

        // Create image label
        ImageIcon imageIcon = new ImageIcon(LoginUI.class.getResource("/icon/logo01.png")); // 使用类加载器加载资源文件
        JLabel headerImageLabel = new JLabel(imageIcon);
        headerPanel.add(headerImageLabel);
        

        // 设置主面板
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // 创建中间内容面板
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // 缩小上下间距
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST; // 左对齐

        JLabel userLabel = new JLabel(" 账号 ");
        userLabel.setHorizontalAlignment(SwingConstants.CENTER);
        userField = new JTextField(15);

        JLabel passwordLabel = new JLabel("密码");
        passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        passwordField = new JPasswordField(15);

        JButton loginButton = new JButton("登录");
        JButton registerButton = new JButton("注册");

        // 布局中间内容
        gbc.gridx = 0;
        gbc.gridy = 0;
        contentPanel.add(userLabel, gbc);

        gbc.gridx = 1;
        contentPanel.add(userField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        contentPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        contentPanel.add(passwordField, gbc);

        // gbc.gridx = 0;
        // gbc.gridy = 2;
        // contentPanel.add(rememberBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER; // 居中对齐按钮
        contentPanel.add(loginButton, gbc);

        gbc.gridy = 4;
        contentPanel.add(registerButton, gbc);

        // 添加组件到主面板
        mainPanel.add(headerPanel, BorderLayout.NORTH); // 将 headerPanel 添加到主面板的北边
        mainPanel.add(contentPanel, BorderLayout.CENTER); // 将 contentPanel 添加到主面板的中心

        // 添加主面板到窗体
        add(mainPanel);

        loginButton.addActionListener(e -> Login());
        registerButton.addActionListener(e -> Register());

        // 可以动态调整大小
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int width = getWidth();
                int height = getHeight();
                System.out.println("Current Width: " + width + ", Current Height: " + height);
            }
        });
    }

    private boolean isLoginReady_DB(){
        try{
            // List<Object[]> dataList = new ArrayList<>();
            // 创立连接
            Connection conn = new LocalhostJDBC().getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from User");
            while(rs.next()){
                String RSname = rs.getString(1);
                String RSpwd = rs.getString(2);
                String TFname = userField.getText();
                String TFPwd = String.valueOf(passwordField.getPassword());
                System.out.println("/* 以下是密码 */");
                System.out.println(RSname+" : "+RSpwd+" | "+TFname+" : "+TFPwd);
                if(RSname.equals(TFname) && RSpwd.equals(TFPwd))return true;
            }
            conn.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    private void Login() {
        boolean isLoginReady = isLoginReady_DB();
        if(isLoginReady){
            setVisible(false);
        }
        else JOptionPane.showMessageDialog(null, "登陆失败！请检查你的账号以及密码输入！");
    }

    private void Register(){
        String TFname = userField.getText();
        String TFPwd = String.valueOf(passwordField.getPassword());
        if(!isInputValid(TFname, TFPwd)){
            JOptionPane.showMessageDialog(null, "请检查你的账号以及密码输入！",
            "注册失败！", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try{
            Connection conn = new LocalhostJDBC().getConnection();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("insert into User values('"+TFname+"','"+TFPwd+"')");
            conn.close();
            JOptionPane.showMessageDialog(null, "注册成功！\n账号: "+TFname+" 密码: "+TFPwd, "注册成功！", JOptionPane.INFORMATION_MESSAGE);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


    private boolean isInputValid(String acc,String pwd){
        // TODO 完成账号密码输入检查
        if(acc == null || pwd == null)return false;
        return true;
    }
}