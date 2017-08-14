package utility;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;




public class DataBase {
	private static final String DBDRIVER = "com.mysql.jdbc.Driver";
	public static Connection conn = null; 
	public static PreparedStatement pst = null;
	static Logger log = Logger.getLogger("lavasoft"); 
	/**
	 * 连接数据库
	 * @param DBURL
	 * @param DBUSER
	 * @param DBPASS
	 * @return conn
	 */
	public static Connection connactDB(String DBURL, String DBUSER,String DBPASS) throws Exception{
		close();
	    try {
			Class.forName(DBDRIVER);
			System.out.println(DBURL+DBUSER+DBPASS);
			conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
			return conn;
		} catch (ClassNotFoundException e) {
			throw new Exception("驱动加载失败"+e.toString());
		} catch (SQLException e) {
			throw new Exception("连接数据库失败"+e.toString());
		}
	    
	}
//	/**
//	 * 插入数据
//	 * @param sql
//	 * @return 返回插入条数
//	 * @throws Exception 
//	 */
//	public static <T> T executeSQL(String sql) throws Exception{
//		log.info("执行sql："+sql);
//		try {
//			pst = conn.prepareStatement(sql);
//			pst.executeUpdate(sql);
//			return (T) (Integer) pst.getUpdateCount();
//		} catch (SQLException e) {
//			throw new Exception("执行sql异常"+e.toString());
//		}
//	}
//	
//	/**
//	 * 批量插入数据
//	 * @param sql
//	 * @return 返回插入条数
//	 * @throws Exception 
//	 */
//	public static <T> T executeSQLAll(String sql) throws Exception{
//		log.info("执行sql："+sql);
//		final int batchSize = 10000;
//		int count = 0;
//		int commitCount = 0;
//		try {
//			conn.setAutoCommit(false);
//			pst = conn.prepareStatement(sql);
//			for(int i=0;i<=500000;i++){
//				pst.setInt(1,i);
//				pst.setString(2,"productName"+i);
//				pst.setString(3,"8956784578");
//				pst.addBatch();
//				if(++count%batchSize==0){
//					pst.executeBatch();
//					if(++commitCount%5==0){
//						conn.commit();
//						log.info("已提交数："+commitCount);
//					}
//				}
//				
//			}
//			return (T) (Integer) pst.getUpdateCount();
//		} catch (SQLException e) {
//			throw new Exception("执行sql异常"+e.toString());
//		}
//	}
	
	/**
	 * 关闭DB连接及释放资源
	 */
	public static void close(){
		try{
			if(pst!=null){
				pst.close();
			}
			if(conn!=null){
				conn.close();
			}
		}catch(Exception e){
			log.warning("释放资源失败"+e.toString());
		}
	}
	
}