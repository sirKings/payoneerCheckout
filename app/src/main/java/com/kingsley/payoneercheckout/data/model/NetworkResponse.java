package com.kingsley.payoneercheckout.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NetworkResponse {

    @SerializedName("networks")
    private Network network;

    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public class Network {

        @SerializedName("applicable")
        private List<Result> items;

        public List<Result> getItems() {
            return items;
        }

        public void setItems(List<Result> items) {
            this.items = items;
        }
    }
}
