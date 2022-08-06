package com.example.phase2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MediaRentalManager implements MediaRentalInt {
    private int limitedPlanMax;
    protected List<Customer> customerDatabase;
    protected List<Media> mediaDatabase;

    public MediaRentalManager() {
        this.limitedPlanMax = 2;
        this.customerDatabase = new ArrayList<Customer>();
        this.mediaDatabase = new ArrayList<Media>();
    }

    @Override
    public void addAlbum(String code, String title, int copiesAvailable,
                         String artist, String songs) {
        mediaDatabase.add(new Album(code, title, copiesAvailable, artist, songs));
    }

    @Override
    public void addCustomer(String id, String name, String address, String plan, String mobile) {
        customerDatabase.add(new Customer(id, name, address, plan, mobile));
    }

    @Override
    public void addMovie(String code, String title, int copiesAvailable, String rating) {
        mediaDatabase.add(new Movie(code, title, copiesAvailable, rating));
    }

    @Override
    public void addGame(String code, String title, int copiesAvailable, double weight) {
        mediaDatabase.add(new Game(code, title, copiesAvailable, weight));
    }

    @Override
    public boolean addToCart(String customerId, String mediaCode) {
        // if media already exists in queue, return false; else add to queue

        int index1 = -1;
        for (int i = 0; i < customerDatabase.size(); i++) {
            if (customerDatabase.get(i).getId().equals(customerId)) {
                index1 = i;
            }
        }
        if (index1 == -1) {
            return false;
        }
        for (int i = 0; i < customerDatabase.get(index1).requests.size(); i++) {
            if (customerDatabase.get(index1).requests.get(i).equals(mediaCode)) {
                return false;
            }
        }
        customerDatabase.get(index1).requests.add(mediaCode);
        return true;
    }

    @Override
    public String getAllCustomersInfo() {

        String customerInfo = "***** Customers' Information *****\n";
        Collections.sort(customerDatabase);

        for (int i = 0; i < customerDatabase.size(); i++) {
            customerInfo += customerDatabase.get(i).toString() + "\n";
        }
        return customerInfo;
    }

    @Override
    public String getAllMediaInfo() {
        String mediaInfo = "-----> Media Information <------\n";
        Collections.sort(mediaDatabase);
        for (int i = 0; i < mediaDatabase.size(); i++) {
            mediaInfo += mediaDatabase.get(i).toString() + "\n";
        }
        return mediaInfo;
    }

    @Override
    public String processRequests() {
        String processedRequests = "";
        Collections.sort(customerDatabase);
        // iterate over customers
        for (Customer customer : customerDatabase) {
            int limit = limitedPlanMax;
            Collections.sort(customer.requests);
            Collections.sort(customer.rented);
            for (Media media : mediaDatabase) {
                if (customer.getPlan().equals("LIMITED") && limit > 0) {
                    for (int i = 0; i < customer.requests.size(); i++) {
                        if (customer.requests.get(i).equals(media.getCode())) {
                            processedRequests += "Sending " + media.getTitle() + " to " + customer.getName() + "\n";
                            customer.requests.remove(i);
                            customer.rented.add(media.getCode());
                            media.adjustCopiesAvailable(false);
                            limit--;
                        }
                    }

                } else if (customer.getPlan().equals("UNLIMITED")) {
                    for (int i = 0; i < customer.requests.size(); i++) {
                        if (customer.requests.get(i).equals(media.getCode())) {
                            processedRequests += "Sending " + media.getTitle() + " to " + customer.getName() + "\n";
                            customer.requests.remove(i);
                            customer.rented.add(media.getCode());
                            media.adjustCopiesAvailable(false);
                        }
                    }
                }
                Collections.sort(customer.requests);
                Collections.sort(customer.rented);
            }

        }

        return processedRequests;
    }

    @Override
    public boolean removeFromCart(String customerId, String mediaCode) {
        // return false if customerName not found
        for (int i = 0; i < customerDatabase.size(); i++) {
            if (customerDatabase.get(i).getId().equals(customerId)) {
                customerDatabase.get(i).requests.remove(mediaCode);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean returnMedia(String customerId, String mediaCode) {
        // return false if mediaCode is not found in rented
        // first check if customer exists in database
        int index = -1;
        for (int i = 0; i < customerDatabase.size(); i++) {
            if (customerDatabase.get(i).getId().equals(customerId)) {
                index = i;
            }
        }
        if (index == -1) {
            return false;
        }
        Customer c = customerDatabase.get(index);
        // then check if customer has mediaTitle in rented
        for (int i = 0; i < c.rented.size(); i++) {
            if (c.rented.get(i).equals(mediaCode)) {
                c.rented.remove(mediaCode);
                for (int j = 0; j < mediaDatabase.size(); j++) {
                    if (mediaDatabase.get(j).getCode().equals(mediaCode)) {
                        mediaDatabase.get(j).adjustCopiesAvailable(true);
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public ArrayList<String> searchMedia(String code, String rating, String artist, String songs) {
        ArrayList<String> searchedMedia = new ArrayList<String>();
        for (Media media : mediaDatabase) {
            if (code == null && rating == null && artist == null && songs == null)
                searchedMedia.add(media.getCode());
            else if (media instanceof Movie) {
                Movie movie = (Movie) media;
                if ((code == movie.getCode() && rating == movie.getRating())
                        || (code == null && rating == movie.getRating())
                        || (rating == null && code == movie.getCode()))
                    searchedMedia.add(media.getTitle());
            } else if (media instanceof Album) {
                Album album = (Album) media;
                if ((code == album.getCode() && processGivenSongs(album))
                        || (code == null && processGivenSongs(album))
                        || (code == album.getCode() && album.getSongs() == null))
                    searchedMedia.add(album.getCode());
            } else if (media instanceof Game) {
                Game game = (Game) media;
                if (code == game.getCode())
                    searchedMedia.add(game.getCode());
            }
        }
        Collections.sort(searchedMedia);// to just sort media code that match the search criteria -thankfully strings
        // are comparable
        return searchedMedia;
    }


    @Override
    public void setLimitedPlanLimit(int value) {
        this.limitedPlanMax = value;
    }

    private boolean processGivenSongs(Album album) {
        int checker = 0;
        String songs = album.getSongs();
        String[] songList = songs.split(",");
        for (int i = 0; i < songList.length; i++) {
            if (album.getSongs().contains(songList[i]))
                checker += 1;
        }
        if (checker == songList.length)// all songs in the search must exist in the given album
            return true;
        else
            return false;
    }

    public int searchCustomer(String customerId) {

        int index = -1;
        for (int i = 0; i < customerDatabase.size(); i++) {
            if (customerDatabase.get(i).getId().equals(customerId))
                index = i;
        }
        return index;
    }


    public boolean deleteCustomer(String customerId) {

        int index = searchCustomer(customerId);

        if (index != -1) {
            customerDatabase.remove(index);
            return true;
        } else
            return false;
    }

    public boolean updateCustomer(String customerId, String newName, String newAddress,
                                  String newPlan, String newMobile) {
        int i = searchCustomer(customerId);

        if (i != -1) {
            Customer customer = customerDatabase.get(i);
            if (newName.length() > 0)
                customer.setName(newName);
            if (newAddress.length() > 0)
                customer.setAddress(newAddress);
            if (newPlan.length() > 0)
                customer.setPlan(newPlan);
            if (newMobile.length() > 0)
                customer.setMobile(newMobile);
            return true;
        } else
            return false;
    }

    public boolean deleteMedia(String mediaCode) {
        for (int i = 0; i < mediaDatabase.size(); i++) {
            if (mediaDatabase.get(i).getCode().equals(mediaCode)) {
                mediaDatabase.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean updateMedia(String customerId, String newName, String newAddress,
                               String newPlan, String newMobile) {
        int i = searchCustomer(customerId);

        if (i != -1) {
            Customer customer = customerDatabase.get(i);
            if (newName.length() > 0)
                customer.setName(newName);
            if (newAddress.length() > 0)
                customer.setAddress(newAddress);
            if (newPlan.length() > 0)
                customer.setPlan(newPlan);
            if (newMobile.length() > 0)
                customer.setMobile(newMobile);
            return true;
        } else
            return false;
    }
}

