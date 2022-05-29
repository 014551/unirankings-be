package cz.cvut.fel.unirankings.times.pageobject;

import org.openqa.selenium.WebDriver;

public interface IPageObjectFactory {

  RankingsListPage createRankingsListPage(WebDriver driver, String year);
}
