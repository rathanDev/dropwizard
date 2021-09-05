GetStarted
https://www.dropwizard.io/en/latest/getting-started.html

Continue Reading
https://www.dropwizard.io/en/latest/manual/core.html

# build image
docker build -t dropwizard/dropwizard-docker .

# run
docker run -p 8080:8080 dropwizard/dropwizard-docker





9/4/2021, 11:52:31 AM
-------------------------------------------------------------------------------------------
"CREATE TABLE ADMIN.sale_tb
(
prod_id NUMBER ,
cust_id VARCHAR2 (20) ,
"desc"  VARCHAR2 (20)
)
LOGGING"
Table ADMIN.SALE_TB created.


9/4/2021, 11:52:31 AM
-------------------------------------------------------------------------------------------
"ALTER TABLE ADMIN.sale_tb
ADD CONSTRAINT sale_tb_PK PRIMARY KEY ( prod_id )
USING INDEX LOGGING"
Table ADMIN.SALE_TB altered.