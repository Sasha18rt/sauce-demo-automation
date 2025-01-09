package com.sasha.data;

public enum Products {
    DEFAULT_PRODUCT("Sauce Labs Backpack", 29.99, "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection."),
    SECOND_PRODUCT("Sauce Labs Bike Light", 9.99, "A red light isn't the desired state in testing but it sure helps when riding your bike at night. Water-resistant with 3 lighting modes, 1 AAA battery included.");

    private final String name;
    private final double price;
    private final String description;

    private Products(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getDescription() { return description; }
}
