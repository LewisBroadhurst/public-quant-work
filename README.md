# public-quant-work

A personal quant-finance and trading-systems playground, spanning 2023–2024. It collects
everything from option-pricing scripts and FX/crypto backtesting engines through to the
supporting engineering study — Java concurrency, RabbitMQ messaging, a C# trading-engine
skeleton, and a React dashboard for live price streams.

This is exploratory/learning work, not a production system. Several pieces are unfinished
by design and are kept for reference. See [Status & caveats](#status--caveats) before trying
to run anything.

---

## Repository map

| Area | Path | Language | What it is |
| --- | --- | --- | --- |
| Option pricing | [`black_scholes/`](black_scholes) | Python | Black-Scholes call/put pricer |
| Simulation | [`monte_carlo/`](monte_carlo) | Python | Monte-Carlo stock-path simulator |
| FX backtesting | [`oanda_trading_bot/`](oanda_trading_bot) | Python | OANDA candle download, indicators, charting, strategy simulators |
| Legacy archive | [`old-quant-work/`](old-quant-work) | Python / Java / C# / TS | Everything below, merged in as a git subtree |
| — server | [`old-quant-work/server/`](old-quant-work/server) | Python | Live price streaming, Coinbase data, web scraping, earlier copy of the OANDA bot |
| — client | [`old-quant-work/client/`](old-quant-work/client) | TypeScript | React + Vite dashboard consuming the OANDA price stream |
| — Java | [`old-quant-work/Java/`](old-quant-work/Java) | Java | Spring Boot app plus multithreading and RabbitMQ study notes/code |
| — C#/C++ | [`old-quant-work/c_c++_c#/`](old-quant-work/c_c++_c%23) | C# | Trading-engine server skeleton and a logging library |
| — theory | [`old-quant-work/finance_theory/`](old-quant-work/finance_theory) | Markdown / Python | Written notes on markets, plus time-value-of-money code |

---

## 1. Pricing and simulation

### Black-Scholes — [`black_scholes/`](black_scholes)

Closed-form European option pricing. [`main.py`](black_scholes/main.py) implements
`call_option_price` and `put_option_price` from spot, strike, time to expiry, risk-free
rate and volatility, using `scipy.stats.norm` for the cumulative normal.
[`overview.md`](black_scholes/overview.md) holds the background notes (1973 paper, market-neutral
strategies, delta hedging).

```bash
python black_scholes/main.py
```

### Monte-Carlo — [`monte_carlo/`](monte_carlo)

[`main.py`](monte_carlo/main.py) runs 1,000 geometric-Brownian-motion price paths over a
252-day trading year, collects them into a DataFrame, and plots the mean path as the
highest-probability forecast. [`overview.md`](monte_carlo/overview.md) covers the
probabilistic-vs-deterministic framing and what "stochastic" means here.

```bash
python monte_carlo/main.py
```

---

## 2. OANDA FX trading bot — [`oanda_trading_bot/`](oanda_trading_bot)

The most developed piece of work in the repo: an FX backtesting stack built on OANDA candle
data. Price data is downloaded to pickled DataFrames, then replayed candle-by-candle
(`df.iterrows()`) through strategy simulators.

**Data collection**
- [`download_candle_data/api.py`](oanda_trading_bot/download_candle_data/api.py) — `OandaApi`
  wrapper: authenticated session, `fetch_candles`, and `get_candles_df` which flattens
  mid/bid/ask OHLC into a DataFrame.
- [`collect_data.py`](oanda_trading_bot/collect_data.py) — entry point that runs a bulk
  collection across instruments and granularities.

**Charting and indicators** — [`charting/`](oanda_trading_bot/charting)
- [`indicators.py`](oanda_trading_bot/charting/indicators.py) — Bollinger Bands, ATR, RSI
  (with EMA smoothing), EMA, MACD.
- [`candle_patterns.py`](oanda_trading_bot/charting/candle_patterns.py) — candle body
  properties, order-block detection, engulfing and engulfing-order-block patterns.
- [`price_time_chart.py`](oanda_trading_bot/charting/price_time_chart.py) — Plotly candle and
  line charts, including the string-timestamp trick that removes weekend gaps.

**Strategies** — [`backtesting_strategies/`](oanda_trading_bot/backtesting_strategies)
- [`concepts/market_structure.py`](oanda_trading_bot/backtesting_strategies/concepts/market_structure.py) —
  a market-structure state machine tracking higher highs / higher lows (and the bearish
  mirror), detecting break-of-structure continuations and reversals, and using Fibonacci
  retracement levels (0.25 / 0.62) to time entries. Caps concurrent positions at three.
- [`market_structure/ms_v1.py`](oanda_trading_bot/backtesting_strategies/market_structure/ms_v1.py) —
  the runner that drives the state machine over a 5-minute DataFrame and writes
  `trade` / `sl` / `tp` columns.
- [`concepts/daily_highs_lows.py`](oanda_trading_bot/backtesting_strategies/concepts/daily_highs_lows.py) —
  rolling previous-day highs/lows from 288 five-minute candles per session.
- [`inducements_v1/`](oanda_trading_bot/backtesting_strategies/inducements_v1) — a liquidity
  "inducement" strategy: trade the sweep of a previous daily high/low inside a defined
  session window. [`notes.md`](oanda_trading_bot/backtesting_strategies/inducements_v1/notes.md)
  tracks the plan and what is still outstanding.

The development diary for this bot lives at
[`old-quant-work/server/oanda_trading_bot/README.md`](old-quant-work/server/oanda_trading_bot/README.md).

---

## 3. Legacy archive — [`old-quant-work/`](old-quant-work)

Merged into this repository as a git subtree (commit `80a88c3e`). It is a snapshot of an
earlier multi-language project; the top-level `oanda_trading_bot/` above is a later fork of
the copy under `old-quant-work/server/`, so the two diverge slightly.

### 3.1 Python server — [`old-quant-work/server/`](old-quant-work/server)

**Live price streaming (OANDA)** — [`data_providers/oanda/`](old-quant-work/server/data_providers/oanda)
Several iterations of the same idea, kept side by side:
- [`classes/oanda_api.py`](old-quant-work/server/data_providers/oanda/classes/oanda_api.py) — REST
  candle API, now reading credentials from `.env` rather than a hardcoded constants module.
- [`classes/price_streamer.py`](old-quant-work/server/data_providers/oanda/classes/price_streamer.py) —
  a `threading.Thread` subclass consuming the OANDA pricing stream into a shared dict guarded
  by a lock, with per-instrument `threading.Event`s to signal new prices.
- [`streaming/oanda_stream_prices.py`](old-quant-work/server/data_providers/oanda/streaming/oanda_stream_prices.py) —
  the simpler functional version of the same stream.
- [`classes/`](old-quant-work/server/data_providers/oanda/classes) and
  [`shared/`](old-quant-work/server/data_providers/shared) — a small price-object hierarchy
  (`BasePriceApi` → `PriceApi` / `StreamApiPrice`) normalising bid/ask payloads.
- [`streaming/streamer.py`](old-quant-work/server/streaming/streamer.py) — thread orchestration
  driven by [`config/settings.json`](old-quant-work/server/config/settings.json), which defines
  the traded pairs (EUR/USD, GBP/USD, EUR/CHF, USD/CHF) and per-trade risk.

**Crypto** — [`coinbase_backtesting_bot/`](old-quant-work/server/coinbase_backtesting_bot)
[`scripts/get_coinbase_candlestick_data.py`](old-quant-work/server/coinbase_backtesting_bot/scripts/get_coinbase_candlestick_data.py)
pages through the Coinbase REST API to pull candles between two datetimes at a chosen
granularity, rate-limiting between requests and pickling the result;
[`helpers.py`](old-quant-work/server/coinbase_backtesting_bot/scripts/helpers.py) maps Coinbase
granularity names to seconds. Exploratory analysis lives in
[`notebooks/start.ipynb`](old-quant-work/server/coinbase_backtesting_bot/notebooks/start.ipynb).
[`data_providers/coinbase/coinbase_price_stream.py`](old-quant-work/server/data_providers/coinbase/coinbase_price_stream.py)
is a minimal spot-price client.

**Web scraping** — [`web_scraping/investing_com.py`](old-quant-work/server/web_scraping/investing_com.py)
pulls market cap and revenue for NVDA/AAPL from investing.com with BeautifulSoup;
[`web_scraping.md`](old-quant-work/server/web_scraping/web_scraping.md) has the accompanying notes.

**Threading notes** — [`misc_scripts/streaming_threads.py`](old-quant-work/server/misc_scripts/streaming_threads.py)
documents the Python working-directory / `sys.path` lesson that caused a lot of pain early on.

### 3.2 React dashboard — [`old-quant-work/client/`](old-quant-work/client)

React 18 + TypeScript + Vite (SWC) + Tailwind + Redux Toolkit, with Plotly for charting.
- [`components/streaming/StreamingPanel.tsx`](old-quant-work/client/src/components/streaming/StreamingPanel.tsx) —
  consumes the OANDA pricing stream directly in the browser via `fetch` and a
  `ReadableStream` reader.
- [`components/charts/CandlestickPlot.tsx`](old-quant-work/client/src/components/charts/CandlestickPlot.tsx) —
  Plotly candlestick rendering.
- [`components/statistics/StatisticPanel.tsx`](old-quant-work/client/src/components/statistics/StatisticPanel.tsx),
  [`components/header/Header.tsx`](old-quant-work/client/src/components/header/Header.tsx) — dashboard shell.
- [`redux/services/oandaPriceStream.ts`](old-quant-work/client/src/redux/services/oandaPriceStream.ts) —
  RTK Query API slice (still scaffolded from the toolkit's Pokémon example).

The longer-term ambitions for this UI — TradingView-style charts, backtest dashboards,
Monte-Carlo yield profiles, a risk calculator — are written up in
[`client/README.md`](old-quant-work/client/README.md).

```bash
cd old-quant-work/client && pnpm install && pnpm dev
```

### 3.3 Java — [`old-quant-work/Java/`](old-quant-work/Java)

A Spring Boot 3.1 / Java 17 Maven project
([`QuantApplication.java`](old-quant-work/Java/src/main/java/com/x/quant/QuantApplication.java))
that mostly serves as a host for structured study, each topic paired with its own notes file.

- **Fundamentals** — [`fundamentals/`](old-quant-work/Java/src/main/java/com/x/quant/fundamentals):
  data types, keywords, generics, exception handling, JVM
  [memory management](old-quant-work/Java/src/main/java/com/x/quant/fundamentals/memorymanagement/memorymanagement.md)
  (heap/method area/stack, GC), and
  [data structures](old-quant-work/Java/src/main/java/com/x/quant/fundamentals/datastructures/datastructures.md).
- **Multithreading, concurrency and performance** —
  [`udemy/multithreading_concurrency_performance/`](old-quant-work/Java/src/main/java/com/x/quant/udemy/multithreading_concurrency_performance):
  concurrency vs parallelism, thread creation and inheritance,
  [`MultiExecutor`](old-quant-work/Java/src/main/java/com/x/quant/udemy/multithreading_concurrency_performance/thread_fundamentals/MultiExecutor.java),
  thread termination and interrupts, and a
  [vault-cracking case study](old-quant-work/Java/src/main/java/com/x/quant/udemy/multithreading_concurrency_performance/thread_fundamentals/case_study/Vault.java)
  racing ascending/descending hacker threads against a police thread.
- **RabbitMQ / JMS** — [`udemy/rabbitmq/`](old-quant-work/Java/src/main/java/com/x/quant/udemy/rabbitmq):
  working publishers and consumers for every exchange type — [default](old-quant-work/Java/src/main/java/com/x/quant/udemy/rabbitmq/default_exchange/default_exchange.md),
  [direct](old-quant-work/Java/src/main/java/com/x/quant/udemy/rabbitmq/direct_exchange/DirectExchange.md),
  [fanout](old-quant-work/Java/src/main/java/com/x/quant/udemy/rabbitmq/fanout_exchange/fanout_exchange.md),
  [topic](old-quant-work/Java/src/main/java/com/x/quant/udemy/rabbitmq/topic_exchange/topic_exchange.md),
  [headers](old-quant-work/Java/src/main/java/com/x/quant/udemy/rabbitmq/headers_exchange/headers_exchange.md) —
  plus a Spring AMQP integration
  ([`rabbit_with_springboot/`](old-quant-work/Java/src/main/java/com/x/quant/udemy/rabbitmq/rabbit_with_springboot))
  and an [export-to-Excel realtime example](old-quant-work/Java/src/main/java/com/x/quant/udemy/rabbitmq/realtime_example/RealtimeExample.java).

```bash
cd old-quant-work/Java && ./mvnw spring-boot:run
```

### 3.4 C# trading engine — [`old-quant-work/c_c++_c#/`](old-quant-work/c_c++_c%23)

A .NET 6 exchange-server skeleton following the standard hosted-service pattern.
- [`TradingEngine/`](old-quant-work/c_c++_c%23/TradingEngine) — `TradingEngineServer` as a
  `BackgroundService`, a host builder wiring up dependency injection, a static service
  provider, and configuration bound from
  [`appsettings.json`](old-quant-work/c_c++_c%23/TradingEngine/appsettings.json) (server port,
  logger type).
- [`LoggingCS/`](old-quant-work/c_c++_c%23/LoggingCS) — a separate logging library project:
  `ILogger` / `ITextLogger` interfaces, an `AbstractLogger` base, log levels and logger types.

### 3.5 Finance theory — [`old-quant-work/finance_theory/`](old-quant-work/finance_theory)

Written notes, largely from the *Quantitative Finance & Algorithmic Trading in Python* course:
- [Time value of money](old-quant-work/finance_theory/stock_market_basics/time_value_of_money/notes.md),
  with discrete and continuous present/future value functions in
  [`time_value_money.py`](old-quant-work/finance_theory/stock_market_basics/time_value_of_money/time_value_money.py).
- [Stocks and shares](old-quant-work/finance_theory/stock_market_basics/stocks_and_shares/notes.md),
  [commodities](old-quant-work/finance_theory/stock_market_basics/commodities/notes.md),
  [currencies and the forex market](old-quant-work/finance_theory/stock_market_basics/currencies_forex_market/notes.md),
  and [bonds](old-quant-work/finance_theory/bonds_theory/what_are_bonds/notes.md).
- The [README](old-quant-work/finance_theory/README.md) argues the case for dynamic over static
  financial models, using 2008 and the recent rate cycle as the motivating examples.

Longer-range project ideas — a stocks financial dashboard, a crypto backtesting bot, an
arbitrage bot — are sketched in [`WeeklyGoals.md`](old-quant-work/WeeklyGoals.md).

---

## Configuration

Nothing here ships credentials. The code expects the following, mostly via `.env` files
loaded with `python-dotenv` (Python) or `import.meta.env` (Vite):

| Variable | Used by |
| --- | --- |
| `OANDA_API_KEY`, `OANDA_ACCOUNT_ID`, `OANDA_URL`, `OANDA_STREAM_URL` | `old-quant-work/server` OANDA providers |
| `COINBASE_API_KEY`, `COINBASE_API_SECRET` | Coinbase spot-price client |
| `COINBASE_API_KEY_TRADING_040224`, `COINBASE_API_SECRET_TRADING_040224` | Coinbase candle download script |
| `VITE_OANDA_API_KEY`, `VITE_OANDA_FXPRACTISE_ACCOUNT_ID`, `VITE_OANDA_FXPRACTISE_STREAM_URL` | React streaming panel |

Python dependencies are informal: see
[`old-quant-work/server/requirements.txt`](old-quant-work/server/requirements.txt) for the
server (beautifulsoup4, numpy, pandas, coinbase, python-dotenv). The pricing and bot code also
needs `scipy`, `matplotlib`, `plotly`, `requests` and `python-dateutil`.

---

## Status & caveats

- **Exploratory throughout.** Strategy results were never validated against live execution,
  and no piece of this is investment advice or production-ready trading code.
- **`oanda_trading_bot/download_candle_data/api.py` will not import as-is** — it depends on a
  `constants.secrets` module that is deliberately not committed. The `old-quant-work/server`
  copies were migrated to `.env` instead.
- **Backtest data is not committed.** `oanda_trading_bot/main.py` expects pickled candle files
  (e.g. `candle_instrument_data/EUR_USD_H4.pkl`) that you need to generate with
  `collect_data.py` first.
- **Unfinished by design.** The C# `TextLogger` methods throw `NotImplementedException`, the
  inducement strategy's TODO list is still open, and the RTK Query slice retains its template
  naming.
- **A virtualenv is checked in** under `oanda_trading_bot/venv/`, which is why the repository
  is much larger than the source it contains. Create your own environment rather than using it.
- **Duplication is intentional.** `oanda_trading_bot/` and
  `old-quant-work/server/oanda_trading_bot/` are two points in the same lineage, kept separate
  so the earlier state survives the subtree merge.
