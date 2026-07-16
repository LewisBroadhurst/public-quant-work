## Why stop a thread?
- Threads consume resources, even if it's not doing anything
- If a thread has completed work and the application is still running, 
we will want to clean up the thread and free up resources.
- If a thread is misbehaving, we may want to stop it.

## Thread.interrupt()
