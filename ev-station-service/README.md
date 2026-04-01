# APIs Link

1. Create new: POST
http://localhost:8081/api/stations 
   
       {
            "name": "charge station",
            "location": "pune warje",
             "availableSlots": 7
            }
2. All Station Using pageble: GET
   http://localhost:8081/api/stations/page?page=0&size=2
       
       key - value
       page   0
       value  3

3. Find By Id: GET
   http://localhost:8081/api/stations/3

4. Search By Name & Location key value form : GET
   http://localhost:8081/api/stations/search?name=charge Station&location=amravati ravinagar

5. Find by Minimum slot available: GET
   http://localhost:8081/api/stations/filter?minSlots=5

6. Available stations means avaibleSlots is > "0": GET
   http://localhost:8081/api/stations/available

7. Update Perticular field data: "Patch"
   http://localhost:8081/api/stations/8

8. Update All Fields: PUT
   http://localhost:8081/api/stations/4

       {
          "id": 4,
          "name": "charge station",
         "location": "pune warje",
         "availableSlots": 7
        }
9. Delete Data: DELETE
   http://localhost:8081/api/stations/11