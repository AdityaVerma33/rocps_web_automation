package com.subex.automation.helpers.db;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

class DataBaseData implements Serializable
{
	private static final long serialVersionUID = -1719659397146445456L;
	List<TableData> allTables;
	ConstraintsData constraintsData;

	DataBaseData( List<TableData> allTables )
	{
		this( allTables, null );
	}

	DataBaseData( List<TableData> allTables, ConstraintsData constraintsData )
	{
		this.allTables = allTables;
		this.constraintsData = constraintsData;
	}

}

class ConstraintsData implements Serializable
{
	private static final long serialVersionUID = 9140690285405635637L;
	private transient Connection dbConnection;
	List<String> enableConstraintsQuerys = new ArrayList<String>();
	List<String> disableConstraintsQuerys = new ArrayList<String>();

	ConstraintsData( Connection dbConnection ) throws SQLException
	{
		this.dbConnection = dbConnection;
		this.enableConstraintsQuerys = getConstraintsQuery( new String[]
		{ "PK_%", "UK_%", "FK_%" }, true );
		this.disableConstraintsQuerys = getConstraintsQuery( new String[]
		{ "FK_%", "UK_%", "PK_%" }, false );
	}

	private List<String> getConstraintsQuery( String[] filterConditions, boolean enableConstraints ) throws SQLException
	{
		List<String> toReturn = new ArrayList<String>();
		for ( String filterCon : filterConditions )
		{
			Statement statement = dbConnection.createStatement();
			String constraintsQuery = "select TABLE_NAME, CONSTRAINT_NAME from user_constraints where CONSTRAINT_NAME like '" + filterCon + "'";

			ResultSet constraints = statement.executeQuery( constraintsQuery );

			while ( constraints.next() )
			{
				String query = "ALTER TABLE " + constraints.getString( 1 );
				if ( enableConstraints )
				{
					query += " ENABLE CONSTRAINT " + constraints.getString( 2 );
				}
				else
				{
					query += " DISABLE CONSTRAINT " + constraints.getString( 2 ) + " CASCADE";
				}
				toReturn.add( query );
			}

			statement.close();
			statement = null;
		}
		return toReturn;
	}

}

class TableData implements Serializable
{
	private static final long serialVersionUID = -1743139908333531202L;
	String insertQuery;
	String clearQuery;
	int[] sqlTypes;
	List<Object[]> allRows;

	TableData( String tableName, int[] sqlTypes, List<Object[]> allRows )
	{
		this.sqlTypes = sqlTypes;
		this.insertQuery = getInsertQuery( tableName, sqlTypes.length );
		this.clearQuery = getClearQuery( tableName, sqlTypes.length );
		this.allRows = allRows;
	}

	private static String getInsertQuery( String tableName, int colCount )
	{
		if ( colCount == 0 )
			return null;
		StringBuffer query = new StringBuffer( "INSERT INTO " ).append( tableName ).append( " VALUES ( " );
		for ( int i = 0; i < colCount; i++ )
			query.append( "?," );

		query.setLength( query.length() - 1 ); //remove extra comma
		query.append( " )" );
		return query.toString();
	}

	private static String getClearQuery( String tableName, int colCount )
	{
		if ( colCount == 0 )
			return null;

		StringBuffer query = new StringBuffer( "TRUNCATE TABLE " ).append( tableName );

		return query.toString();
	}
}
