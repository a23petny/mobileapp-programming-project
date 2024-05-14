package com.example.project;

import com.google.gson.annotations.SerializedName;

import java.lang.ref.SoftReference;

public class Snack {

        private String image;
        private String name;
        private char emoji;
        private String healthLvl;
        @SerializedName("cost")
        private int price;
        @SerializedName("size")
        private int eta;

        private String location;
        private String ingredients;
        private String desc;

        public String getName(){
            return this.name;
        }
}
