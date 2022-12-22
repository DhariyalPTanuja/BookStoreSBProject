# BookStoreSBProject
BookStore Spring Boot Application
BookStoreProject
Bookstore is a simple Java Spring Boot application to store and order books.
The BookStore application is accessible through a web browser
The user has to provide their first name, last name, email, and password,address,date of birth.
Each book has information about an author,name , book description,image, price of the book , quantity of book in the bookstore.
Each order take the detail about user ,book ,quantity and delivery address.
Each user can order list of book 
Functionality
BookStore uses MySql database and consists of a database named “bookstore_sb_db” which has four tables named:
1. “user_information”. The user_information  table consist of following columns - user_id,first_name,last_name,email,address,dob,password,confirm_password
2.“book_info” : The book_info table consist of following columns
bookid, author_name,  book_description, book_img, book_name, price, quantity
3.“ “cart_info”: “cart_info” table consist of the following columns
Cart_id,cart_quantity,book_id,user_id
4.“ “order_info”: The order_info table consist of the following columns
Orde_id, cancel, date, delivery_address, order_price, order_quantity, order_user_id
Jpa is used to communicate with the database.
BookStore supports the following functionality:
•         Insertion of Record: It inserts the record in database, The endpoint for this function is: “/bookStoreSBProject/user/register”
•	Get record by id: It gets all the detail of the record by id. The endpoint for this function is: “/bookStoreSBProject/user/getById/{id}”. Here id is a Path variable
•	Get All Record: it gets all records that are stored. The URL for this function is: “/bookStoreSBProject/user/getAll”
•	Update a Record: update the existing record. The URL for this function is  “/bookStoreSBProject/user/update/{id}”, where id is the path variable. Here we pass the ModelDto (UserDto/OrderDto/BookDto/CartDto) object too. While updating if id is not matched with the path variable “id” in the URL, it gives an exception. for this, we create an exception class and an exception handler class.
•	Delete a record: delete the existing record.The URL for this function is “/bookStoreSBProject/user/delete/{id}”


