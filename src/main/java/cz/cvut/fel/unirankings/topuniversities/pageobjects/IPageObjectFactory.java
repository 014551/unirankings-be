package cz.cvut.fel.unirankings.topuniversities.pageobjects;

import org.openqa.selenium.WebDriver;

public interface IPageObjectFactory {

  RankingsListPage createRankingsListPage(WebDriver driver, String year);
}
