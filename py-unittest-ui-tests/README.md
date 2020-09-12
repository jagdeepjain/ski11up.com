# Python unittest For UI Testing

## Install Dependencies

`unittest` comes with standard `python` packaging so no need to install separately.

### Selenium

`$ pip3 install selenium`

## Execute Test

Remember that `python3` does not support relative path and we need to run `unittest` more like `python` modules with `class` qualifier.

`$ python3 -m unittest tests.test_google_search.TestGoogleSearch`
