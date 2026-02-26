package com.subex.automation.helpers.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.subex.automation.helpers.report.Log4jHelper;

/*
 * Base class for reading data from config files.
 */

public class ConfigReader extends ResourceReader
{
	private final Properties properties = new Properties();

	public Properties getProperties()
	{
		return properties;
	}

	public ConfigReader( String configFileName ) throws Exception {
		InputStream stream = null;
		try {
			stream = getResourceAsStream( configFileName );
			properties.load( stream );
		}
		catch ( FileNotFoundException e ) {
			Log4jHelper.logInfo( "Could not read config file: " + configFileName );
			e.printStackTrace();
		}
		catch ( IOException e ) {
			Log4jHelper.logInfo( "Could not read config file: " + configFileName );
			e.printStackTrace();
		}
		finally {
			if (stream != null)
				stream.close();
		}
	}
	
	public InputStream getResourceAsStream( String resource ) throws FileNotFoundException {
		String stripped = resource.startsWith( "/" ) ? resource.substring( 1 ) : resource;
		InputStream stream = new FileInputStream(stripped);

		return stream;
	}
}