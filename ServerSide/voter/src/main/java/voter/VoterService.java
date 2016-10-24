package voter;

public class VoterService {

    private String userType;
    private String idNum;
    private String password;
    private String name;
    private String surname;
    private String locationRegistered;
    private String cellphone;
    private String email;

    public VoterService() {

    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLocationRegistered() {
        return locationRegistered;
    }

    public void setLocationRegistered(String locationRegistered) {
        this.locationRegistered = locationRegistered;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public VoterService(String userType, String idNum, String password, String name, String surname, String locationRegistered, String cellphone, String email) {
        this.userType = userType;
        this.idNum = idNum;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.locationRegistered = locationRegistered;
        this.cellphone = cellphone;
        this.email = email;
    }

    public Boolean register(String userType, String idNum, String password, String name, String surname, String locationRegistered, String cellphone, String email) {

        if (idNum == null || idNum.length() != 13) {
            return false;
        }

        return true;
    }

    public VoterService login() {
        return null;
    }

    Boolean castNationalVote() {
        return false;
    }

    Boolean castProvincialVote() {
        return false;
    }
}
