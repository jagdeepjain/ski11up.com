from selenium import webdriver
from pages.google_landing_page import GoogleLandingPage


def test_google_search():
    browser = webdriver.Chrome()
    glp = GoogleLandingPage(driver=browser)

    glp.go()
    glp.get_search_box().text_input('covid19')
    glp.get_search_button().click()
    browser.close()
