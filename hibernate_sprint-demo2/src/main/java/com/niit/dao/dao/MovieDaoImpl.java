package com.niit.dao.dao;

import com.niit.dao.model.Movie;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional

public class MovieDaoImpl implements MovieDao {
    @Autowired //Spring Container detects the object that implements this interface and inject its reference to object reference variable
    private SessionFactory sessionFactory;

    @Override
    public boolean saveMovie(Movie movie) {
        try {
            System.out.println("movie @ impl");
            System.out.println(movie);
            this.sessionFactory.getCurrentSession().saveOrUpdate(movie);
            return true;
        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Movie> getAllMovie() {
        return this.sessionFactory.getCurrentSession().createQuery("from Movie").list();
    }

    @Override
    public boolean deleteMovie(int movieId) {

        try {
            Movie nobj = this.sessionFactory.getCurrentSession().load(Movie.class, movieId);
            this.sessionFactory.getCurrentSession().delete(nobj);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Movie getMovieById(int id) {
        return this.sessionFactory.getCurrentSession().get(Movie.class,id);
    }

}
