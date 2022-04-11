import numpy as np
import matplotlib.pyplot as plt
from scipy.stats import norm

data = np.loadtxt('Profit.txt', delimiter=',')

x1,x2,x3,x4 = data[:,0], data[:,1], data[:,2], data[:,3]

fig, axes = plt.subplots(1, 4, figsize=(16,6), sharex=False, sharey=True)
Xs = [x1,x2,x3,x4]
player = ['Dealer','Player 1', 'Player 2', 'Player 3']
colors = ['b','r','g','y']
mins = [-150,-75,-75,-75]
maxs = [150,75,75,75]

for i, ax in enumerate(axes.flatten()):
    mu, std = norm.fit(Xs[i])
    x = np.linspace(mins[i], maxs[i], 100)
    p = norm.pdf(x, mu, std)
    ax.plot(x, p, color=colors[i], linewidth=2)
    ax.set_title(player[i] + ': mu=%.2f, std = %.2f' %(mu,std))
    
plt.suptitle('Normal Distribution of Ngau Players Profit per 50 rounds')
plt.show()