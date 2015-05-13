package QIAutomation.Utilities;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import QIAutomation.DataAccess.SEEDDBConnectionManager;

import yodlee.gather.exceptions.GeneralException;


public class IAEReportsUtil<T extends Comparable<?>>{

	public static final byte SITEPDB = 0;
	public static final byte REPALDA = 1;

	public static <T extends Comparable<?>> List<T> getReportAsList(String query, byte connectionDB,Class<T> clazz, String username, String password) throws SQLException{

		return new ArrayList<T>(getReportAsMap(query,connectionDB,clazz,username,password, null).values());
	}

	public static <T extends Comparable<?>> Map<String, T> getReportAsMap(String query, byte connectionDB,Class<T> clazz, String username, String password,String fieldName)throws SQLException{
		//YDataLogger.out("2^^^^^^^^^calling convertToMap");

		Map<String, T> reportMap = new HashMap<String, T>();

		Connection connection = null;
		if (connectionDB == IAEReportsUtil.SITEPDB){
			connection = SEEDDBConnectionManager.connectToSitep(username, password);
		}else if(connectionDB == IAEReportsUtil.REPALDA){
			connection = SEEDDBConnectionManager.connectToRepalda();
		}else{
			throw new GeneralException("Invalid DB");
		}

		Statement statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);
		statement.setFetchSize(500);
		ResultSet resultSet = statement.executeQuery(query);

		ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
		Field[] field = clazz.getDeclaredFields();
		Map<Integer, Field> fieldMap = new HashMap<Integer, Field>();
		int keyField = -1;
		for (Field itField : field){
			for (int i=1;i<=resultSetMetaData.getColumnCount();i++){
				if (itField.getName().equalsIgnoreCase(resultSetMetaData.getColumnName(i))){
					//YDataLogger.out("2^^^^^found -> "+itField.getName());
					fieldMap.put(i, itField);
					itField.setAccessible(true);
					if(null != fieldName && itField.getName().equalsIgnoreCase(fieldName)){
						keyField = i;
					}
				}
			}
		}
		if (fieldMap.size()!=resultSetMetaData.getColumnCount()){
			throw new GeneralException("Result set & container class are not compatible");
		}

		boolean generateKey = false;
		if (keyField==-1){
			generateKey = true;
		}

		while (resultSet.next()) {
			String key;
			//YDataLogger.out("2^^^^^^^^^^container class -> ");
			try {
				T containerClassObj = (T) clazz.newInstance();
				//YDataLogger.out("2^^^^^^^^^^container class -> "+containerClassObj.getClass());
				for (int i=1;i<=resultSetMetaData.getColumnCount();i++){
					//YDataLogger.out("2^^^^^^^^^^containerObj -> "+fieldMap.get(i).get(containerClassObj));
					fieldMap.get(i).set(containerClassObj, resultSet.getString(i));
				}
				if (generateKey){
					keyField++;
					key = String.valueOf(keyField);
				}else{
					key = resultSet.getString(keyField);
				}
				reportMap.put(key,containerClassObj);
			} catch (IllegalAccessException e) {
				throw new GeneralException(e.getMessage());
			} catch (InstantiationException e) {
				throw new GeneralException(e.getMessage());
			}
		}

		resultSet.close();
		statement.close();
		connection.close();

		return reportMap;
	}
}



