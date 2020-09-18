# hibernate-inheritance

In this HW you will have a practice with different inheritance strategies.

Action items:

    - Clone the project. 
    - Configure Hibernate. 
    - Add required annotations in model classes. 
    
### Implement the following requirements: 
1. There are classes: Animal, Cat and Dog. Both Cat and Dog extends Animal. 
Add the implementation of `Single table` inheritance strategy.

1. There are classes: Machine, Car and Truck. Both Car and Truck extends Machine. 
Add the implementation of `Table per class` inheritance strategy.

1. There are classes: Person, Coach and Mentor. Both Coach and Mentor extends Person. 
Add the implementation of `Join tables` inheritance strategy.

1. There are classes: Character, Bowman and Farmer. Both Bowman and Farmer extends Character. 
Add the implementation of `Implicit strategy (Hibernate only)` inheritance strategy.

1. There are classes: Figure, Circle and Triangle. Both Circle and Triangle extends Figure. 
Add the implementation of `Mapped Superclass` inheritance strategy.

### Implement the following methods
1. Insert at least 5 dogs and 5 cats into the DB.
1. Select all cats and dogs where name starts with 'A'.
1. Insert at least 5 cars and 5 trucks into the DB.
1. Select all cars and trucks older than 4 years.
1. Insert at least 3 coaches and 5 mentors into the DB.
1. Select all mentors older than 20 years.
1. Select all coaches with experience more than 5 years.
1. Insert at least 3 bowmen and 5 farmers into the DB.
1. Select all characters sorted ascending by power.
1. Insert at least 3 circles and 5 triangles into the DB.
1. Select all figures where color is red.

### Embeddable
1. There are classes: NewsPost and PostMetadata. Each NewsPost has its own PostMetadata. 
Make PostMetadata embeddable in the NewsPost.
1. Insert at least 5 posts with metadata.
1. Select all news posts with metadata larger than 10000 bytes.

### Main class
1. Each implemented method should be called in the Main class.

### Results
1. Add the log file with generated sql requests.
