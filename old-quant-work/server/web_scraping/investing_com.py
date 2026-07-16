from bs4 import BeautifulSoup
import pandas as pd
import requests

# May be better to get these into dictionaries and then into a dataframe
# Why are DFs better? Table like data
# cloudscraper - pip package that bypasses cloudflare

def investing_com_nvidia():
    url = "https://www.investing.com/equities/nvidia-corp"
    response = requests.get(url)
    soup = BeautifulSoup(response.content, "html.parser")
    
    divs = soup.select("dd[data-test='marketCap'] a span")
    marketcap_content = ["Market Cap", "NVDA", divs[2].text, divs[3].text]
            
    print(marketcap_content)
 
def investing_com_apple():
    url = "https://www.investing.com/equities/apple-computer-inc"
    response = requests.get(url)
    soup = BeautifulSoup(response.content, "html.parser")
    
    divs = soup.select("dd[data-test='marketCap'] a span")
    marketcap_content = ["Market Cap", "AAPL", divs[2].text, divs[3].text]
            
    revenue_divs = soup.select("dd[data-test='revenue'] span")
    revenue_content = ["Revenue", "AAPL", revenue_divs[2].text, revenue_divs[3].text]
    
    print(marketcap_content) 
    print(revenue_content)  
    
investing_com_nvidia()
investing_com_apple()
# Need to look at the data-test values in the html