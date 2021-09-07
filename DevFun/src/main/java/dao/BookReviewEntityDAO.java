package dao;

import entity.BookReviewEntity;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

public class BookReviewEntityDAO extends AbstractDAO<BookReviewEntity> {

    public BookReviewEntityDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public BookReviewEntity find(String id) {

    }

}
