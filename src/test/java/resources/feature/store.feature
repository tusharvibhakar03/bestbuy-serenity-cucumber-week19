Feature: BestBuy Application
  As a user I want to test BestBuy Application

  Scenario: Check if the BestBuy application can be accessed by users
    When User sends a GET request to list endpoint for store
    Then User must get back a valid status code  for store 200

  Scenario Outline: Create a new store & verify if the store is added

    When I create a new Store by providing the information name "<name>", address "<address>", city "<city>", state "<state>", zip "<zip>", type  "<type>", address- "<address2>", lat "<lat>",lng "<lng>",hours "<hours>",services "<services>"
    Then I verify that the store  with lat "<lat>" is created
    Examples:
      | name  | address       | city | state | zip   | type | address2 | lat | lng | hours | services |
      | viral12 | prime Testing | abcd | auto  | 102df | best | avenue   | 10  | 100 | 10:15 | tesla    |

  Scenario: Update an exciting store & verify that store is updated
    When I update a store with storeId and give information storeId "39" name "viral1", address "testing", city "southAmerica", state "Newyork", zip "we3wr", type  "abcd", address- "highway", lat "20",lng "10",hours "11:11",services "LMNO"
    Then I verify that the store with id storeId "39" is updated

  Scenario: Delete an exciting store & verify that store is deleted
    When I delete store with store Id
    Then I verify using  store id  that  data is deleted