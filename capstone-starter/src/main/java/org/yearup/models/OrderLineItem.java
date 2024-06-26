package org.yearup.models;

public class OrderLineItem
{
    private int orderLineItemId;
    private int orderId;
    private int productId;
    private double salePrice;
    private int quantity;
    private double discount;

    public OrderLineItem
            (int orderLineItemId,
                         int orderId,
                         int productId,
                         double salePrice,
                         int quantity,
                         double discount
            )
    {
        this.orderLineItemId = orderLineItemId;
        this.orderId = orderId;
        this.productId = productId;
        this.salePrice = salePrice;
        this.quantity = quantity;
        this.discount = discount;
    }

    public OrderLineItem()
    {
    }

    public int getOrderLineItemId() {
        return orderLineItemId;
    }

    public void setOrderLineItemId(int orderLineItemId) {
        this.orderLineItemId = orderLineItemId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
