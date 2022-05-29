package cz.cvut.fel.unirankings.topuniversities.pageobjects;

import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Service;

@Service("qsPageObjectFactory")
public class PageObjectFactory implements IPageObjectFactory {

  @Override
  public RankingsListPage createRankingsListPage(WebDriver driver, String year) {
    return new RankingsListPage(driver, year);
  }
}
