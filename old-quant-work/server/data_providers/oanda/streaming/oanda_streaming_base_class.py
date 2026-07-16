import threading
# Missing the log wrapper

# Class that acts as the base Class for Streaming via OANDA
# Based on threading.Thread
class OandaStreamingBaseClass(threading.Thread):
    
    # What are each of these params for and where should they come from?
    # All need type inference
    def __init__(self, shared_prices, price_lock: threading.Lock, price_events, logname):
        super().__init__()
        
        self.shared_prices = shared_prices
        self.price_lock = price_lock
        self.price_events = price_events
        # self.log = LogWrapper(logname)
        
    def log_message(self, message, error = False):
        if error == True:
            self.log.logger.error(message)
        else:
            self.log.logger.information(message)