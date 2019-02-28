package mom.todorov.elsys.org.diplomaappv1;

public class Place {
    long placeId;
    String placeName;
    String description;
    String imageLink;
    int level;
    long typeId;
    String coordinates;

    Place (long placeId, String placeName, String description, String imageLink,
           int level, long typeId, String coordinates) {
        this.placeId = placeId;
        this.placeName = placeName;
        this.description = description;
        this.imageLink = imageLink;
        this.level = level;
        this.typeId = typeId;
        this.coordinates = coordinates;
    }
}
