# Gradient Circle Progressbar Android Project

This repository contains an Android project that demonstrates a custom view called "Gradient Circle Progressbar". The Gradient Circle Progressbar is a visually appealing and customizable progress indicator that displays a circular progress bar with a gradient color fill.

<image src=https://user-images.githubusercontent.com/20221469/56956837-eb4a6880-6b62-11e9-87a3-4ba252255f8f.gif width=225 height=400>

## Features

- Customizable progress values and animation duration.
- Smooth animation of the progress bar with a gradient fill.
- Support for different gradient colors and configurations.
- Customizable background color and stroke width.
- Adjustable start and end angles for different progress bar shapes.
- Flexible and easy-to-use API for integration into Android applications.
- User-friendly UI with a visually appealing design.
  
 ### how to use

1. Clone or download the repository.
2. Open the project in Android Studio.
3. Build and run the app on an Android device or emulator.
4. Explore the various customization options and interact with the Gradient Circle Progressbar.

 
 ```xml
 <com.example.circlegradientprogess.CircularProgressBar
        android:id="@+id/circularProgressBar"
        android:layout_width="300dp"
        android:layout_height="300dp"
        app:circlebackgroundcolor="#ecedee"
        app:circlestrokewidth="16dp"
        app:endcolor="@color/colorAccent"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:progess="40"
        app:roundcorner="true"
        app:startcolor="@color/colorPrimaryDark">
```
## Usage

- Customize the appearance of the Gradient Circle Progressbar by modifying the gradient colors, animation duration, and progress values.
- Integrate the Gradient Circle Progressbar into your own Android projects to provide visually appealing and interactive progress indicators.
- Experiment with different configurations, such as start and end angles, stroke width, and background color, to achieve desired visual effects.
  
##  Developer
  jignesh khunt
  (jigneshkhunt13@gmail.com)
  
##  License

Copyright 2019 jignesh khunt

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
