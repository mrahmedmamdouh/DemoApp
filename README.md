📱 DemoApp

DemoApp is a modern Android application showcasing the latest tools and libraries in Android development. It integrates Jetpack Compose for UI, Room for local data storage, Hilt for dependency injection, and Retrofit for seamless API communication. The project emphasizes clean architecture and modular design for scalability and maintainability.

🌟 Features
🔑 Login Screen: Simplified login interface for user interaction.
👋 Greeting Screen: Dynamically greets the user based on the time of the day.
💊 Medicine Management:
Fetches a list of medicines from a RESTful API.
Displays medicines in a card-style layout.
Details View: Showcases detailed information about each medicine.
🗄️ Offline Caching: Uses Room for local data storage, enabling offline support.
🚦 Robust State Management: Implements Model-View-Intent (MVI) with Jetpack Compose.
🏗️ Architecture
The project adheres to Clean Architecture principles with a separation of concerns across the following layers:

Presentation Layer:

Built with Jetpack Compose for a modern, declarative UI.
Implements MVI for state-driven UI and ViewModel-powered state management.
Powered by Hilt for dependency injection.
Domain Layer:

Contains core business logic and use cases such as GetMedicinesUseCase.
Data Layer:

Handles data operations with a repository pattern.
Integrates Retrofit for networking and Room for local database storage.
🚀 Tech Stack
Language: Kotlin
UI Framework: Jetpack Compose
Dependency Injection: Hilt
Networking: Retrofit with OkHttp
Local Database: Room
Testing:
JUnit 5
Mockito
MockK
Kotlin Coroutines Test
Build System: Gradle (Kotlin DSL)
📦 Project Structure
graphql
نسخ الكود
DemoApp/
├── data/                     # Data handling logic
│   ├── datasource/           # Local & remote data sources
│   ├── dto/                  # Data transfer objects (API response models)
│   ├── mapper/               # Mapping between layers (DTO <-> Domain)
│   ├── repo/                 # Repository implementations
├── domain/                   # Business logic
│   ├── entity/               # Core business entities
│   ├── repo/                 # Repository interfaces
│   ├── usecase/              # Use cases
├── presentation/             # UI layer
│   ├── view/                 # Jetpack Compose screens
│   ├── viewmodel/            # ViewModel and state management
│   ├── state/                # UI states
│   ├── model/                # UI-specific models
├── di/                       # Hilt modules for dependency injection
├── utils/                    # Utility classes and helpers
├── build.gradle.kts          # Gradle configuration
└── README.md                 # Project documentation
🛠️ Setup Instructions
Clone the Repository
bash
نسخ الكود
git clone https://github.com/mrahmedmamdouh/DemoApp.git
cd DemoApp
Open in Android Studio
Open the project in Android Studio.
Sync Gradle to download dependencies.
Run the Application
Connect an Android device or start an emulator.
Click Run (Shift + F10).
🧪 Testing
The project includes robust unit tests for ViewModels, Use Cases, and Repositories.

Run Unit Tests
bash
نسخ الكود
./gradlew test
Run Instrumentation Tests
bash
نسخ الكود
./gradlew connectedAndroidTest
📖 Documentation
Jetpack Compose: Compose Docs
Hilt (DI): Hilt Docs
Retrofit: Retrofit Docs
Room Database: Room Docs
