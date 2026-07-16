import os
from data_providers.oanda.classes.oanda_api import OandaApi
from streaming.streamer import run_streamer

### The file where this is run from, is the point at which everything else should reference
### This is why there are issues when trying to run the application from specific folders. 
### The main (or 'main' equivalent) should be in the root directory  
          
### To run a script outside of the root, you must run that script from the same folder and 
### add the relevant sys.path.append to send the current wd as the project root
if __name__ == '__main__': 
    print("Start...")
    
    api = OandaApi()
    run_streamer()
    
    print("End...")
    