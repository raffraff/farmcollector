# FarmCollector

## 1. API Endpoints
**Planted API:**
- **Endpoint**: POST /farm/plant
- Sample Request Body:
```json
{
  "farmName": "MyFarm1",
  "season": "SPRING",
  "cropType": "Corn",
  "plantingArea": 11.5,
  "expectedWeight": 21.5
}
```
**Harvested API:**
- **Endpoint**: PUT /farm/harvest
- Sample Request Body:
```json
{
  "farmName": "MyFarm1",
  "cropType": "Corn",
  "actualHarvestedWeight": 52
}
```
## 2. Accessing the Report page
Open a web browser and navigate to http://localhost:8080/report/{season}. Replace {season} with either SPRING, SUMMER, FALL, WINTER