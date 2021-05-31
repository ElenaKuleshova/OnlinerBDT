Feature: Onliner filter results

Scenario: Searching for tv models using filters

  Given User is on the Onliner home page
  * User opens 'Каталог' page
  * User selects 'Электроника' from catalog main page
  * User selects 'Телевидение' and open 'Телевизоры' from Electronica submenu
  When User selects filters on Televisions page
    | Производитель       | Samsung   |      |
    | Диагональ           | 40        | 50   |
    | Разрешение          | 1920x1080 |      |
    | цена в предложениях | до        | 1000 |
  Then search results satisfy all entered filters

