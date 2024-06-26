# CapStone-3-API
--Overview 
-This project involved working with an API by fixing bugs, adding code, and making changes to the backend in order to allow communication to the front end. This was done in multiple phases which tasked us with different things to get the application properly running.
--Phase 1 
-In this phase I created the functions for both the category controller and DAO. I added CRUD operations with SQL statements in order to allow the user to search through products on the front end. I also did the controller and proper verifications to allow adding, updating, and deleting. 
--Phase 2
-In this phase there were multiple bugs involving the product controller. After debugging and going through code the problem was found in the category DAO with the problem being multiple things involving the SQL query and Statement.
The fixes allowed for more accurate searches and got rid of the improper data. 
-Secondly I fixed an issue with how the update statement was executing its statement. After this I also defeated the extra products in the database.
--Phase 3
-Phase 3 tasked us with creating and fixing the Shopping cart. First the MySQLShoppingCart had to be made, then I added the CRUD operations to allow products to be gathered. I also fixed issues involving how the shopping cart saved data. It had to be constantly updated because an issue was the shopping cart not updating until I refreshed the application. The Shopping cart also only allows users who are logged in to add items.
--Phase 4
-Here I allowed the user to register a new account and log in as that account. 
