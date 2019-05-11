package radio.core;

public class User extends PersistentObject<String> {
    private String name = null;
    private UserPermission permission;

    private String email = null;
    private String phone = null;

    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String name, String email, String phone, UserPermission perm) {
        this.name = name;
        this.permission = perm;
        this.email = email;
        this.phone = phone;
        this.username = username;
        this.password = password;
    }

    @Override
    String getKey() {
        return username;
    }

    // Change for something else
    boolean canChangeBankingInfo() {
        return true;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getUsername() {
        return username;
    }

    public UserPermission getPermission() {
        return permission;
    }

    public void setPermission(UserPermission permission) {
        this.permission = permission;
    }
}
