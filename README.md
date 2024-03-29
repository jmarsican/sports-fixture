# Sports Fixture Android App

Model View Presenter using RxJava, Dagger and Room

![Repo](./assets/screenshot1.png?raw=true "Fixtures")

![Repo](./assets/screenshot2.png?raw=true "Results")

![Repo](./assets/repository_pattern.png?raw=true "Architecture")

![Repo](./assets/MVP.png?raw=true "Architecture")

A one screen application with 2 tabs: Fixtures and Results.
Fixtures: ​ https://storage.googleapis.com/cdn-og-test-api/test-task/fixtures.json
Results: ​ https://storage.googleapis.com/cdn-og-test-api/test-task/results.json

1) Both screens should contains list of matches divided by month,
2) every match item must contain following info:
a) Competition name
b) Venue name
c) Match date and time (in user timezone)
d) Home and Away team names
3) Match item on Fixture page should have additional info:
a) Match day of the month, Match day of the week
b) If match is in postponed state, it must be highlighted
4) Match item on Result page should have additional info
a) Home and Away team scores
b) Winner team must be highlighted
5) Add possibility to filter matches by competition
Despite task low complexity please try to apply specific architecture approach for your solution.
Please avoid using code generation frameworks (like AndroidAnnotation) which massively alter your code. Dagger framework is an exception. ​ Use any other libraries and languages(java/kotlin) you want. UI does not have to be exactly the same, it’s up to you. Result code should be well written and structured.
