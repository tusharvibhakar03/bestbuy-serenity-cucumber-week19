Feature:BestBuy Application
  As a user I want to test BestBuy Application

  Scenario Outline: CRUD Test
    Given Check if the BestBuy application can be accessed by users
    When I create a new product by providing the information name "<name>", type "<type>", upc "<upc>", description "<description>", model "<model>", price  "<price>", shipping "<shipping>", manufacturer "<manufacturer>"
    Then I verify that the product  with "<price>" is created
    And I update a product with product Id and give information ProductId "673890" name "viral1", type "HardGood", upc "abcd", description "auto", model "best", price  "10", shipping "11" , manufacturer "tesla"
    Then I verify that the product with id productId "673890" is updated
    And I delete product with product Id
    Then I verify using  product id  that  data is deleted
    Examples:
      | name   | type    | upc     | description | model | price | shipping | manufacturer |
      | Michel1 | Jackson | 0123415 | auto        | best  | 11    | 10       | tesla        |
