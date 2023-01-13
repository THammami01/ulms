CREATE TABLE IF NOT EXISTS Book (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    title TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS Subscriber (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    fullname TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS Loan (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    bookId INTEGER NOT NULL,
    subscriberId INTEGER NOT NULL,
	FOREIGN KEY(bookId) REFERENCES Book(id) ON DELETE CASCADE,
	FOREIGN KEY(subscriberId) REFERENCES Subscriber(id) ON DELETE CASCADE
);

INSERT INTO Book(title) VALUES("Book 01"), ("Book 02"), ("Book 03");
INSERT INTO Subscriber(fullname) VALUES("X1 Y1"), ("X2 Y2"), ("X3 Y3");
INSERT INTO Loan(bookId, subscriberId) VALUES(1, 1), (2, 2);

SELECT * FROM Book;
SELECT * FROM Subscriber;
SELECT * FROM Loan;

-- LOANED BOOKS; WITHOUT JOINING THE SUBSCRIBER
SELECT B.id as bookId, B.title as bookTitle
FROM Loan AS L, Book as B
WHERE L.bookId = B.id;

-- LOANED BOOKS; BY JOINING THE SUBSCRIBER
SELECT B.id as bookId, B.title as bookTitle, S.id AS subscriberId, S.fullname AS subscriberFullname
FROM Loan AS L, Book as B, Subscriber as S
WHERE L.bookId = B.id AND L.subscriberId = S.id;

-- AVAILABLE BOOKS
SELECT id as bookId, title as bookTitle
FROM Book
WHERE id NOT IN (SELECT bookId FROM Loan);
