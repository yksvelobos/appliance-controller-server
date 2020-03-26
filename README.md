# appliance-controller-server
 
## Description ##
Appliance controller server is backend service to control an appliances.

#NOTE: Currently, the names of current actions are output to the console
Planned: Save each one to db and display current working action in current state of applianse by request

#Workflow

# 1. Register appliance by name in application and get its id

# Request: 

**url** :/appliance/register?name=electrolux1
**method**: POST

## Success Response:
{
    "id": 167,
    "name": "electrolux1",
    "deleteDate": null,
    "workProgram": null
}

# 2. Create action for work programm of registered appliance 
# Request: 

**url** : /action/updateAction
**method**: POST

## Success Response:
    {
        "id": 1,
        "name": "hello",
        "order": 1,
        "duration": 1000,
        "deleteDate": null
    }
    
 # 3 Create work programm
 # Request:
 
**url** : /workprogram/createWorkProgram?name=program2
**method**: POST

## Success Response:
{
    "id": 232,
    "name": "program2",
    "deleteDate": null,
    "actions": null
}

 # 4 Add action to work program
 # Request:
 
**url** : /workprogram/workprogram/add-action-to-program?program_id=35&action_id=1
**method**: POST

## Success Response:

{
    "id": 35,
    "name": null,
    "deleteDate": null,
    "actions": [
        {
            "id": 1,
            "name": "hello",
            "duration": null,
            "deleteDate": null
        }
    ]
}

 # 4 Add action to work program
 # Request:
 
**url** : /appliance/add-program?applianceId=135&programId=35
**method**: POST

## Success Response:

{
    "id": 135,
    "name": "electrolux1",
    "deleteDate": null,
    "workProgram": {
        "id": 35,
        "name": null,
        "deleteDate": null,
        "actions": [
            {
                "id": 1,
                "name": "hello",
                "order": null,
                "duration": null,
                "deleteDate": null
            },
            {
                "id": 2,
                "name": "hello2",
                "order": null,
                "duration": null,
                "deleteDate": null
            }
        ]
    }
}

 # 5 Starts executing the linked program
 # Request:
 
**url** : /appliance/start-work-program?applianceId=135
**method**: POST

