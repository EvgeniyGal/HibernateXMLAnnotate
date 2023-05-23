package jdbc_lesson.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"phoneNumbers", "proFile"})
@ToString(exclude = "phoneNumbers")
@Builder
@Entity
@Table(name = "people")
public class People implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "age")
    private Integer age;

    @Column(name = "name")
    private String name;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Builder.Default
    @OneToMany(mappedBy = "people", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PhoneNumber> phoneNumbers = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private ProFile proFile;

}
