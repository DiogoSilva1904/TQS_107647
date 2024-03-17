package deti.lab5;

import static java.lang.invoke.MethodHandles.lookup;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.slf4j.LoggerFactory.getLogger;

import java.util.Date;
import org.slf4j.Logger;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.text.SimpleDateFormat;
public class BookSearchSteps {
    static final Logger log = getLogger(lookup().lookupClass());

    private Library library;

    @Given("a library with these books")
    public void a_library_with_these_books(io.cucumber.datatable.DataTable dataTable) {
        library = new Library();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dataTable.asLists().forEach(row -> {
            try{
                String title = row.get(0);
                String author = row.get(1);
                   Date year = parseDate(row.get(2));
                library.addBook(new Book(title, author, year));
            } catch (Exception e) {
                log.error("Error parsing date", e);
            }
        });
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


}
