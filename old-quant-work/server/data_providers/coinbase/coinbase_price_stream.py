from coinbase.wallet.client import Client
from dotenv import load_dotenv

import os
load_dotenv()

api_key = os.getenv('COINBASE_API_KEY')
api_secret = os.getenv('COINBASE_API_SECRET')

client = Client(api_key, api_secret)

spot_price = client.get_spot_price(currency_pair = 'BTC-USD')

get_coinbase_fiat_currencies = client.get_currencies()

print(spot_price)