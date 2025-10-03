package za.ac.cput.domain.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Builder
public class Role {
    @Id
    private String description;

    private Role(String description) {
        this.description = description;
    }
}
