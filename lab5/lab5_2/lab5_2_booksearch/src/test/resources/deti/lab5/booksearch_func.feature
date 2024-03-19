@booksearch_sample
Feature: Book Search
  Allow a costumer to search for books

  Background: A book search
    Given a library with these books
        | title          | author       | year |
        | The Hobbit     | J.R.R. Tolkien | 12/2/1937 |
        | The Fellowship of the Ring | J.R.R. Tolkien | 13/4/1954 |
        | The Two Towers | J.R.R. Tolkien | 15/6/1954 |
        | The Return of the King | J.R.R. Tolkien | 26/10/1955 |
        | The Catcher in the Rye | J.D. Salinger | 11/9/1951 |
        | The Great Gatsby | F. Scott Fitzgerald | 09/12/1925 |
        | To Kill a Mockingbird | Harper Lee | 12/3/1960 |
        | 1984 | George Orwell | 17/6/1949 |
        | Animal Farm | George Orwell | 26/5/1945 |

  Scenario: Search for a book by title
    When the user search for a book with the title 'The Hobbit'
    Then the book with the title 'The Hobbit' is found

  Scenario: Search for a book by year
    When the user search for a book with the year '1954'
    Then the books with the year '1954' are found

  Scenario: Search for a book by author
    When the user search for a book with the author 'George Orwell'
    Then the books with the author 'George Orwell' are found
