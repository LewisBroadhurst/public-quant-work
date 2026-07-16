import Plot from 'react-plotly.js';

export default function CandlestickPlot() {

    return <section className='p-5 bg-white self-start'>
        <Plot
            data={[
                {
                    type: 'candlestick',
                    x: [1, 2, 3],
                    y: [2, 3]
                },
            ]}
            layout={{ width: 320, height: 240, title: 'A Fancy Plot', margin: { l: 20, r: 20, t: 40, b: 20 } }}
        />
    </section>
}