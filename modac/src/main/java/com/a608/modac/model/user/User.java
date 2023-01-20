package com.a608.modac.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/*
CREATE TABLE IF NOT EXISTS `modac`.`users` (
  `seq` INT NOT NULL,
  PRIMARY KEY (`seq`),
  INDEX `fk_users_memberships1_idx` (`memberships_seq` ASC) VISIBLE,
  CONSTRAINT `fk_users_memberships1`
    FOREIGN KEY (`memberships_seq`)
    REFERENCES `modac`.`membership` (`seq`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
 */
@Getter
@Entity
@ToString
@Table(name = "todos")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(name="email")
    private String email;

    @Column(name = "id")
    private String id;

    @Column(name="nickname")
    private String nickname;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "room_type")
    private String roomType;    //싱글룸 테마(우주....)

    @Column(name = "total_second")
    private Long totalSecond; //인생 총 공부시간

    @Column(name = "memberships_seq")
    private Long membershipsSeq;

    @Builder
    public User(final Long seq, final Long usersSeq, final String title, final Byte status, final String totalSecond, final Long categoriesSeq) {
        this.seq = seq;
        this.usersSeq = usersSeq;
        this.title = title;
        this.status = status;
        this.totalSecond = totalSecond;
        this.categoriesSeq = categoriesSeq;
    }

    public User() {
    }

    public void updateTodo(final String title, final Byte status, final String totalSecond){
        this.title = title;
        this.status = status;
        this.totalSecond = totalSecond;
    }

}
