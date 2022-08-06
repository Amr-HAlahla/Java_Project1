package com.example.phase2;

public abstract class Media implements Comparable<Media> {

    protected String code;
    protected String title;
    protected int copiesAvailable;

    public Media() {

        this.title = "";
        this.copiesAvailable = 0;
        this.code = "";

    }

    public Media(String title) {

        this.title = title;
    }

    public Media(String code, String title, int copiesAvailable) {

        this.code = code;
        this.title = title;
        this.copiesAvailable = copiesAvailable;

    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCopiesAvailable(int copiesAvailable) {
        this.copiesAvailable = copiesAvailable;
    }

    public String getTitle() {
        return title;
    }

    public int getCopiesAvailable() {
        return copiesAvailable;
    }

    public String getCode() {
        return code;
    }

    public void adjustCopiesAvailable(boolean add) {

        if (add) {
            this.copiesAvailable++;
        } else {
            this.copiesAvailable--;
        }
    }

    @Override
    public int compareTo(Media o) {

        return this.getCode().compareTo(o.getCode());
    }

}
