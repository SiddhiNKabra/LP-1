class RingElection:
    def __init__(self, totalprocess):
        self.total = totalprocess
        self.state = [True for x in range(self.total)]
    def up(self, pos):
        self.state[pos - 1] = True
    def down(self, pos):
        self.state[pos - 1] = False
    def Election(self, pos):
        if(pos > self.total):
            return
        if(self.state[pos - 1] == False):
            print(f"p{pos} cannot start election as it is inactive")
            return
        print(f"p{pos} initiated the election process")
        start = pos - 1
        ptr = (start + 1) % self.total
        last_true = start
        while(ptr != start):
            if(self.state[ptr] == True):
                print(f"p{last_true + 1} sends the election mewssage to p{ptr+1}")
                last_true = ptr
            ptr = (ptr + 1) % self.total
        print(f"p{last_true + 1} sends the election mewssage to p{ptr+1}")
        maxi = max(x for x, val in enumerate(self.state) if val == True)
        print(f"Election is won by p{maxi + 1}")
algo = RingElection(7)
algo.down(7)
algo.Election(4)