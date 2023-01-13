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

CREATE TABLE IF NOT EXISTS Settings (
	label TEXT PRIMARY KEY,
	value TEXT NOT NULL
);

INSERT INTO Book(title) VALUES("Book 01"), ("Book 02"), ("Book 03");
INSERT INTO Subscriber(fullname) VALUES("X1 Y1"), ("X2 Y2"), ("X3 Y3");
INSERT INTO Loan(bookId, subscriberId) VALUES(1, 1), (2, 2);
INSERT INTO Settings VALUES("language", "french");

SELECT * FROM Book;
SELECT * FROM Subscriber;
SELECT * FROM Loan;