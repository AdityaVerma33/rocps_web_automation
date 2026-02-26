package com.subex.automation.helpers.enums;

public enum ActionsEnum {

	COMMON_TASKS("Common Tasks"),
	COPY("Copy"),
	EXPORT("Export"),
	NEW("New"),
	ADD("Add"),
	EDIT("Edit"),
	DELETE("Delete"),
	UNDELETE("Undelete"),
	SAVE_AS("Save As"),
	COPY_SELECTED_CELL("Selected Cell(s)"),
	COPY_SELECTED_COLUMN("Selected Column"),
	COPY_SELECTED_ROW("Selected Row(s)"),
	COPY_ALL_ROWS("All Rows"),
	EXPORT_ALL("All Rows"),
	EXPORT_CONFIGURED("Configured Rows"),
	EXPORT_SELECTED("Selected Row(s)"),
	VIEW_LOG("View Log"),
	MOVE_UP("Move Up"),
	MOVE_DOWN("Move Down");
	
	private String name;
	
	private ActionsEnum(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return this.name;
	}
}