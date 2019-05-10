package radio.core.users;

import java.util.Optional;

public class User {
    public UserPermission permission;
    public String name;
    public String username, password;

    // FIXME(borja): Optional is not serializable
    public Optional<String> email = Optional.empty();
    // FIXME(borja): Optional is not serializable
    public Optional<String> phone = Optional.empty();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
