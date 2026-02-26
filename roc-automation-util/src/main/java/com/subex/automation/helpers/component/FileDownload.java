package com.subex.automation.helpers.component;

public enum FileDownload
{
	R1920_1080( "SaveFileRadio1920_1080.png", "OKButton1920_1080.png" ),
	R1280_1024( "SaveFileRadio1280_1024.png", "OKButton1280_1024.png" ),
	Default( "SaveFileRadio1280_720.png", "OKButton1280_720.png" );

	public final String saveFile;
	public final String okButton;

	private FileDownload( String saveFile, String okButton )
	{
		this.saveFile = saveFile;
		this.okButton = okButton;
	}
}
