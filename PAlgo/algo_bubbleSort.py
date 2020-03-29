def bubbleSort(arr):
    """
    冒泡排序
    :param list:
    :return:
    """
    for i in range(1, len(arr)):
        for j in range(0, len(arr) - i):
            if arr[j] > arr[j + 1]:
                arr[j], arr[j + 1] = arr[j + 1], arr[j]
    return arr


l = [1, 3, 5, 7, 7, 3, 9, 5, 3]
print(bubbleSort(l))
