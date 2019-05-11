package radio.transfer;

import radio.core.User;
import radio.core.UserPermission;

public class UserTransfer {
    public final String name;
    public final String username;
    public final String password;
    public final String email;
    public final String phoneNumber;
    public final UserPermission permission;

    public UserTransfer(String name, String username, String password, String email, String phoneNumber, UserPermission permission) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.permission = permission;
    }

    public UserTransfer(User u) {
        this.name = u.getName();
        this.username = u.getUsername();
        this.email = u.getEmail();
        this.phoneNumber = u.getPhone();
        this.password = null;
        this.permission = u.getPermission();
    }
}
