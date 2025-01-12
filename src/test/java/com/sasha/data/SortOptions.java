package com.sasha.data;

public enum SortOptions {
    A_TO_Z("az","Name (A to Z)"),
    Z_TO_A("za","Name (Z to A)"),
    LOW_TO_HIGH("lohi","Price (low to high)"),
    HIGH_TO_LOW("hilo", "Price (high to low)");

    private String optionName;
    private String value;

    SortOptions(String value, String optionNam ) {
        this.optionName = optionName;
        this.value = value;
    }

    public String getOptionName() { return optionName; }

    public String getValue() { return value; }
}
