# Practical Work on Sorting Methods

[Português](README.md) | [English](README_EN.md)

This project aims to implement and analyze the performance of six sorting algorithms for the Algorithms and Data Structures 2 course.

Each algorithm has its own characteristics and efficiencies, varying according to the size and nature of the data to be sorted.

The algorithms implemented in this project are applied to sort a list of NBA players, considering different sorting criteria. The performance of each method is evaluated based on execution time, number of comparisons, and number of movements performed during sorting.

The sorting algorithms implemented are briefly described below:

## Bubble Sort:
A simple sorting algorithm that compares adjacent pairs of elements and swaps them if they are in the wrong order. This process is repeated until no more swaps are needed.

## Heap Sort:
A sorting algorithm based on a data structure called heap (partially ordered binary tree). The algorithm builds a maximum heap and then extracts the maximum element, rebuilds the heap, and repeats this process until all elements are sorted.

## Insertion Sort:
A sorting algorithm that iteratively inserts each element of the list into the correct position, comparing it with the already sorted elements to its left.

## Quick Sort:
A divide-and-conquer-based sorting algorithm that selects a 'pivot' and partitions the list around the pivot, placing all elements smaller than the pivot to the left and all larger elements to the right. This process is applied recursively to the resulting sublists.

## Selection Sort:
A sorting algorithm that iteratively selects the smallest (or largest) element from the list and swaps it with the first unsorted element.

## Merge Sort:
A divide-and-conquer-based sorting algorithm that splits the list into two halves, sorts each half recursively, and then merges the sorted halves into a single sorted list.

## Queue:
A queue is a data structure that follows the "FIFO" (First-In-First-Out) approach, which means that the first element inserted into the queue is the first to be removed. Similar to a queue of people in a supermarket, where the person who arrives first is the first to be served. The basic operations in a queue are inserting elements at the end (enqueue) and removing elements from the front (dequeue).

## Stack:
A stack is a data structure that follows the "LIFO" (Last-In-First-Out) approach, which means that the last element inserted into the stack is the first to be removed. It is similar to a stack of plates, where the last plate placed is the first to be taken out. The basic operations in a stack are inserting elements at the top (push) and removing elements from the top (pop).

## List:
A list is a data structure that stores a collection of elements in an ordered sequence. The list can be implemented in various ways, such as an array-based list or a linked list. The list allows for inserting and removing elements at any position and also supports retrieving elements by their position. Additionally, the list can have a variable size.

## Linked List:
A linked list is a data structure composed of nodes that are interconnected through references. Each node contains a value and a pointer to the next node in the sequence. The linked list can be singly linked (each node points only to the next) or doubly linked (each node points to both the next and previous nodes). The linked list is efficient for inserting and removing elements, especially at intermediate positions, but retrieving elements requires traversing the list from the beginning.

## Doubly Linked List:
A doubly linked list is an extension of the linked list, where each node has a pointer to the previous and next nodes in the sequence. This allows traversing the list in both directions, facilitating the insertion and removal of elements at any position. However, the doubly linked list requires more storage space due to the additional pointer in each node.
