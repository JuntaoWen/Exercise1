#  Exercise 1- MobileShop: Android Challenge Solution

## Juntao Wen 1296844

## https://github.com/JuntaoWen/Exercise1

## Tech Stack

- Kotlin 2.0.21
- Jetpack Compose (Material 3)
- Android Gradle Plugin 8.7.2
- Min SDK 26, Target SDK 35

## Prerequisites

- Android Studio Ladybug (or newer)
- JDK 17
- Android SDK Platform 35 installed
- A running Android emulator or physical Android device

## Configuration

No API keys or environment variables are required.

Before running, make sure drawable filenames are lowercase (Android resource rule), for example:

- `nike_air_force_1_07.png`
- `luka_5.png`
- `pegasus_42.png`

## Run Instructions

### Run in Android Studio


1. Open Android Studio.
2. Select **Open** and choose this project folder.
3. Let Gradle sync complete.
4. Start an emulator or connect an Android phone.
5. Select the `app` run configuration.
6. Click **Run**.




## Screenshots

<img width="440" height="927" alt="splash" src="https://github.com/user-attachments/assets/f525cba3-8248-4e5a-9a94-f96e13b361a5" />
- Splash screen

  
<img width="440" height="927" alt="product_detail" src="https://github.com/user-attachments/assets/381af2c0-0c9e-48ec-9bd0-1d4668a4bc8a" />
- Home screen (product grid)

  
<img width="440" height="927" alt="home" src="https://github.com/user-attachments/assets/c3237e0b-25f4-44a7-8f00-1b179add8f34" />
- Product detail screen


<img width="440" height="927" alt="bag" src="https://github.com/user-attachments/assets/b01860d9-a425-4bf6-8019-f9df7848a77e" />
- Bag screen



## Exact Project Structure

This application strictly adheres to modern Android development best practices, utilizing a unidirectional data flow and the MVVM (Model-View-ViewModel) architectural pattern to ensure a clean separation of concerns.

```text
Exercise1/
├── .gitignore
├── build.gradle.kts
├── gradle.properties
├── settings.gradle.kts
├── README.md
├── gradle/
│   └── wrapper/
│       └── gradle-wrapper.properties
└── app/
    ├── build.gradle.kts
    ├── proguard-rules.pro
    └── src/
        └── main/
            ├── AndroidManifest.xml
            ├── java/com/example/mobileshop/
            │   ├── MainActivity.kt
            │   ├── data/
            │   │   ├── CartItem.kt
            │   │   ├── SampleCatalog.kt
            │   │   ├── Shoe.kt
            │   │   └── ShoeColor.kt
            │   └── ui/
            │       ├── MobileShopApp.kt
            │       ├── screens/
            │       │   ├── BagScreen.kt
            │       │   ├── HomeScreen.kt
            │       │   ├── ProductDetailScreen.kt
            │       │   └── SplashScreen.kt
            │       ├── shop/
            │       │   └── ShopViewModel.kt
            │       ├── theme/
            │       │   ├── Color.kt
            │       │   ├── Theme.kt
            │       │   └── Type.kt
            │       └── util/
            │           └── MoneyFormat.kt
            └── res/
                ├── drawable/
                │   ├── luka_5.png
                │   ├── nike_air_force_1_07.png
                │   └── pegasus_42.png
                └── values/
                    ├── strings.xml
                    └── themes.xml
```
Module Responsibilities
- data/ (The Model): This package is exclusively responsible for the application's data layer. Since the exercise requires no external database, SampleCatalog.kt acts as our local repository, serving instances of Shoe data classes to the rest of the application.

- ui/screens/ (The View): Contains the Jetpack Compose functions that draw the user interface. These screens are completely stateless regarding business logic; they only observe state provided by the ViewModel and trigger user intent events (like clicking "Add to Bag").

- shop/ShopViewModel.kt (The ViewModel): The brain of the application. It holds the MutableStateList of items currently in the user's shopping bag, processes logic such as calculating the final price total, and exposes this state to the UI layer to reactively update the screens.

- ui/theme/: Centralizes all styling. By keeping colors and typography here, the application maintains a consistent design language across all screens, adhering to the "App Design and Arts" grading criteria.
