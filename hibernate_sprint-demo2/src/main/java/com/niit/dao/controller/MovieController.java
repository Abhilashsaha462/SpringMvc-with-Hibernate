package com.niit.dao.controller;

import com.niit.dao.dao.MovieDao;
import com.niit.dao.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")

public class MovieController {
    @Autowired
    private MovieDao movieDao;

    @GetMapping("/")
    public ModelAndView getHomePage()
    {
        System.out.println("I am at Home Page");
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("movie", new Movie());
        modelAndView.addObject("movieList",movieDao.getAllMovie());
        return modelAndView;
    }

    @PostMapping("/saveMovie")
    public String saveMovie(@ModelAttribute("movie") Movie movie, ModelMap modelMap)
    {
        boolean status = movieDao.saveMovie(movie);
        modelMap.addAttribute("movieList",movieDao.getAllMovie());
        return "redirect:/";
    }

    @GetMapping("/deletemovie/{movieId}")
    public String deleteMovie(@PathVariable("movieId") int movieId)
    {
        movieDao.deleteMovie(movieId);
        return "redirect:/";
    }

    @GetMapping("/edit/{nid}")
    public ModelAndView editMovie(@PathVariable int nid)
    {
        ModelAndView mv = new ModelAndView("index");
        System.out.println("Received id:"+nid);
        Movie movie = movieDao.getMovieById(nid);
        System.out.println(movie);
        mv.addObject("movie",movie);
        mv.addObject("movieList",movieDao.getAllMovie());
        return mv;
    }
}
