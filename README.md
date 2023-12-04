# Advent Of Code

Solutions to https://adventofcode.com/

### Format text input as list of strings
Copy the input text from the website, then:
```
pbpaste | sed -r 's/$/",/g' | sed -r 's/^/"/' | sed '$ s/.$//' | pbcopy
```