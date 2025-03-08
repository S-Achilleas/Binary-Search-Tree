# Word Frequency Analyzer with Self-Balancing BST

This project implements a word frequency counter using a self-balancing Binary Search Tree (BST) to efficiently manage and analyze text data. It supports dynamic word insertion, stop-word filtering, and provides statistical insights such as maximum frequency and mean occurrence.

## Core Features

- **Word Frequency Tracking**: Count occurrences of each word in a text file, ignoring case.
- **Stop-Word Management**: Exclude common words (e.g., "the", "and") from analysis using a linked list.
- **Self-Balancing BST**: Automatically reorganizes the tree during searches to keep frequently accessed words near the root (similar to a splay tree).
- **Sorting Options**:
  - **Alphabetical Order**: Print words lexicographically.
  - **Frequency Order**: Use quicksort to rank words by occurrence count.
- **Statistical Metrics**: Calculate total words, distinct words, max frequency, and mean frequency.

## Key Components

- **Data Structures**:
  - `BST`: Self-balancing tree for storing `WordFrequency` objects. Uses rotations (`rotL`, `rotR`) for reorganization.
  - `List`: Linked list for managing stop words.
  - `stack`: Iterative tree traversal for efficiency.
- **Classes**:
  - `WordFrequency`: Tracks a word and its count, linked to a `Key` for comparisons.
  - `Key`: Encapsulates word strings for standardized comparison operations.
  - `Sort`: Quicksort implementation to sort words by frequency.
- **Interface**:
  - `WordCounter`: Defines core operations like insertion, search, and reporting.

- **Technical Highlights**
- Efficient Reorganization: Frequently searched words move closer to the BST root, reducing future access times.
- Stop-Word Filtering: Improves analysis relevance by ignoring common words.
- Memory Efficiency: Uses iterative traversal (stack) instead of recursion to avoid stack overflow for large datasets.
