create user libraryuser
    superuser
    createdb
    createrole;
alter user libraryuser with encrypted password 'secretbook';

create database librarydb;
grant all privileges on database librarydb to libraryuser;

-- before executing next commands we need to change manually the database to 'chefdb' from the console
create schema if not exists library_book;
alter database librarydb set search_path to "library_book";
set search_path = 'library_book';