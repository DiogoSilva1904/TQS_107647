Feature: Select and buy a trip from the travel agency
  Scenario: Select departure and destination cities
    When I navigate to "https://blazedemo.com/"
    And I select "Boston" as the departure city
    And I select "London" as the destination city
    And I click on the Find Flights button
    Then I should see the following "Flights from Boston to London:"
    When I select the flight number 3
    Then I should see the header "Your flight from TLV to SFO has been reserved."
    When I write my name as "João Ratão"
    And I write my "address" as "Rua das Flores, 123"
    And I write my "city" as "Braga"
    And I write my "state" as "Braga"
    And I write my "zipCode" as "12345"
    And I write my "creditCardNumber" as "1234567890"
    And I write my "creditCardMonth" as "12"
    And I write my "creditCardYear" as "2022"
    And The name on the credit card is "João Ratão"
    And I click on the Purchase Flight button
    Then I should see the message "Thank you for your purchase today!"
    Then The title of the page should be "BlazeDemo Confirmation"