import unittest

from selenium import webdriver
from pages.google_landing_page import GoogleLandingPage


class TestGoogleSearch(unittest.TestCase):

    def setUp(self):
        print('setting up browser...')
        self.browser = webdriver.Chrome()
        self.glp = GoogleLandingPage(driver=self.browser)

    def test_google_search(self):
        self.glp.go()
        self.glp.get_search_box().text_input('covid19')
        self.glp.get_search_button().click()

    def tearDown(self):
        print('cleaning up browser...')
        self.browser.close()


if __name__ == '__main__':
    unittest.main()
