package cz.cvut.fel.unirankings.times.pageobject;

import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Service;

@Service("thePageObjectFactory")
public class PageObjectFactory implements IPageObjectFactory {

  @Override
  public RankingsListPage createRankingsListPage(WebDriver driver, String year) {
    return new RankingsListPage(driver, year);
  }
}
