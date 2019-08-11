# Top K- Substring Matching Algorithms
In this project I have implemented the paper titled “Efficient Top-k Approximate algorithms for Substring Matching of SIGMOD '13.The link to the paper can be found here http://dbgroup.cs.tsinghua.edu.cn/dd/list/3.pdf

The main idea of the paper is to match the query string accepted from the user to the existing strings using the pattern matching technique particularly by using substrings.By generating q-grams it is possible to extrat the top k strings witht eh similar substrings.Four different algorithms are implemented as given in the paper and the same has been extended to the dblp dataset.

Programming Language:The implementation is based in java using the Netbeans IDE.

Dataset:For the algorithms, we use the DBLP.xml dataset which is a large xml file file containing the tags author, citation, paper and conference.

Database:The Dataset is stored in the MySQL database by setting up the connection using the code present in DBConnection.java.

DataParsing:To extract only the four important file use Element.java, Paper.java and Conference.java and run the Parser.java file to parse the file.It uses the SAX parser and extracts the required data and store it in the database.

The code for the creation of four tables in the MySQL database is given in db.sql

Algorithms:The implementation of the algorithms are given under the packages titled ToPKNaive, TopKINDEX, TopKSplit and TopKLBNew

Also find the important classes used for these algorithms under the respective packages.

Find the dblp datset at the google drive link https://drive.google.com/open?id=1sLVRM57sRB3rx3EcUkA7DcvHcsntVNnL
