# Task 1 & 2
## Whitespace Tokenizer: What are the problems in the output?
Output:
  * I
  * saw
  * a
  * man.
  * "I'm
  * Sam,"
  * he
  * said.
Problems: Tokens contain punctuation marks ('said.', '"I'm') which does not make sense.

## BreakIterator Tokenizer
Solves the problem with punctuation marks. However, spaces are recognized as tokens.

# Task 3
## WhitespaceTokenizer
see WhitespaceTokenizerPipeline. The tokenizer has problems with the whitespace & special characters in the html.

## BreakIteratorTokenizer
see BreakIteratorTokenizerPipeline