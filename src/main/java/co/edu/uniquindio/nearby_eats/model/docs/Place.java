package co.edu.uniquindio.nearby_eats.model.docs;

import co.edu.uniquindio.nearby_eats.model.subdocs.Location;
import co.edu.uniquindio.nearby_eats.model.subdocs.Review;
import co.edu.uniquindio.nearby_eats.model.subdocs.Schedule;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "places")
@Getter
@Setter
@AllArgsConstructor
@ToString
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Place implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    private String name;
    private String description;
    private List<String> pictures;
    private List<Schedule> schedules;
    private List<String> phones;
    private List<String> categories;
    private Location location;
    private String status;
    private String createdBy;
    private List<Review> reviews;
    private String creationDate;
    private String deletionDate;
    private boolean open;
    private float score;

    public Place() {
        this.reviews = new ArrayList<>();
        this.pictures = new ArrayList<>();
        this.schedules = new ArrayList<>();
        this.phones = new ArrayList<>();
        this.categories = new ArrayList<>();
    }
}
