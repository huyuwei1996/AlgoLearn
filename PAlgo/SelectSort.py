"""常规选择排序"""


def SelectSort(arr: list):
    for i in range(len(arr) - 1):
        small_index = i
        small = arr[i]
        for j in range(i + 1, len(arr)):
            if small > arr[j]:
                small_index = j
                small = arr[j]
        if (small_index != i):
            arr[small_index] = arr[i]
            arr[i] = small
    return arr
