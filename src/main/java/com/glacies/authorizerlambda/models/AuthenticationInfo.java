package com.glacies.authorizerlambda.models;


import java.io.Serializable;

public class AuthenticationInfo implements Serializable {
    private String UUID;
    private String role;
    private String idToken;
    private String accessToken;
    private String refreshToken;

    private String username;
    private String storeUUID;

    public AuthenticationInfo(String UUID, String role, String idToken,
                              String accessToken, String refreshToken,
                              String username, String storeUUID) {
        this.UUID = UUID;
        this.role = role;
        this.idToken = idToken;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.username = username;
        this.storeUUID = storeUUID;
    }

    public AuthenticationInfo() {
    }

    public static AuthenticationInfoBuilder builder() {
        return new AuthenticationInfoBuilder();
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStoreUUID() {
        return storeUUID;
    }

    public void setStoreUUID(String storeUUID) {
        this.storeUUID = storeUUID;
    }

    public static class AuthenticationInfoBuilder {
        private String UUID;
        private String role;
        private String idToken;
        private String accessToken;
        private String refreshToken;
        private String username;
        private String storeUUID;

        AuthenticationInfoBuilder() {
        }

        public AuthenticationInfoBuilder UUID(String UUID) {
            this.UUID = UUID;
            return this;
        }

        public AuthenticationInfoBuilder role(String role) {
            this.role = role;
            return this;
        }

        public AuthenticationInfoBuilder idToken(String idToken) {
            this.idToken = idToken;
            return this;
        }

        public AuthenticationInfoBuilder accessToken(String accessToken) {
            this.accessToken = accessToken;
            return this;
        }

        public AuthenticationInfoBuilder refreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
            return this;
        }

        public AuthenticationInfoBuilder username(String username) {
            this.username = username;
            return this;
        }

        public AuthenticationInfoBuilder storeUUID(String storeUUID) {
            this.storeUUID = storeUUID;
            return this;
        }

        public AuthenticationInfo build() {
            return new AuthenticationInfo(this.UUID, this.role, this.idToken, this.accessToken, this.refreshToken, this.username, this.storeUUID);
        }

        public String toString() {
            return "AuthenticationInfo.AuthenticationInfoBuilder(UUID=" + this.UUID + ", role=" + this.role + ", idToken=" + this.idToken + ", accessToken=" + this.accessToken + ", refreshToken=" + this.refreshToken + ", username=" + this.username + ", storeUUID=" + this.storeUUID + ")";
        }
    }
}
