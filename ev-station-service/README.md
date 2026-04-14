🔧 CRUD Endpoints

1. Add Station (POST)  
     http://localhost:8081/api/stations/save  

          {
         "stationName": "EV Hub Pune",
         "location": "Pune City Center",
           "capacity": 10,
         "pricePerKWh": 12.5,
          "occupiedSlots": 3

2. Get All Stations (GET)  

        http://localhost:8081/api/stations/page

3. Get Station by ID (GET)  
      
       http://localhost:8081/api/stations/{id}  
        Example:
          GET http://localhost:8081/api/stations/1

4. Update Station (PATCH – partial update)  

        http://localhost:8081/api/stations/{id}  
          Example:
           PATCH http://localhost:8081/api/stations/1  
        Body (JSON):

        {
          "pricePerKWh": 18.0,
          "available": false
        }

5. Delete Station (DELETE)  
      
       http://localhost:8081/api/stations/{id}  
          Example:
          DELETE http://localhost:8080/api/stations/1

🔍 Search & Filter Endpoints
7. Search Stations by Location (GET)  
    
       http://localhost:8081/api/stations/search?location=Pune

8. Available Stations (GET)  
   
       http://localhost:8081/api/stations/available

9. Filter Stations by Minimum Slots (GET)  

        http://localhost:8081/api/stations/filter?minSlots=5

📑 Pagination & Sorting Endpoints
10. Pagination (GET)  
        
         http://localhost:8081/api/stations/page?page=0&size=10

11. Pagination + Sorting (GET)  

        http://localhost:8081/api/stations/page/sort?page=0&size=10&sortBy=pricePerKWh&sortDirection=desc

⚡ Example Workflow in Postman
POST → Add a few stations with different names, locations, capacities, and prices.

GET → Fetch all stations.

GET /search → Search by location.

GET /available → Get only available stations.

GET /page/sort → Paginate and sort by capacity or pricePerKWh.

PATCH → Update only one field (like available).

DELETE → Remove a station.