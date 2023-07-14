## Concept 

Since I've used spring boot for a [small practice project in the past](https://github.com/alanbarrientos/dietapp). I thought that I could try to model the same paterns that I had in my spring boot app into this assestment.

The patterns that I tried to copy are the Entity, the Repository, and the Controller. They all operate in memory.

I imagined having very simplified UIs that will fulfill the requirements of the assestment. Then I created what would be the controller endpoints used in the backend to suport the UIs.

When i was studying before joining mentormate, I was told to follow adam bien's advice on the BCE pattern (Boundary Control Entity). So I tried to model the BCE pattern, which has the controllers as "Boundaries", which are the access endpoint of the application. Then Controllers may call other controllers, utility methods, which are the "Control" part, or do repository calls which are the "Entity" part. But Utility code and Repositoryes will never call a controller. And the UI will always go tru the controllers/boundary first.

### More concretly what I worked on is:

1. Controllers, being the "Boundary" of the application are named after the business utility they perform, like BorrowController and EbookActionController(download/OnlineWatch)
2. Repositories, being the "Entity" part in the BCE are named after the main table they deal with, like Users and History. They are Singletons and extend a base abstract class that implements the common functionality. Since they can be instantiated only once I added the seed data in the constructors. I do not feel comfortable about this desition but I was unsure if there was a better way to do it. 
3. Entities are simple POJOs that extend a base abstract class that implements common functionality.
4. Regarding Exception handling, I decided to throw exception in any code used below the controllers and then the controllers will catch any exception produced below them. In spring boot it is common to let the exceptions pass tru the Controllers and handle them centrally so that HTTP requests are never answered with a 200 OK response when there was an execption, but with a 500 SERVER ERROR response. However, I didn't go that way for this assestment due to time restrictions.
5. Regarding unit testing I only saw it at the end that I was doing more of an integration testing than unit testing. This is because when I test the controllers they interact directly with the repositories, and since they don't persist in a database, I did not moked them. Then when I test a controller I was testing the actual usage of what the app will do in produciton. I see now that I could mock the Repositories to do a proper unit test of the Controlers, and then also unit test each Repository but for time reasons I didn't do that part. Just wanted to at least let you know that I understand that part.

I have 2 examples of imagined UIs and what are the controler methods I created

Now we can look at the code. There are some rough edges because I was too optimistic choosing my presentation time, I thought I had enough time but I can see now that I could have used the weekend to improve a lot of things. But I sitll wanted to present what I have.
