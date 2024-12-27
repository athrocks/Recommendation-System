# Java Recommendation System

Welcome to the **Java Recommendation System** project! This project is a movie recommendation system built in Java. It demonstrates how to work with data structures, interfaces, and algorithms to deliver personalized movie recommendations.

---

## Features
- **Read and Parse Data:** Load and analyze movie and ratings data.
- **Filter Movies:** Apply filters based on genres, years, or other criteria.
- **Average Ratings:** Calculate and display the average ratings for movies.
- **Customizable Recommendations:** Generate personalized movie recommendations for users based on their preferences and ratings.

---

![img](https://github.com/athrocks/Recommendation-System/blob/main/src/com/atharva/project/data/Screenshot%202024-12-27%20182548.png)

---

## Prerequisites
- **Java Development Kit (JDK):** Version 8 or above.
- **Integrated Development Environment (IDE):** IntelliJ IDEA, Eclipse, or any other preferred IDE.
- Basic understanding of Java programming and object-oriented principles.

---

## Getting Started

### Clone the Repository
```
git clone https://github.com/yourusername/java-recommendation-system.git
cd java-recommendation-system
```

### Data Files
1. **Movies File:** A CSV file containing movie metadata (e.g., title, year, genres).
2. **Ratings File:** A CSV file containing user ratings for movies.

Place these files in the `/data` directory. Sample files:
- `movies.csv`
- `ratings.csv`

### Project Structure
```
java-recommendation-system/
├── data/
│   ├── movies.csv
│   └── ratings.csv
├── src/
│   ├── Movie.java
│   ├── Rater.java
│   ├── Filter.java
│   ├── ThirdRatings.java
│   ├── RecommendationRunner.java
│   └── ... (other classes)
├── README.md
└── ...
```

---

## Usage
1. **Load Data:** The program reads movie and rating data from the `movies.csv` and `ratings.csv` files.
2. **Apply Filters:** Use predefined or custom filters to narrow down the movie list.
3. **Generate Recommendations:** Generate a list of recommended movies for a specific user.

### Run the Application
- Compile and run the main class (`RecommendationRunner.java`) in your IDE or terminal.
- Example command:
  ```
  javac -d bin src/*.java
  java -cp bin RecommendationRunner
  ```

---

## Example Output
```
Top Recommendations for User 1:
1. Movie Title: The Godfather (1972)
   Average Rating: 4.8
2. Movie Title: Inception (2010)
   Average Rating: 4.7
...
```

---

## Customization
- **Filters:** Extend the `Filter` class to add new filters (e.g., runtime, director).
- **Algorithm:** Modify `ThirdRatings.java` to experiment with different recommendation algorithms.

---

## License
This project is licensed under the MIT License. See `LICENSE` for details.

---
