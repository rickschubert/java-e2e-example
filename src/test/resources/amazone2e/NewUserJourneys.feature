Feature: New User Journey

  Scenario: Search product and add to basket
    Given I am on the amazon "UK" "home" page
    And I search for "TV"
    And I get redirected to the category "TVs"
    And I select product number "2" from carousel number "1"
    When I add the product to the basket
    And I open the basket
    Then I see the product in my basket
