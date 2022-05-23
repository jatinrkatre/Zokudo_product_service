package com.cards.zokudo.entities;

import com.cards.zokudo.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "login_logs", indexes = {
        @Index(name = "user_id", columnList = "user_id")
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginLogs extends AbstractEntity {

    @Column(name = "user_id")
    private long userId;

    @Enumerated(EnumType.STRING)
    private Status status;
}
