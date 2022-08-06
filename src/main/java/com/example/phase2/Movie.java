package com.example.phase2;

public class Movie extends Media {

    private String rating;

    public Movie() {
    }

    public Movie(String code,String title, int copiesAvailable, String rating) {
        super(code,title, copiesAvailable);
        setRating(rating);
    }
    public void setRating(String rating) throws IllegalArgumentException{
        if (rating.equals("AC") || rating.equals("DR") || rating.equals("HR"))
            this.rating = rating;
        else
            throw  new IllegalArgumentException("Invalid Rating");
    }
    public String getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "code='" + code + '\'' +
                ", title='" + title + '\'' +
                ", copiesAvailable=" + copiesAvailable +
                ", rating='" + rating + '\'' +
                '}';
    }
}