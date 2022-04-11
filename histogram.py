import numpy as np
import matplotlib.pyplot as plt

data = np.loadtxt('Profit.txt', delimiter=',')

x1,x2,x3,x4 = data[:,0], data[:,1], data[:,2], data[:,3]

fig, axes = plt.subplots(1, 4, figsize=(16,6), sharex=False, sharey=True)
Xs = [x1,x2,x3,x4]
player = ['Dealer','Player 1', 'Player 2', 'Player 3']
colors = ['b','r','g','y']

for i, ax in enumerate(axes.flatten()):
    ax.hist(Xs[i], alpha=0.5, bins=380, density=True, stacked=True, color=colors[i], label=player[i])
    ax.set_title(player[i])


plt.suptitle('Ngau Players Profit per 50 rounds')
plt.tight_layout()
plt.show()
