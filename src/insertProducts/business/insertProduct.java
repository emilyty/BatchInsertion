package insertProducts.business;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

import utility.DataBase;


public class insertProduct extends DataBase{
	static Logger log = Logger.getLogger("lavasoft"); 
	public static PreparedStatement pst = null;
	
	/**
	 * 往数据库中插入商品数据
	 * 50W商品数据大概要50秒
	 * @throws SQLException
	 */
	public static void batchInsertProduct(int total) throws SQLException{
		
		long start = System.currentTimeMillis();
		StringBuffer sBuffer = new StringBuffer("insert into plt_taobao_product_copy(num_iid,title,dp_id) values");
		//int total = 500000;//造50W数据
		int count =0;
		try{
			conn.setAutoCommit(false);
			for(int i=1;i<=total;i++){
					if(i%10000==0){
						sBuffer.append("('"+i+"','productName_"+i+"','22222222');");
					}else{
						sBuffer.append("('"+i+"','productName_"+i+"','22222222'),");
					}
					if(i%10000==0){//每一万提交一次
						++count;
						log.info("提交"+count+"次");
						pst = conn.prepareStatement(sBuffer.toString());
						pst.addBatch(sBuffer.toString());
						pst.executeBatch();
						conn.commit();
						sBuffer = new StringBuffer("insert into plt_taobao_product_copy(num_iid,title,dp_id) values");
					}
			}
		}catch(SQLException e){
			log.warning("sql执行异常");
			throw new SQLException(e.toString());
		}finally{
			pst.close();
			conn.close(); 
			System.out.println((System.currentTimeMillis()-start));
		}
	}

}
