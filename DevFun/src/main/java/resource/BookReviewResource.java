package resource;

import dao.BookReviewEntityDAO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Date;

@Path("/book")
@Produces(MediaType.TEXT_PLAIN)
public class BookReviewResource {

    private final BookReviewEntityDAO bookReviewEntityDAO;

    public BookReviewResource(BookReviewEntityDAO bookReviewEntityDAO) {
        this.bookReviewEntityDAO = bookReviewEntityDAO;
    }

    @GET
    @Path("/healthCheck")
    public String healthCheck() {
        return "Ping received at " + new Date();
    }



}
