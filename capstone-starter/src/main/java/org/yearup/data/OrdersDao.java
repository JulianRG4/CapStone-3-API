package org.yearup.data;

import org.yearup.models.Order;
import org.yearup.models.ShoppingCart;

import java.util.List;

public interface OrdersDao
{
    ShoppingCart createOrder(int userId);
    void createOrderLineItem(int userId, ShoppingCart shoppingCart);
    Order getOrderById(int userId);
}
