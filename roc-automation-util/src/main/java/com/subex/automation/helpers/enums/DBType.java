package com.subex.automation.helpers.enums;

public enum DBType
{
	ORACLE( "Oracle", "jdbc:oracle:thin:@", "oracle.jdbc.driver.OracleDriver", 1521 ),
	MSSQLSERVDER( "Microsoft SQL Server", "jdbc:jtds:sqlserver://", "net.sourceforge.jtds.jdbc.Driver", 1433 ),
	POSTGRESQL( "PostgreSQL", "jdbc:postgresql://", "org.postgresql.Driver", 5432 );

	public final String productName;
	public final String url;
	public final String driverClass;
	public final int port;

	private DBType( String productName, String url, String driverClass, int port )
	{
		this.productName = productName;
		this.driverClass = driverClass;
		this.url = url;
		this.port = port;
	}
}
