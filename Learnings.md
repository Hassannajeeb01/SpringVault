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
- Eventual consistency, the concept where if your db writes fail you just add them to a kafka queue and execute later
- @Transactional rolls back for unchecked excpetions, for checked ones you have to specify
- Use this so your operations do not overwrite each other and happen atomically
- For 2 players, you use Webscokets for two way communication
- Runtime exception cz exception forces everything to be in a try catch whereas runtime is unchecked
- You can use generated value to auto generate the keys
- work on how to use Onetomany and manytoone in entities
- Use JOIN FETCH for queries with N+1 issue, practice more on lazy vs eager loading
- save() persists everything when the @transactional ends, saveandflush() does it for every time it is called
- so do the runtimeexception for trasncatinal in your code as well
- design patterns
- Spring used AOP proxies to intercept the method call, manage the transaction lifecyle and handle the roll back logic
- practice some queries for spring JPA
- restCLient and WebClient for calling APIs within a spring app
- Authentiation in spring
- COnstructor injectionm is when you make a priavte object of the class and then do this.x = x in the current class constructor
- git config --global core.autocrlf true -> makes line endings auto configuarable for the environment they are being used in for git, since linux and windows have different line endings
- ok so JWT, has a header which is just the algorithm, payload whihc is e.g email + expiry time, and signature, whihc is a hash made using your service's private key
- for JWT, the security config is your configuration of security, it simply sets the jwtFIlter as security and then then the filter is what catches all requests and authenticates where necessary
- RequestParam is like /user/123
- PathVariable is like /user?id=123
- Service should be an interface with a class implemeting it. 
- Dependency Inversion (DIP) is the architectural principle: high-level business logic shouldn't depend on low-level implementation details—both should depend on abstractions (interfaces).
Inversion of Control (IoC) is the design strategy where we hand over the responsibility of instantiating and managing those dependencies to a framework. Instead of the high-level class saying, 'I will create my own database repository,' the framework controls the lifecycle and hands it to the class.
- Dependency Injection (DI) is the concrete mechanism Spring uses to achieve IoC—wiring those managed beans directly into class constructors at startup.
OCP doesn't mean wrapping every single new feature in a delegate service—that leads to wrapper hell. For cross-cutting infrastructure concerns like metrics or logging, we use Spring AOP or decorators. But for evolving domain logic, we design extension points using strategies or events so the core flow doesn't need to be ripped apart.


SOLID Principles:

- The open closed principle means a class you create should be accepting of new code but not of any modifications. In practice, I built this blackjack game whihc has a blackjackservice containing the business logic. The service in use is actually an implementation of the interface blackjackservice. Now, if lets say i need to add something additional to the responsivility of this class, like metrics on how long it takes to analyze a game state, i can make a new service by extending the blackjackservice, make the new one the primary, call the previous service in it as a delegate. The delegate then handles the actual game logic and the new service just wraps arounf those requests with a time calculation metric. This makes sure we never actually change our blackjackservice to add new responsibilities



Design Patterns:
- Strategy Pattern: To eliminate business logic being polluted by if/else. Lets say you add more games here, make beans for all the gameEngines, label them with String names like @Component("ticTacToe").
Then in a GameEvaluationService you inject them as a map like Map<String, RuleEngine> ruleEngines;
inject this via constructor injection and Spring will automatically load all the RuleEngine beans (implementing RuleEngine interface)
Now you GameEvaluationService does not even need to know what game you playing, it can just analyze on runtime. So from your specific game service when you need to analuze you just call gameEvaluationService.analyzeGame(game)

- Decorator pattern: I built this blackjack game whihc has a blackjackservice containing the business logic. The service in use is actually an implementation of the interface blackjackservice. Now, if lets say i need to add something additional to the responsivility of this class, like metrics on how long it takes to analyze a game state, i can make a new service by extending the blackjackservice, make the new one the primary, call the previous service in it as a delegate. The delegate then handles the actual game logic and the new service just wraps arounf those requests with a time calculation metric.





- 
toDO:
- exception handling for when a gameid does not exist, controlleradvice - DONE
- have spring authentication implemented - Partially DONE
- implement checking email validity, verification
- change game saving based on email instead of sessionID
- implement interfaces and abstract classes, specially for services


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

How i added Auth:
Here's the full plan:

**1. Dependencies**
Add Spring Security and JWT library to `build.gradle`

**2. User Entity**
Create a `User` entity with id, email, password fields — maps to a `users` table in PostgreSQL

**3. User Repository**
Interface extending JpaRepository with a `findByEmail` method

**4. Auth DTOs**
Two simple DTOs — `RegisterRequest` and `LoginRequest` (email + password fields)

**5. JWT Utility**
A `@Component` class that can generate a token from email, extract email from token, and validate a token

**6. Password Encoder Bean**
Register BCryptPasswordEncoder as a Spring bean

**7. Auth Service**
Business logic for register (hash password, save user) and login (validate credentials, return JWT)

**8. Auth Controller**
Two endpoints — `POST /auth/register` and `POST /auth/login`

**9. JWT Filter**
Intercepts every request, reads Authorization header, validates token, sets authentication in Spring Security context

**10. Security Config**
Tell Spring Security — permit `/auth/**`, protect everything else, plug in the JWT filter, make sessions stateless

---

That's the full list in order. Start with step 1 — dependencies. Tell me when done.

