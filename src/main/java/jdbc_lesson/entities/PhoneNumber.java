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
@Table(name = "phone_number")
public class PhoneNumber implements BaseEntity<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "number")
    private String number;

    @Enumerated(EnumType.STRING)
    @Column(name = "phone_type")
    private PhoneType phoneType;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    private People people;
}
