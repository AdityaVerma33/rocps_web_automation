package com.subex.rocps.automation.helpers.application.products.productBundles.product;

import java.util.Map;

public class ProductItemFactory
{

	protected Map<String, String> productItemsFactMap = null;

	/**Constructor:
	 * @param productItemsFactMap
	 */
	public ProductItemFactory( Map<String, String> productItemsFactMap )
	{
		this.productItemsFactMap = productItemsFactMap;
	}

	//Method to get the object of Product Item
	public ProductItem getTypeOfItem( String typeOfItem )
	{
		if ( typeOfItem.contains( "OneOff" ) )
			return new OneOffImpl( productItemsFactMap );
		else if ( typeOfItem.contains( "Recurring" ) )
			return new RecurringImpl( productItemsFactMap );
		return null;

	}
}
