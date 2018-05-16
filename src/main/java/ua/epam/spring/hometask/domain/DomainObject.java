package ua.epam.spring.hometask.domain;

import lombok.*;

/**
 * @author Yuriy_Tkach
 */
@ToString
@NoArgsConstructor
@EqualsAndHashCode( exclude = {"id"})
public class DomainObject {

    @Setter
    @Getter
    private Long id;
}
