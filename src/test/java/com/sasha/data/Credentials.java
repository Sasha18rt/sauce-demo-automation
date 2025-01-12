package com.sasha.data;

public enum Credentials {
    STANDARD_USER("standard_user", "secret_sauce", ""),
    LOCKED_OUT_USER("locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."),
    PROBLEM_USER("problem_user", "secret_sauce", ""),
    PERFORMANCE_GLITCH_USER("performance_glitch_user", "secret_sauce", ""),
    ERROR_USER("error_user", "secret_sauce", ""),
    VISUAL_USER("visual_user", "secret_sauce", ""),
    INVALID_USER("invalid_user", "wrong_password", "Epic sadface: Username and password do not match any user in this service"),
    EMPTY_FIELDS("","","Epic sadface: Username is required");

    private final String username;
    private final String password;
    private final String expectedErrorMessage;

    Credentials(String username, String password, String expectedErrorMessage) {
        this.username = username;
        this.password = password;
        this.expectedErrorMessage = expectedErrorMessage;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getExpectedErrorMessage() {
        return expectedErrorMessage;
    }

    public boolean isLoginExpectedToSucceed() {
        return expectedErrorMessage.isEmpty();
    }
}
