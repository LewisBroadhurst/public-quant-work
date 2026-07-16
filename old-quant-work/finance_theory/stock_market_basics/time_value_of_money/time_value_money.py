from math import exp

# Discrete Value
def future_discrete_value(x: float, r: float, n: int):
    return x * (1 + r) ** n

def present_discrete_value(x: float, r: float, n: int):
    return x * (1 + r) ** -n


# Continuous Value
def future_continuous_value(x: float, r: float, t: int):
    return x * exp(r * t)

def present_continuous_value(x: float, r: float, t: int):
    return x * exp(-r * t)


# What happens under the hood here?
if __name__ == "__main__":
    
    # Value of investment in USD
    x = 1000

    # Interest rate
    r = 0.05

    # Number of years
    n = 10

    print(f"Future discrete value of x: {future_discrete_value(x, r, n)}")
    print(f"Present discrete value of x: {present_discrete_value(x, r, n)}")
    print(f"Future continuous value of x: {future_continuous_value(x, r, n)}")
    print(f"Present continuous value of x: {present_continuous_value(x, r, n)}")