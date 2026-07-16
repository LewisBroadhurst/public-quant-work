import os
import sys
sys.path.append("../")

from data_providers.oanda.classes.oanda_api import OandaApi
from data_providers.oanda.classes.stream_price_api import StreamApiPrice
from data_providers.oanda.streaming.oanda_stream_prices import stream_prices

### The file where this is run from, is the point at which everything else should reference
### This is why there are issues when trying to run the application from specific folders. 
### The main (or 'main' equivalent) should be in the root directory            

if __name__ == '__main__': 
    print(os.getcwd())
    print("Start...")
    api = OandaApi()
    stream_prices(['EUR_USD'])
    print("End...")
    