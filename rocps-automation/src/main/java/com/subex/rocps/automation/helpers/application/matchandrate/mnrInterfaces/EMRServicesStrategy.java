package com.subex.rocps.automation.helpers.application.matchandrate.mnrInterfaces;

import java.util.Map;

public interface EMRServicesStrategy 
{
	public void addServices(Map<String,String> map) throws Exception;
	public void editServices(Map<String,String> map) throws Exception;
}
