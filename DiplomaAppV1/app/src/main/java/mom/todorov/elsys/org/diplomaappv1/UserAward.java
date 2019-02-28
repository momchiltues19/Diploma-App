package mom.todorov.elsys.org.diplomaappv1;

public class UserAward {
    long userAwardId;
    long userId;
    long awardId;

    UserAward(long userAwardId, long userId, long awardId) {
        this.userAwardId = userAwardId;
        this.userId = userId;
        this.awardId = awardId;
    }
}
