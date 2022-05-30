package sg.edu.np.mad.practical3;

import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private String password;
    private String description;
    private int id;
    private boolean followed;
    public User(){
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {return password;}

    public void setPassword(String password) { this.password = password; }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isFollowed() {
        return followed;
    }

    public void setFollowed(boolean followed) {
        this.followed = followed;
    }
}
