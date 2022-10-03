
<p align="center">
  <img width="460" height="300" src="/images_for_doc/icon_white_wide.png">
</p>


# Paper in Economics
Android App to meet a new and fascinating paper, article, and data in economics.

<p align="left">
  <img width="500" height="300" src="/images_for_doc/layout_image.jpg">
</p>


## Why not Twitter?

Twitter is for floating information, and an AI algorithm is implemented for user engagement, which makes it hard to find related content and consume content thoroughly.


<br/><br/>

# for Developer

## Getting Started

- git clone
- make sure debug=ture in [DataForDev.kt](app/src/main/java/com/yoji0806/paperineconomics/DataForDev.kt) file.
- install to your device via AndroidStudio.

The differences between the deployed app and your local app is below:
- no Google login request in your local app. 
- Your local app fetch and show dummy data from local files.

If you want to develop in the same environment as the deployed app, set up below.
- make your own project in [Firebase](https://firebase.google.com/).
- download google-services.json from Firebase.
- put google-services.json in the [app directory](app).
- change "debug" into false in [DataForDev.kt](app/src/main/java/com/yoji0806/paperineconomics/DataForDev.kt) file.
- install to your device via AndroidStudio. (An emulator doesn't work with google auth request.)


## Architecture

### Overview

This Android application repo is at the bottom right of the picture below.  
There is another repository for the scraping script in Python.  
If you want to implement web scraping or recommend engine,  make a pull request in the repository below. 

[Github:[Python]: paper_scraper](https://github.com/yoji0806/paper_scraper)


<p align="left">
  <img width="500" height="300" src="/images_for_doc/architecture_overview.jpg">
</p>


### Layout

We use Navigation Drawer and Fragment component.  
If you add UI, please make it Fragment.


<p align="left">
  <img width="500" height="300" src="/images_for_doc/architecture_layout_file_diagram.jpg">
</p>

## Branching Strategy
We follow the GitHub flow branch strategy.  
For detail, please refer to [the official page](https://docs.github.com/en/get-started/quickstart/github-flow)  
The primary branch is the main branch.

## Recommended Comment Style

If you have your comment style, please follow it.  
If you don't, please follow the style below so that reviewing codes of pull requests is easy.
- Make the first comment line as the Title.
  - in title, put [add] /[update] /[clean] /[debug] /[wip] on the first.
- Make the second line blank gently.
- (optional) Write descriptive information under the third line.

ex:
```
[add] update profile image.

- add a dependency to load an image from URI.
- add safeArgs "imageUrl"
```

This style help reviewers understand the context more deeply, like below.

<p align="left">
  <img width="660" height="300" src="/images_for_doc/comment_style_from_reviewers_view.jpg">
</p>