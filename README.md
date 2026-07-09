# 🌊 NewsWave — Android News App

NewsWave is a modern, responsive, and native Android application written in Java. It allows users to browse top news headlines across multiple categories, search for specific topics, and read full articles in an embedded view.

---

## 🚀 Features

- **Dynamic Categories**: Quickly filter news by 7 distinct topics: `General`, `Business`, `Sports`, `Technology`, `Health`, `Entertainment`, and `Science`.
- **Search Support**: Input custom queries in the search bar to find relevant stories globally.
- **Embedded Web View**: Read the full article directly inside the app using an integrated web view, keeping the user experience seamless.
- **Offline Mode Detection**: Detects network connectivity issues and prompts the user with a clean "No Internet" screen.
- **Pull to Refresh**: Swipe down to easily reload and fetch the latest articles.
- **Shuffled Articles**: Shuffles loaded articles on every request to provide a fresh presentation of content.
- **Smooth Splash Screen**: A customized 3-second splash screen designed with Edge-to-Edge interface support.

---

## 🛠️ Tech Stack & Dependencies

The project is built using native Android development patterns in Java:

- **Network Requests**: [Volley](https://github.com/google/volley) for fast, asynchronous HTTP requests to the backend server.
- **JSON Serialization**: [Gson](https://github.com/google/gson) by Google to parse the API response into structured Java objects.
- **Image Caching & Loading**: [Picasso](https://square.github.io/picasso/) by Square for downloading, caching, and showing article thumbnails in lists.
- **UI Components**:
  - `RecyclerView` for memory-efficient scrollable lists.
  - `SwipeRefreshLayout` for standard pull-to-refresh gestures.
  - `LinearProgressIndicator` to display clean network loading states.
  - Native `WebView` for in-app article reading.

---

## 📂 Codebase Structure

Here is an overview of the key source files within the project:

```text
app/src/main/
├── java/com/example/newsapp/
│   ├── MainActivity.java        # Core dashboard controller; handles categories, search, Volley requests
│   ├── NewsFullActivity.java    # Screen displaying the selected news article inside a WebView
│   ├── SplashActivity.java      # Welcome screen displaying brand identity before transitioning to Main
│   ├── NewsRecyclerAdaptor.java # Adapter mapping News Article items into the RecyclerView layout
│   └── NewsResponse.java        # GSON Model matching the backend API response schema
│
└── res/
    ├── layout/
    │   ├── activity_main.xml       # Main layout with category buttons, search bar, and RecyclerView
    │   ├── activity_news_full.xml  # Layout hosting the WebView for full article viewing
    │   ├── activity_splash.xml     # Splash screen UI layout
    │   └── news_recycler_row.xml   # UI design for a single news article card in the list
    └── values/
        └── colors.xml              # Global color definitions (Primary brand colors)
```

---

## 🔌 API Integration

The app connects to a custom Vercel-hosted proxy backend:
- **Base Endpoint**: `https://news-wave-back-end.vercel.app/news`
- **Category Query**: `https://news-wave-back-end.vercel.app/news?catagory={CATEGORY}` (e.g., `BUSINESS`)
- **Search Query**: `https://news-wave-back-end.vercel.app/news?q={QUERY}`

*Note: The project also includes the `News-API-Java` library dependency in the gradle configuration for future integrations directly with NewsAPI.*

---

## 🏁 Getting Started

### Prerequisites
- **Android Studio** (Koala or newer recommended)
- **Android SDK** (Min SDK: 24, Target SDK: 34)
- **Java JDK 8** or higher

### Steps to Run
1. **Clone the Repository**:
   ```bash
   git clone https://github.com/ArslanRude/NewsWavew_NewsApp_in_Java.git
   ```
2. **Open in Android Studio**:
   - Open Android Studio, select **File > Open**, and select the project directory.
   - Allow Gradle to sync and build project dependencies.
3. **Run the App**:
   - Connect an Android device or start an emulator.
   - Click the **Run** button (green play icon) in the Android Studio toolbar.
