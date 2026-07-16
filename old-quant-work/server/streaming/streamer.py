import json
import threading

def load_settings():
    with open("./config/settings.json", "r") as f:
        return json.loads(f.read())
    
def run_price_stream():
    print("run_price_streamer START")
    
    print("run_price_streamer END")
    
    
def run_streamer():
    settings = load_settings()
    
    shared_prices = {}
    shared_price_events = {}
    shared_prices_lock = threading.Lock()
    
    for p in settings['pairs'].keys():
        shared_price_events[p] = threading.Event()
        shared_price_events[p] = {}
        
    threads = []
    
    price_stream_thread = threading.Thread(target=run_price_stream)
    threads.append(price_stream_thread)
    price_stream_thread.start()
    
    for t in threads:
        t.join()
    
    print("Complete")