from data_providers.oanda.base_price_api import OandaApiPrice

class PriceApi(OandaApiPrice):
    
    def __init__(self, api_object, homeConversions):
        
        super().__init__(api_object)
        
        base_instrument = self.instrument.split('_')[1]
        
        for hc in homeConversions:
            if hc['currency'] == base_instrument:
                self.sell_conv = float(hc['positionValue'])
                self.buy_conv = float(hc['positionValue'])
                
    def __repr__(self):
        return f"ApiPrice() {self.instrument} {self.ask} {self.bid} {self.sell_conv} {self.buy_conv}"