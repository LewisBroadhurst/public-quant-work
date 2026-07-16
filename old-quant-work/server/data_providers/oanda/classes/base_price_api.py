class OandaApiPrice:
    
    def __init__(self, api_object):
        self.instrument = api_object['instrument']
        self.ask = float(api_object['asks'][0]['price'])
        self.bid = float(api_object['bids'][0]['price'])
        
        