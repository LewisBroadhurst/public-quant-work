# React + TypeScript + Vite + SWC + AntD
- What is SWC?

## Goals
- TradingView type charts where I can analyse instruments
- Web scraping news displayed
- Kick off simulations and display results for EU/GU
- Metatrader account display
- Execute trades via REST/FIX w/ risk calculator, this could actually be a really great idea.
    - Create the Metatrader assistants built into the web
    - Trade Copier
    - Add an institutional touch. Yield profiles to see how long it takes for a trade to go profitable.
        - If after 10 mins 90% of trades tend to SL, close at 10mins.
        - Monte Carlo Simulations
        - Will need detailed explanations for how these will work, get into the udemy course and see how they can be applied

- Getting python plots into FE UI, getting data from an S3 bucket.
- Run a backtest then see progression of backtest via dashboard.
- Can then view that dashboard at a later date.
- Implement a message cue for this.

## Plotting charts
- ACtaully really easy. 
- Get data from API call to python BE.
- Use plotly.js etc and put data into plotly.