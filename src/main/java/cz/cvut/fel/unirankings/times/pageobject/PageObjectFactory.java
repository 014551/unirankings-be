package cz.cvut.fel.unirankings.times.pageobject;

import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Service;

/** Factory for the list of rankings page objects for timeshighereducation.com. */
@Service("thePageObjectFactory")
public class PageObjectFactory implements IPageObjectFactory {

  @Override
  public RankingsListPage createRankingsListPage(WebDriver driver, String year) {
    return new RankingsListPage(driver, year);
  }
}
