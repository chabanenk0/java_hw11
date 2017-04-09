package com.github.chabanenk0.servlet;

import com.github.chabanenk0.servlet.Entity.Review;
import com.github.chabanenk0.servlet.Service.ReviewRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import static java.lang.System.exit;

public class MainServlet extends HttpServlet {
    // put here the real credentials for your database.
    // And create the database before:
    // CREATE DATABASE java_guestbook DEFAULT CHARACTER SET utf8;
    public static String DATABASE_NAME = "java_guestbook";
    public static String DATABASE_LOGIN = "root";
    public static String DATABASE_PASS = "111";

    private ReviewRepository reviewRepository;

    public MainServlet() {
        try {
            this.reviewRepository = new ReviewRepository(
                    MainServlet.DATABASE_NAME,
                    MainServlet.DATABASE_LOGIN,
                    MainServlet.DATABASE_PASS
            );
        } catch (SQLException e) {
            System.out.println("Error connecting to the database! Check your credentials!");
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        request.setAttribute("reviews", this.reviewRepository.getReviews());

        request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String message = request.getParameter("message");
        int rating = Integer.parseInt(request.getParameter("rating"));

        Review review = new Review(name, message, rating, "");

        try {
            this.reviewRepository.insertReview(review);
        } catch (SQLException e) {
            response.sendError(500, "Error inserting your review. Please try again later...");
            e.printStackTrace();
        }

        response.sendRedirect("/");
    }
}
