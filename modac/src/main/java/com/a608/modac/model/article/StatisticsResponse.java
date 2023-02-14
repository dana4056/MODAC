package com.a608.modac.model.article;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StatisticsResponse {
	private List<CountByDayOfWeek> daysOfWeekList;
	private List<CountByCategory> categoriesList;

	@Getter
	@AllArgsConstructor
	@NoArgsConstructor
	public static class CountByDayOfWeek {
		String dayOfWeek;
		String dateInfo;
		Integer countArticles;

		public void plusCount(){
			this.countArticles++;
		}
	}

	@Getter
	@AllArgsConstructor
	@NoArgsConstructor
	public static class CountByCategory {
		String categoryName;
		Integer countArticles;

		public void plusCount(){
			this.countArticles++;
		}
	}
}
