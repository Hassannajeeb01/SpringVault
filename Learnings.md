- Figure out the internals of spring boot
- org.boot: this provides you with bootrun and packages everything into an executable .jar for you
- org.boot.autoconfigure: looks for your gradle file and configures everything accordingly
- @SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration.class})
    You can use this to exclude specific things from your redis 
- concurrenthashmp is threadsafe
- spring makes one bean for every service, singletons concept
- controller handles requests in threads simaltaneously
- to handle load of too many requests, best method is to have multiple instances of backend spring app
- you can use PATCH http request instead of POST if you have min side effect from a request
- Spring bean scopes: singleton, prototype (for every injection), request (per http request), session (new instance per user session)
- DTOs exist so you have a standard response format independent of your implementation. also does not expose implementation to world
- Exceptions are handles using controlleradvice and custom exceptions
- Using runtimeexception instead of plain exception makes it unchecked so it naturally bubbles up to the controlleradvice witthout try catch blocks
- You can use @version for JPA and Redlock for redis to make sure we dont overwrite simaltanoesu requests

toDO:
- exception handling for when a gameid does not exist, controlleradvice

Postgres:
- A cluster is the engine process running on a port which can host multiple DBs
- Catalog is a specific DB inside the cluster



How i designed this:
    deal() -> two cards to player, two to dealer one marked facedown, remove cards from deck List

    turn(player/dealer) -> 
        if dealer then flip card -> analyze number and decide -> hit(player/dealer)/stand(player/dealer) 

    hit(player/dealer) -> drawCard() -> analyze -> hit/stand

    stand(player/dealer) -> if player stands then turn(dealer) -> if dealer stands then end game

    drawCard(player/dealer) -> add card to deck, call removeCard on same card


    -------------------------

    Card -> Value, attribute(facedown/up) default up

    Deck -> List(Card), removeCard

    Player -> name, List(cards), cardsValue

    Dealer -> List(Cards), cardsValue

