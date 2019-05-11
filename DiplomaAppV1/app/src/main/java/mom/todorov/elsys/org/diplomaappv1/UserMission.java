package mom.todorov.elsys.org.diplomaappv1;

public class UserMission {
    long userMissionId;
    long userId;
    long missionId;

    UserMission(long userMissionId, long userId, long missionId) {
        this.userMissionId = userMissionId;
        this.userId = userId;
        this.missionId = missionId;
    }

}
