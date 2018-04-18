# JavaSoundComparator
This is a small project I had done when I was doing the course "Object Oriented Programming in Java" taught by Prof. Anuradha Ravi

This takes in two .wav sound files and compares their signals in various ways to report how similar they are. 

This implementation has four different Comparisons:
1. Comparing Directly - Sample to Sample
2. Comparing the Relative Amplitudes (Difference Method)
3. BitShifting and Iteratively Comparing Sample to Sample.(sliding one signal over the other)
4. BitShifting and Iteratively Comparing the Relative Aplitudes

The Comparison module gives out % similarity.
eg: If the files are the same, it says 100% similar. If they are slightly different it says about 70% similar etc. Completely different audio files are generally 10-20% similar.
