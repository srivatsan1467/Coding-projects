import numpy as np
import pandas as pd
import matplotlib.pyplot as plt

# Parameters
np.random.seed(42)
trading_days = 500  # Two years of trading days
base_prices = [100, 120, 150]  # Starting prices for Stock A, B, and C
volatility = [0.02, 0.015, 0.03]  # Daily standard deviation for returns
mean_return = [0.0005, 0.0003, 0.0008]  # Average daily returns

# Generate stock price data using a random walk
def generate_stock_prices(base_price, mean_return, volatility, days):
    daily_returns = np.random.normal(mean_return, volatility, days)
    price_series = base_price * np.exp(np.cumsum(daily_returns))
    return price_series

# Generate data for each stock
stock_a_prices = generate_stock_prices(base_prices[0], mean_return[0], volatility[0], trading_days)
stock_b_prices = generate_stock_prices(base_prices[1], mean_return[1], volatility[1], trading_days)
stock_c_prices = generate_stock_prices(base_prices[2], mean_return[2], volatility[2], trading_days)

# Generate trading volumes using normal distribution
volume_a = np.random.normal(1_000, 200, trading_days).astype(int)
volume_b = np.random.normal(1_200, 250, trading_days).astype(int)
volume_c = np.random.normal(900, 150, trading_days).astype(int)

# Combine data into a DataFrame
dates = pd.date_range(start="2022-01-01", periods=trading_days, freq='B')  # Business days
stock_data = pd.DataFrame({
    'Date': dates,
    'Stock_A_Price': stock_a_prices,
    'Stock_B_Price': stock_b_prices,
    'Stock_C_Price': stock_c_prices,
    'Volume_A': volume_a,
    'Volume_B': volume_b,
    'Volume_C': volume_c
})

# Calculate daily returns
stock_data['Return_A'] = stock_data['Stock_A_Price'].pct_change()
stock_data['Return_B'] = stock_data['Stock_B_Price'].pct_change()
stock_data['Return_C'] = stock_data['Stock_C_Price'].pct_change()

# Portfolio Analysis
weights = np.array([0.4, 0.3, 0.3])  # Portfolio weights for A, B, C
daily_returns = stock_data[['Return_A', 'Return_B', 'Return_C']].dropna()
portfolio_daily_return = daily_returns.dot(weights)

# Portfolio variance
portfolio_variance = portfolio_daily_return.var()

# Sharpe Ratio (Assuming risk-free rate = 0)
sharpe_ratio = portfolio_daily_return.mean() / portfolio_daily_return.std()

# Plot the simulated stock prices
plt.figure(figsize=(12, 6))
plt.plot(stock_data['Date'], stock_data['Stock_A_Price'], label='Stock A')
plt.plot(stock_data['Date'], stock_data['Stock_B_Price'], label='Stock B')
plt.plot(stock_data['Date'], stock_data['Stock_C_Price'], label='Stock C')
plt.title("Simulated Stock Prices Over Time")
plt.xlabel("Date")
plt.ylabel("Price ($)")
plt.legend()
plt.grid(True)
plt.show()

print(f"Portfolio Variance: {portfolio_variance:.6f}")
print(f"Sharpe Ratio: {sharpe_ratio:.2f}")
 