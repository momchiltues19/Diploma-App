package mom.todorov.elsys.org.diplomaappv1;

class User {
    long userId;
    String username;
    String password;
    long avatarId;
    long roleId;
    long experiencePoints;
    long distanceWalked;


    User(long userId, String username, String password){
        this.userId=userId;
        this.username=username;
        this.password=password;
        this.avatarId = 1;
        this.roleId = 2;
        this.experiencePoints = 0;
        this.distanceWalked = 0;
    }

    User(long userId, String username, String password, long avatarId, long roleId, long experiencePoints, long distanceWalked) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.avatarId = avatarId;
        this.roleId = roleId;
        this.experiencePoints = experiencePoints;
        this.distanceWalked = distanceWalked;
    }
}




