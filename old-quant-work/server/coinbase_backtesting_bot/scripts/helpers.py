import sys

def convert_coinbase_granularity_into_time(granularity: str):
    granularities = {
        "ONE_MINUTE": 60,
        "FIVE_MINUTE": 300,
        "FIFTEEN_MINUTE": 900,
        "THIRTY_MINUTE": 1800,
        "ONE_HOUR": 3600,
        "TWO_HOUR": 7200,
        "SIX_HOUR": 21600,
        "ONE_DAY": 86400
    }
    
    if granularity in granularities.keys():
        return granularities[granularity]
    
    sys.exit("No granularity found")