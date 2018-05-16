package ua.epam.spring.hometask.domain;

import lombok.*;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

/**
 * @author Yuriy_Tkach
 */
@Setter
@Getter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Auditorium extends DomainObject {

    private String name;
    private long numberOfSeats;
    private Set<Long> vipSeats = Collections.emptySet();


    /**
     * Counts how many vip seats are there in supplied <code>seats</code>
     *
     * @param seats Seats to process
     * @return number of vip seats in request
     */
    public long countVipSeats(Collection<Long> seats) {
        return seats.stream().filter(seat -> vipSeats.contains(seat)).count();
    }

}
