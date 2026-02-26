package com.subex.rocps.automation.helpers.dbscript;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.db.DBHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class DBScriptImpl extends PSAcceptanceTest
{

	/*
	 * This method is to run xdr Extract Template Script in backend
	 */

	public void xdrExtTempScript( String fromDt, String toDt, String xdrExtTempNm ) throws Exception
	{
		DBHelper dbHelper = null;
		int totalrows = 0;
		try
		{
			dbHelper = DBHelper.connectToReferenceDB( false );
			String updateQuery1 = "update xdr_extract_config set pxec_from_dttm = ? where pxec_name= ?";
			String updateQuery2 = "update xdr_extract_config set pxec_TO_dttm=?, pxec_from_dttm = ? where pxec_name=?";

			Date dateObj = new Date( 0 );

			if ( dbHelper != null )
			{
				if ( ValidationHelper.isNotEmpty( toDt ) )
				{
					PreparedStatement stmt = dbHelper.dbConnection.prepareStatement( updateQuery2 );
					stmt.setDate( 1, dateObj.valueOf( toDt ) );
					stmt.setDate( 2, dateObj.valueOf( fromDt ) );
					stmt.setString( 3, xdrExtTempNm );

					totalrows = stmt.executeUpdate();
				}
				else
				{
					PreparedStatement stmt = dbHelper.dbConnection.prepareStatement( updateQuery1 );
					stmt.setDate( 1, dateObj.valueOf( fromDt ) );
					stmt.setString( 2, xdrExtTempNm );
					totalrows = stmt.executeUpdate();
				}
				Log4jHelper.logInfo( " total rows updated in the xdr_extract_config table : " + totalrows );
			}

			assertTrue( totalrows > 0, "pxec_from_dttm and pxec_TO_dttm is not set in XDR Extract Template with template name " + xdrExtTempNm );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
		finally
		{
			if ( dbHelper != null )
				dbHelper.closeConnection();
		}
	}

	/*
	 * This method is to get paor_id of aggregation processor from backend
	 */
	private int getPaorIdAggProcesor( String paor_name ) throws Exception
	{

		DBHelper dbHelper = null;
		int totalrows = 0;
		int paor_id = 0;
		try
		{

			dbHelper = DBHelper.connectToReferenceDB( false );
			if ( dbHelper != null )
			{

				String aggPAOR_IDQuery = "select paor_id from aggregation_processor where paor_name = ?";
				PreparedStatement aggPAOR_IDQuerystmt = dbHelper.dbConnection.prepareStatement( aggPAOR_IDQuery );
				aggPAOR_IDQuerystmt.setString( 1, paor_name );
				totalrows = aggPAOR_IDQuerystmt.executeUpdate();
				ResultSet rsPaor_id = aggPAOR_IDQuerystmt.executeQuery( aggPAOR_IDQuery );
				while ( rsPaor_id.next() )
				{
					paor_id = rsPaor_id.getInt( "paor_id" );
				}

				// Log4jHelper.logInfo(" total rows found in the 'aggregation_processor' table :
				// " + totalrows);
			}
			// assertTrue(totalrows > 0, "paor_id is not found in aggregation_processor with
			// paor_name " + paor_name);
			return paor_id;

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
		finally
		{
			if ( dbHelper != null )
				dbHelper.closeConnection();
		}
	}

	/*
	 * This method is to get max PXAK_ID of xdr_agg_keys from backend
	 */
	private int getMaxPxakIdOfXdrAggKeys() throws Exception
	{

		DBHelper dbHelper = null;
		int totalrows = 0;
		int maxPxak_id = 0;
		try
		{

			dbHelper = DBHelper.connectToReferenceDB( false );
			if ( dbHelper != null )
			{

				String maxPxakIdQuery = "select max(PXAK_ID) from xdr_agg_keys";
				PreparedStatement maxPxakIdQueryStmt = dbHelper.dbConnection.prepareStatement( maxPxakIdQuery );
				totalrows = maxPxakIdQueryStmt.executeUpdate();
				ResultSet rs_maxPxak_id = maxPxakIdQueryStmt.executeQuery( maxPxakIdQuery );
				while ( rs_maxPxak_id.next() )
				{
					maxPxak_id = rs_maxPxak_id.getInt( "max(PXAK_ID)" );
				}
			}
			return maxPxak_id;

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
		finally
		{
			if ( dbHelper != null )
				dbHelper.closeConnection();
		}
	}

	/*
	 * This method is to get max pxfs_id of Xdr_Agg_File_Sequence from backend
	 */
	private int getMaxPxfsIdOfXdrAggFlSeq() throws Exception
	{

		DBHelper dbHelper = null;
		int totalrows = 0;
		int maxPxfs_id = 0;
		try
		{

			dbHelper = DBHelper.connectToReferenceDB( false );
			if ( dbHelper != null )
			{

				String maxPxfsIdQuery = "select max(pxfs_id) from Xdr_Agg_File_Sequence";
				PreparedStatement maxPxfsIdQueryStmt = dbHelper.dbConnection.prepareStatement( maxPxfsIdQuery );
				totalrows = maxPxfsIdQueryStmt.executeUpdate();
				ResultSet rs_maxPxfs_id = maxPxfsIdQueryStmt.executeQuery( maxPxfsIdQuery );
				while ( rs_maxPxfs_id.next() )
				{
					maxPxfs_id = rs_maxPxfs_id.getInt( "MAX(PXFS_ID)" );
				}
			}
			return maxPxfs_id;

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
		finally
		{
			if ( dbHelper != null )
				dbHelper.closeConnection();
		}
	}

	/*
	 * This method is to get max PXAK_ORDER_NO of xdr_agg_keys from backend
	 */
	private int getMaxPxakOrderNoOfXdrAggKeys( String paor_name ) throws Exception
	{

		DBHelper dbHelper = null;
		int totalrows = 0;
		int paor_id = getPaorIdAggProcesor( paor_name );
		int maxPxak_orderNo = 0;
		try
		{

			dbHelper = DBHelper.connectToReferenceDB( false );
			if ( dbHelper != null )
			{

				String maxPxakOrderNoQuery = "select max(PXAK_ORDER_NO) from xdr_agg_keys where pxec_id=?";
				PreparedStatement maxPxakOrderNoQueryStmt = dbHelper.dbConnection.prepareStatement( maxPxakOrderNoQuery );
				maxPxakOrderNoQueryStmt.setInt( 1, paor_id );
				totalrows = maxPxakOrderNoQueryStmt.executeUpdate();
				ResultSet rs_maxPxakOrderNo = maxPxakOrderNoQueryStmt.executeQuery( maxPxakOrderNoQuery );
				while ( rs_maxPxakOrderNo.next() )
				{
					maxPxak_orderNo = rs_maxPxakOrderNo.getInt( "max(PXAK_ORDER_NO)" );
				}
			}
			return maxPxak_orderNo;

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
		finally
		{
			if ( dbHelper != null )
				dbHelper.closeConnection();
		}
	}

	/*
	 * This method is to check PAOR_ID of xdr_agg_keys is present or not from
	 * backend
	 */
	public boolean isPaorIdInXdrAggKeysPresent( String paor_name ) throws Exception
	{

		DBHelper dbHelper = null;
		int totalrows = 0;
		int paor_id = getPaorIdAggProcesor( paor_name );
		try
		{

			dbHelper = DBHelper.connectToReferenceDB( false );
			if ( dbHelper != null )
			{

				String paorIdQuery = "select * from xdr_agg_keys where pxec_id=?";
				PreparedStatement paorIdQueryStmt = dbHelper.dbConnection.prepareStatement( paorIdQuery );
				paorIdQueryStmt.setInt( 1, paor_id );
				totalrows = paorIdQueryStmt.executeUpdate();
				if ( totalrows > 0 )
					return true;
			}
			return false;

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
		finally
		{
			if ( dbHelper != null )
				dbHelper.closeConnection();
		}
	}

	/*
	 * This method is to check PAOR_ID of Xdr_Agg_File_Sequence is present or not
	 * from backend
	 */
	public boolean isPaorIdInXdrAggFlSeqPresent( String paor_name ) throws Exception
	{

		DBHelper dbHelper = null;
		int totalrows = 0;
		int paor_id = getPaorIdAggProcesor( paor_name );
		try
		{

			dbHelper = DBHelper.connectToReferenceDB( false );
			if ( dbHelper != null )
			{

				String paorIdQuery = "select * from Xdr_Agg_File_Sequence where paor_id=?";
				PreparedStatement paorIdQueryStmt = dbHelper.dbConnection.prepareStatement( paorIdQuery );
				paorIdQueryStmt.setInt( 1, paor_id );
				totalrows = paorIdQueryStmt.executeUpdate();
				if ( totalrows > 0 )
					return true;
			}
			return false;

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
		finally
		{
			if ( dbHelper != null )
				dbHelper.closeConnection();
		}
	}

	/*
	 * This method is to check PAOR_ID and tcl_id combination of xdr_agg_keys is
	 * present or not from backend
	 */
	public boolean isPaorIdWithTclIdPresent( String paor_name, String tbd_name, String tcl_name ) throws Exception
	{

		DBHelper dbHelper = null;
		int totalrows = 0;
		int paor_id = getPaorIdAggProcesor( paor_name );
		int tcl_id = getTclId( tbd_name, tcl_name );
		try
		{

			dbHelper = DBHelper.connectToReferenceDB( false );
			if ( dbHelper != null )
			{

				String paorIdTclIdQuery = "select * from xdr_agg_keys where pxec_id=? and tcl_id=?";
				PreparedStatement paorIdTclIdQueryStmt = dbHelper.dbConnection.prepareStatement( paorIdTclIdQuery );
				paorIdTclIdQueryStmt.setInt( 1, paor_id );
				paorIdTclIdQueryStmt.setInt( 2, tcl_id );
				totalrows = paorIdTclIdQueryStmt.executeUpdate();
				if ( totalrows > 0 )
				{

					return true;
				}
			}
			return false;

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
		finally
		{
			if ( dbHelper != null )
				dbHelper.closeConnection();
		}
	}

	/*
	 * This method is to get tcl_id of event date for rated event table from backend
	 */

	private int getTclId( String tbd_name, String tcl_name ) throws Exception
	{

		DBHelper dbHelper = null;
		int totalrows = 0;
		int tcl_id = 0;
		try
		{

			dbHelper = DBHelper.connectToReferenceDB( false );
			if ( dbHelper != null )
			{

				String evtDTTcl_IdQuery = "(select tcl_id from table_column where tbd_id=(select tbd_id from table_dfn  where tbd_name= ?) and tcl_name= ?)";
				PreparedStatement evtDTTcl_IdQuery_stmt = dbHelper.dbConnection.prepareStatement( evtDTTcl_IdQuery );
				evtDTTcl_IdQuery_stmt.setString( 1, tbd_name );
				evtDTTcl_IdQuery_stmt.setString( 2, tcl_name );
				totalrows = evtDTTcl_IdQuery_stmt.executeUpdate();

				ResultSet rs_Tcl_id = evtDTTcl_IdQuery_stmt.executeQuery( evtDTTcl_IdQuery );
				while ( rs_Tcl_id.next() )
				{
					tcl_id = rs_Tcl_id.getInt( "tcl_id" );
				}
			}
			return tcl_id;

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
		finally
		{
			if ( dbHelper != null )
				dbHelper.closeConnection();
		}
	}

	/*
	 * This method is to insert into xdr_agg_keys table from backend
	 */
	public void insertIntoAggKeys( String paor_name, String tbd_name, String tcl_name, String system_generated_fl, String pxak_dlt_fl, String pxak_version_id, String ptn_id ) throws Exception
	{
		int pxak_id = getMaxPxakIdOfXdrAggKeys();
		if ( pxak_id == 0 )
		{
			pxak_id = 1;
		}
		else
		{
			pxak_id++;
		}
		if ( isPaorIdInXdrAggKeysPresent( paor_name ) )
		{
			if ( !isPaorIdWithTclIdPresent( paor_name, tbd_name, tcl_name ) )
			{
				int pxak_order_no = getMaxPxakOrderNoOfXdrAggKeys( paor_name );
				pxak_order_no++;
				insertAggKeysScript( pxak_id, paor_name, tbd_name, tcl_name, pxak_order_no, system_generated_fl, pxak_dlt_fl, pxak_version_id, ptn_id );
			}
			else
			{
				Log4jHelper.logInfo( " Aggregation processor '" + paor_name + "' with tbd_name '" + tbd_name + "' and  tcl_name'" + tcl_name + "' already prsent  in the 'xdr_agg_keys' table. " );
			}
		}
		else
		{
			insertAggKeysScript( pxak_id, paor_name, tbd_name, tcl_name, 1, system_generated_fl, pxak_dlt_fl, pxak_version_id, ptn_id );
		}
	}

	/*
	 * This method is to insert into xdr_agg_keys table from backend
	 */
	private void insertAggKeysScript( int pxak_id, String paor_name, String tbd_name, String tcl_name, int pxak_order_no, String system_generated_fl, String pxak_dlt_fl, String pxak_version_id, String ptn_id ) throws Exception
	{
		DBHelper dbHelper = null;
		int totalrows = 0;
		try
		{

			dbHelper = DBHelper.connectToReferenceDB( false );

			if ( dbHelper != null )
			{

				int paor_id = getPaorIdAggProcesor( paor_name );
				int tcl_id = getTclId( tbd_name, tcl_name );
				String insertQuery = "insert into xdr_agg_keys ( PXAK_ID,pxec_id,TCL_ID,PXAK_ORDER_NO,SYSTEM_GENERATED_FL,PXAK_DELETE_FL,PXAK_VERSION_ID,PTN_ID)" + "values(?, ?,?,?,?,?,?,?) ";
				PreparedStatement stmt = dbHelper.dbConnection.prepareStatement( insertQuery );
				stmt.setInt( 1, pxak_id );
				stmt.setInt( 2, paor_id );
				stmt.setInt( 3, tcl_id );
				stmt.setInt( 4, pxak_order_no );
				stmt.setString( 5, system_generated_fl );
				stmt.setString( 6, pxak_dlt_fl );
				stmt.setInt( 7, Integer.valueOf( pxak_version_id ) );
				stmt.setInt( 8, Integer.valueOf( ptn_id ) );
				totalrows = stmt.executeUpdate();
				Log4jHelper.logInfo( " total rows inserted in the 'xdr_agg_keys' table : " + totalrows );
				Log4jHelper.logInfo( "'xdr_agg_keys' is inserted  successfully from backend for this Aggregation processor:  '" + paor_name + " with tbd_name " + tbd_name + " and tcl_name " + tcl_name );

			}

			assertTrue( totalrows > 0, "Values are not inserted in 'xdr_agg_keys' table" );
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
		finally
		{
			if ( dbHelper != null )
				dbHelper.closeConnection();
		}
	}

	/*
	 * This method is to insert into Xdr_Agg_File_Sequence table from backend
	 */
	public void insertIntoAggFileSequence( String paor_name, String pxfs_seq_no, String pxfs_min_seq_no, String pxfs_max_seq_no, String pxfs_seq_no_pattern, String pxfs_incr_by, String pxfs_ref_dt, String pxfs_system_gen_fl, String pxfs_dlt_fl, String pxfs_version_id, String pxfs_ptn_id ) throws Exception
	{
		int pxfs_id = getMaxPxfsIdOfXdrAggFlSeq();
		if ( pxfs_id == 0 )
		{
			pxfs_id = 1;
		}
		else
		{
			pxfs_id++;
		}
		if ( !isPaorIdInXdrAggFlSeqPresent( paor_name ) )
		{
			insertXdrAggFileSeqScript( pxfs_id, paor_name, pxfs_seq_no, pxfs_min_seq_no, pxfs_max_seq_no, pxfs_seq_no_pattern, pxfs_incr_by, pxfs_ref_dt, pxfs_system_gen_fl, pxfs_dlt_fl, pxfs_version_id, pxfs_ptn_id );
		}
		else
		{
			Log4jHelper.logInfo( " Aggregation processor '" + paor_name + "' already prsent  in the 'Xdr_Agg_File_Sequence' table. " );
		}
	}

	/*
	 * This method is to insert into Xdr_Agg_File_Sequence table from backend
	 */
	private void insertXdrAggFileSeqScript( int pxfs_id, String paor_nm, String pxfs_seq_no, String pxfs_min_seq_no, String pxfs_max_seq_no, String pxfs_seq_no_pattern, String pxfs_incr_by, String pxfs_ref_dt, String pxfs_system_gen_fl, String pxfs_dlt_fl, String pxfs_version_id, String pxfs_ptn_id ) throws Exception
	{
		DBHelper dbHelper = null;
		Date dateObj = new Date( 0 );
		int totalrows = 0;
		try
		{

			dbHelper = DBHelper.connectToReferenceDB( false );

			if ( dbHelper != null )
			{

				int paor_id = getPaorIdAggProcesor( paor_nm );
				String insertQuery = "insert into Xdr_Agg_File_Sequence (PXFS_ID,PAOR_ID,PXFS_SEQUENCE_NO,PXFS_MIN_SEQ_NO,PXFS_MAX_SEQ_NO," + "PXFS_SEQ_NO_PATTERN,PXFS_INCREMENT_BY,PXFS_REFERENCE_DATE,SYSTEM_GENERATED_FL,PXFS_DELETE_FL,PXFS_VERSION_ID,PTN_ID)" + "values(?,?,?,?,?,?,?,? ,?,?,?,?)";

				PreparedStatement stmt = dbHelper.dbConnection.prepareStatement( insertQuery );
				stmt.setInt( 1, pxfs_id );
				stmt.setInt( 2, paor_id );
				stmt.setInt( 3, Integer.valueOf( pxfs_seq_no ) );
				stmt.setInt( 4, Integer.valueOf( pxfs_min_seq_no ) );
				stmt.setInt( 5, Integer.valueOf( pxfs_max_seq_no ) );
				stmt.setString( 6, pxfs_seq_no_pattern );
				stmt.setInt( 7, Integer.valueOf( pxfs_incr_by ) );
				stmt.setDate( 8, dateObj.valueOf( pxfs_ref_dt ) );
				stmt.setString( 9, pxfs_system_gen_fl );
				stmt.setString( 10, pxfs_dlt_fl );
				stmt.setInt( 11, Integer.valueOf( pxfs_version_id ) );
				stmt.setInt( 12, Integer.valueOf( pxfs_ptn_id ) );
				totalrows = stmt.executeUpdate();
				Log4jHelper.logInfo( " total rows inserted in the 'Xdr_Agg_File_Sequence' table : " + totalrows );
				Log4jHelper.logInfo( "'Xdr_Agg_File_Sequence' is inserted  successfully from backend with the pxfs_id:  '" + pxfs_id + " for this aggregation processor " + paor_nm );
			}

			assertTrue( totalrows > 0, "Values are not inserted in 'Xdr_Agg_File_Sequence' table" );
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
		finally
		{
			if ( dbHelper != null )
				dbHelper.closeConnection();
		}
	}

	/*
	 * This method is to get max PRFS_FILE_SEQ_NUM of ROAMING_File_status from backend with fileter of PRF_CONFIG_TYPE,prfs_sender,PRFS_RECEPIENT
	 */
	public int getMaxPRFS_FILE_SEQ_NUM( String fileType, String sender, String recepient ) throws Exception
	{

		DBHelper dbHelper = null;
		int totalrows = 0;
		int maxPRFS_FILE_SEQ_NUM = 0;
		try
		{
			dbHelper = DBHelper.connectToReferenceDB( false );
			if ( dbHelper != null )
			{

				String maxPRFS_FILE_SEQ_NUMQuery = "select max(PRFS_FILE_SEQ_NUM) from ROAMING_File_status where PRF_CONFIG_TYPE=? AND prfs_sender=? and PRFS_RECEPIENT=?";
				PreparedStatement maxPRFS_FILE_SEQ_NUMQueryStmt = dbHelper.dbConnection.prepareStatement( maxPRFS_FILE_SEQ_NUMQuery );
				maxPRFS_FILE_SEQ_NUMQueryStmt.setString( 1, fileType );
				maxPRFS_FILE_SEQ_NUMQueryStmt.setString( 2, sender );
				maxPRFS_FILE_SEQ_NUMQueryStmt.setString( 3, recepient );
				totalrows = maxPRFS_FILE_SEQ_NUMQueryStmt.executeUpdate();
				ResultSet rs_PRFS_FILE_SEQ_NUM = maxPRFS_FILE_SEQ_NUMQueryStmt.executeQuery( maxPRFS_FILE_SEQ_NUMQuery );
				while ( rs_PRFS_FILE_SEQ_NUM.next() )
				{
					maxPRFS_FILE_SEQ_NUM = rs_PRFS_FILE_SEQ_NUM.getInt( "MAX(PRFS_FILE_SEQ_NUM)" );
				}
			}
			return maxPRFS_FILE_SEQ_NUM;

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
		finally
		{
			if ( dbHelper != null )
				dbHelper.closeConnection();
		}
	}

	/*
	 * This method is to get PRFS_FILE_NAME  of ROAMING_File_status table from backend with fileter of pRFS_FILE_SEQ_NUM, PRF_CONFIG_TYPE,prfs_sender,PRFS_RECEPIENT
	 */

	public String getPrfsFileName( int pRFS_FILE_SEQ_NUM, String fileType, String sender, String recepient ) throws Exception
	{

		DBHelper dbHelper = null;
		int totalrows = 0;
		String prfs_file_name = "";
		try
		{

			dbHelper = DBHelper.connectToReferenceDB( false );
			if ( dbHelper != null )
			{

				String prfs_file_namedQuery = "select prfs_file_name from ROAMING_File_status  where PRF_CONFIG_TYPE=? AND prfs_sender=? and PRFS_RECEPIENT=? and PRFS_FILE_SEQ_NUM=?";
				PreparedStatement prfs_file_nameQuery_stmt = dbHelper.dbConnection.prepareStatement( prfs_file_namedQuery );
				prfs_file_nameQuery_stmt.setString( 1, fileType );
				prfs_file_nameQuery_stmt.setString( 2, sender );
				prfs_file_nameQuery_stmt.setString( 3, recepient );
				prfs_file_nameQuery_stmt.setInt( 4, pRFS_FILE_SEQ_NUM );
				totalrows = prfs_file_nameQuery_stmt.executeUpdate();

				ResultSet rs_prfs_file_name = prfs_file_nameQuery_stmt.executeQuery( prfs_file_namedQuery );
				while ( rs_prfs_file_name.next() )
				{
					prfs_file_name = rs_prfs_file_name.getString( "PRFS_FILE_NAME" );
				}
			}
			Log4jHelper.logInfo( "File name-: "+prfs_file_name+" for pRFS_FILE_SEQ_NUM:"+pRFS_FILE_SEQ_NUM+" file type "+fileType );
			return prfs_file_name;

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
		finally
		{
			if ( dbHelper != null )
				dbHelper.closeConnection();
		}
	}

}
