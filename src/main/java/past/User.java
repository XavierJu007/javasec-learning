public class User {
    private final Boolean access;
    private final String name;

    public User(Boolean access, String name) {
        this.access = access;
        this.name = name;
    }

    public Boolean getAccess() {
        return access;
    }

    public String getName() {
        return name;
    }
}
