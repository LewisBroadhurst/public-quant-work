import { useState } from "react";
import CandlestickPlot from "../charts/CandlestickPlot";

export default function StreamingPanel() {

    const url = import.meta.env.VITE_OANDA_FXPRACTISE_STREAM_URL;
    const accountId = import.meta.env.VITE_OANDA_FXPRACTISE_ACCOUNT_ID;

    const [eurusdPrice, setEurusdPrice] = useState();

    function handleStreamClick() {
        // Have funtionality to understand if stream is already running

        const x = fetch(`${url}/accounts/${accountId}/pricing/stream?instruments=EUR_USD`, {
            method: "GET",
            headers: new Headers({
                "Authorization": `Bearer ${import.meta.env.VITE_OANDA_API_KEY}`,
                "Content-Type": "application/json"
            })
        })
            .then(res => res.body)
            .then(body => {
                // @ts-ignore
                const reader = body.getReader();
                console.log(reader)
                return new ReadableStream({
                    start(controller) {
                        function push() {
                            // "done" is a Boolean and value a "Uint8Array"
                            reader.read().then(({ done, value }) => {
                                // If there is no more data to read
                                if (done) {
                                    console.log("done", done);
                                    controller.close();
                                    return;
                                }
                                // Get the data and send it to the browser via the controller
                                controller.enqueue(value);

                                // Check chunks by logging to the console
                                // console.log(done, value);

                                const x = new TextDecoder().decode(value.buffer);
                                try {
                                    const jsonObject = JSON.parse(x);
                                    console.log(jsonObject);
                                    if (jsonObject.type === "PRICE") {
                                        setEurusdPrice(jsonObject);
                                    }
                                } catch (error) {
                                    console.error('Error parsing JSON:', error);
                                }

                                push();
                            });
                        }

                        push();
                    }


                })
            })
            .then((stream) =>
                // Respond with our stream
                new Response(stream, { headers: { "Content-Type": "application/octet-stream" } }).text(),
            )
            .then((result) => {
                // Do things with result
                console.log(result, "crab cheese");
            })

        console.log(x);
    }

    return <section className="p-4 bg-slate-50 flex flex-col gap-4" >
        <h1 className="text-2xl underline">Streaming Panel</h1>

        <section className="flex flex-row">
            <CandlestickPlot />

            {Boolean(eurusdPrice) && <pre>{JSON.stringify(eurusdPrice, null, 2)}</pre>}
        </section>

        <section className="flex flex-row gap-2 items-center">
            <button
                className="bg-blue-500 text-white px-4 py-2 rounded-sm self-start"
                onClick={handleStreamClick}
            >
                Start EURUSD Stream
            </button>

            {/* @ts-ignore */}
            <span>{eurusdPrice?.bids[0] ? 'ON' : 'OFF'}</span>
        </section>
    </section >
}