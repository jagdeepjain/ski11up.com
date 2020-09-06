from selenium.webdriver.common.by import By

from webdriver.element import Element
from webdriver.locator import Locator
from pages.main_page import MainPage


class GoogleLandingPage(MainPage):

    def get_search_box(self):
        locator = Locator(by=By.NAME, value='q')
        return Element(self.driver, locator)

    def get_search_button(self):
        locator = Locator(By.NAME, value='btnK')
        return Element(self.driver, locator)
