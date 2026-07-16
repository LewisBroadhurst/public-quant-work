import { useEffect, useState } from "react"

// type PolygonPreviousClose = {
//     adjusted: true,
//     queryCount: number,
//     requestId: string,
//     results: [],
//     resultsCount: number,
//     status: string,
//     ticker: string,
// }

// AAPL, TSLA, NVDA, AMZN, GOOG, FB, MSFT, PYPL, NFLX, ADBE, CRM, PFE

const API_KEY = import.meta.env.VITE_POLYGON_API_KEY;

export default function StatisticPanel() {

    const [ticker, setTicker] = useState<string>();
    const [statistics, setStatistics] = useState();

    function handleInputChange(e: React.ChangeEvent<HTMLInputElement>) {
        setTicker(e.target.value);
        console.log(ticker)
    }

    function handleStockSearch() {
        fetch(`https://api.polygon.io/v2/aggs/ticker/${ticker}/prev?adjusted=true&apiKey=${API_KEY}`)
            .then(res => res.json())
            .then(data => setStatistics(data))
            .catch(err => console.error(err))
    }

    console.log(statistics)

    return <article className="flex flex-col gap-10 p-4 bg-slate-100">
        <h1 className="text-2xl underline">Statistics</h1>

        <section className="flex flex-col gap-1">
            <span>Current Ticker: {ticker ?? "Enter ticker below"}</span>
            <div className="flex flex-row gap-4">
                <input className="rounded-sm" type="text" placeholder="e.g TSLA" onChange={handleInputChange} />
                <button
                    className="bg-slate-300 px-4 py-2 rounded-sm"
                    onClick={handleStockSearch}
                >
                    Search
                </button>
            </div>
        </section>

        <section>
            {Boolean(statistics) && <pre>{JSON.stringify(statistics, null, 2)}</pre>}
        </section>
    </article>
}