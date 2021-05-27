package org.example.theCatApiTest.utils;

public class Attach {
    private String bread;
    private String image;
    private String url;

    public void setBread(String bread) {
        this.bread = bread;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Attach{" +
                "bread='" + bread + '\'' +
                ", image='" + image + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}

