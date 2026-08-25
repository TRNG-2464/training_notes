package com.revature.basics.model;

import lombok.*;
import org.springframework.stereotype.Component;

/*
 * This class showcases the power of Lombok
 *
 * With a few annotations, you can provide standard boilerplate code
 * for a Java Pojo or Spring Bean
 */
@Component
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BetterPojo {
    private Integer id;
    private String name;
    private boolean isActive;
}
