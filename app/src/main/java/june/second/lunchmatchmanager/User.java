package june.second.lunchmatchmanager;

public class User {

    private boolean userApproval;
    private String userId;
    private String userPw;
    private String userName;
    private String userGender;
    private String userBirthday;
    private String userNickName;
    private String userNickComment;


    public User(boolean userApproval, String userId, String userPw, String userName, String userGender, String userBirthday, String userNickName, String userNickComment) {
        this.userApproval = userApproval;
        this.userId = userId;
        this.userPw = userPw;
        this.userName = userName;
        this.userGender = userGender;
        this.userBirthday = userBirthday;
        this.userNickName = userNickName;
        this.userNickComment = userNickComment;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(String userBirthday) {
        this.userBirthday = userBirthday;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public String getUserNickComment() {
        return userNickComment;
    }

    public void setUserNickComment(String userNickComment) {
        this.userNickComment = userNickComment;
    }


}
