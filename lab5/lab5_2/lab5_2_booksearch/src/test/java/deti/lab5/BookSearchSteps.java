package deti.lab5;

import static java.lang.invoke.MethodHandles.lookup;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.slf4j.LoggerFactory.getLogger;

import java.util.Date;
import org.slf4j.Logger;


import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

public class BookSearchSteps {
    static final Logger log = getLogger(lookup().lookupClass());

    private Library library;

    List<Book> result;

    @Given("a library with these books")
    public void a_library_with_these_books(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

        library = new Library();
        for (Map<String, String> book : rows) {
            library.addBook(new Book(book.get("title"), book.get("author"),
                   parseDate(book.get("year"))));
        }
        result= null;
    }

    private Date parseDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return dateFormat.parse(dateString);
        } catch (Exception e) {
            e.printStackTrace(); // Handle parsing exception appropriately
            return null; // Or throw an exception
        }
    }

    @When("the user search for a book with the title {string}")
    public void the_user_search_for_a_book_with_the_title(String title) {
       library.searchBookByTitle(title);
    }

    @Then("the book with the title {string} is found")
    public void the_book_with_the_title_is_found(String title) {
        log.debug("Title: {}", title, library.searchBookByTitle(title).getTitle());
        assertEquals(title, library.searchBookByTitle(title).getTitle());
    }

    @When("the user search for a book with the year {string}")
    public void the_user_search_for_a_book_with_the_year(String year) {
        Date date1 = parseDate("01/01/" + year);
        Date date2 = parseDate("31/12/" + year);
        library.findBooks(date1, date2);
    }

    @Then("the books with the year {string} are found")
    public void the_book_with_the_year_is_found(String year) {
        Date date1 = parseDate("01/01/" + year);
        Date date2 = parseDate("31/12/" + year);
        assertEquals(Integer.parseInt(year), library.findBooks(date1, date2).get(0).getPublished().getYear() + 1900);
    }


    @When("the user search for a book with the author {string}")
    public void the_user_search_for_a_book_with_the_author(String author) {
        library.getBooksByAuthor(author);
    }

    @Then("the books with the author {string} are found")
    public void the_books_with_the_author_are_found(String author) {
        log.debug("Author: {}", author, library.getBooksByAuthor(author).get(0).getAuthor());
        assertEquals(author, library.getBooksByAuthor(author).get(0).getAuthor());
    }
}
