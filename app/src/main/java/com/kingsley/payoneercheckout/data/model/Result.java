package com.kingsley.payoneercheckout.data.model;

import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("label")
    private String name;

    @SerializedName("links")
    private Links links;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }


    public class Links {
        @SerializedName("logo")
        private String logo;

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }
    }
}
