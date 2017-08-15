package insertProducts.business;

import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import GUI.initUI;

public class mian {
    public static void main(String[] args) throws SQLException {    
        JFrame frame = new JFrame("连接数据库之后自动造数据");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();    
        frame.add(panel);
        initUI UI = new initUI();
        UI.placeComponents(panel);
        frame.setVisible(true);
    }
}
