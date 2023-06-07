-- ========== Creation of the user and the database (schema) ==========
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

-- ========== Tables and relations creation ==========
-- it is done via liquibase change log

-- ========== Database data loading ==========
-- Insert authors
INSERT INTO authors (first_name, middle_name, last_name)
VALUES ('J.K.', NULL, 'Rowling'),
 ('Suzanne', NULL, 'Collins'),
 ('Stephen', NULL, 'King'),
 ('J.R.R.', NULL, 'Tolkien'),
 ('E.B.', NULL, 'White'),
 ('C.S.', NULL, 'Lewis'),
 ('Roald', NULL, 'Dahl'),
 ('Shel', NULL, 'Silverstein'),
 ('Dan', '', 'Brown'),
 ('Unknown', NULL, NULL);

-- Insert genres
INSERT INTO genres (genre_name)
VALUES ( 'Fantasy'),
 ('Young Adult'),
 ( 'Horror'),
 ( 'Children'),
 ( 'Comics');

-- Insert tags
INSERT INTO tags ( tag_name)
VALUES ( 'Magic'),
('Adventure'),
 ( 'Dystopian'),
 ( 'Horror'),
 ( 'Friendship'),
 ( 'For Kids'),
 ( 'DC');

-- Insert publishers
INSERT INTO publishers  (publisher_name)
VALUES ( 'Publisher A'),
 ( 'Publisher B'),
 ( 'Publisher C');

-- Insert Harry Potter books
INSERT INTO books (book_title, isbn, published_date, total_pages, rating, publisher_id)
VALUES ('Harry Potter and the Philosopher''s Stone', '9780590353427', '1997-06-26', 320, 4.5, 1);

INSERT INTO authors_books (author_id, book_id)
VALUES (1, 1);

INSERT INTO book_genres (book_id, genre_id)
VALUES (1, 1);

INSERT INTO book_tags (book_id, tag_id)
VALUES (1, 1),
(1, 2);

INSERT INTO books ( book_title, isbn, published_date, total_pages, rating, publisher_id)
VALUES ( 'Harry Potter and the Chamber of Secrets', '9780439064873', '1998-07-02', 352, 4.6, 1);

INSERT INTO authors_books (author_id, book_id)
VALUES (1, 2);

INSERT INTO book_genres (book_id, genre_id)
VALUES (2, 1);

INSERT INTO book_tags (book_id, tag_id)
VALUES (2, 1),
(2, 2);

-- Insert Hunger Games books
INSERT INTO books ( book_title, isbn, published_date, total_pages, rating, publisher_id)
VALUES ( 'The Hunger Games', '9780439023481', '2008-09-14', 374, 4.2, 2);

INSERT INTO authors_books (author_id, book_id)
VALUES (2, 3);

INSERT INTO book_genres (book_id, genre_id)
VALUES (3, 2);

INSERT INTO book_tags (book_id, tag_id)
VALUES (3, 3);

INSERT INTO books ( book_title, isbn, published_date, total_pages, rating, publisher_id)
VALUES ('Catching Fire', '9780439023498', '2009-09-01', 391, 4.4, 2);

INSERT INTO authors_books (author_id, book_id)
VALUES (2, 4);

INSERT INTO book_genres (book_id, genre_id)
VALUES (4, 2);

INSERT INTO book_tags (book_id, tag_id)
VALUES (4, 3);

-- Insert Stephen King books
INSERT INTO books ( book_title, isbn, published_date, total_pages, rating, publisher_id)
VALUES ( 'The Shining', '9780307743657', '1977-01-28', 447, 4.1, 3);

INSERT INTO authors_books (author_id, book_id)
VALUES (3, 5);

INSERT INTO book_genres (book_id, genre_id)
VALUES (5, 3);

INSERT INTO book_tags (book_id, tag_id)
VALUES (5, 4);

INSERT INTO books ( book_title, isbn, published_date, total_pages, rating, publisher_id)
VALUES ( 'It', '9780450411434', '1986-09-15', 1138, 4.7, 3);

INSERT INTO authors_books (author_id, book_id)
VALUES (3, 6);

INSERT INTO book_genres (book_id, genre_id)
VALUES (6, 3);

INSERT INTO book_tags (book_id, tag_id)
VALUES (6, 4);

-- Insert Lord of the Rings books
INSERT INTO books (book_title, isbn, published_date, total_pages, rating, publisher_id)
VALUES ('The Fellowship of the Ring', '9780618574940', '1954-07-29', 423, 4.5, 3);

INSERT INTO authors_books (author_id, book_id)
VALUES (4, 7);

INSERT INTO book_genres (book_id, genre_id)
VALUES (7, 1);

INSERT INTO book_tags (book_id, tag_id)
VALUES (7, 2),
 (7, 1);

