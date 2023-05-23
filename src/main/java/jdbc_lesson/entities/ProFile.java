package jdbc_lesson.entities;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "people")
@ToString(exclude = "people")
@Builder
@Entity
@Table(name = "profile")
public class ProFile {
    @Id
    @Column(name = "people_id")
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "address")
    private String address;

    @Column(name = "placeOfBirthday")
    private String placeOfBirthday;

    @OneToOne
    @JoinColumn(name = "people_id")
    @MapsId
    private People people;

}
