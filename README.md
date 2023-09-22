# Predictive-Keyboard/Autocompleter


The autocompleter takes an input from a user via Java Application and sends that prefix to a trie method that finds the most popular 
words given that prefix. Within milliseconds, the method returns the nine most used words that the user can choose from. 
Now this is not an autofill feature, this program only aims to provide suggestions for user input. 


# WordItem Implementation
Each word that is extracted from the dictionary file given via Professor Tony, has two fields. The String which contains the given word and the 
count which contains the occurrence of each word in the dictionary. My program extracts all the words from the file, creates WordItem objects, 
and then fills an array of type WordItem. This array is later used to fill the trie up with all the words.

# Trie Implementation
The trie was one of the hardest things to learn during the duration of this project, It was my first time being introduced to it. 
The trie gets filled with all the words from the WordItem array and has three fields, the children trienode, boolean aword, and ArrayList mostFreqUsed. 
The trie class has multiple methods used to help find a list of words given for each prefix entered by the user.

# How does it work?
It all starts with initialization. When the program boots up it automatically fills an array as mentioned before with words and their frequency. 
It then fills the trie up with all those words creating nodes for the given words. The array is also passed into the trie when 
it is created so it can be stored for use in methods. After that, the Java application opens up for the user to start typing. When the user enters a letter
it automatically sends that letter to a trie method called insertString. This method finds the trie node associated with the letter entered and 
then grabs the list of most popular words associated with that letter.  It does this by doing binary searches on the WordItem array using the 
prefix entered by the user to find all possible words for that prefix. For example, if the user enters “a” , all words that start with the 
letter “a” will be stored to be sorted. Once the sorting is done using the built in arrays.sort method (based on occurrence in descending order) , 
there are still too many words to choose from, so the method narrows the array to only nine words at most. After that the array list of the nine most 
popular words is returned to the user. It is a constant method call to the trie to find the most popular words, and it is very quick and efficient. 

# Challenges
The most challenging part of this project was easily finding the most popular words given a letter entered by the user. I had a hard time figuring out where to start.
But given the notes from Tony, It helped guide me. Extracting words from the file was not too hard, and working with LinkedLists and ArrayLists is stuff I have done before. 
But the trie methods were the most difficult for me. It was a new concept and it took a while for me to fully grasp what was going on whenever I was inserting words into the trie.

# Conclusion
Throughout this project of implementing the Autocompleter using Java and prefix trees, I have embarked on a fulfilling journey of learning and growth as a programmer. 
Working on this project has been an incredible experience that has enriched my understanding of data structures, algorithms, and Java programming.
From the outset, diving into the world of prefix trees was both fascinating and challenging. I grasped the concepts behind trie data structure and its efficiency in 
storing and retrieving words based on prefixes. Through trial and error, I honed my problem-solving skills as I encountered various obstacles and found creative solutions 
to overcome them. Understanding how to efficiently insert words into the trie and effectively search for suggestions based on user
input has been a rewarding experience, and it has provided me with valuable insights into algorithmic optimizations.
Moreover, this project has given me a real-world context to apply my Java programming skills. I have become more adept at working with classes, methods, and data structures in Java. 
Additionally, I have learned how to handle user input and provide real-time feedback, which is essential in building user-friendly applications.
As a student, this project has reaffirmed my passion for software development and my dedication to continuous learning. I now feel more confident in 
my programming abilities and inspired to explore further challenging projects.
In conclusion, working on the Autocompleter using Java and prefix trees has been a transformative experience. It has broadened my horizons, enriched my technical knowledge, 
and fueled my enthusiasm for programming. I am excited to take this newfound knowledge and skills into future projects and continue my journey of growth as a programmer.


# Future Improvements
-Case insensitive search
-Improved GUI
-Autofill feature that finishes word for user instead of suggestions
