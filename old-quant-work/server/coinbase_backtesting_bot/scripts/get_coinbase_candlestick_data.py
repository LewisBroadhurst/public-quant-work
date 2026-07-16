import os
import datetime as dt
import sys
import time
import pickle
import pandas as pd

sys.path.append("../../")

from coinbase.rest import RESTClient
from json import dumps
from dotenv import dotenv_values, load_dotenv
from math import ceil

from coinbase_backtesting_bot.scripts.helpers import convert_coinbase_granularity_into_time

load_dotenv()
secrets = dotenv_values(".env")

api_key = os.getenv('COINBASE_API_KEY_TRADING_040224')
api_secret = os.getenv('COINBASE_API_SECRET_TRADING_040224')
client = RESTClient(api_key=api_key, api_secret=api_secret)
filename_prefix = "COINBASE_CANDLESTICKS"
sleep = 5

## Script to get Cryptocurrency Candles from Coinbase.
if __name__ == "__main__":

    # 1. Enter time dates the script should retrive from and to
    # e.g. dt.datetime(2023, 1, 1, 14, 30).timestamp()
    datetime_from = dt.datetime(2022, 1, 1)
    datetime_to = dt.datetime(2022, 1, 2)
    
    # 2. Enter Granularity [ONE_MINUTE... FIFTEEN_MINUTE... ONE_DAY...] and Product [BTC-USD, ETH-USD...]
    granularity = "ONE_DAY"
    product = "BTC-USD"
    
    # exit early if incorrect datetimes
    if datetime_from >= datetime_to:
        sys.exit("datetime_from must be less than datetime_to")
        
    time_range = datetime_to - datetime_from
    granularity_in_posix = convert_coinbase_granularity_into_time(granularity)
    number_of_candlesticks = int(time_range.total_seconds() / granularity_in_posix)
    number_of_requests_required = ceil(number_of_candlesticks / 300)
    
    print(f"Getting the candlestick data for {product} from {datetime_from} to {datetime_to}.")
    print(f"This will require {number_of_requests_required} requests to get {number_of_candlesticks} candlesticks.")
    
    candlestick_data = []
    
    if number_of_requests_required == 1:
        candles_response = client.get_candles(product, int(datetime_from.timestamp()), int(datetime_to.timestamp()), granularity)
        candlestick_data = candles_response['candles']
        
    else:
        length_of_time_for_segment = time_range / number_of_requests_required
        current_segment_start = datetime_from
        current_segment_end = datetime_from + length_of_time_for_segment
        
        for i in range(1, number_of_requests_required):
            candles_response = client.get_candles(product, int(current_segment_start.timestamp()), int(current_segment_end.timestamp()), granularity)
            candles_list = candles_response['candles']
            
            current_segment_start += length_of_time_for_segment
            current_segment_end += length_of_time_for_segment
            
            candlestick_data.extend(candles_list)
            
            if i < number_of_requests_required:
                print(f"Sleeping for {sleep} seconds to not spam coinbase API on bigger calls ({i} / {number_of_requests_required}).")
                time.sleep(sleep)
                        
    filename_suffix = f"{int(datetime_from.timestamp() / 1_000_000)}_{int(datetime_to.timestamp() / 1_000_000)}.pkl"
    filename = f"{filename_prefix}_{product}_{granularity}_{filename_suffix}"
    
    print(f"Data will be saved under the filename {filename}.")
    
    with open(filename, 'wb') as file:
        
        df = pd.DataFrame(candlestick_data)
        df['start'] = df["start"].astype(int)
        df["candle_datetime"] = pd.to_datetime(df["start"], unit='s')
        
        df_renamed = df.rename(columns={'start': 'candle_open_posix'})
        df_renamed = df_renamed[["candle_datetime", "candle_open_posix", "low", "high", "open", "close", "volume"]]
        
        sorted_df = df_renamed.sort_values(by="candle_open_posix", ascending=True)
        sorted_df.reset_index(drop=True, inplace=True)
        pickle.dump(sorted_df, file)
        
    print("Data successfully pickled. Yum.")
        
        
        
        
    
    