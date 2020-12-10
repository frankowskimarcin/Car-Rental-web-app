# Car-Rental-web-app
Backend for Car Rental Web Application


### Database available at the address: http://localhost:8081/h2-console
![h2_console](https://user-images.githubusercontent.com/56775198/101819448-91bf6380-3b25-11eb-9e89-bb5751bebacf.png)
---

### How to test REST API (with Postman):
![postman](https://user-images.githubusercontent.com/56775198/101820670-3a21f780-3b27-11eb-9edc-e1682b07b979.png)

*GET* http://localhost:8081/cars

*GET* http://localhost:8081/cars/1001

*DELETE* http://localhost:8081/cars/1001

*POST* http://localhost:8081/cars \
with sample Body in JSON format:
{
    "brand": "BMW",
    "model": "3 Series 320d",
    "registrationPlate": "PZ12345",
    "rentStatus": false
}

*PUT* http://localhost:8081/cars/1001 \
{
    "brand": "Toyota",
    "model": "Corolla",
    "registrationPlate": "PO999XX"
}

*GET* http://localhost:8081/rentals

*GET* http://localhost:8081/rentals/1

*GET* http://localhost:8081/rentals/1002/102

*PUT* http://localhost:8081/rentals/10 \
{
    "id": 1002,
    "REGISTRATION_PLATE": "WE123QW"
}

*DELETE* http://localhost:8081/rentals/1
