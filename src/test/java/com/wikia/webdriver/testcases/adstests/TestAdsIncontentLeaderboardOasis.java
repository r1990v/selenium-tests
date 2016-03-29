package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;

import org.testng.annotations.Test;

public class TestAdsIncontentLeaderboardOasis extends TemplateNoFirstLoad {

  private static final String URL_PARAM_ENABLE_SITEWIDE =
      "InstantGlobals.wgAdDriverIncontentLeaderboardSlotCountries=[XX]";

  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "adsIncontentLeaderboard",
      groups = "AdsIncontentLeaderboard"
  )
  public void adsIncontentLeaderboard(String wikiName,
                                String article,
                                int lineItemId,
                                int slotWidth,
                                int slotHeight) {

    String url = urlBuilder.getUrlForPath(wikiName, article);
    url = urlBuilder.appendQueryStringToURL(url, URL_PARAM_ENABLE_SITEWIDE);

    new AdsBaseObject(driver, url)
        .triggerIncontentLeaderboard()
        .verifyLineItemId(AdsContent.INCONTENT_LEADERBOARD, lineItemId)
        .verifyIframeSize(AdsContent.INCONTENT_LEADERBOARD, "gpt", slotWidth, slotHeight);
  }
}