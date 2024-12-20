count = int(input())

for _ in range(count):

    password = int(input())

    for number in range(2, 1_000_001):
        if password % number == 0:
            print("NO")
            break
        if number == 1_000_000:
            print("YES")