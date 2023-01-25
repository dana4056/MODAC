package com.a608.modac.model.category;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Category {

	@Id
	private String name;

	@Column(name = "template_filepath")
	private String templateFilepath;
}
