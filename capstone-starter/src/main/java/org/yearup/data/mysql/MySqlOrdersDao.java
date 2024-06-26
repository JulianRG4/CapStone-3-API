package org.yearup.data.mysql;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yearup.data.OrdersDao;
import org.yearup.data.ProfileDao;
import org.yearup.data.ShoppingCartDao;
import org.yearup.models.Order;
import org.yearup.models.Profile;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Map;

@Component
public class MySqlOrdersDao extends MySqlDaoBase implements OrdersDao
{
   ShoppingCartDao shoppingCartDao;
   ProfileDao profileDao;

   @Autowired
   public MySqlOrdersDao(DataSource dataSource, ShoppingCartDao shoppingCartDao, ProfileDao profileDao)
   {
         super(dataSource);
         this.shoppingCartDao = shoppingCartDao;
         this.profileDao = profileDao;
   }

    @Override
    public ShoppingCart createOrder(int userId)
    {
        Profile profile = profileDao.getById(userId);

        int orderId = 0;
        try(Connection connection = getConnection())
        {
            String sql = """
                    INSERT INTO orders
                    (user_id
                    ,date
                    ,address
                    ,city
                    ,state
                    ,zip
                    ,Shipping_amount)
                    VALUES
                    (?,?,?,?,?,?,?,?);
                    """;
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, userId);
            statement.setString(2, LocalDate.now().toString());
            statement.setString(3, profile.getAddress());
            statement.setString(4, profile.getCity());
            statement.setString(5, profile.getState());
            statement.setString(6, profile.getZip());
            statement.setDouble(7, 5.00);

            // creates the order
            statement.executeUpdate();
            ResultSet keys = statement.getGeneratedKeys();

            if (keys.next())
            {
                orderId = keys.getInt(1);
            }
            ShoppingCart cart = shoppingCartDao.getByUserId(userId);
            for(Map.Entry<Integer, ShoppingCartItem> cartItem : cart.getItems().entrySet())
            {
                int productId = cartItem.getKey();
                ShoppingCartItem item = cartItem.getValue();
                sql = """
                        INSERT INTO order_line_items
                        (order_id
                        ,product_id
                        ,sale_price
                        ,quantity)
                        ,discount)
                        VALUES
                        (?,?,?,?,?,?);
                        """;

                PreparedStatement statement2 = connection.prepareStatement(sql);
                statement2.setInt(1, orderId);
                statement2.setInt(2, productId);
                // insert each item into the order line items
            }

        }catch (Exception e)
        {
            System.out.println(e);
        }
        return shoppingCartDao.getByUserId(userId);
    }

    @Override
    public void createOrderLineItem(int userId, ShoppingCart shoppingCart) {

    }

    @Override
    public Order getOrderById(int userId) {
        return null;
    }
}
