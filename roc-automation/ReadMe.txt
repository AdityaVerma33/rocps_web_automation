abc	PREREQUISITES

1. JDK 1.7 has to be installed in the machine for Eclipse. Please contact IT Team for the same.
2. Sikuli has to be installed in the machine. Installable is available in the below location
	\\rocra-testing1\Automation\Software\Sikuli\Sikuli-X-1.0rc3 (r905)-win32.exe
3. To let the machine be running without getting locked follow the steps in below link, install caffeine available in below location. Caffeine should be made active for 24 hours
	\\rocra-testing1\Automation\Software\Caffeine\caffeine.zip
4. Extract Apache Ant present in below location to your local machine and set it's bin folder in Environment Variables. e.g. ANT_HOME
	\\rocra-testing1\Automation\Software\Build\apache-ant-1.9.6-bin.zip
5. Extract Maven present in below location to your local machine and set it's bin folder in Environment Variables. e.g. MAVEN_HOME
	\\rocra-testing1\Automation\Software\Build\apache-maven-3.3.9-bin.zip
6. Extract Cygwin present in below location to your local machine and set it's bin folder in Environment Variables. e.g. CYGWIN_HOME
	\\rocra-testing1\Automation\Software\Cygwin\Cygwin.zip
7. Ensure to add the above variables in PATH.

--------------------------------------------------------------

	NOTE
	
1. Copying 'config.properties' files across different svn automation versions is not supported.
	
--------------------------------------------------------------

	TO GET AUTOMATION PROJECT FILES

1. Create a folder in your hard drive(Eg - Automation_ROC). This is your checkout folder.
2. Create 2 folders, one for roc-automation-util and other for roc-automation.
3. Go inside roc-automation-util folder and right click > Click on SVN Checkout for the below mentioned svn paths - 

	roc-web-util   -  svn://10.113.32.127/Spark/trunk/automation/1.0.0/roc-web-util
	
4. Go inside roc-automation folder and right click > Click on SVN Checkout for the below mentioned svn paths -

	roc-automation - svn://10.113.32.127/Spark/trunk/automation/1.0.0/roc-automation
	
-------------------------------------------------------------	

	MANDATORY REFERENCE AND USAGE DATABASE NAME AND BRINGING UP ROC SET UP
	
1. Download the binaries from blrfs01 or Jenkins for ROC Server and Client
2. Create a reference DB.
3. Create a usage DB.
4. Run installer and bring up ROC set up.
5. Ensure that you give the TIMEZONE ID as 'Asia/Calcutta' in the web.xml in tomcat.

--------------------------------------------------------------

	PLACING THE SETTINGS FILES
	
1. Go to "C:\Users\<username>" location using Explorer. Eg., C:\Users\madhu.duraisamy
2. If .m2 folder is not available, create the same.
3. Copy/Replace the settings file present in the below location, into .m2 repository
	\\rocra-testing1\Automation\Software\m2\Settings.xml

4. Extract the below mentioned folder into your .m2 directory.
	\\rocra-testing1\Automation\Software\m2\repository.zip

NOTE: .m2 can be accessed by opening WINDOWS RUN Prompt and typing ".m2", and pressing ENTER key

----------------------------------------------------------------

	TO SETUP ECLIPSE
	
1. Go to the following location to get the ECLIPSE MARS zip file
	\\rocra-testing1\Automation\Software\Eclipse\eclipse-java-mars-2-win32-x86_64.zip
2. Extract the contents to your local drive (Ex: D:\Eclipse Mars)
3. Open the Eclipse folder and then Click on the 'eclipse.exe' application icon
4. If Eclipse opens a pop-up to prompt you to select a workspace. Select a workspace on your hard drive (Ex: D:\Automation Workspace)

   Eclipse will be up and ready
   
--------------------------------------------------------------

	INSTALLING TestNG PLUG-IN TO ECLIPSE
	
1. Select Help in the toolbar, Help -> Install new software
2. For Eclipse Mars and above, enter http://beust.com/eclipse in the 'Work with' textbox
3. Click on Add
4. Specify a Name and give the same location : http://beust.com/eclipse, in the new pop-up window
5. Select the checkbox and Eclipse will then guide you through the process

----------------------------------------------------------------
	
	TO IMPORT THE AUTOMATION PROJECT FILES
	
1. Open Eclipse, In Eclipse window, Click on File -> Import
2. In the new pop-up window, Expand Maven dropdown -> Select 'Existing Maven Projects'
3. Click on 'Browse', Browse for the Root Directory folder 'roc-web-util'
4. Click Finish
5. Again Import 'roc-automation' in the same way : 
   Expand Maven dropdown -> Select 'Existing Maven Projects'
   Browse for the Root Directory folder 'roc-automation'
   Click Finish
