webissues
=========

Simple spring-based web application

How to configure:
1) Create database with parameters from bean dataSource in dispatcher-servlet.xml, or reconfigure data source in xml.
2) Start application - hibernate configuration is not like in production mode, so it will create tables automatically.
3) Apply init.sql from application resources.

Now you can log in with login=admin and password=admin
