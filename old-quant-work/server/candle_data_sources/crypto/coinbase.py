from dotenv import dotenv_values

secrets = dotenv_values(".env")

COINBASE_API_KEY = secrets['COINBASE_API_KEY']

print(COINBASE_API_KEY)