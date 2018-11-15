# DeckOfCards_REST_API_SpringBoot-Junit-Mockito
DeckOfCards_REST_API_Application. This project is build in Spring boot 2 + Rest API + Junit and Mockito

Steps :

mvn clean
mvn test
mvn clean install
Go to the target folder
java -jar DeckOfCards-0.0.1-SNAPSHOT.jar

Verify your RESTful call.

1) Post call for create game :

http://localhost:8080/deckofCards/createGame

Input : null
OutPut :

{
    "responseCode": 201,
    "responseMessage": "Your game Has been created Here Is your Game ID : 2",
    "errorDiscription": null,
    "httpHeaders": null,
    "responseObject": null,
    "error": false
}

2) creating Deck

http://localhost:8080/deckofCards/createDeck

request :

{
	"gameId":"1"
}

Response :

{
    "responseCode": 201,
    "responseMessage": "Your deck id Has been created Here Is your deck ID : 1",
    "errorDiscription": null,
    "httpHeaders": null,
    "responseObject": null,
    "error": false
}

3) Add Player

http://localhost:8080/deckofCards/addPlayer

request :

{
	"gameId":"1"
}

Response :

{
    "responseCode": 201,
    "responseMessage": "Player has been Added.! Player Id is : 9",
    "errorDiscription": null,
    "httpHeaders": null,
    "responseObject": null,
    "error": false
}

4) Remove player 

http://localhost:8080/deckofCards/removePlayer/3/2

request : none

Response :

{
    "responseCode": 200,
    "responseMessage": "Player is deleted from the Game",
    "errorDiscription": null,
    "httpHeaders": null,
    "responseObject": null,
    "error": false
}


5 shuffle

http://localhost:8080/deckofCards/shuffle/1

Request : none

Response :

{
    "responseCode": 200,
    "responseMessage": "Successfully shuffled.!",
    "errorDiscription": null,
    "httpHeaders": null,
    "responseObject": null,
    "error": false
}

6) dealCards 

http://localhost:8080/deckofCards/dealCards/1

Request : none

Response :

{
    "responseCode": 200,
    "responseMessage": "Successfully deal all the Cards",
    "errorDiscription": null,
    "httpHeaders": null,
    "responseObject": null,
    "error": false
}

7) getListofCardsForPlayer 

http://localhost:8080/deckofCards/getListofCardsForPlayer/1/9

Request :

Response :

{
    "responseCode": 200,
    "responseMessage": "Successfully fetched card for the player",
    "errorDiscription": null,
    "httpHeaders": null,
    "responseObject": [
        "PlayerId :9",
        "suit :Spade rank :FIVE",
        "suit :Heart rank :SIX",
        "suit :Diamond rank :TEN",
        "suit :Diamond rank :SIX",
        "suit :Clubs rank :QUEEN"
    ],
    "error": false
}

8) getListofPlayer

http://localhost:8080/deckofCards/getListofPlayer/1

Request :

Response :

{
    "responseCode": 200,
    "responseMessage": "getListofPlayer fetched Successfully.!",
    "errorDiscription": null,
    "httpHeaders": null,
    "responseObject": [
        "PlayerId :1 TotalCount :45",
        "PlayerId :6 TotalCount :44",
        "PlayerId :4 TotalCount :42",
        "PlayerId :8 TotalCount :41",
        "PlayerId :9 TotalCount :39",
        "PlayerId :2 TotalCount :34",
        "PlayerId :3 TotalCount :34",
        "PlayerId :5 TotalCount :23",
        "PlayerId :7 TotalCount :17"
    ],
    "error": false
}

9) getCountOfLeftCards

http://localhost:8080/deckofCards/getCountOfLeftCards/1

Request :

Response :

{
    "responseCode": 200,
    "responseMessage": "getCountOfLeftCards fetched Successfully.!",
    "errorDiscription": null,
    "httpHeaders": null,
    "responseObject": [
        "2 Spade",
        "2 Heart",
        "0 Diamond",
        "3 Clubs"
    ],
    "error": false
}

10) getCountOfEachCard

http://localhost:8080/deckofCards/getCountOfEachCard/1

Request :

Response :

{
    "responseCode": 200,
    "responseMessage": "getCountOfLeftCards fetched Successfully.!",
    "errorDiscription": null,
    "httpHeaders": null,
    "responseObject": {
        "Heart": [
            {
                "suit": "Heart",
                "rank": "SEVEN"
            },
            {
                "suit": "Heart",
                "rank": "FIVE"
            }
        ],
        "Spade": [
            {
                "suit": "Spade",
                "rank": "QUEEN"
            },
            {
                "suit": "Spade",
                "rank": "ACE"
            }
        ],
        "Clubs": [
            {
                "suit": "Clubs",
                "rank": "KING"
            },
            {
                "suit": "Clubs",
                "rank": "NINE"
            },
            {
                "suit": "Clubs",
                "rank": "SEVEN"
            }
        ],
        "Diamond": []
    },
    "error": false
}

