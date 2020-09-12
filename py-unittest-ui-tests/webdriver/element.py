from selenium.webdriver.support.wait import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC


class Element:
    def __init__(self, driver, locator):
        super().__init__()
        self.driver = driver
        self.locator = locator
        self.find()

    def find(self):
        self.element = WebDriverWait(self.driver, 30).until(
            EC.visibility_of_element_located(locator=self.locator))

    def text_input(self, search_string):
        self.element.send_keys(search_string)

    def click(self):
        self.element = WebDriverWait(self.driver, 30).until(
            EC.element_to_be_clickable(locator=self.locator))
        self.element.click()
