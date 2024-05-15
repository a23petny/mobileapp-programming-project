package com.example.project;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("WeakerAccess")
public class Snack {

        private String image;
        private String title;
        private String emoji;
        private String healthLvl;
        @SerializedName("cost")
        private int price;
        @SerializedName("size")
        private int eta;

        private String location;
        private String ingredients;
        private String desc;

        public Snack(String image, String title, String emoji, String healthLvl, int price, int eta, String location, String ingredients, String desc) {
                this.image = image;
                this.title = title;
                this.emoji = emoji;
                this.healthLvl = healthLvl;
                this.price = price;
                this.eta = eta;
                this.location = location;
                this.ingredients = ingredients;
                this.desc = desc;
        }

        // Getters and Setters for each field
        public String getImage() {
                return image;
        }

        public void setImage(String image) {
                this.image = image;
        }

        public String getTitle() {
                return title;
        }

        public void setTitle(String title) {
                this.title = title;
        }

        public String getEmoji() {
                return emoji;
        }

        public void setEmoji(String emoji) {
                this.emoji = emoji;
        }

        public String getHealthLvl() {
                return healthLvl;
        }

        public void setHealthLvl(String healthLvl) {
                this.healthLvl = healthLvl;
        }

        public int getPrice() {
                return price;
        }

        public void setPrice(int price) {
                this.price = price;
        }

        public int getEta() {
                return eta;
        }

        public void setEta(int eta) {
                this.eta = eta;
        }

        public String getLocation() {
                return location;
        }

        public void setLocation(String location) {
                this.location = location;
        }

        public String getIngredients() {
                return ingredients;
        }

        public void setIngredients(String ingredients) {
                this.ingredients = ingredients;
        }

        public String getDesc() {
                return desc;
        }

        public void setDesc(String desc) {
                this.desc = desc;
        }

}
