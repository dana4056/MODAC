package com.a608.modac.model.membership;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Entity
@ToString
// @DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
public class Membership {

	@Id
	private String grade;

}
