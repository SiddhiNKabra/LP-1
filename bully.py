class BullyElection:
    def __init__(self, totalprocess):
        self.total = totalprocess
        self.state = [True for x in range(self.total)]
    def up(self, pos):
        self.state[pos - 1] = True
    def down(self, pos):
        self.state[pos - 1] = False
    def election(self, pos):
        if(pos > self.total):
            return
        if(self.state[pos - 1] == False):
            print(f"p{pos} cannot conduct election as it is inactive")
            return
        print(f"p{pos} initiated elction process")
        for i in range(pos, self.total):
            print(f"election message sent from p{pos} to p{i + 1}")
        for i in range(pos, self.total):
            if(self.state[i] == True):
                print(f"p{i+1} responds OK to p{pos}")
        for i in range(pos, self.total):
            if(self.state[i] == True):
                self.election(i+1)
                return
        maxi = max(x for x, val in enumerate(self.state) if val == True)
        print(f"Election won by p{maxi + 1}")
algo = BullyElection(9)
algo.down(1)
algo.election(9) 