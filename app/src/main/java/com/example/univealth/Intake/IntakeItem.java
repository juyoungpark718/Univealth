package com.example.univealth.Intake;

public class IntakeItem {
    int foodID;
    String foodName;
    int foodKcal;

    public IntakeItem(int foodID, String foodName, int foodKcal) {
        this.foodID = foodID;
        this.foodName = foodName;
        this.foodKcal = foodKcal;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getFoodKcal() {
        return foodKcal;
    }

    public void setFoodKcal(int foodKcal) {
        this.foodKcal = foodKcal;
    }

    public int getFoodID() {return foodID; }

    public void setFoodID(int foodID) { this.foodID = foodID; }
}

