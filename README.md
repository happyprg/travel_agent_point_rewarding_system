# ERD
![ERD](ERD/ERD.png)
## DDL
- https://github.com/hongsgo/triple_point/blob/master/ERD/triple_point_ddl.sql
## TEST scripts
- https://github.com/hongsgo/triple_point/blob/master/ERD/TestScript.sql
# Tests by REST API
## Add
 ![ADD](docs/images/1.ADD.png)
## MOD(Removed photos)
 ![MOD](docs/images/2.MOD.png)
## DELETE
 ![DELETE](docs/images/3.DELETE.png)
# Check Point API(Total point=Random value)
 ![CHECK POINT](docs/images/4.CheckPoint.png)
# Save Point API(Empty contents)
![SAVE POINT](docs/images/5.SavePoint.png)

 # How to run
 * git clone this repo
 * gradle build
 * docker build -t test .
 * docker run -p8080:8080 test 
 
