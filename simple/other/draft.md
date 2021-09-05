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

# ----------------------------------------------------------------------------------------------------------------------

MySQL db err
ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'onePassword@123';

create table task_tb (
task_id int not null,
task_desc varchar(100) ,
task_date varchar(20) ,
primary key(task_id)
);

insert into task_tb values (1, 'Become React Guru', '2021-01-01'), (2, 'Learn Dropwizard', '2021-02-01');

select * from task_tb;
# ----------------------------------------------------------------------------------------------------------------------
# ----------------------------------------------------------------------------------------------------------------------
# ----------------------------------------------------------------------------------------------------------------------