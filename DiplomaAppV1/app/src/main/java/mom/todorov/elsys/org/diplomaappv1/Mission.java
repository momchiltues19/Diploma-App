package mom.todorov.elsys.org.diplomaappv1;

public class Mission {
    long missionId;
    String missionName;
    String requirements;
    String imageLink;

    Mission(long missionId, String missionName, String requirements, String imageLink) {
        this.missionId = missionId;
        this.missionName = missionName;
        this.requirements = requirements;
        this.imageLink = imageLink;
    }
}
