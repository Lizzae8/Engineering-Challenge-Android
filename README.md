# Android-Developer-Challenge

`version 1.6`
`challenge status: open`

## Completed features
* Targeted sdkVersion 23 with minSdkVersion being 16.
* Developed using MVP design pattern.
* Ability to search food, search autocomplete, display food details.
* Display food nutrition value using pie chart.
* Ability for user to add custom food.
* Ability to keep track of user's weekly calorie intake. Data get reset every Monday. (Limitation: max amount of calorie tracked per day is 50000 kcal. This is due to the limitation of the library used to draw the line chart. For more details, check https://github.com/kai91/Engineering-Challenge-Android/commit/059ae4c594fe46de2c28161f7e4dcc93a5c28c46)
* Display user's weekly calorie intake using line chart.
* Optimized layouts for phone and tablets.
* Used RealmIO as data storage.
* Used Retrofit for network call.
* Check FoodDetailsPresenter.java for time taken for query to complete log, FoodDao.java for time taken to save and retrieve data from local database.

Welcome! We've been expecting you. Holmusk is a big data based high tech company specializing in healthcare in Singapore.

If you're someone who bleeds code and aches to make a difference in the world, then you are at the right place. You will be part of a world‑class team working on the most exciting ground‑breaking technology in an inspiring and collaborative environment.

## Basics

This is the Holmusk Android developer challenge. The rules of the challenge are very simple and are as follows

* You are required to code in Java
* You will be able to submit the challenge anytime you are ready provided the challenge is still open
* Your code should be commented
* Your app should have targetSdkVersion 22 and should support minSdkVersion 16 (the lower the better but min 16 is a must)
* You should implement your app to support *all* Android mobile sizes. Only one orientation is enough - we're not too fiesty on that
* You should use Android Studio IDE and gradle based project structure
* Because we, at Holmusk, take code organization seriously, please do ensure your source files are organized when you submit. Please do ensure that, at the minimum, you follow MVC and your code organization reflects that.
* You are required to fork this repo and submit a pull request
* If you wish to not make public, your submission, please complete the code in your local repository and email a patch file to careers@holmusk.com
* Please note that you will also be judged on the elegance of your code, level of abstraction and technical skills presented in the implementation. For more details, refer to the Judging Criteria section below.

## The Challenge

### What You'll need to build
You'll need to build an app that is able to retrieve nutrition information for different food types, persist it locally and display it to the user in a very interesting manner.


### Bits and Pieces to take note of
* Use `http://test.holmusk.com/food/search?q=food_search_query`, where the •food_search_query* parameter should be replaced by a food name, to search for food items.
* Present the data in your app. How you present will be a direct reflection of your creativity and motivation so we encourage you to spend as much time on this part as possible. You are **not** limited to native Android views and widgets, feel free to create your own representations or use open source libraries. One of our favorites is the parallax based scrollview.
* You will need to implement **autocomplete with a search view** [With a maximum of 10 results so that the interface does not look cluttered] so that users are able to easily enter food items they have had.
* You will need to store all of your results into Realm(http://realm.io) /SQlite in the most efficient way possible. Please do log the time taken for data storage and retrieval in the console.
* You will also need to allow users to enter new food items which should then be synced with your *local dataStore* (Realm/SQlite).
* With that said we wish you good luck and look forward to receiving your submission!

## Judging Criteria
* What you have produced will determine your final outcome. 60% of your product, from our point of view, depends on your User Experience and User Interfaces for this challenge thus we would encourage you to make the best use of the Animation, ObjectAnimator and other open source libraries. Because we love people who have a passion for expanding their horizons, your background with these libraries do not matter so much provided you are able to demonstrate your learning ability!

### Bonus Points

At Holmusk we do our best to go the extra mile and as such you would recieve brownie points if
* your app provides simple analytics such as keeping track of a users diet for the day and providing relevant graphs, animations etc.
* you use Realm(http://realm.io) for Data storing
* you use Retrofit(http://square.github.io/retrofit) for Network calls
* you use Picasso(http://square.github.io/picasso) for Image rendering
* you create at least one custom View by extending View class or any of its subclasses
* you use  Kimono's API builer (https://www.kimonolabs.com) to extract food related data from MyfitnessPal (https://www.myfitnesspal.com), FatSecrets (http://www.fatsecret.com.sg) or other food websites such as CalorieKing (http://www.calorieking.com).
