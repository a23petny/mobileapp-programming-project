package com.example.project;

import com.google.gson.annotations.SerializedName;

public class Snack {
        private String name;
        @SerializedName("cost")
        private int feet;
        @SerializedName("size")
        private int meter;

        public String getName(){
            return this.name;
        }
}
