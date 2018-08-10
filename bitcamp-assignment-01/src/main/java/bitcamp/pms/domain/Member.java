package bitcamp.pms.domain;

public class Member {
    protected String id;
    protected String email;
    protected String password;
    protected String fax;
    protected String phone;
    protected String iPhone;
    protected String content;
    
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getFax() {
        return fax;
    }
    public void setFax(String fax) {
        this.fax = fax;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getIPhone() {
        return iPhone;
    }
    public void setIPhone(String iphone) {
        this.iPhone = iphone;
    }
    
    
}
