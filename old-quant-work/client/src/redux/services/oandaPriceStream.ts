import { createApi, fetchBaseQuery } from '@reduxjs/toolkit/query/react'

export const oandaPriceStreamAPI = createApi({
    reducerPath: 'pokemonApi',
    baseQuery: fetchBaseQuery({ baseUrl: '/' }),
    endpoints: (builder) => ({
        getOandaPriceStream: builder.query<null, null>({
            query: () => ``,
        }),
    }),
})

export const { useGetOandaPriceStreamQuery } = oandaPriceStreamAPI