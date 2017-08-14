package insertProducts.business;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

import utility.DataBase;


public class insertProduct extends DataBase{
	static Logger log = Logger.getLogger("lavasoft"); 
	public static PreparedStatement pst = null;
	
	/**
	 * �����ݿ��в�����Ʒ����
	 * 50W��Ʒ���ݴ��Ҫ50��
	 * @throws SQLException
	 */
	public static void batchInsertProduct(int total) throws SQLException{
		
		long start = System.currentTimeMillis();
		StringBuffer sBuffer = new StringBuffer("insert into plt_taobao_product_copy(num_iid,title,dp_id) values");
		//int total = 500000;//��50W����
		int count =0;
		try{
			conn.setAutoCommit(false);
			for(int i=1;i<=total;i++){
					if(i%10000==0){
						sBuffer.append("('"+i+"','productName_"+i+"','22222222');");
					}else{
						sBuffer.append("('"+i+"','productName_"+i+"','22222222'),");
					}
					if(i%10000==0){//ÿһ���ύһ��
						++count;
						log.info("�ύ"+count+"��");
						pst = conn.prepareStatement(sBuffer.toString());
						pst.addBatch(sBuffer.toString());
						pst.executeBatch();
						conn.commit();
						sBuffer = new StringBuffer("insert into plt_taobao_product_copy(num_iid,title,dp_id) values");
					}
			}
		}catch(SQLException e){
			log.warning("sqlִ���쳣");
			throw new SQLException(e.toString());
		}finally{
			pst.close();
			conn.close(); 
			System.out.println((System.currentTimeMillis()-start));
		}
	}

}
