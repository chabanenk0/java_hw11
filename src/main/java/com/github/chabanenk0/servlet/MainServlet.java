package com.github.chabanenk0.servlet;

import com.github.chabanenk0.servlet.Entity.Review;
import com.github.chabanenk0.servlet.Service.ReviewRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MainServlet extends HttpServlet {
    private ReviewRepository reviewRepository;

    public MainServlet() {
        this.reviewRepository = new ReviewRepository();
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

        Review review = new Review(name, message, rating);

        PrintWriter writer = response.getWriter();
        writer.print("review:" + review.toString());
    }
}
