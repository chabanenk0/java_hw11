package com.github.chabanenk0.servlet.Entity;

/**
 * Created by dmitry on 08.04.17.
 */
public class Review
{
    private String name;
    private String message;
    private int rating;

    public Review()
    {
    }

    public Review(String name, String message, int rating)
    {
        this.name = name;
        this.message = message;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String toString()
    {
        return "Name:" + name + ", Message:" + message + ", Rating:" + rating;
    }
}
