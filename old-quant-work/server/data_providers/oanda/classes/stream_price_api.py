import os
from dotenv import load_dotenv
from data_providers.oanda.classes.base_price_api import OandaApiPrice

load_dotenv()
api_key = os.getenv('OANDA_API_KEY')
account_id = os.getenv('OANDA_ACCOUNT_ID')
oanda_url = os.getenv('OANDA_STREAM_URL')

headers = {
    "Authorization": f"Bearer {api_key}",
    "Content-Type": "application/json"
}

class StreamApiPrice(OandaApiPrice):
    
    def __init__(self, api_object):
        super().__init__(api_object)
        
    def __repr__(self):
        return f"StreamApiPrice {self.ask} {self.bid}"    
    
def stream_prices(pairs_list):
    
    print("Start price stream...")
    
    params = dict(
        instruments=','.join(pairs_list)
    )
    
    url = f"{oanda_url}/accounts/{account_id}/pricing/stream"
    
    response = requests.get(url, params=params, headers=headers, stream=True)
    
    for price in response.iter_lines():
        if price:
            decoded_price = json.loads(price.decode('utf-8'))
            
            if 'type' in decoded_price and decoded_price['type'] == 'PRICE':
                print(StreamApiPrice(decoded_price))