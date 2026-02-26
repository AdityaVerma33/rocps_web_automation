package com.subex.automation.helpers.db;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.enums.DBType;
import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class DBHelper extends AcceptanceTest {
	DBType dbType;
	private String completeURL;
	private String user;
	private String password;

	public Connection dbConnection;
	private DataBaseData database;

	public Connection con = null;
	public Statement smt = null;
	public ResultSet rs = null;
	public String[][] localProperty = null;
	public String[][] globalProperty = null;
	public String query = null;
	public int i = 0;
	public int j = 0;
	public int tableCount = 0;
	
	DBHelper() throws Exception {
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con = DriverManager.getConnection("jdbc:odbc:SQL_SERVER;user=sa;password=welcome");
			smt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);	
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private DBHelper( String user, String password )
	{
		this.user = user;
		this.password = password;
	}
	
	public static DBHelper createReferenceConnection() throws Exception {
		try {
			DBHelper helper = null;
			if ( configProp.getDbType().equalsIgnoreCase( "sqlserver" ) )
				helper = createMSSQLDBHelper( configProp.getDbMachineName(), configProp.getDbUserName(), configProp.getDbPassword(), configProp.getDB(), configProp.getDbInstance() );
			else if ( configProp.getDbType().equalsIgnoreCase( "oracle" ) )
				helper = createOracleDBHelper( configProp.getDbMachineName(), configProp.getDbUserName(), configProp.getDbPassword(), configProp.getDbInstance() );
			else if ( configProp.getDbType().equalsIgnoreCase( "PostgreSQL" ) )
				helper = createPostgreSQLDBHelper( configProp.getDbMachineName(), configProp.getDbUserName(), configProp.getDbPassword(), configProp.getDB() );
			
			return helper;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static DBHelper createConnection(String dbType, String dbHost, String dbUserName, String dbPassword, String dbDatabase, String dbInstance) throws Exception {
		try {
			DBHelper helper = null;
			if ( dbType.equalsIgnoreCase( "sqlserver" ) )
				helper = createMSSQLDBHelper( dbHost, dbUserName, dbPassword, dbDatabase, dbInstance );
			else if ( dbType.equalsIgnoreCase( "oracle" ) )
				helper = createOracleDBHelper( dbHost, dbUserName, dbPassword, dbInstance );
			else if ( dbType.equalsIgnoreCase( "PostgreSQL" ) )
				helper = createPostgreSQLDBHelper( dbHost, dbUserName, dbPassword, dbDatabase );
			
			return helper;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static DBHelper createUsageConnection() throws Exception {
		try {
			DBHelper helper = null;
			if ( configProp.getDbType().equalsIgnoreCase( "sqlserver" ) )
				helper = createMSSQLDBHelper( configProp.getUsageMachineName(), configProp.getUsageUsername(), configProp.getUsagePassword(), configProp.getUsageDatabase(), configProp.getUsageInstance() );
			else if ( configProp.getDbType().equalsIgnoreCase( "oracle" ) )
				helper = createOracleDBHelper( configProp.getUsageMachineName(), configProp.getUsageUsername(), configProp.getUsagePassword(), configProp.getUsageInstance() );
			else if ( configProp.getDbType().equalsIgnoreCase( "PostgreSQL" ) )
				helper = createPostgreSQLDBHelper( configProp.getUsageMachineName(), configProp.getUsageUsername(), configProp.getUsagePassword(), configProp.getUsageDatabase() );
			
			return helper;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static DBHelper createFederatedDataSourceonnection(String[] dbDetails) throws Exception {
		try {
			DBHelper helper = null;
			if ( dbDetails[0].equalsIgnoreCase( "sqlserver" ) )
				helper = createMSSQLDBHelper( dbDetails[1], dbDetails[2], dbDetails[3], dbDetails[4], dbDetails[5] );
			else if ( dbDetails[0].equalsIgnoreCase( "oracle" ) )
				helper = createOracleDBHelper( dbDetails[1], dbDetails[2], dbDetails[3], dbDetails[5] );
			else if ( dbDetails[0].equalsIgnoreCase( "PostgreSQL" ) )
				helper = createPostgreSQLDBHelper( dbDetails[1], dbDetails[2], dbDetails[3], dbDetails[4] );
			
			return helper;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static int getPortNumber(DBHelper dbHelper) throws Exception {
		try {
			int port = dbHelper.dbType.port;
			int configPort = Integer.parseInt(configProp.getDBPortNumber());
			if (!(configPort == port))
				port = configPort;
			
			return port;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static int getPortNumber() throws Exception {
		try {
			int port = 5432;
			int configPort = Integer.parseInt(configProp.getDBPortNumber());
			if (!(configPort == port))
				port = configPort;
			
			return port;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	public static DBHelper createOracleDBHelper( String serverIP, String userName, String password, String instanceName ) throws Exception
	{
		try {
			DBHelper dbHelper = createOracleDBHelper(serverIP, userName, password, instanceName, true);
			return dbHelper;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static DBHelper createOracleDBHelper( String serverIP, String userName, String password, String instanceName, boolean throwError ) throws Exception
	{
		try {
			DBHelper dbHelper = new DBHelper( userName, password );
			dbHelper.dbType = DBType.ORACLE;
			int port = getPortNumber(dbHelper);
			
			String inst = ( ValidationHelper.isEmpty( instanceName ) ) ? "oracle9i" : instanceName;
			dbHelper.completeURL = dbHelper.dbType.url + serverIP + ":" + port + ":" + inst;
			dbHelper.dbConnection = dbHelper.getConnection(throwError);
			return dbHelper;
		} catch (Exception e) {
			if (throwError) {
				FailureHelper.setErrorMessage(e);
				throw e;
			}
			else {
				if (errorMsg != null)
					errorMsg = null;
				return null;
			}
		}
	}
	
	public static DBHelper createPostgreSQLDBHelper( String serverIP, String userName, String password, String dbName ) throws Exception
	{
		try {
			DBHelper dbHelper = createPostgreSQLDBHelper(serverIP, userName, password, dbName, true);
			return dbHelper;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static DBHelper createPostgreSQLDBHelper( String serverIP, String userName, String password, String dbName, boolean throwError ) throws Exception
	{
		try {
			DBHelper dbHelper = new DBHelper( userName, password );
			dbHelper.dbType = DBType.POSTGRESQL;
			int port = getPortNumber(dbHelper);
			
			dbHelper.completeURL = dbHelper.dbType.url + serverIP + ":" + port + "/" + dbName;
			dbHelper.dbConnection = dbHelper.getConnection(throwError);
			return dbHelper;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	public static DBHelper createMSSQLDBHelper( String serverIP, String userName, String password, String dbName, String instanceName ) throws Exception
	{
		try {
			DBHelper dbHelper = createMSSQLDBHelper(serverIP, userName, password, dbName, instanceName, true);
			return dbHelper;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static DBHelper createMSSQLDBHelper( String serverIP, String userName, String password, String dbName, String instanceName, boolean throwError ) throws Exception
	{
		try {
			DBHelper dbHelper = new DBHelper( userName, password );
			dbHelper.dbType = DBType.MSSQLSERVDER;
			int port = getPortNumber(dbHelper);
			
			dbHelper.completeURL = dbHelper.dbType.url + serverIP + ":" + port + ";databaseName=" + dbName + ";" + "Instance=" + instanceName + ";";
			dbHelper.dbConnection = dbHelper.getConnection(throwError);
			return dbHelper;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	private void copyDBIntoMemory( boolean shallCopyConstraints ) throws Exception
	{
		try {
			List<TableData> allTables = getData();
			
			ConstraintsData constraintsData = shallCopyConstraints ? new ConstraintsData( dbConnection ) : null;
			dbConnection.close();
			database = new DataBaseData( allTables, constraintsData );
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private List<TableData> getData() throws Exception {
		try {
			String databaseProductName = dbConnection.getMetaData().getDatabaseProductName();
			boolean isDBMSSQLServer = databaseProductName.equals( DBType.MSSQLSERVDER.productName );
			ResultSet allTablesRS = getAllTables();
			List<TableData> allTables = new ArrayList<TableData>();
	
			while ( allTablesRS.next() )
			{
				String tableName = allTablesRS.getString( 3 );
				if ( isDBMSSQLServer && tableName.equals( "dtproperties" ) ) //to filter a system table in sqlserver
					continue;
				String query = "SELECT * FROM " + tableName;
				Statement statement = dbConnection.createStatement();
				ResultSet tableData = statement.executeQuery( query );
	
				int colCount = tableData.getMetaData().getColumnCount();
				int[] sqlTypes = new int[colCount];
				for ( int i = 0; i < colCount; i++ )
				{
					sqlTypes[i] = tableData.getMetaData().getColumnType( i + 1 );
				}
	
				List<Object[]> allRows = new ArrayList<Object[]>();
				while ( tableData.next() )
				{
					Object[] rowData = new Object[colCount];
					for ( int i = 0; i < colCount; i++ )
					{
						Object obj = tableData.getObject( i + 1 );
						//The below mentioned typecasting is done since oracel.sql.Timestamp is not seriazable and the driver is returning it.
						//Bug in the ojdbc_14g driver.
						if( obj instanceof oracle .sql.TIMESTAMP )
							obj = ((oracle.sql.TIMESTAMP) obj).timestampValue() ;
	
						rowData[i] = obj;
					}
					allRows.add( rowData );
				}
				
				allTables.add( new TableData( tableName, sqlTypes, allRows ) );
				statement.close();
			}
			
			return allTables;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	private ResultSet getAllTables() throws Exception
	{
		try {
			DatabaseMetaData databaseMetaData = dbConnection.getMetaData();
			String databaseProductName = dbConnection.getMetaData().getDatabaseProductName();
			String schemaPattern = databaseProductName.equals( DBType.ORACLE.productName ) ? dbConnection.getMetaData().getUserName() : null;
			return databaseMetaData.getTables( dbConnection.getCatalog(), schemaPattern, null, new String[] { "TABLE" } );
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	private ResultSet getModifiedTables() throws Exception {
		try {
			
			String query = "SELECT TABLE_NAME FROM USER_TAB_MODIFICATIONS";
			Statement statement = dbConnection.createStatement();
			ResultSet tableData = statement.executeQuery( query );
			
			return tableData;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void populateDBFromFile( String filePath ) throws Exception {
		try {
			String directory = automationPath + "\\src\\main\\resources\\DB_Backups\\";
			loadDBFromFile( directory + "\\" + filePath );
			populateDBFromMemory();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	public void populateDBFromMemory() throws Exception
	{
		try {
			for ( TableData tableData : database.allTables )
			{
				String insertQuery = tableData.insertQuery;
				if ( insertQuery == null )
					continue;
	
				PreparedStatement statement = dbConnection.prepareStatement( insertQuery );
				for ( Object[] rowData : tableData.allRows )
				{
					for ( int i = 0; i < rowData.length; i++ )
					{
						if ( rowData[i] == null )
							statement.setNull( i + 1, tableData.sqlTypes[i] );
						else
							statement.setObject( i + 1, rowData[i] );
					}
	
					statement.addBatch();
				}
				statement.executeBatch();
				
				statement.close();
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	public void enableConstraints( String filePath ) throws Exception {
		try {
			loadDBFromFile( filePath );
			Statement statement = dbConnection.createStatement();
			for ( String query : database.constraintsData.enableConstraintsQuerys )
			{
				statement.addBatch( query );
			}
			statement.executeBatch();
			statement.close();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	public void clearDB( String filePath ) throws Exception
	{
		try {
			String directory = automationPath + "\\src\\main\\resources\\DB_Backups\\";
			loadDBFromFile( directory + filePath );
			Statement statement = dbConnection.createStatement();
			
			for ( TableData tableData : database.allTables )
			{
				statement.addBatch( tableData.clearQuery );
			}
			
			statement.executeBatch();
			statement.close();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	public void disableConstraints( String filePath ) throws Exception
	{
		try {
			loadDBFromFile( filePath );
			Statement statement = dbConnection.createStatement();
			for ( String query : database.constraintsData.disableConstraintsQuerys )
			{
				statement.addBatch( query );
			}
			statement.executeBatch();
			statement.close();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	public void clearDBIndependently() throws Exception
	{
		try {
			String databaseProductName = dbConnection.getMetaData().getDatabaseProductName();
			ResultSet allTablesRS = getAllTables();
			Statement statement = dbConnection.createStatement();
			while ( allTablesRS.next() )
			{
				String tableName = allTablesRS.getString( 3 );
				if ( databaseProductName.equals( DBType.MSSQLSERVDER.productName ) && tableName.equals( "dtproperties" ) ) //to filter a system table in sqlserver
					continue;
				String query = "TRUNCATE TABLE " + tableName;
				statement.addBatch( query );
			}
			statement.executeBatch();
			statement.close();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	public void saveDBtoFile( String filePath, boolean shallCopyConstraints, String type ) throws Exception
	{
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		
		try {
			String fileName = getFileName(filePath);
			writeToFile(fileName, type);
			
			copyDBIntoMemory( shallCopyConstraints );
			String directory = automationPath + "\\src\\main\\resources\\DB_Backups\\";
			if (!filePath.endsWith(".data"))
				filePath = filePath + ".data";
			fos = new FileOutputStream( directory + filePath );
			oos = new ObjectOutputStream( fos );
			oos.writeObject( database );
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		finally {
			if (oos != null)
				oos.close();
			if (fos != null)
				fos.close();
		}
	}
	
	public static String getFileName(String fileName) throws Exception {
		try {
			
			String directory = automationPath + "\\src\\main\\resources\\DB_Backups\\";
			if (fileName.startsWith("/") || fileName.substring(1, 2).contains(":"))
				directory = "";
			
			if (fileName.endsWith(".sql"))
				fileName = directory + fileName;
			else
				fileName = directory + fileName + ".sql";
			
			return fileName;
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	private void loadDBFromFile( String filePath ) throws Exception {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		
		try {
			if ( database == null )
			{
				fis = new FileInputStream( filePath );
				ois = new ObjectInputStream( fis );
				database = ( DataBaseData ) ois.readObject();
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		finally {
			if (ois != null)
				ois.close();
			if (fis != null)
				fis.close();
		}
	}

	public void closeConnection() throws Exception
	{
		try
		{
			if (dbConnection != null)
				dbConnection.close();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	private Connection getConnection(boolean throwError) throws Exception
	{
		try
		{
			Connection con = null;
			Class.forName( dbType.driverClass );
			con = DriverManager.getConnection( completeURL, user, password );
			
			return con;
		} catch (Exception e) {
			if (throwError) {
				FailureHelper.setErrorMessage(e);
				throw e;
			}
			else {
				if (errorMsg != null)
					errorMsg = null;
				return null;
			}
		}
	}
	
	public static DBHelper connectToDB(String dbType, String dbHost, String dbUsername, String dbPassword, String dbDatabase, String dbInstance, boolean throwError) throws Exception {
		try {
			DBHelper dbHelper = null;
			if ( dbType.equalsIgnoreCase( "sqlserver" ) )
				dbHelper = createMSSQLDBHelper( dbHost, dbUsername, dbPassword, dbDatabase, dbInstance, throwError );
			else if ( dbType.equalsIgnoreCase( "oracle" ) )
				dbHelper = createOracleDBHelper( dbHost, dbUsername, dbPassword, dbInstance, throwError );
			else if ( dbType.equalsIgnoreCase( "PostgreSQL" ) )
				dbHelper = createPostgreSQLDBHelper( dbHost, dbUsername, dbPassword, dbDatabase, throwError );
			
			return dbHelper;
		} catch (Exception e) {
			if (throwError) {
				FailureHelper.setErrorMessage(e);
				throw e;
			}
			else {
				if (errorMsg != null)
					errorMsg = null;
				return null;
			}
		}
	}
	
	public static DBHelper connectToDB(String dbType, String dbHost, String dbUsername, String dbPassword, String dbDatabase, String dbInstance) throws Exception {
		try {
			DBHelper dbHelper = connectToDB(dbType, dbHost, dbUsername, dbPassword, dbDatabase, dbInstance, true);
			
			return dbHelper;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static DBHelper connectToReferenceDB(boolean throwError) throws Exception {
		try {
			String[] dbDetails = getReferenceDatabase();
			
			if (ValidationHelper.isNotEmpty(dbDetails)) {
				DBHelper dbHelper = connectToDB(dbDetails[0], dbDetails[1], dbDetails[2], dbDetails[3], dbDetails[4], dbDetails[5], throwError);
				return dbHelper;}
			else
				return null;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public String[][] createTable(String fileName) throws Exception {
		FileInputStream fstream = null;
	   	DataInputStream datastream = null;
		BufferedReader breader = null;
		
		try {
			String groupFields[][] = new String[255][255];
			fstream = new FileInputStream(fileName);
		   	datastream = new DataInputStream(fstream);
		   	breader = new BufferedReader(new InputStreamReader(datastream));
		   	
		   	String strLine;
		   	int count = 0;
		   	while ((strLine = breader.readLine()) != null) {
		   		groupFields[count] = strLine.split(",");
		   		count++;
		   	}
		   	
		   	return groupFields;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		finally {
			if (breader != null)
				breader.close();
			if (datastream != null)
				datastream.close();
			if (fstream != null)
				fstream.close();
		}
	}
	
	public int findTemplateId(int id) throws Exception {
		try {
			query = "select cte_id from case_template";
			int flag = 0;
			
			rs = smt.executeQuery(query);
			while(rs.next()) {
				if(rs.getInt("cte_id") == id) {
					flag = 1;
					break;
				}
			}
			return flag;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void getNextobjextId() throws Exception {
		try {
			query = "select noi_current_no from next_object_id where noi_object_name = 'CaseGenerationRequest'";
			rs = null;
			rs = smt.executeQuery(query);
			rs.next();
			tableCount = rs.getInt("noi_current_no");
			tableCount++;

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void getLocalColumn(int id) throws Exception {
		try {
			query = "select csp_column_name, csp_type from case_template_property where cte_id = "+id;
			rs = null;
			int count = 0;
			
			rs = smt.executeQuery(query);
			while(rs.next())
				count++;
			localProperty = new String[count][2];
			
			for(i = 0; i < count; i++){
					rs.absolute(i+1);
					localProperty[i][0] = rs.getString("csp_column_name");
					localProperty[i][1] = rs.getString("csp_type");
			}
			
			for(i = 0; i<count;i++) {
					if(localProperty[i][1].equals("Integer"))
						localProperty[i][1] = "int";
					else if(localProperty[i][1].equals("String"))
						localProperty[i][1] = "varchar(255)";
					else if(localProperty[i][1].equals("Decimal"))
						localProperty[i][1] = "bigint";
					else if(localProperty[i][1].equals("Datetime"))
						localProperty[i][1] = "datetime";
					else if(localProperty[i][1].equals("Boolean"))
						localProperty[i][1] = "char(1)";
					else if(localProperty[i][1].equals("Long"))
						localProperty[i][1] = "int";
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void getGlobalColumn(int id) throws Exception {
		
		try {
			query = "select cgp.scgp_column_name,com.cmp_name from case_global_property cgp, case_global_property_map cgpm,property_dfn pd,component com where cgp.scgp_id=cgpm.scgp_id and cgpm.cte_id = "+id+" and cgp.prd_id = pd.prd_id and pd.cmp_id = com.cmp_id";
			rs = null;
			int count = 0;
		
			rs = smt.executeQuery(query);
			while(rs.next())
				count++;
			globalProperty = new String[count][2];
			globalProperty = new String[count][2];
			
			for(i = 0; i < count; i++){
				rs.absolute(i+1);
				globalProperty[i][0] = rs.getString("scgp_column_name");
				globalProperty[i][1] = rs.getString("cmp_name");
			}
			
				
			for(i = 0; i<count;i++)
				globalProperty[i][1] = dataTypes(globalProperty[i][1]);
				
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createTableQuery() throws Exception {
		
		try {
			String createTable = "create table case_input_"+tableCount+"(civ_id int not null,cgr_id int not null,cost bigint not null,severity int not null,cse_id int,";
			
			if( localProperty.length > 0 ) {
				
				for(i = 0; i < localProperty.length - 1; i++)
					createTable = createTable + localProperty[i][0] + " " + localProperty[i][1] + ",";
				createTable =  createTable + localProperty[i][0] + " " + localProperty[i][1] + ",";
			}
			if( globalProperty.length > 0 ) {
	
				for(i = 0; i < globalProperty.length - 1; i++)
					createTable = createTable + globalProperty[i][0] + " " + globalProperty[i][1] + ",";
				createTable =  createTable + globalProperty[i][0] + " " + globalProperty[i][1] + ",";
			}
			createTable = createTable + "expiry_dttm datetime,status varchar(1),cse_source_info char(1),extra1 varchar(30),extra2 varchar(30),extra3 varchar(30),constraint pk_case_input_"+tableCount+" unique (civ_id))";
			
			smt.executeUpdate(createTable);
			query = "update next_object_id set noi_current_no = " + tableCount +" where noi_object_name = 'CaseGenerationRequest'"; 
			smt.executeUpdate(query);
		
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public String dataTypes(String field) throws Exception {
		try {
			if(field.equals("Integer Property"))
				field = "int";
			else if(field.equals("String Property"))
				field = "varchar(255)";
			else if(field.equals("Decimal Property"))
				field = "bigint";
			else if(field.equals("Date Property"))
				field = "datetime";
			else if(field.equals("Flag Property"))
				field = "char(1)";
			else if(field.equals("Long Property"))
				field = "int";
			else if(field.equals("Hard Lookup Property"))
				field = "varchar(255)";
			else if(field.equals("SQL Lookup Property"))
				field = "varchar(255)";
			return field;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	void writeToFile( String fileName, String type ) throws Exception
	{
		try {
			File file = new File(fileName);
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			
			String databaseProductName = dbConnection.getMetaData().getDatabaseProductName();
			boolean isDBMSSQLServer = databaseProductName.equals( DBType.MSSQLSERVDER.productName );
			
			ArrayList<String> allInserts = new ArrayList<>();
			if (type.equalsIgnoreCase("AllTables")) {
				ResultSet allTablesRS = getAllTables();
				allInserts = getData(dbConnection, allTablesRS, 3, isDBMSSQLServer);
			}
			else if (type.equalsIgnoreCase("ModifiedTables")) {
				ResultSet modifiedTablesRS = getModifiedTables();
				allInserts = getData(dbConnection, modifiedTablesRS, 0, isDBMSSQLServer);
			}
//			else
//				allInserts = getData(dbConnection, tableNames, isDBMSSQLServer);
			
			for (int i = 0; i < allInserts.size(); i++) {
				bw.write(allInserts.get(i));
				
				if ((i%10) == 0)
					bw.flush();
			}
			
			bw.close();
			fw.close();
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	private ArrayList<String> getData(Connection dbConnection, ResultSet allTablesRS, int columnIndex, boolean isDBMSSQLServer) throws Exception {
		try {
			ArrayList<String> allRows = new ArrayList<>();
			
			while ( allTablesRS.next() )
			{
				String tableName = allTablesRS.getString( columnIndex );
				if ( isDBMSSQLServer && tableName.equals( "dtproperties" ) )
					continue;
				String query = "SELECT * FROM " + tableName;
				Statement statement = dbConnection.createStatement();
				ResultSet tableData = statement.executeQuery( query );
				
				int colCount = tableData.getMetaData().getColumnCount();
				int[] sqlTypes = new int[colCount];
				String[] sqlName = new String[colCount];
				for ( int i = 0; i < colCount; i++ )
				{
					sqlTypes[i] = tableData.getMetaData().getColumnType( i + 1 );
					sqlName[i] = tableData.getMetaData().getColumnName( i + 1 );
				}

				String cols = "";
				for (int i = 0; i < sqlName.length; i++) {
					if (i ==0)
						cols = sqlName[i];
					else
						cols = cols + "," + sqlName[i];
				}
				
				String dummy = "INSERT INTO " + tableName + "(" + cols + ")" + " VALUES (";
				String insertData = "";
				while ( tableData.next() )
				{
					for ( int i = 0; i < colCount; i++ )
					{
						Object obj = tableData.getObject( i + 1 );
						if( obj instanceof oracle .sql.TIMESTAMP )
							obj = ((oracle.sql.TIMESTAMP) obj).timestampValue() ;

						if (obj == null)
							obj = "";
						if (i == 0)
							insertData = dummy + "\"" + obj.toString() + "\"";
						else
							insertData = insertData + ",\"" + obj.toString() + "\"";
					}
					
					insertData = insertData + ");" + System.getProperty("line.separator");
					allRows.add(insertData);
				}
				
				statement.close();
			}
			
			return allRows;
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public ArrayList<String> getData(Connection dbConnection, String[] tableNames, boolean isDBMSSQLServer) throws Exception {
		try {
			ArrayList<String> allRows = new ArrayList<>();
			
			for (int x = 0; x < tableNames.length; x++)
			{
				if ( isDBMSSQLServer && tableNames[x].equals( "dtproperties" ) )
					continue;
				String query = "SELECT * FROM " + tableNames[x];
				Statement statement = dbConnection.createStatement();
				ResultSet tableData = statement.executeQuery( query );
				
				int colCount = tableData.getMetaData().getColumnCount();
				int[] sqlTypes = new int[colCount];
				String[] sqlName = new String[colCount];
				for ( int i = 0; i < colCount; i++ )
				{
					sqlTypes[i] = tableData.getMetaData().getColumnType( i + 1 );
					sqlName[i] = tableData.getMetaData().getColumnName( i + 1 );
				}

				String cols = "";
				for (int i = 0; i < sqlName.length; i++) {
					if (i ==0)
						cols = sqlName[i];
					else
						cols = cols + "," + sqlName[i];
				}
				
				String dummy = "INSERT INTO " + tableNames[x] + "(" + cols + ")" + " VALUES (";
				String insertData = "";
				while ( tableData.next() )
				{
					for ( int i = 0; i < colCount; i++ )
					{
						Object obj = tableData.getObject( i + 1 );
						if( obj instanceof oracle .sql.TIMESTAMP )
							obj = ((oracle.sql.TIMESTAMP) obj).timestampValue() ;

						if (obj == null)
							obj = "";
						if (i == 0)
							insertData = dummy + "\"" + obj.toString() + "\"";
						else
							insertData = insertData + ",\"" + obj.toString() + "\"";
					}
					
					insertData = insertData + ");" + System.getProperty("line.separator");
					allRows.add(insertData);
				}
				
				statement.close();
			}
			
			return allRows;
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void importOracleDump(String fileName, String dbUsername, String dbPassword, String dbHost) throws Exception {
		BufferedReader in = null;
		try {
			String fileNameWithoutExt = fileName.replace(".dmp", "");
			String str = "imp " + dbUsername + "/" + dbPassword + "@" + dbHost +
					" file=" + fileName + " log=" + fileNameWithoutExt + ".log full=N ignore=Y grants=Y indexes=Y;";
			Process process = Runtime.getRuntime().exec(str);
			in = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			
			if (in != null) {
				String line = null;
				String[] error = new String[1000];
				int count = 0;
				
				while ((line = in.readLine()) != null) {
					error[count] = line;
					count++;
				}
				
				if (ValidationHelper.isNotEmpty(error)) {
					FailureHelper.setErrorMessage(error);
					FailureHelper.failTest("DB import using 'imp' command did not succeed.");
				}
			}
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		finally {
			if (in != null)
				in.close();
		}
	}

	public static void exportOracleDB(String fileName, String dbUsername, String dbPassword, String dbHost) throws Exception {
		BufferedReader in = null;
		try {
			if (!fileName.endsWith(".dmp"))
				fileName = fileName + ".dmp";
			
			String str = "exp " + dbUsername + "/" + dbPassword + "@" + dbHost + " file=" + fileName + " owner=" + dbUsername;
			Process process = Runtime.getRuntime().exec(str);
			in = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			
			if (in != null) {
				String line = null;
				String[] error = new String[1000];
				int count = 0;
				
				while ((line = in.readLine()) != null) {
					error[count] = line;
					count++;
				}
				
				if (ValidationHelper.isNotEmpty(error)) {
					FailureHelper.setErrorMessage(error);
					FailureHelper.failTest("DB export using 'exp' command did not succeed.");
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		finally {
			if (in != null)
				in.close();
		}
	}
	
	public static String[] getReferenceDatabase() throws Exception {
		try {
			if (!ValidationHelper.isReferenceDBDetailsEmpty()) {
				String[] dbDetails = new String[9];
				dbDetails[0] = configProp.getDbType().split(",")[0];
				dbDetails[1] = configProp.getDbMachineName().split(",")[0];
				dbDetails[2] = configProp.getDbUserName().split(",")[0];
				dbDetails[3] = configProp.getDbPassword().split(",")[0];
				dbDetails[4] = configProp.getDB().split(",")[0];
				dbDetails[5] = configProp.getDbInstance().split(",")[0];
				dbDetails[6] = configProp.getDBPortNumber().split(",")[0];
				dbDetails[7] = configProp.getDbUnicode().split(",")[0];
				dbDetails[8] = configProp.getDbEnableHA().split(",")[0];
				
				return dbDetails;
			}
			else
				return null;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String[][] getReferenceDatabases() throws Exception {
		try {
			if (!ValidationHelper.isReferenceDBDetailsEmpty()) {
				String[] dbType = configProp.getDbType().split(",");
				String[] dbHost = configProp.getDbMachineName().split(",");
				String[] dbUsername = configProp.getDbUserName().split(",");
				String[] dbPassword = configProp.getDbPassword().split(",");
				String[] dbDatabase = configProp.getDB().split(",");
				String[] dbInstance = configProp.getDbInstance().split(",");
				String[] dbPort = configProp.getDBPortNumber().split(",");
				String[] dbUnicode = configProp.getDbUnicode().split(",");
				String[] dbEnableHA = configProp.getDbEnableHA().split(",");
				
				int length = dbUsername.length;
				String[][] dbDetails = new String[length][9];
				int count = 0;
				
				for (int i = 0; i < length; i++) {
					if (dbType != null && dbType.length >= i+1 && dbType[i] != null)
						dbDetails[count][0] = dbType[i];
					else
						dbDetails[count][0] = dbType[0];
					
					if (dbHost != null && dbHost.length >= i+1 && dbHost[i] != null)
						dbDetails[count][1] = dbHost[i];
					else
						dbDetails[count][1] = dbHost[0];
					
					if (dbUsername != null && dbUsername.length >= i+1 && dbUsername[i] != null)
						dbDetails[count][2] = dbUsername[i];
					else
						dbDetails[count][2] = dbUsername[0];
					
					if (dbPassword != null && dbPassword.length >= i+1 && dbPassword[i] != null)
						dbDetails[count][3] = dbPassword[i];
					else
						dbDetails[count][3] = dbPassword[0];
					
					if (dbDatabase != null && dbDatabase.length >= i+1 && dbDatabase[i] != null)
						dbDetails[count][4] = dbDatabase[i];
					else
						dbDetails[count][4] = dbDatabase[0];
					
					if (dbInstance != null && dbInstance.length >= i+1 && dbInstance[i] != null)
						dbDetails[count][5] = dbInstance[i];
					else
						dbDetails[count][5] = dbInstance[0];
					
					if (dbPort != null && dbPort.length >= i+1 && dbPort[i] != null)
						dbDetails[count][6] = dbPort[i];
					else
						dbDetails[count][6] = dbPort[0];
					
					if (dbUnicode != null && dbUnicode.length >= i+1 && dbUnicode[i] != null)
						dbDetails[count][7] = dbUnicode[i];
					else
						dbDetails[count][7] = dbUnicode[0];
					
					if (dbEnableHA != null && dbEnableHA.length >= i+1 && dbEnableHA[i] != null)
						dbDetails[count][8] = dbEnableHA[i];
					else
						dbDetails[count][8] = dbEnableHA[0];
					count++;
				}
				
				return dbDetails;
			}
			else
				return null;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}