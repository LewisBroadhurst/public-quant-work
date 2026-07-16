import os
from dotenv import load_dotenv

import threading
import requests
import json

load_dotenv()
api_key = os.getenv('OANDA_API_KEY')
account_id = os.getenv('OANDA_ACCOUNT_ID')
oanda_url = os.getenv('OANDA_STREAM_URL')

headers = {
    "Authorization": f"Bearer {api_key}",
    "Content-Type": "application/json"
}

class PriceStreamer(threading.Thread):
    
    def __init__(self, shared_prices, price_lock: threading.Lock, price_events):
        super().__init__()
        
        self.pairs_list = shared_prices.keys()
        self.price_lock = price_lock
        self.price_events = price_events
        self.shared_prices = shared_prices
        self.log = LogWrapper("PriceStreamer")
        print(self.pairs_list)
        
    def stream_prices(self):
    
        print("Start price stream...")
        
        params = dict(
            instruments=','.join(self.pairs_list)
        )
        
        url = f"{oanda_url}/accounts/{account_id}/pricing/stream"
        
        response = requests.get(url, params=params, headers=headers, stream=True)
        
        for price in response.iter_lines():
            if price:
                decoded_price = json.loads(price.decode('utf-8'))
                
                if 'type' in decoded_price and decoded_price['type'] == 'PRICE':
                    print(StreamApiPrice(decoded_price))