INSERT INTO books (book_title, isbn, published_date, total_pages, rating, publisher_id)
VALUES ( 'The Two Towers', '9780345339713', '1954-11-11', 352, 4.4, 3);

INSERT INTO authors_books (author_id, book_id)
VALUES (4, 8);

INSERT INTO book_genres (book_id, genre_id)
VALUES (8, 1);

INSERT INTO book_tags (book_id, tag_id)
VALUES (8, 2),
 (8, 1);

-- Insert children's books
INSERT INTO books (book_title, isbn, published_date, total_pages, rating, publisher_id)
VALUES ('Charlotte''s Web', '9780064400558', '1952-10-15', 192, 4.7, 3);

INSERT INTO authors_books (author_id, book_id)
VALUES (5, 9);

INSERT INTO book_genres (book_id, genre_id)
VALUES (9, 4);

INSERT INTO book_tags (book_id, tag_id)
VALUES (9, 5),
 (9, 6);

INSERT INTO books (book_title, isbn, published_date, total_pages, rating, publisher_id)
VALUES ('The Chronicles of Narnia: The Lion, the Witch and the Wardrobe', '9780060764890', '1950-10-16', 208, 4.5, 2);

INSERT INTO authors_books (author_id, book_id)
VALUES (6, 10);

INSERT INTO book_genres (book_id, genre_id)
VALUES (10, 4);

INSERT INTO book_tags (book_id, tag_id)
VALUES (10, 5),
 (10, 6);

INSERT INTO books (book_title, isbn, published_date, total_pages, rating, publisher_id)
VALUES ( 'Matilda', '9780142410370', '1988-10-21', 240, 4.8, 2);

INSERT INTO authors_books (author_id, book_id)
VALUES (7, 11);

INSERT INTO book_genres (book_id, genre_id)
VALUES (11, 4);

INSERT INTO book_tags (book_id, tag_id)
VALUES (11, 5),
 (11, 6);

INSERT INTO books (book_title, isbn, published_date, total_pages, rating, publisher_id)
VALUES ( 'The Giving Tree', '9780060256654', '1964-10-07', 64, 4.6, 1);

INSERT INTO authors_books (author_id, book_id)
VALUES (8, 12);

INSERT INTO book_genres (book_id, genre_id)
VALUES (12, 4);

INSERT INTO book_tags (book_id, tag_id)
VALUES (12, 5),
 (12, 6);

INSERT INTO books (book_title, isbn, published_date, total_pages, rating, publisher_id)
VALUES ('Where the Wild Things Are', '9780060254926', '1963-04-01', 48, 4.4, 3);

INSERT INTO authors_books (author_id, book_id)
VALUES (8, 13);

INSERT INTO book_genres (book_id, genre_id)
VALUES (13, 4);

INSERT INTO book_tags (book_id, tag_id)
VALUES (13, 5),
 (13, 6);

-- Insert comics
INSERT INTO books (book_title, isbn, published_date, total_pages, rating, publisher_id)
VALUES ('Watchmen', '9780930289232', '1986-09-01', 416, 4.9, 2);

INSERT INTO authors_books (author_id, book_id)
VALUES (10, 14);

INSERT INTO book_genres (book_id, genre_id)
VALUES (14, 5);

INSERT INTO book_tags (book_id, tag_id)
VALUES (14, 7);

INSERT INTO books (book_title, isbn, published_date, total_pages, rating, publisher_id)
VALUES ( 'Batman: The Dark Knight Returns', '9781563893421', '1986-02-01', 224, 4.7, 2);

INSERT INTO authors_books (author_id, book_id)
VALUES (10, 15);

INSERT INTO book_genres (book_id, genre_id)
VALUES (15, 5);

INSERT INTO book_tags (book_id, tag_id)
VALUES (15, 7);

INSERT INTO books ( book_title, isbn, published_date, total_pages, rating, publisher_id)
VALUES ( 'Maus', '9780394747231', '1986-10-01', 296, 4.8, 2);

INSERT INTO authors_books (author_id, book_id)
VALUES (10, 16);

INSERT INTO book_genres (book_id, genre_id)
VALUES (16, 5);

INSERT INTO book_tags (book_id, tag_id)
VALUES (16, 7);

-- Insert Dan Brown books
INSERT INTO books ( book_title, isbn, published_date, total_pages, rating, publisher_id)
VALUES ( 'The Da Vinci Code', '9780307474278', '2003-03-18', 454, 4.3, 1);

INSERT INTO authors_books (author_id, book_id)
VALUES (9, 17);

INSERT INTO book_genres (book_id, genre_id)
VALUES (17, 1);

INSERT INTO book_tags (book_id, tag_id)
VALUES (17, 2);

INSERT INTO books (book_title, isbn, published_date, total_pages, rating, publisher_id)
VALUES ('Angels & Demons', '9780743493468', '2000-06-01', 616, 4.2, 1);

