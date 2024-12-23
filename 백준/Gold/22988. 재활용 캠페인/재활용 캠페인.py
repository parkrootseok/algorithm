N, X = map(int, input().split())
numbers = list(sorted(map(int, input().split())))

count = 0
remain = 0
left = 0
right = N - 1

while left <= right:

    if numbers[left] == X:
        count += 1
        right -= 1
        continue

    if left == right:
        remain += 1
        break
        
    if (X / 2) <= numbers[left] + numbers[right]:
        count += 1
        left += 1
        right -= 1
    else:
        left += 1
        remain += 1


print(count + (remain // 3))