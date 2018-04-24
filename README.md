# TopK-Algorithms

In this project we have implemented the paper titled “Efficient Top-k Approximate algorithms for Substring Matching.

Programming Language:The implementation is based in java using the Netbeans IDE.

Dataset:For the algorithms, we use the DBLP.xml dataset which is a large xml file file containing the tags author, citation, paper and conference.

Database:The Dataset is stored in the MySQL database by setting up the connection using the code present in DBConnection.java

Data ParsingTo extract only the four important file use Element.java, Paper.java and Conference.java and run the Parser.java file to parse the file.It uses the SAX parser and extracts the required data and store it in the database.

The code for the creation of four tables in the MySQL database is given in db.sql

Algorithms:The implementation of the algorithms are given under the packages titled ToPKNaive, TopKINDEX, TopKSplit and TopKLBNew

Also find the important classes used for these algorithms under the respective packages.

