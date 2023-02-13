package com.a608.modac.model.article;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StatisticsResponse {
	private List<CountByDayOfWeek> daysOfWeekList;
	private List<CountByCategory> categoriesList;

	public StatisticsResponse(
		List<CountByDayOfWeek> daysOfWeekList,
		List<CountByCategory> categoriesList) {
		this.daysOfWeekList = daysOfWeekList;
		this.categoriesList = categoriesList;
	}

	@Getter
	@AllArgsConstructor
	@NoArgsConstructor
	public class CountByDayOfWeek {
		String dayOfWeek;
		String dateInfo;
		Integer countArticles;

	}

	@Getter
	@AllArgsConstructor
	@NoArgsConstructor
	public class CountByCategory {
		String categoryName;
		Integer countArticles;
	}
}
