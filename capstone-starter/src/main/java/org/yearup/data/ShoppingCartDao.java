package org.yearup.data;

import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;

public interface ShoppingCartDao
{
    ShoppingCart getByUserId(int userId);

    ShoppingCart addItem(int userId, int productId);

    ShoppingCart updateItemQuantity(int itemId, ShoppingCartItem shoppingCartItem, int userId);

    ShoppingCart removeItem(int userId);


    // add additional method signatures here

}
