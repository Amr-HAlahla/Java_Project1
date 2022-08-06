package com.example.phase2;

import java.util.ArrayList;

public interface MediaRentalInt {

    public void addCustomer(String id, String name, String address, String plan, String mobile);
    public void addMovie(String code, String title, int copiesAvailable, String rating);
    public void addGame(String code, String title, int copiesAvailable, double weight);
    public void addAlbum(String code, String title, int copiesAvailable, String artist, String songs);
    public void setLimitedPlanLimit(int value);
    public String getAllCustomersInfo();
    public String getAllMediaInfo();
    public boolean addToCart(String customerName, String mediaTitle);
    public boolean removeFromCart(String customerName, String mediaTitle);
    public String processRequests();
    public boolean returnMedia(String customerName, String mediaTitle);
    public ArrayList<String> searchMedia(String title, String rating, String artist, String songs);

}
