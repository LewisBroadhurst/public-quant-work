## Motivation - Concurrency and Parallelism
Why do we need multiple threads?
1. Responsiveness
   - Achieved via Concurrency with multiple threads

2. Performance - Parallelism
    - Achieved via Parallel execution of the multiple threads

Multithreading allows us to process multiple inputs at the same time.
e.g. An ecommerce store with 1000s of users. If 2 users purchase at the same time,
one user will be stuck waiting for the other to finish. This is a bad user experience
and could be processed in parallel with multiple threads.

Responsiveness is particularly important in UI applications. Think of Netflix.
Concurrency allows us to complete multiple tasks at the same time.
Can complete complex tasks much faster & in a shorter period of time.

Multithreaded programming is fundamentally different from single-threaded programming.

## What threads are?
A thread contains two things:
1. A stack
2. Instruction pointer

A multithreaded application comes with multiple threads. 
Each thread then has its own stack and instruction pointer.

Stack:
- Region in memory where local variables are stored, and passed into functions.

Instruction pointer:
- Points to the next instruction to be executed.

___

## Context Switch
Each application we run on our computer is a process and run imdependantly from
other processes.
Usually there are much more processes than CPU cores.

Each process may have one or more threads, these are competing to be the executed 
thread by the CPU.
Act of processing a thread, stopping it, and then starting another thread is called
a Context Switch.
It is a very expensive computational process and is the price of multitasking and 
concurrency. 
When switching between threads, the CPU has to store data for one thread and 
restore the data for another.

Too many threads can lead to thrashing.
Thrashing is when the CPU spends more time switching between threads than actually
producing meaningful work.

Threads consume less resources than processes.
Context switching between threads in the same process is much faster than between
different processes.

## Thread Scheduling
Example: Music Player and Text Editor. We have one core.
- Music player = two threads. One for playing music, one for the UI.
- Text editor = two threads. One for the UI, one for saving file every 2 seconds.

#### First come, first served approach
- Problem: if long thread comes first can cause starvation for other threads.
   - Especially important for UI threads.

#### Shortest job first approach
- Problem: UI threads will have lots of small jobs.
   - Larger threads will never run or be starved for a long time.

#### 'Real life' approach
- OS divides time into short epochs of time 
- In each epoch the OS will decide which thread to run.
  - Note not all threads will run in each epoch.
  - Threads that are not run or complete in the last epoch will be prioritised to run 
  in the next epoch.

- Decision determined by: `Dynamic Property = Static Priority + Bonus`
  - Static Priority: Priority of the thread set by developer

___

## When to prefer multithreading to multiprocessing?
- If tasks share a lot of data, it is easier to share data between threads than 
  between processes.
- Threads much faster to create and destroy than processes.

## When to prefer multiprocessing to multithreading?
- Security and stability are of a high importance.
- Tasks are unrelated to each other.
