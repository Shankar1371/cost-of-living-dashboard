# Cost of Living Dashboard

Cost of Living Dashboard is an Android application that helps people understand how changes in prices impact their everyday lives. The app combines personal expense tracking with public cost-of-living indicators so users can view their own spending alongside broader economic trends.

## Why It Matters

Keeping a budget is hard when prices for rent, groceries, and utilities are constantly shifting. This project aims to give individuals a single place to:

* record daily purchases,
* watch how local markets evolve, and
* make informed decisions about future spending.

By bringing these data points together, the application turns raw numbers into actionable insight that can improve financial awareness and planning.

## Features

* **Personal expense tracking** – log purchases with categories and notes.
* **Public cost-of-living data** – view inflation, rent, and other indices from open data sources.
* **Trend analysis** – charts and history views show how costs change over time.
* **Budgeting tools** – set spending limits and receive alerts when approaching them.
* **Offline-first design** – all data is stored locally with Room so the core features work without a network connection.

  --Dashboard page
  _________________________________________________________________________________________________________________________________________________
  <img width="379" height="716" alt="image" src="https://github.com/user-attachments/assets/22df7a2b-c7d6-4372-864c-44d94ce92eaf" />
  _________________________________________________________________________________________________________________________________________________
  --Add expense page
  _________________________________________________________________________________________________________________________________________________
  <img width="376" height="712" alt="image" src="https://github.com/user-attachments/assets/32084329-da91-4bfa-a04b-2293359b29e9" />

  _________________________________________________________________________________________________________________________________________________
  --Analytics page
  _________________________________________________________________________________________________________________________________________________
  <img width="341" height="720" alt="image" src="https://github.com/user-attachments/assets/5e581637-05f2-4f31-aed6-5435de257cf2" />

  _________________________________________________________________________________________________________________________________________________
  --Expense History Page
  _________________________________________________________________________________________________________________________________________________
  <img width="364" height="720" alt="image" src="https://github.com/user-attachments/assets/ec61b2fb-614f-47dd-b63f-2cb08433af09" />

  _________________________________________________________________________________________________________________________________________________
  --Inflation Tracker Page
  _________________________________________________________________________________________________________________________________________________
  <img width="346" height="715" alt="image" src="https://github.com/user-attachments/assets/a5546700-4055-4fd3-83cd-03d28cc6caaa" />

  _________________________________________________________________________________________________________________________________________________
  --Settings page
  _________________________________________________________________________________________________________________________________________________
  <img width="352" height="713" alt="image" src="https://github.com/user-attachments/assets/ca909357-a81d-4598-b5ca-8db2e063156f" />

  _________________________________________________________________________________________________________________________________________________

## Technology Stack

The app is built with the modern Android toolchain:

* Kotlin language
* MVVM architecture with ViewModel and LiveData
* Navigation component for in-app movement
* Room database for persistent storage
* Material Design components for a familiar user experience

## Getting Started

1. Install [Android Studio](https://developer.android.com/studio) (Electric Eel or newer is recommended).
2. Clone this repository.
3. Open the project in Android Studio and allow Gradle to sync the dependencies.
4. Connect an Android device or start the emulator, then run the app from the IDE.

The main app module and its dependencies are defined in `app/build.gradle.kts`.

## Roadmap

Some features that would further enhance the dashboard include:

* Importing bank statements for automated expense entry.
* Exporting reports for financial reviews.
* Cloud backup and multi-device sync.

## Contributing

Contributions of all kinds are welcome. If you plan to open a pull request, please discuss major changes in an issue first and run `./gradlew test` before submitting your work.

