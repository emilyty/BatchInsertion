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
 * ���ݿ����Ӵ���
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
	  * ���ڲ���
	  * @param panel
	  */
    public void placeComponents(JPanel panel) {
        panel.setLayout(null);
        
        //���ݿ����������
        JLabel DBURLLabel = new JLabel("*DatabaseUrl:");
        DBURLLabel.setBounds(10,10,80,25);
        panel.add(DBURLLabel);
        DataBaseUrlText = new JTextField(20);
        DataBaseUrlText.setBounds(100,10,450,25);
        panel.add(DataBaseUrlText);
        
        //���ݿ��û��������
        JLabel userLabel = new JLabel("*User:");
        userLabel.setBounds(10,40,80,25);
        panel.add(userLabel);
        userText = new JTextField(20);
        userText.setBounds(100,40,165,25);
        panel.add(userText);

        // ���ݿ����������
        JLabel passwordLabel = new JLabel("*Password:");
        passwordLabel.setBounds(10,70,165,25);
        panel.add(passwordLabel);
        passwordText = new JPasswordField(20);
        passwordText.setBounds(100,70,165,25);
        panel.add(passwordText);
        
        //��Ҫ���������
        JLabel totalcountLabel = new JLabel("*total:");
        totalcountLabel.setBounds(10,100,165,25);
        panel.add(totalcountLabel);
        JTextField counttext = new JTextField(20);
        counttext.setBounds(100,100,165,25);
        panel.add(counttext);
        
        //��ʾlog��Ϣ
        Logtext = new JTextArea();
        Logtext.setBounds(10,170,560,250);
        panel.add(Logtext);
        
        
        // ������¼��ť
        beginButton = new JButton("��ʼ");
        beginButton.setBounds(400, 100, 80, 25);
        panel.add(beginButton);
        
        //��ť�¼�����
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
                		Logtext.append("total�ֶβ���Ϊ��\n");
                		throw new NumberFormatException("total�ֶ�Ϊ��"+e1.toString());
                }
                 try {
                	 DataBase.connactDB(databaseURL, dbUser, dbpwd);
                	 Logtext.append("���������ݿ�\n");
                	 Logtext.append("׼��������\n");
                	 long start = System.currentTimeMillis();
                	 insertProduct.batchInsertProduct(total);
                	 long time = System.currentTimeMillis()-start;
                	 Logtext.append("�����\n"+"�ܹ���ʱ��"+time+" ����");
                	 
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