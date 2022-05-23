package com.cards.zokudo.entities;

import com.cards.zokudo.enums.Status;

import javax.persistence.*;

@Entity
@Table(name="users_has_program")
public class UsersHasProgram extends  AbstractEntity{

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id" , referencedColumnName ="id")
    private Users users;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="program_id" , referencedColumnName ="id")
    private Program program;

    @Enumerated(EnumType.STRING)
    private Status status;


    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
