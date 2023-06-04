ALTER TABLE authors
alter author_id add generated always as identity;

ALTER TABLE genres
alter genre_id add generated always as identity;

ALTER TABLE tags
alter tag_id add generated always as identity;

ALTER TABLE publishers
alter publisher_id add generated always as identity;

ALTER TABLE books
alter book_id add generated always as identity;