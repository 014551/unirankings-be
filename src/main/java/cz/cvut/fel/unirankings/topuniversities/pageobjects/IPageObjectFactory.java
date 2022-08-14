package cz.cvut.fel.unirankings.topuniversities.pageobjects;

import org.openqa.selenium.WebDriver;

/** Factory for the list of rankings page objects. */
public interface IPageObjectFactory {

  /**
   * Creates new rankings' page object.
   *
   * @param driver to use for the rankings' page object
   * @param year of the rankings
   * @return page object
   */
  RankingsListPage createRankingsListPage(WebDriver driver, String year);
}
