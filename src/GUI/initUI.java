package GUI;
import insertProducts.business.insertProduct;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField; 

import utility.DataBase;
/**
 * 数据库连接窗口
 * @author ya.tu
 *
 */
public class initUI  extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8101074979398636682L;
	private JTextField DataBaseUrlText =null;
	private JTextField userText = null;
	private JPasswordField passwordText = null;
	private JButton beginButton = null;
	public JTextArea Logtext = null;
	int	total;
	private static String pre = "jdbc:mysql://";
	 
	 /**
	  * 窗口布局
	  * @param panel
	  */
    public void placeComponents(JPanel panel) {
        panel.setLayout(null);
        
        //数据库连接输入框
        JLabel DBURLLabel = new JLabel("*DatabaseUrl:");
        DBURLLabel.setBounds(10,10,80,25);
        panel.add(DBURLLabel);
        DataBaseUrlText = new JTextField(20);
        DataBaseUrlText.setBounds(100,10,450,25);
        panel.add(DataBaseUrlText);
        
        //数据库用户名输入框
        JLabel userLabel = new JLabel("*User:");
        userLabel.setBounds(10,40,80,25);
        panel.add(userLabel);
        userText = new JTextField(20);
        userText.setBounds(100,40,165,25);
        panel.add(userText);

        // 数据库密码输入框
        JLabel passwordLabel = new JLabel("*Password:");
        passwordLabel.setBounds(10,70,165,25);
        panel.add(passwordLabel);
        passwordText = new JPasswordField(20);
        passwordText.setBounds(100,70,165,25);
        panel.add(passwordText);
        
        //需要造的数据量
        JLabel totalcountLabel = new JLabel("*total:");
        totalcountLabel.setBounds(10,100,165,25);
        panel.add(totalcountLabel);
        JTextField counttext = new JTextField(20);
        counttext.setBounds(100,100,165,25);
        panel.add(counttext);
        
        //显示log信息
        Logtext = new JTextArea();
        Logtext.setBounds(10,170,560,250);
        panel.add(Logtext);
        
        
        // 创建登录按钮
        beginButton = new JButton("开始");
        beginButton.setBounds(400, 100, 80, 25);
        panel.add(beginButton);
        
        //按钮事件监听
        beginButton.addActionListener(new ActionListener(){
        	 
            @SuppressWarnings("deprecation")
			@Override
            public void actionPerformed(ActionEvent arg0) {
                
                String databaseURL =pre+ DataBaseUrlText.getText().trim();
                String dbUser = userText.getText().trim();
                String dbpwd = passwordText.getText().trim();
                try{
                	total = Integer.parseInt(counttext.getText());
                }catch(NumberFormatException e1){
                		Logtext.append("total字段不能为空\n");
                		throw new NumberFormatException("total字段为空"+e1.toString());
                }
                 try {
                	 DataBase.connactDB(databaseURL, dbUser, dbpwd);
                	 Logtext.append("已连接数据库\n");
                	 Logtext.append("准备造数据\n");
                	 long start = System.currentTimeMillis();
                	 insertProduct.batchInsertProduct(total);
                	 long time = System.currentTimeMillis()-start;
                	 Logtext.append("已完成\n"+"总共耗时："+time+" 毫秒");
                	 
				} catch (Exception e) {
					DataBase.close();
					Logtext.append(e.getMessage());
				}
            }
        });
    }

	@Override
	public void actionPerformed(ActionEvent e) {

		
	}



}