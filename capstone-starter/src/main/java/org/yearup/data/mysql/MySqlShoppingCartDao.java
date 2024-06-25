package org.yearup.data.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yearup.data.ProductDao;
import org.yearup.data.ShoppingCartDao;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;

import javax.sql.DataSource;
import java.sql.*;

@Component
public class MySqlShoppingCartDao extends MySqlDaoBase implements ShoppingCartDao
{
    ShoppingCart shoppingCart = new ShoppingCart();
    ProductDao productDao;
    @Autowired
    public MySqlShoppingCartDao(DataSource dataSource, ProductDao productDao)
    {
        super(dataSource);
        this.productDao = productDao;
    }

    @Override
    public ShoppingCart getByUserId(int userId)
    {
        try(Connection connection = getConnection())
        {
            String sql = """
                    SELECT user_id
                    , product_id
                    , quantity
                    FROM shopping_cart
                    WHERE user_id = ?;
                    """;

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);

            ResultSet row = statement.executeQuery();

            while (row.next())
            {
                ShoppingCartItem shoppingCartItem = mapRowToShoppingCartItem(row);
                shoppingCart.add(shoppingCartItem);
            }
        }catch (Exception e)
        {
            System.out.println(e);
        }
        return shoppingCart;
    }

    @Override
    public ShoppingCart addItem(int userId, int productId)
    {

        try(Connection connection = getConnection())
        {
            String sql = """
                    INSERT INTO shopping_cart(user_id, product_id, quantity)
                    VALUES(?, ?, ?)
                    ON DUPLICATE KEY UPDATE quantity = quantity + 1;
                    """;

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            statement.setInt(2, productId);
            statement.setInt(3, 1);

            statement.executeUpdate();

            ShoppingCartItem ShoppingCartItem = new ShoppingCartItem();
            {{
                ShoppingCartItem.setProduct(productDao.getById(productId));
                ShoppingCartItem.setQuantity(1);
            }}
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
        return getByUserId(userId);
    }

    @Override
    public ShoppingCart updateItemQuantity(int itemId, ShoppingCartItem shoppingCartItem, int userId)
    {

        try(Connection connection = getConnection())
        {
            String sql = """
                    UPDATE shopping_cart
                    SET quantity = ?
                    WHERE user_id = ? AND product_id = ?
                    """;

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, shoppingCartItem.getQuantity());
            statement.setInt(2, userId);
            statement.setInt(3, itemId);

            statement.executeUpdate();

            return getByUserId(userId);


        }catch (SQLException e)
        {
            System.out.println(e);
        }
        return getByUserId(userId);

    }

    @Override
    public ShoppingCart removeItem(int userId)
    {
      try(Connection connection = getConnection())
        {
            String sql = """
                    DELETE FROM shopping_cart
                    WHERE user_id = ?;
                    """;

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);

            statement.executeUpdate();
            shoppingCart.clear();
        }catch (SQLException e)
        {
            System.out.println("Error: " + e) ;
        }
      return getByUserId(userId);
    }


    public ShoppingCartItem mapRowToShoppingCartItem(ResultSet row) throws SQLException
    {
       int productId = row.getInt("product_id");
       int quantity = row.getInt("quantity");

         ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
         {{
              shoppingCartItem.setProduct(productDao.getById(productId));
              shoppingCartItem.setQuantity(quantity);
         }}
       return shoppingCartItem;
    }

}
