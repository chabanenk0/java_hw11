package com.github.chabanenk0.servlet.Service;

import com.github.chabanenk0.servlet.Entity.Review;

import java.util.LinkedList;
import java.util.List;

public class ReviewRepository
{
    public List<Review> getReviews() {
        List<Review> reviews = new LinkedList<Review>();
        reviews.add(new Review("TestName Vasya", "Super site! Very useful", 1));
        return reviews;
    }
}
