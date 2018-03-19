package ep2.model;

public class User {

    private String login;
    private String filename;
    private byte[] photo;

    public User() {
    }

    public User(String login, String filename, byte[] photo) {
        this.login = login;
        this.filename = filename;
        this.photo = photo;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
}
