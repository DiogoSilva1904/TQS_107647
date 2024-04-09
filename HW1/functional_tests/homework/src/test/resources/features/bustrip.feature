@bus_trip
Feature: Make a reservation
  Scenario: User wants to make a reservation for a bus trip from Aveiro to Coimbra
    Given the user is on the bus trip reservation page
    When the user writes the departure city as "Aveiro"
    And the user writes the arrival city as "Coimbra"
    And the user selects the first trip
    Then the user should see the trip details

    When the user clicks on the button to make a reservation

    When the user writes the name as "Diogo Silva"
    And the user chooses the seat number "1A"
    And the user writes the email as "diogo@gmail.com"
    And the user writes the nif as "123456789"

    When the user clicks on the button to confirm the reservation
    Then the user should see the reservation confirmation message


