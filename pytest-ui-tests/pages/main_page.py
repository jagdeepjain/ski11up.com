class MainPage:
    url = "https://google.co.in"

    def __init__(self, driver):
        super().__init__()
        self.driver = driver

    def go(self):
        self.driver.get(self.url)
