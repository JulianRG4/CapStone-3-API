package org.yearup.data.mysql;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.yearup.data.CategoryDao;
import org.yearup.models.Category;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class MySqlCategoryDao extends MySqlDaoBase implements CategoryDao
{
    public MySqlCategoryDao(DataSource dataSource)
    {
        super(dataSource);
    }

    @Override
    public List<Category> getAllCategories()
    {
        List<Category> categories = new ArrayList<>();

        try(Connection connection = getConnection())
        {
            String sql = "SELECT category_id\n" +
                    "\t, name\n" +
                    "\t, description\n" +
                    "FROM categories;";

            Statement statement = connection.createStatement();
            ResultSet row = statement.executeQuery(sql);

            while (row.next())
            {
                int categoryId = row.getInt("category_id");
                String categoryName = row.getString("name");
                String categoryDescription = row.getString("description");

                categories.add(new Category(categoryId, categoryName, categoryDescription));
            }
        }catch (Exception e)
        {
            System.out.println(e);
        }
        return categories;
    }

    @Override
    public Category getById(int categoryId)
    {
        try(Connection connection = getConnection())
        {
            String sql = "SELECT category_id\n" +
                    "\t, name\n" +
                    "\t, description\n" +
                    "FROM categories\n" +
                    "WHERE category_id = ?;";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,categoryId);

            ResultSet row = statement.executeQuery();

            if(row.next())
            {
                return mapRow(row);
            }
        }catch (Exception e)
        {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public Category create(Category category)
    {
      int NewCategoryId = 0;
      try(Connection connection = getConnection())
      {
          String sql = """
                  INSERT INTO categories(name, Description)
                  VALUES(?, ?);
                  """;
          PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
          statement.setString(1, category.getName());
          statement.setString(2, category.getDescription());

          statement.executeUpdate();
          ResultSet Keys = statement.getGeneratedKeys();

          if(Keys.next())
          {
              NewCategoryId = Keys.getInt(1);
          }
      }catch (Exception e)
      {
          System.out.println(e);
      }
      return getById(NewCategoryId);

    }

    @Override
    public void update(int categoryId, Category category)
    {
        try(Connection connection = getConnection())
        {
            String sql = """
                    UPDATE categories
                    SET name = ?
                        .Description = ?
                        WHERE category_id = ?;
                    """;
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, category.getName());
            statement.setString(2, category.getDescription());
            statement.setInt(3, categoryId);

            statement.executeUpdate();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    @Override
    public void delete(int categoryId)
    {
        try(Connection connection = getConnection())
        {
            String sql = """
                    DELETE FROM categories
                    WHERE category_id = ?;
                    """;

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, categoryId);

            statement.executeUpdate();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    private Category mapRow(ResultSet row) throws SQLException
    {
        int categoryId = row.getInt("category_id");
        String name = row.getString("name");
        String description = row.getString("description");

        Category category = new Category()
        {{
            setCategoryId(categoryId);
            setName(name);
            setDescription(description);
        }};

        return category;
    }

}
