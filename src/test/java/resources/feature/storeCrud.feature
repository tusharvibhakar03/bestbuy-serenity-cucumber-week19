Feature:BestBuy Application
  As a user I want to test BestBuy Application

  Scenario Outline: CRUD Test
    Given Check if the BestBuy application can be accessed by users
    When I create a new Store by providing the information name "<name>", address "<address>", city "<city>", state "<state>", zip "<zip>", type  "<type>", address- "<address2>", lat "<lat>",lng "<lng>",hours "<hours>",services "<services>"
    Then I verify that the store  with lat "<lat>" is created
    And I update a store with storeId and give information storeId "39" name "viral1", address "testing", city "southAmerica", state "Newyork", zip "we3wr", type  "abcd", address- "highway", lat "20",lng "10",hours "11:11",services "LMNO"
    Then I verify that the store with id storeId "39" is updated
    And I delete store with store Id
    Then I verify using  store id  that  data is deleted
    Examples:
      | name     | address       | city | state | zip   | type | address2 | lat | lng | hours | services |
      | viral121 | prime Testing | abcd | auto  | 102df | best | avenue   | 10  | 100 | 10:15 | tesla    |
