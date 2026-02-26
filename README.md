# ROCPS Automation Setup Guide

This document explains how to set up, import, configure, and run the ROCPS Web Automation Framework.

---

## 📌 PREREQUISITES

- **JDK 1.8 or later** must be installed (required for Eclipse).
- **Maven** should be installed and its `bin` folder added to the **Environment Variables**  
  Example:  
  ```text
  MAVEN_HOME = C:\apache-maven-xxx\

📥 GETTING THE AUTOMATION PROJECT FILES

Create a folder on your machine (example: Automation_ROCPS).

Inside it, create three folders:

roc-automation-util

roc-automation

rocps-web-automation

Download/checkout the corresponding GitHub repositories into each folder

🔌 INSTALLING TESTNG PLUGIN IN ECLIPSE

Open Eclipse → Help → Install New Software

In the Work with: textbox enter:

http://beust.com/eclipse


Click Add

Provide a name (example: TestNG Plugin)

Select the checkbox for TestNG and follow the installation steps.


📂 IMPORTING PROJECTS INTO ECLIPSE

Open Eclipse → File → Import

Select Maven → Existing Maven Projects

Browse and import the roc-web-util project folder → click Finish

Repeat the same steps to import:

roc-automation

rocps-web-automation

If Java-related errors appear:

Right-click project → Properties

Go to Java Build Path

Select JRE System Library → Edit

Choose Workspace Default JRE

Click Installed JREs

Ensure JDK 1.8 is selected

If not visible, click Search… and navigate to:

C:\Program Files\Java\


Apply changes

Go to Project → Clean → Clean all projects

Run Maven Install on each project:

Right-click roc-web-util → Run As → Maven install

Right-click roc-automation → Run As → Maven install

Right-click rocps-web-automation → Run As → Maven install

All installs should complete successfully.


🔗 ADDING DEPENDENCIES BETWEEN PROJECTS

Right-click roc-automation → Properties

Go to Project References

Select the checkbox for roc-web-util

Right-click rocps-web-automation → Properties

Go to Project References

Select:

roc-web-util

roc-automation

Clean all projects again:
Project → Clean → Clean all projects



⚙️ ADDITIONAL CHANGES

Locate psconfig.properties inside:

[Checkout-Folder]\rocps-web-automation\src\main\resources


Update config.properties with:

Your automation directory paths

ROCPS deployment details


🧹 CLEARING TEMP DIRECTORY (Required Before Execution)

Open Run window → Windows + R

Type: %temp%

Press Enter

Delete all temporary files


🪟 CLOSE UNWANTED WINDOWS

Before starting execution, close all other windows to avoid interruptions.


▶️ HOW TO RUN AUTOMATION TESTS (ATs)

In Eclipse, expand:

rocps-web-automation → src → main → resources


Right-click the required XML suite file → Run As → TestNG Suite

System Testing XMLs (AT suites) are prefixed with: SystemTesting

📊 REPORTS

After execution, reports are generated at:

rocps-web-automation\Report\



⭐ HAPPY AUTOMATING ROCPS ⭐
