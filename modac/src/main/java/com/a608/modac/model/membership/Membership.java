package com.a608.modac.model.membership;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Entity
@ToString
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "membership")
public class Membership {

	@Id
	private String grade;

}
