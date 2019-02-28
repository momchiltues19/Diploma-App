package mom.todorov.elsys.org.diplomaappv1;

public class Award {
    long awardId;
    String awardName;
    String requirements;
    String imageLink;

    Award(long awardId, String awardName, String requirements, String imageLink) {
        this.awardId = awardId;
        this.awardName = awardName;
        this.requirements = requirements;
        this.imageLink = imageLink;
    }
}
