package radio.core;

public class User extends PersistentObject<String> {
    private String name = null;
    private UserPermission permission = UserPermission.NONE;

    public String email = null;
    public String phone = null;

    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    String getKey() {
        return username;
    }
}
