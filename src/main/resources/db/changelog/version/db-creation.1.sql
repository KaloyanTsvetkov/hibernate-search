CREATE TABLE publishers (
  publisher_id INT PRIMARY KEY,
  publisher_name VARCHAR(255)
);

-- Create the authors table
CREATE TABLE authors (
  author_id INT PRIMARY KEY,
  first_name VARCHAR(255),
  middle_name VARCHAR(255),
  last_name VARCHAR(255)
);

-- Create the books table
CREATE TABLE books (
  book_id INT PRIMARY KEY,
  book_title VARCHAR(255),
  isbn VARCHAR(13),
  published_date DATE,
  total_pages INT,
  rating DECIMAL(3, 1),
  publisher_id INT,
  FOREIGN KEY (publisher_id) REFERENCES publishers(publisher_id)
);

-- Create the authors_books junction table
CREATE TABLE authors_books (
  author_id INT,
  book_id INT,
  PRIMARY KEY (author_id, book_id),
  FOREIGN KEY (author_id) REFERENCES authors(author_id),
  FOREIGN KEY (book_id) REFERENCES books(book_id)
);

-- Create the tags table (unchanged)
CREATE TABLE tags (
  tag_id INT PRIMARY KEY,
  tag_name VARCHAR(255)
);

-- Create the genres table (unchanged)
CREATE TABLE genres (
  genre_id INT PRIMARY KEY,
  genre_name VARCHAR(255)
);

-- Create the book_tags junction table (unchanged)
CREATE TABLE book_tags (
  book_id INT,
  tag_id INT,
  PRIMARY KEY (book_id, tag_id),
  FOREIGN KEY (book_id) REFERENCES books(book_id),
  FOREIGN KEY (tag_id) REFERENCES tags(tag_id)
);

-- Create the book_genres junction table (unchanged)
CREATE TABLE book_genres (
  book_id INT,
  genre_id INT,
  PRIMARY KEY (book_id, genre_id),
  FOREIGN KEY (book_id) REFERENCES books(book_id),
  FOREIGN KEY (genre_id) REFERENCES genres(genre_id)
);