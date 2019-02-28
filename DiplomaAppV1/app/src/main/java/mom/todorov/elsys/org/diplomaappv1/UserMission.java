package mom.todorov.elsys.org.diplomaappv1;

public class UserMission {
    long userMissionId;
    long userId;
    long missionId;

    UserMission(long userAwardId, long userId, long missionId) {
        this.userMissionId = userAwardId;
        this.userId = userId;
        this.missionId = missionId;
    }

}