INSERT INTO authors_books (author_id, book_id)
VALUES (9, 18);

INSERT INTO book_genres (book_id, genre_id)
VALUES (18, 1);

INSERT INTO book_tags (book_id, tag_id)
VALUES (18, 2);

INSERT INTO books (book_title, isbn, published_date, total_pages, rating, publisher_id)
VALUES ( 'The Lost Symbol', '9780385504225', '2009-09-15', 639, 4.1, 1);

INSERT INTO authors_books (author_id, book_id)
VALUES (9, 19);

INSERT INTO book_genres (book_id, genre_id)
VALUES (19, 1);

INSERT INTO book_tags (book_id, tag_id)
VALUES (19, 2);

INSERT INTO books ( book_title, isbn, published_date, total_pages, rating, publisher_id)
VALUES ( 'Inferno', '9780804139364', '2013-05-14', 481, 4.0, 1);

INSERT INTO authors_books (author_id, book_id)
VALUES (9, 20);

INSERT INTO book_genres (book_id, genre_id)
VALUES (20, 1);

INSERT INTO book_tags (book_id, tag_id)
VALUES (20, 2);

INSERT INTO books (book_title, isbn, published_date, total_pages, rating, publisher_id)
VALUES ( 'Origin', '9780385514231', '2017-10-03', 480, 4.2, 1);

INSERT INTO authors_books (author_id, book_id)
VALUES (9, 21);

INSERT INTO book_genres (book_id, genre_id)
VALUES (21, 1);

INSERT INTO book_tags (book_id, tag_id)
VALUES (21, 2);

-- Insert "Ready Player One" book
INSERT INTO books ( book_title, isbn, published_date, total_pages, rating, publisher_id)
VALUES ( 'Ready Player One', '9780307887443', '2011-08-16', 374, 4.2, 1);

INSERT INTO authors_books (author_id, book_id)
VALUES (10, 22);

INSERT INTO book_genres (book_id, genre_id)
VALUES (22, 3);

INSERT INTO book_tags (book_id, tag_id)
VALUES (22, 4);


-- Insert Harry Potter and the Prisoner of Azkaban
INSERT INTO books (book_title, isbn, published_date, total_pages, rating, publisher_id)
VALUES ('Harry Potter and the Prisoner of Azkaban', '9780439136358', '1999-07-08', 448, 4.7, 1);

INSERT INTO authors_books (author_id, book_id)
VALUES (1, 23);

INSERT INTO book_genres (book_id, genre_id)
VALUES (23, 1);

INSERT INTO book_tags (book_id, tag_id)
VALUES (23, 1),
(23, 2);

-- Insert Harry Potter and the Goblet of Fire
INSERT INTO books (book_title, isbn, published_date, total_pages, rating, publisher_id)
VALUES ('Harry Potter and the Goblet of Fire', '9780439139601', '2000-07-08', 734, 4.8, 1);

INSERT INTO authors_books (author_id, book_id)
VALUES (1, 24);

INSERT INTO book_genres (book_id, genre_id)
VALUES (24, 1);

INSERT INTO book_tags (book_id, tag_id)
VALUES (24, 1),
(24, 2);

-- Insert Harry Potter and the Order of the Phoenix
INSERT INTO books (book_title, isbn, published_date, total_pages, rating, publisher_id)
VALUES ('Harry Potter and the Order of the Phoenix', '9780439358071', '2003-06-21', 870, 4.9, 1);

INSERT INTO authors_books (author_id, book_id)
VALUES (1, 25);

INSERT INTO book_genres (book_id, genre_id)
VALUES (25, 1);

INSERT INTO book_tags (book_id, tag_id)
VALUES (25, 1),
(25, 2);

-- Insert Harry Potter and the Half-Blood Prince
INSERT INTO books (book_title, isbn, published_date, total_pages, rating, publisher_id)
VALUES ('Harry Potter and the Half-Blood Prince', '9780439785969', '2005-07-16', 652, 4.8, 1);

INSERT INTO authors_books (author_id, book_id)
VALUES (1, 26);

INSERT INTO book_genres (book_id, genre_id)
VALUES (26, 1);

INSERT INTO book_tags (book_id, tag_id)
VALUES (26, 1),
(26, 2);

-- Insert Harry Potter and the Deathly Hallows
INSERT INTO books (book_title, isbn, published_date, total_pages, rating, publisher_id)
VALUES ('Harry Potter and the Deathly Hallows', '9780545010221', '2007-07-21', 607, 4.9, 1);

INSERT INTO authors_books (author_id, book_id)
VALUES (1, 27);

INSERT INTO book_genres (book_id, genre_id)
VALUES (27, 1);

INSERT INTO book_tags (book_id, tag_id)
VALUES (27, 1),
(27, 2);