package com.subex.automation.helpers.config;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class ResourceReader
{
	public InputStream getResourceAsStream( String resource ) throws FileNotFoundException
	{
		String stripped = resource.startsWith( "/" ) ? resource.substring( 1 ) : resource;

		InputStream stream = null;
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		if ( classLoader != null )
		{
			stream = classLoader.getResourceAsStream( stripped );
		}
		if ( stream == null )
		{
			stream = ResourceReader.class.getResourceAsStream( resource );
		}
		if ( stream == null )
		{
			stream = ResourceReader.class.getClassLoader().getResourceAsStream( stripped );
		}
		if ( stream == null )
		{
			throw new RuntimeException( resource + " not found" );
		}
		return stream;
	}
}