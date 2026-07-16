# Just a fun quant finance playground
Covers the following:
- Quantitative Finance & Algorithmic Trading in Python [Udemy Course]

## Quantitative Finance & Algorithmic Trading in Python
### Financial Models
Scenario: Analysing price of Apple stock since 2010.
Analysing historical data. Learn relationships between quantative factors such as standard deviations 
and other things such as news events.
Problem with this is that it assumes future conditions to be the same as the past. For example recent 
years have given cheap debt with newar 0% interest rates. In the last 2 years, interest rates have 
comparatively skyrocketed. Here it would be more sensible to look at pricing in a high interest era.
2008 is a great example of how models can fail.
This is called Static Models & are clearly flawed.

We should use Dynamic Models instead.
Take non-linear relationships between the features (financial parameters) into account.
Here, we define how the parameters evolve over the course of time and use the models accordingly.

deltaF(t, x, y, z...) / deltaT =/= 0


