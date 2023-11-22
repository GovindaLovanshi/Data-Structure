price = [5,7,8,6,8,4]

max_profit = 0

buy_price = float("inf")

for i in range(0,len(price)):
    if buy_price < price[i]:
        profit = price[i] - buy_price
        max_profit = max(max_profit,profit)
    else:
        buy_price = price[i]

print("max profit :",max_profit)