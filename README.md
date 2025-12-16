# Advent of Code Kotlin Solutions

Short Kotlin implementations for the 2023 Advent of Code puzzles.

### Format text input as list of strings
Copy the input text from the website, then:
```
pbpaste | sed -r 's/$/",/g' | sed -r 's/^/"/' | sed '$ s/.$//' | pbcopy
```