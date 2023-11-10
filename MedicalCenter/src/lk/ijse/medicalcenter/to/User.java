package lk.ijse.medicalcenter.to;

public class User {
    private String employeeId;
    private String role;
    private String userName;
    private String password;

    public User() {
    }

    public User(String employeeId, String role, String userName, String password) {
        this.employeeId = employeeId;
        this.role = role;
        this.userName = userName;
        this.password = password;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
