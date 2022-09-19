
<p align="center">
  <img width="460" height="300" src="/images_for_doc/icon_white_wide.png">
</p>

# Paper in Economics
Android App to meet a new and fascinating paper, article, and data in economics.


![layout_image](/images_for_doc/layout_image.jpg)


## Why not Twitter?

Twitter is for floating information, and an AI algorithm is implemented for user engagement, making it hard to find related content and consume content thoroughly.




# for Developer

## Architecture

### Overview

There is another repository for the scraping script.

![architecture_overview](/images_for_doc/architecture_overview.jpg)

### Layout

![architecture_layout_file_diagram](/images_for_doc/architecture_layout_file_diagram.jpg)

## Branching Strategy
we follow the GitHub flow branch strategy.
For detail, please refer to [the official page](https://docs.github.com/en/get-started/quickstart/github-flow)

The primary branch is the main branch.

## recommended comment style

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