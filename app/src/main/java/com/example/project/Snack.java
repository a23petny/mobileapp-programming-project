package com.example.project;

import com.google.gson.annotations.SerializedName;

import java.lang.ref.SoftReference;
@SuppressWarnings("WeakerAccess")
public class Snack {

        private String image;
        private String title;
        private char emoji;
        private String healthLvl;
        @SerializedName("cost")
        private int price;
        @SerializedName("size")
        private int eta;

        private String location;
        private String ingredients;
        private String desc;

        public Snack(String title) {
                this.title = title;
        }

        public String getTitle(){
            return this.title;
        }
}
