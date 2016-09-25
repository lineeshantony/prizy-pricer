Instructions:

Running Prizy Pricer locally:

Step 1:
Run the SQL queries given alongwith the project to a MYSQL database

Step 2:
Create artifacts (war/ear) using maven command:
mvn clean install -DskipTests

Step 3:
Add the WAR file to the local server (Apache Tomcat used by me)

Step 4:
Access the product loader page by using the following localhost url:
http://localhost:8080/prizy-pricer-web/productloader.xhtml

Step 5:
Access the admin page to view the list of products by using the following localhost url:
http://localhost:8080/prizy-pricer-web/productlist.xhtml

Clicking on each view product button will take you to the individual product detail page


Accessing Unit test cases:
Unit test cases are found in the same package structure as each individual class in the src/test/java folder.