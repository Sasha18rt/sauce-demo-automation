package com.sasha.data;

public enum Products {
    DEFAULT_PRODUCT("Sauce Labs Backpack", 29.99, "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection."),
    SECOND_PRODUCT("Sauce Labs Bike Light", 9.99, "A red light isn't the desired state in testing but it sure helps when riding your bike at night. Water-resistant with 3 lighting modes, 1 AAA battery included."),
    LAST_PRODUCT("Test.allTheThings() T-Shirt (Red)", 15.99, "This classic Sauce Labs t-shirt is perfect to wear when cozying up to your keyboard to automate a few tests. Super-soft and comfy ringspun combed cotton."),
    CHEAPEST_PRODUCT("Sauce Labs Onesie", 7.99, "Rib snap infant onesie for the junior automation engineer in development. Reinforced 3-snap bottom closure, two-needle hemmed sleeves and bottom won't unravel."),
    MOST_EXPENSIVE_PRODUCT("Sauce Labs Fleece Jacket", 49.99, "It's not every day that you come across a midweight quarter-zip fleece jacket capable of handling everything from a relaxing day outdoors to a busy day at the office.");

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