6. If there are any errors in the projects related to Java, follow the below steps
	•	Right click your project > properties
	•	Select “Java Build Path” on left, then “JRE System Library”, click Edit…
	•	Select "Workspace Default JRE"
	•	Click "Installed JREs"
	•	If you see JRE you want in the list select it (selecting a JDK is OK too)
	•	If not, click Search…, navigate to Computer > Windows C: > Program Files > Java, then click OK
	•	Now you should see all installed JREs, select JDK 1.8
	•	Click OK/Finish
	•	Click on Project in the main toolbar, Project -> Clean -> Select 'Clean all projects' radio button and click OK.
	
7. Right click on 'roc-web-util' and click 'Run As' > 'Maven install'
8. The maven install should be successful.
9. Right click on 'roc-automation' and click 'Run As' > 'Maven install'
10. The maven install should be successful.

---------------------------------------------------------------

	ADDING DEPENDENCIES
	
1. RightClick on 'roc-automation' project, in the project explorer view, in eclipse, click on 'properties'
2. Goto 'Project References' -> click checkbox for 'roc-web-util'
3. Click on Project in the main toolbar, Project -> Clean -> Select 'Clean all projects' radio button and click OK. Clean both util & roc-automation projects.

---------------------------------------------------------------

	ADDITIONAL CHANGES
	
1. 'config.properties' is present in the path 'Checkout-Folder\\roc_automation\src\main\resources'.
2. Change the 'config.properties' file according as per your Automation location details and ROC deployement details.

----------------------------------------------------------------
	
	CLEARING TEMP DIRECTORY
	
1. Temp should be cleared before running automation suite.
2. Do windows+R > type %temp%.
3. Temp directory will open.
4. Select all and delete the files permanently.

-----------------------------------------------------------------

	CLOSE UNWANTED WINDOWS
	
1. Close all other windows before starting execution.

-----------------------------------------------------------------

	WINDOWS FILE UPLOAD
	
1. Before starting execution launch the windows file upload pop up and ensure that it is not in maximized mode.

------------------------------------------------------------------

	BROWSER LOADING MASK

1. Before starting execution please ensure that browser loading mask should not take more than 60 sec to launch spark app.

------------------------------------------------------------------

	HOW TO TAKE A BACKUP OF REFERENCE DATABASE
	
1. In Eclipse > Expand the project rocra-automation > src > main > resources.
2. Tester has to run 'CreateDbBackup.xml' initially before running any system AT as this will create the freshdb.dat file
3. For running  'CreateDbBackup.xml' right click on 'CreateDbBackup.xml' to get context menu and click 'Run As' > 'TestNG Suite' .
4. Before running the system flow ATs, user has to run the 'CreateDbBackup.xml' once.
   This will create a backup of the freshly installed reference DB and use the backup file for restoring the ref DB when a system flow AT is completed.

----------------------------------------------------------------

	HOW TO RUN ATs
	
1. In Eclipse > Expand the project roc-automation > src > main > resources.
2. For running  a suite, right click on XML file to get context menu and click 'Run As' > 'TestNG Suite' .
3. Before running the system flow ATs, user has to run the 'CreateDbBackup.xml' once.
Note: All the system testing XMLs(Also called ATs) are prefixed with 'SystemTesting'.

----------------------------------------------------------------

	REPORTS

1. Reports will get generated in roc-automation Checkout_Folder\Report folder
	
----------------------------------------------------------------

	STEPS TO UPGRADE THE AUTOMATION ROC SETUP
	
In order to upgrade (i.e. running Installer of same version or new version) the current setup that is being used for Automation follow the below steps
1. In Eclipse > Expand the project roc-automation > src > main > resources.
2. Right click RestoreDbBackup.xml file and click 'Run As' > 'TestNG Suite'
3. Run Installer
4. If there is any change in Server, Tomcat, DataDir or database, update the changes in config.properties and selenium-config.properties files
5. Right click on 'CreateDbBackup.xml' and click 'Run As' > 'TestNG Suite'
6. Run the flows as usual

----------------------------------------------------------------

	STEPS TO UPDATE THE AUTOMATION CHECKOUT FOLDERS
	
1.	Go to the checkout folder
2. Right click roc-automation-util folder and click SVN Update
3. Right click roc-automation folder and click SVN Update
4. In eclipse, right click roc-automation-util and click Refresh
5. Right click roc-automation and click Refresh
6. Right click roc-automation-util and click 'Run As' > 'Maven Install'
7. Right click roc-automation and click 'Run As' > 'Maven Install'
8. Select roc-automation-util project
9. Click on Project in the main toolbar, Project -> Clean -> Select 'Clean all projects' radio button and click OK.
10. Select roc-automation-util project
11. Click on Project in the main toolbar, Project -> Clean -> Select 'Clean all projects' radio button and click OK.
12. Run the flows as usual
