package mom.todorov.elsys.org.diplomaappv1;

public class UserPlace {
    long userPlaceId;
    long userId;
    long placeId;

    UserPlace(long userPlaceId, long userId, long missionId) {
        this.userPlaceId = userPlaceId;
        this.userId = userId;
        this.placeId = missionId;
    }
}
